<template>
    <div>
        <div class="guidebar">
            <el-menu class="el-menu-demo" mode="horizontal" :router="true">
                <el-menu-item><img src='@/assets/logo.png' class="logo"></el-menu-item>
                <el-menu-item index="">首页</el-menu-item>
                <el-menu-item index="/CreditReport">信用报告</el-menu-item>
                <el-menu-item index="/PsychologyHome">心理测试</el-menu-item>
                <el-menu-item index="/HonestyVideo">金融教育</el-menu-item>
                <el-menu-item index="">好友人脉</el-menu-item>
                <el-menu-item index="">意见反馈</el-menu-item>
                <el-menu-item index="">
                    <img src='@/assets/avatar.png' class="avatar">
                </el-menu-item>
            </el-menu>
        </div>
        <div>

        </div>
    </div>
</template>
<script>
export default {
    
}
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
</style>