<template>
<div class="rate">
    <div class="guidebar">
        <el-menu default-active="/HonestyVideo" class="el-menu-demo" mode="horizontal" :router="true">
            <el-menu-item><img src='@/assets/logo.png' class="logo"></el-menu-item>
            <el-menu-item index="/HomePage">首页</el-menu-item>
            <el-menu-item index="/CreditReport">信用报告</el-menu-item>
            <el-menu-item index="/PsychologyGuidebar">心理测试</el-menu-item>
            <el-menu-item index="/HonestyVideo">金融教育</el-menu-item>
            <el-menu-item index="">好友人脉</el-menu-item>
            <el-menu-item index="">意见反馈</el-menu-item>
            <el-menu-item index="/PersonalCenter">
                <img src='@/assets/avatar.png' class="avatar">
            </el-menu-item>
        </el-menu>
    </div>
    <div class="body">
        <div>
            <el-row>
                <el-col :span="12" class="content">
                    <div class="videotitle">
                        {{title}}
                    </div>
                    <video class="video" controls :src="videoUrl"></video>
                </el-col>

                <el-col :span="6" class="side">
                    <div class="videotitle">
                        推荐视频
                    </div>
                    <div class="videolist">
                        <div class="box">
                            <ul>
                                 <li v-for="item in list" :key="item.value" class="videoli">
                                    <el-row>
                                        <el-col :span="10">
                                            <img class="cover" v-bind:src="item.videoCover" alt="" @click="onclick(item.videoId)">
                                        </el-col>
                                        <el-col :span="10">
                                            <p class="subtitle" @click="onclick(item.videoId)">{{item.videoName}}</p>
                                            <p class="subtitle">{{item.videoIntro}}</p>
                                        </el-col>
                                    </el-row>
                                 </li>
                            </ul>
                        </div>
                    </div>
                    <div>
                        <el-button @click="gotoQuestion()">开始答题</el-button>
                    </div>
                </el-col>
            </el-row>
            <div class="videoshort">
                <div class="videotitle">
                    视频概述
                </div>
                <div class="introduction">
                    &ensp;&ensp;&ensp;&ensp;{{introduction}}
                </div>
            </div>
             <div>
                <Footer></Footer>
            </div>
        </div>
    </div>
</div>   
</template>
 
<script>
import qs from 'qs'
import Footer from '@/components/Footer'
export default {
    name:'Video',
    components:{ 
        Footer
    },
    data(){
        return{
            able:true,
            title:'',
            videoUrl:'',
            introduction:'',
            videoId:'',
            list:[],
        }
    },
    mounted:function(){
        //console.log(this.$route.query.videoId)
        this.videoUrl = this.$route.query.videoUrl
        this.videoId = this.$route.query.videoId
        this.getVideo()
        this.getThisVideo()
        this.getwatchvideo()
    },
    methods:{
        gotoQuestion(){
            if(this.able==true){
                this.$router.push({path:'/VideoQuestion',query:{videoId:this.videoId}})}
             else{
                this.$message.error('该视频已经完成作答，不可以再次作答哦');
             }
        },
        getVideo(){
            this.$axios({
            method:"get",
            url: 'http://localhost:8899/common/videos/fundamental'
            }).then(res=>{
                //console.log(res.data.data)
                //var num = [];
                for(var i=0;i<3;i++)
                {
                    //num.push(Math.floor(Math.random()*10));
                    this.list.push(res.data.data[Math.floor(Math.random()*10)])
                }
                    
                console.log(this.list)
            })
        },
        getwatchvideo(){
            this.$axios({
            method:"post",
            url: 'http://localhost:8899/videoScore',
            headers: { token:window.sessionStorage.getItem("token")}
            }).then(res=>{
                console.log(res.data.videoScore)
                for(var i=0;i<res.data.videoScore.length;i++){
                    if(res.data.videoScore[i].video_id==this.videoId){
                        this.able=false
                        console.log(this.able)
                    }
                }
            })
        },
        getThisVideo(){
            this.$axios({
                  method:"post",
                  url:'http://localhost:8899/common/videos',
                  params:{
                    id_list:this.videoId,
                  },
                   paramsSerializer: params => {
                    return qs.stringify(params, { indices: false })
                    },
                }).then(res=>{
                    this.introduction = res.data.data[0].video_intro
                    this.title =res.data.data[0].video_name
                    console.log(res)
                })
        },
        onclick(value){
            for(let i in this.list){
                if(this.list[i].videoId == value ){
                    this.videoUrl = this.list[i].videoUrl
                    this.videoId = value
                    console.log(this.videoId)
                }
            }
        let newUrl = this.$router.resolve({name:'Video', query:{videoUrl:this.videoUrl,videoId:this.videoId}});
            window.open(newUrl.href, "_blank");
        }
    }
}
</script>
    
<style scoped>
.illu{
    height:150px;
    text-align: left;
}
.title{
    color:#fff;
    font-size: 48px;
    font-weight:bold;
    margin-top: 0px;
    margin-left: 220px;
    line-height:150px;
}
.video{
    width: 900px;
    height: 600px;
}
.cover{
    margin-left: 20px;
    height: 120px;
    width: 220px;
}
.guidebar {
    height:100px;
    padding-left: 18%;
    padding-right: 250px;
    float:center;
    display:flex;
    align-items:center;/*所有子元素都垂直居中了*/
  }
   .el-menu-item {
    padding:0px 0;
    color: #000;
    list-style-type:none;
    text-align:center;
    padding-left:30px;
    padding-right:30px;
    font-size:20px;
    height: 100px;
    display:flex;
    align-items:center;/*所有子元素都垂直居中了*/
  }
  .el-menu--horizontal>.el-menu-item.is-active {
    border-bottom: 0.010417rem solid #409EFF;
    color: #fff;
    background-color: #409EFF;
}
  .el-menu-item:hover{
    border-bottom-style:solid;
    border-color: #6969FC;
  }
.logo{
  height: 100px
}
.el-menu{
  height: 100px;
}
.el-menu.el-menu--horizontal {
    border-bottom: solid 0.005208rem #fff8f8;
}
.avatar{
  height: 50%;
}
.body{
    background-color: #F7F9FB;
}
.content{
    background-color: #fff;
    margin-left: 15%;
    padding-left: 20px;
    padding-right: 20px;
}
.side{
    background-color: #fff;
    margin-left: 50px;
    padding-left: 20px;
}
.videotitle{
    font-size: 24px;
    font-weight: bold;
    text-align: left;
    margin-top: 20px;
    margin-bottom: 20px;
}
.videoshort{
    margin-left: 15%;
    margin-right: 7%;
    padding-left: 20px;
    background-color: #fff;
    padding-top: 10px;
    margin-top: 30px;
    padding-bottom: 30px;
    margin-bottom: 50px;
}
.introduction{
    text-align: left;
    font-size: 18px;
    margin-top: 20px;
    margin-bottom: 20px;
}
.el-button{
    margin-top: 20px;
    margin-bottom: 20px;
}
.videolist{
    min-height: 500px;
}
.videoli{
    padding-bottom: 30px;
}
.subtitle{
  margin-left: 55px;
  font-size: 20px;
  padding-bottom:20px;
}
</style>