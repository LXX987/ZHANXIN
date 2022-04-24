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
        <div class="body">
            <div class="content">
                <p class="vqt">视频测试题</p>
                <div class="question">
                    <div v-for="(item) in questions" :key="item.testID" class="text item">
                        <p >{{item.testID}}.&nbsp;{{item.testContent}}</p>
                        <el-radio-group v-model="item.item_score">
                        <ul>
                        <li><el-radio :label="1">{{item.option1}}</el-radio></li>
                        <li><el-radio :label="2">{{item.option2}}</el-radio></li>
                        <li><el-radio :label="3">{{item.option3}}</el-radio></li>
                        <li><el-radio :label="4">{{item.option4}}</el-radio></li>
                        </ul>
                        </el-radio-group>
                    </div>
                </div>
                <el-button @click="cancel()">取消</el-button>
                <el-button @click="submit()">提交</el-button>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    data(){
        return{
           questions: [
               {
                    testID:1,
                    testContent:'xxx',
                    item_score:2,
                    option1:'xxx',
                    option2:'xxx',
                    option3:'xxx',
                    option4:'xxx',
               }
           ],
        }
    },
    methods:{
        cancel()
        {
            this.$router.go(-1)
        },
        submit(){

        }
    }
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
.body{
    background-image: url(../assets/background.png);
    height: 850px;
    width: 100%;
    background-size:100% 100%
}
.content{
    height: 750px;
    background-color: #fff;
    margin-left: 38%;
    margin-right: 38%;
    padding-bottom: 160px;
    padding-top: 50px;
    border-radius: 25px;
    box-shadow:0 0 0 15px  rgba(255, 255, 255, 0.286)
}
.vqt{
    font-size: 28px;
    color:#32BDFE;
    font-weight: bold;
    text-align: center;
    padding-top: 50px;
}
.question{
    margin-top: 20px;
    text-align: left;
    margin-left: 15%;
    font-size: 20px;
    margin-bottom: 20px;
}
.el-radio{
    font-size: 28px;
}
</style>