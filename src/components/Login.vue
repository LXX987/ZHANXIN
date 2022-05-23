<template>
  <div id="hello">
    <div id="body">
      <div>
        <h1 id="headline">登录</h1>
        <div class="editview">
          <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="0px" class="demo-ruleForm">
            <el-form-item prop="userid"> <li><el-input v-model="ruleForm.userid" placeholder="请输入邮箱" class="input_box"></el-input></li></el-form-item>
            <el-form-item prop="password"> <li><el-input placeholder="请输入密码" v-model="ruleForm.password" show-password class="input_box"></el-input></li></el-form-item>
            <Vcode :show="isShow" @success="success" @close="close" @fail="fail" />
            <li><el-button type="primary" round class="btn_login" @click="submit">登录</el-button></li>
            <br>
            <el-link type="primary" @click="register()">没有账号？点击注册</el-link>
            <el-link type="primary" @click="forgetPsw()" class="link-txt">忘记密码？点击找回</el-link>
          </el-form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Vcode from "vue-puzzle-vcode";
export default {
  name: 'Login',
  components: {
    Vcode
  },
  data () {
    var checkId = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('用户邮箱不能为空!'));
      }
      };
    var checkPass = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('密码不能为空!'));
        }
      };
    return {
      radio: '0',
      isShow: false, // 验证码模态框是否出现
      failTime:0,
      ruleForm: {
        userid: '',
        password:'',
      },
      rules: {
          userid: [
          {validator: checkId,trigger: "blur"}
          ],
          password: [
          {validator: checkPass,trigger: "blur"}
          ]
        }
      }  
  },
  methods:{ 
    submit() {
      this.isShow = true;
    },
    success(msg) { // 用户通过了验证
      this.isShow = false; // 通过验证后，需要手动隐藏模态框
      this.login();
    },
    close() { // 用户点击遮罩层，应该关闭模态框
      this.isShow = false;
    },
    fail() {
      this.failTime=this.failTime+1;
      if(this.failTime>=5){
        this.$axios({
          method:"post",
          url: 'http://localhost:8899/user/loginException',
          params:{
              userEmail:this.ruleForm.userid
          }
        }).then(res=>{
            console.log(res.data.msg);
            if(res.data.msg=="登录异常"){
                console.log(res);
                this.$message({
                  showClose: true,
                  message: '登录异常!'
                });
            }
        },err=>{
            console.log(err);
        })
      }
    },
    login(){
      this.$axios({
        method:"post",
        url: 'http://localhost:8899/user/login',
        params:{
            userEmail:this.ruleForm.userid,
            userPassword:this.ruleForm.password,
            // userType: 1,
            userType:0
        }
      }).then(res=>{
          console.log(res.data.msg);
          if(res.data.msg=="登录成功"){
              console.log(res);
              window.sessionStorage.setItem("token",res.data.token);
              this.$router.push('/HomePage')
          }
          else{
            this.$message({
              showClose: true,
              message: '登录失败!'
              });
          }
      },err=>{
          console.log(err);
      })
    },
    register() {
      this.$router.push({path: '/Register'});
    },
    forgetPsw() {
      this.$router.push({path: '/ForgetPsw'});
    }
  }
}
</script>

<style scoped>
#hello{
  min-height:1000px;
  background-image: url(../../src/assets/background.png);
  background-size:100% 100%;
  background-attachment:fixed;
  padding-top: 12%;
}
#body {
  background-color:#ffffff60;
  width: 30%;
  margin-left: 35%;
  margin-right: 35%;
  border-radius: 20px;
  box-shadow: 0px 0px 20px #8899887d;
  padding: 40px;
}
#headline {
  margin-bottom: 30px;
  font-size: 24px;
}
.editview{
    margin-top: 10px;
    margin-bottom: 0px;
    text-align:center;
}
>>>.el-input__inner{
    border-radius:30px;
    border-width:0px;
    height: 60px;
    background-color: #EFEEEE;
    font-size: 16px;
}
li{
     list-style-type:none;
     padding-top:10px;
}
.btn_login{
    height: 60px;
    width: 80%;
    background-color: #32bdfead;
    border-width:0px;
    border-radius:30px;
    font-size: 16px;
}
.input_box {
  width: 80%;
}
h1, h2 {
  font-weight: normal;
  font-size: 20px;
  margin: 8px;
}
>>>.el-form-item__error{
  position:inherit;
}
.link-txt {
  margin-left: 15%;
}
</style>