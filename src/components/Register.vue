<template>
  <div id="hello">
    <div id="body">
      <div>
        <h1 id="headline">注册</h1>
        <div class="editview">
          <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="0px" class="demo-ruleForm">
            <el-form-item prop="useremail"> <li><el-input v-model="ruleForm.useremail" placeholder="请输入邮箱" class="input_box"></el-input></li></el-form-item>
            <el-form-item prop="code"> <li><el-input v-model="ruleForm.verification_code" placeholder="请输入验证码" class="input_code"></el-input>
            <el-button type="primary" round plain class="btn_get" @click="verify()" v-if="!btnBool">{{btnGetCode}}</el-button>
            <el-button type="primary" round plain class="btn_get" v-if="btnBool">{{auth_time}}后可重新发送</el-button>
            </li></el-form-item>
            <el-form-item prop="password"> <li><el-input placeholder="请输入密码" v-model="ruleForm.password" show-password class="input_box"></el-input></li></el-form-item>
            <el-form-item prop="password"> <li><el-input placeholder="请确认密码" v-model="ruleForm.passwordAgain" show-password class="input_box"></el-input></li></el-form-item>
            <li><el-button type="primary" round class="btn_login" @click="register">注册</el-button></li>
              <br>
              <el-radio v-model="radio" label="1" id="protocol"><u @click="link()">用户隐私服务协议</u></el-radio>
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
    var checkEmail = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('用户邮箱不能为空!'));
      }
      if (/^([a-zA-Z0-9]+[_|_|.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|_|.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/.test(value) == false) {
        return callback(new Error("请输入正确的邮箱地址!"));
      } else {
        //校验通过
        return callback();
      }
    };
    var checkPass = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('密码不能为空!'));
      }
    };
    var checkPassAgain = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('输入不能为空!'));
      }
    };
    return {
      radio: '0',
      vericode:'',
      btnGetCode:'获取验证码',
      btnBool:false,
      auth_time:'',
      ruleForm: {
        useremail: '',
        verification_code:'',
        password:'',
        passwordAgain:'',
      },
      rules: {
        useremail: [
          {validator: checkEmail,trigger: "blur"}
        ],
        password: [
          {validator: checkPass,trigger: "blur"}
        ],
        passwordAgain: [
          {validator: checkPassAgain,trigger: "blur"}
        ]
      }
    }  
  },
  methods:{
    link() {
      window.open("https://www.baidu.com",'_blank');
    },
    verify:function(){
      //验证码倒计时
      this.btnBool = true;
      this.auth_time = 6;
            var auth_timetimer =  setInterval(()=>{
                this.auth_time--;
                if(this.auth_time<=0){
                    this.btnBool = false;
                    clearInterval(auth_timetimer);
                }
            }, 1000);
      this.$axios({
              method:"post",
              url:'http://localhost:8888/mail/mail',
              params:{email:this.ruleForm.useremail},
          }).then(res=>{
              this.vericode = res.data;
        console.log(res.data);
        },err=>{
          console.log(err);
        })
      },
      register(){
          if(this.radio==0){
            this.$message({
              showClose: true,
              message: '请先勾选用户隐私协议!'
              });
          }else if(this.ruleForm.verification_code != this.vericode){
              this.$message({
              showClose: true,
              message: '验证码错误!'
              });
          }else if(this.ruleForm.passwordAgain!=this.ruleForm.password){
            this.$message({
              showClose: true,
              message: '两次输入密码不一致!'
              });
          }else {
              this.$axios({
              method:"post",
              url: 'http://localhost:8888/user/registerUser',
              params:{
                  userEmail:this.ruleForm.useremail,
                  userPwd:this.ruleForm.password,
              }
          }).then(res=>{
              if (res.data.msg != "关键数据缺失")
              {
                console.log(res.data);
                if(res.data.msg=="Fail") {
                  this.$message({
                    message: '该账户已存在！',
                    type: 'warning'
                  });
                } else {
                  this.$alert(res.data.userId, '请牢记您的ID', {
                  confirmButtonText: '确定',
                  // callback: action => {
                  //     this.$message({
                  //     type: 'info',
                  //     message: `action: ${ action }`
                  //     });
                  // }
                  });
                }
                
              this.$router.push('/')
              }
              else{
              this.$message({
              showClose: true,
              message: '缺少关键信息!',
              type: 'error'
              })
              }
    },err=>{
      console.log(err);
    })
          }
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
  width: 50%;
}
.btn_get {
  width: 30%;
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