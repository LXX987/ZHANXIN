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
            <Vcode :show="isShow" @success="success" @close="close" />
            <li><el-button type="primary" round class="btn_login" @click="submit">注册</el-button></li>
            <br>
            <el-radio v-model="radio" label="1" id="protocol"><u @click="centerDialogVisible = true">用户隐私服务协议</u></el-radio>
            <el-dialog
              title="用户服务协议和隐私政策"
              :visible.sync="centerDialogVisible"
              width="50%"
              center>
              <span>
                <p>《用户服务协议和隐私政策》（以下简称“协议”）及其条款，系您使用“瞻信”（简称：瞻信）网站（以下简称“本网站”）所订立的、描述您与本网站之间权利义务的协议。</p>
                <p>在注册前务必认真阅读本协议的内容、充分理解各条款内容，如有异议，您可选择不进入本网站。一旦您确认本用户注册协议后，本协议即在您和本网站之间产生法律效力，意味着您完全同意并接受协议的全部条款。请您审慎阅读并选择接受或不接受协议（未成年人应在法定监护人陪同下阅读）。</p>
                <p>用户使用：</p>
                <p>1. 用户按照页面给与的提示完成全部注册步骤后，即成为本软件的用户，用户应自行保管账号及密码，并准确、安全地使用其账号及密码。本软件可通过用户自行设定，向用户提供个性化信息服务。</p>
                <p>2. 用户充分了解并同意，本软件为用户提供个性化信息服务，用户须为自己注册账号下的行为负责，包括用户所导入、上载、传送的任何内容以及由此产生的任何后果，用户应对本软件中的内容自行加以判断，并承担因使用内容而引起的所有风险。本软件不对因用户行为而导致的损失承担责任。</p>
                <p>3. 用户提供的个人信息或发布的信息不真实、不准确、不合法；发表内容不符合本协议或发表内容不符合法律法规的，本软件有权暂停或终止用户使用本平台的服务。用户如果对本软件的暂停或者终止或收回其账号行为有异议，客户可向平台提出意见，要求其继续提供服务。本软件收到客户此类信息的投诉与意见，会审核相关信息，并及时对用户给予反馈。若信息确实不真实或不准确或不合法有效，本软件平台要求用户提供符合要求的信息。</p>
                <p>4. 为避免用户终端数据丢失、更好服务用户之目的，本软件将向注册用户提供数据备份服务，经注册用户授权，本软件将存储于注册用户终端的本软件数据库中的数据资料上载至本软件服务器，并且提供恢复到终端设备服务。</p>
                <p>隐私政策：</p>
                <p>本软件尊重并保护所有使用服务用户的个人隐私权，高度重视用户的隐私及个人信息的保护，本隐私政策帮助您了解我们收集哪些数据、为什么收集这些数据，会利用这些数据做些什么及如何保护这些数据。</p>
                <p>1. 注册账号：您注册并登录本软件账号时至少向我们提供账号名称、头像、手机号码或电子邮箱，并创建密码。注册成功后，您提供的上述信息，将在您使用本软件平台和服务期间持续授权我们使用。在您注销账号时，我们将停止使用并删除上述信息或对您的个人信息进行匿名化处理，法律法规另有规定的除外。</p>
                <p>2. 为了向您提供更优质的产品和服务，我们可能需要收集一些信息。 如果您拒绝提供这些信息，不影响您正常使用本网站业务功能，但我们无法向您提供某些特定功能和服务。</p>
                <p>3. 我们将以国家安全标准和法律法规的规定收集、使用、存储和传输用户信息，并通过用户协议和隐私政策告知您相关信息的使用目的和范围。同时，我们会对个人信息进行加密技术，以确保数据的保密性与安全性。</p>
              </span>
              <span slot="footer" class="dialog-footer">
                <el-button @click="cancel()">取 消</el-button>
                <el-button type="primary" @click="link()">确 定</el-button>
              </span>
            </el-dialog>
            <el-link type="primary" @click="login()">已有账号？点击登录</el-link>
          </el-form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Vcode from "vue-puzzle-vcode";
export default {
  name: 'Register',
  components: {
    Vcode
  },
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
      isShow: false, // 验证码模态框是否出现
      centerDialogVisible: false,
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
    submit() {
      this.isShow = true;
    },
    success(msg) { // 用户通过了验证
      this.isShow = false; // 通过验证后，需要手动隐藏模态框
      this.register();
    },
    close() { // 用户点击遮罩层，应该关闭模态框
      this.isShow = false;
    },
    cancel() {
      this.radio="0";
      this.centerDialogVisible = false;
    },
    link() {
      this.radio="1";
      this.centerDialogVisible = false;
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
              url:'http://localhost:8899/mail/mail',
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
            url: 'http://localhost:8899/user/registerUser',
            params:{
                userEmail:this.ruleForm.useremail,
                userPwd:this.ruleForm.password,
                userType:0,
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
                this.$alert(res.data.userId, '请牢记您的注册邮箱和密码', {
                  confirmButtonText: '确定',
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
    },
    login() {
      this.$router.push({path: '/'});
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