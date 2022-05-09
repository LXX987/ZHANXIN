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
              <h1>我的观看历史</h1>
              <div class="tip"><p>&ensp;&ensp;&ensp;&ensp;我们的信用评级分为六个等级，分别是Excellent(极优秀)、VeryGood(很优秀)、Good(良好)、Fair(一般)、Poor(较差)、VeryBad(很差)。不同的信用等级可以进行不同额度的贷款。</p></div>
              <div class="box" id="">
                    <ul class="video">
                        <li v-for="item in watchList" :key="item.value" class="videoli">
                            <div class="illu" :style="backgroundDiv">
                            <h4 class="title">视频标题：{{item.videoName}}</h4>
                            <h4>视频简介：</h4>
                            <h4>视频时长：</h4>
                            <p class="score">得分{{item.question_score}}</p>
                            <h4>已观看</h4>
                            <div class="detail"><el-button type="text" v-on:click="onclick(item.videoId)">查看视频内容</el-button></div>
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
export default ({
    data(){
      return{
        backgroundDiv: {backgroundImage: 'url(' + require('@/assets/videoscore.png') + ')' },
        num:'1',
        index: 1,
        videoUrl:'',
        list:[],
        watchList:[],
        video_list:[],
      }
    },
    methods:{
      getVideoScore(){
        this.$axios({
            method:"post",
            url: 'http://localhost:8888/videoScore',
            headers: { token:window.sessionStorage.getItem("token")}
            }).then(res=>{
                console.log(res)
                this.watchList = res.data.videoScore
                for(var i = 0;i<this.watchList.length;i++){
                  this.video_list[i] = this.watchList[i].video_id
                }
                console.log(this.video_list)


            })
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
    height: 850px;
    width: 100%;
    background-size:100% 100%
}
.content{
    height: 750px;
    background-color: #fff;
    margin-left: 20%;
    margin-right: 20%;
    padding-bottom: 160px;
    padding-top: 50px;
    border-radius: 25px;
    box-shadow:0 0 0 15px  rgba(255, 255, 255, 0.286)
}
.illu{
    height:350px;
    text-align: left;
    background-repeat:no-repeat;
    background-size:100%;
}
.box {
    width:80%;
    margin-top:15px;
    margin-left: 18%;
}
.video{
    display:flex;
    width:1200px;
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
    margin-left: 40px;
    margin-top: 50px;
    height: 160px;
    width: 240px;
}
.title{
    margin-top: 10px;
    font-weight: bold;
    font-size: 20px;
    margin-left: 35px;
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
</style>