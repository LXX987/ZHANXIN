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
                        视频标题
                    </div>
                    <video class="video" controls :src="videoUrl"></video>
                </el-col>

                <el-col :span="6" class="side">
                    <div class="videotitle">
                        视频介绍
                    </div>
                    <div class="videolist">
                        下一集(获取视频列表得了)
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
import Footer from '@/components/Footer'
export default {
    name:'Video',
    components:{ 
        Footer
    },
    data(){
        return{
            videoUrl:'',
            introduction:'cc',
            videoId:'',
        }
    },
    mounted:function(){
        console.log(this.$route.query.videoId)
        this.videoUrl = this.$route.query.videoUrl
        this.videoId = this.$route.query.videoId
    },
    methods:{
        gotoQuestion(){
            // // 获取id对应的视频题目
            // this.$axios({
            // method:"get",
            // url: 'http://localhost:8888/admin/questions'+'/'+this.videoId,
            // // params:{
            // //     id = this.videoId
            // // }
            // }).then(res=>{
            //     console.log(res.data.data)
            //     this.list = res.data.data
            // })
             this.$router.push({path:'/VideoQuestion',query:{videoId:this.videoId}})
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
</style>