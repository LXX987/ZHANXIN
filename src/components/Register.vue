<template>
  <div id="hello">
    <div id="body">
      <div>
        <h1 id="headline">注册</h1>
        <div class="editview">
          <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="0px" class="demo-ruleForm">
            <el-form-item prop="userid"> <li><el-input v-model="ruleForm.userid" placeholder="请输入邮箱" class="input_box"></el-input></li></el-form-item>
            <el-form-item prop="userid"> <li><el-input v-model="ruleForm.userid" placeholder="请输入验证码" class="input_code"></el-input><el-button type="primary" round plain class="btn_get">获取验证码</el-button></li></el-form-item>
            <el-form-item prop="password"> <li><el-input placeholder="请输入密码" v-model="ruleForm.password" show-password class="input_box"></el-input></li></el-form-item>
            <el-form-item prop="password"> <li><el-input placeholder="请确认密码" v-model="ruleForm.password" show-password class="input_box"></el-input></li></el-form-item>
            <li><el-button type="primary" round class="btn_login" @click="login">登录</el-button></li>
              <br>
              <el-radio v-model="radio" label="1" id="protocol"><u>用户隐私服务协议</u></el-radio>
          </el-form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Register',
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
}
</script>

<style scoped>
#hello{
  min-height:1000px;
  background-image: url(../../src/assets/background.png);
  background-size:100% 100%;
  background-attachment:fixed;
  padding-top: 8%;
}
#body {
  background-color:#ffffff60;
  width: 30%;
  margin-left: 35%;
  margin-right: 35%;
  border-radius: 20px;
  box-shadow: 0px 0px 20px #8888887d;
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
.input_code {
  width: 55%;
}
.btn_get {
  width: 25%;
  height: 60px;
  border-radius:30px;
  font-size: 16px;
}
h1, h2 {
  font-weight: normal;
  font-size: 20px;
  margin: 8px;
}
>>>.el-form-item__error{
  position:inherit;
}
</style>