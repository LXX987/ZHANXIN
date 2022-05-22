<template>
    <div>
        <div class="guidebar">
            <el-menu default-active="/HonestyVideo" class="el-menu-demo" mode="horizontal" :router="true">
                <el-menu-item><img src='@/assets/logo.png' class="logo"></el-menu-item>
                <el-menu-item index="">首页</el-menu-item>
                <el-menu-item index="/CreditReport">信用报告</el-menu-item>
                <el-menu-item index="/PsychologyGuidebar">心理测试</el-menu-item>
                <el-menu-item index="/HonestyVideo">金融教育</el-menu-item>
                <el-menu-item index="">好友人脉</el-menu-item>
                <el-menu-item index="">意见反馈</el-menu-item>
                <el-menu-item index="">
                    <img src='@/assets/avatar.png' class="avatar">
                </el-menu-item>
            </el-menu>
        </div>
        <div>
          <div class="body">
            <div class="content">
              <h1 class="name">我的观看历史</h1>
              <div class="tip">&ensp;&ensp;&ensp;&ensp;已经作答过测试题的视频会被计分，视频和得分会展示在下方列表当中。</div>
              <div class="box" id="">
                    <ul class="video">
                        <li v-for="item in watchList" :key="item.value" class="videoli">
                            <div class="illu" :style="backgroundDiv">
                            <h4 class="title">{{item.video_title}}</h4>
                            <img class="cover" v-bind:src="item.video_cover" alt="">
                            <h4 class="subtitle">{{item.video_time}}</h4>
                            <p class="subtitle">得分：{{item.question_score}}</p>
                            <div class="detail"><el-button type="text" v-on:click="onclick(item.video_id)">查看视频内容</el-button></div>
                            </div>
                        </li>
                    </ul>
              </div>
            </div>
        </div>
        </div>
    </div>
</template>
<script>
import qs from 'qs'
export default ({
    data(){
      return{
        backgroundDiv: {backgroundImage: 'url(' + require('@/assets/videoscore.png') + ')' },
        num:'1',
        index: 1,
        videoUrl:'',
        videoID:'',
        list:[],
        watchList:[],
        video_list:[],
        tempList:[],
      }
    },
    methods:{
      getVideoScore(){
        this.$axios({
            method:"post",
            url: 'http://localhost:8899/videoScore',
            headers: { token:window.sessionStorage.getItem("token")}
            }).then(res=>{
                //console.log(res)
                this.tempList = res.data.videoScore
                for(var i = 0;i<res.data.videoScore.length;i++){
                  this.video_list[i] = res.data.videoScore[i].video_id
                }
                console.log(this.video_list)
                this.$axios({
                  method:"post",
                  url:'http://localhost:8899/common/videos',
                  params:{
                    id_list:JSON.stringify(this.video_list),
                  },
                   paramsSerializer: params => {
                    return qs.stringify(params, { indices: false })
                    },
                }).then(res=>{
                    //console.log(res.data.data)
                    this.watchList = this.tempList
                    for(var i=0;i<this.watchList.length;i++){
                      this.watchList[i].video_title = res.data.data[i].video_name
                      this.watchList[i].video_time =  res.data.data[i].video_time
                      this.watchList[i].video_intro = res.data.data[i].video_intro
                      this.watchList[i].video_url = res.data.data[i].video_url
                      this.watchList[i].video_cover = res.data.data[i].video_cover
                    }
                    //this.watchList.push(res.data.data)
                    console.log(this.watchList)
                })
                // this.$axios.post('http://localhost:8899/common/videos',
                // {id_list:this.video_list}
                // ).then((res)=>{
                //   console.log(res)
                // })

            })
      },
      onclick(value){
        for(let i in this.watchList){
                if(this.watchList[i].video_id == value ){
                    this.videoUrl = this.watchList[i].video_url
                    this.videoId = value
                    console.log(this.videoId)
                }
            }
        let newUrl = this.$router.resolve({name:'Video', query:{videoUrl:this.videoUrl,videoId:this.videoId}});
            window.open(newUrl.href, "_blank");
      }
    },
    mounted(){
      this.getVideoScore()
    }
})
</script>
<style scoped>
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
    background-image: url(../assets/background.png);
    min-height: 850px;
    width: 100%;
    background-size:100% 100%
}
.content{
    min-height: 750px;
    background-color: #fff;
    margin-left: 20%;
    margin-right: 20%;
    padding-bottom: 160px;
    padding-top: 50px;
    border-radius: 25px;
    box-shadow:0 0 0 15px  rgba(255, 255, 255, 0.286)
}
.illu{
    height:280px;
    text-align: left;
    background-repeat:no-repeat;
    background-size:160%;
}
.box {
    width:80%;
    margin-top:15px;
    margin-left: 5%;
    
}
.video{
    display:flex;
    width:1000px;
    list-style-type: none;
    flex-flow: wrap;
}
.videoli{
    align-items:center;
    width:320px;
    margin-bottom:20px;
}
.detail{
    text-align: right;
    vertical-align: bottom;
    margin-right: 25px;
}
.cover{
    margin-left: 60px;
    height: 103px;
    width: 154px;
}
.title{
    padding-top: 35px;
    font-weight: bold;
    font-size: 20px;
    margin-left: 55px;
    padding-bottom: 5px;
}
.subtitle{
  margin-left: 55px;
}
.time{
    margin-left: 35px;
    margin-top: 10px;
    margin-bottom: 15px;
}
.el-button{
    font-size: 18px;
    color: #32BDFE;
}
.name{
  font-size: 28px;
  font-weight: bold;
  text-align: left;
  padding-left: 5%;
  padding-bottom: 20px;
}
.tip{
  font-size: 18px;
  text-align: left;
  padding-left: 5%;
}
</style>