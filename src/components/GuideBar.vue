<template>
<div>
<div class="guidebar">
  <el-menu :default-active="index" class="el-menu-demo" mode="horizontal" :router="true">
    <el-menu-item><img src='@/assets/logo.png' class="logo"></el-menu-item>
    <el-menu-item index="/HomePage" @click="saveIndex('/HomePage')">首页</el-menu-item>
    <el-menu-item index="/CreditReport" @click="saveIndex('/CreditReport')">信用报告</el-menu-item>
    <el-menu-item index="/PsychologyGuidebar" @click="saveIndex('/PsychologyGuidebar')">心理测试</el-menu-item>
    <el-menu-item index="/HonestyVideo" @click="saveIndex('/HonestyVideo')">金融教育</el-menu-item>
    <el-menu-item index="23" @click="saveIndex('/HonestyVideo')">好友人脉</el-menu-item>
    <el-menu-item index="23" @click="saveIndex('/HonestyVideo')">意见反馈</el-menu-item>
    <el-menu-item index="/PersonalCenter" @click="saveIndex('/PersonalCenter')">
      <img src='@/assets/avatar.png' class="avatar">
    </el-menu-item>
</el-menu>
</div>
<div class="body">
  <router-view name="table"></router-view>
  </div>
  <div>
    <Footer></Footer>
  </div>
</div>
</template>
<script>
import Footer from '@/components/Footer'
  export default {
    name:'GuideBar',
    components:{
    Footer
  },
    data() {
       return {
        index:'',
      };
    },
    created(){
      this.index = window.sessionStorage.getItem('index')
    },
    methods:{
      saveIndex(index){
          window.sessionStorage.setItem('index',index)
          this.index=index;
      },
      getIndex(){
        var index_temp = this.$route.path
        if(index_temp=="/HomePage"||index_temp=="/CreditReport"||index_temp=="/PsychologyGuidebar"||index_temp=="/HonestyVideo"||index_temp=="/HonestyVideo"||index_temp=="/PersonalCenter"){
          this.index = index_temp
        }
        else{
          switch(index_temp){
            case"/PsychologyGuidebar/PsychologyResult":this.index="/PsychologyGuidebar/";
            case"/PsychologyGuidebar/PsychologyQuestion":this.index="/PsychologyGuidebar/";
            case"/PsychologyGuidebar/History":this.index="/PsychologyGuidebar/";
            case"/ScoreDetail":this.index="/CreditReport";
            break;
          }
        }
      }
    },
    mounted(){
      this.getIndex()
    }
  }
</script>
<style lang="css" scoped>
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
</style>