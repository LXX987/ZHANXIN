<template>
  <div id="hello">
    <div id="body">
      <el-row>
        <el-col :span="2">
          <div class="grid-content bg-purple">
            <div class="nochoose-block" @click="personalCenter()">
              <img src='@/assets/pic_personalInfo.png'>
              <p class="txt-nochoose">基本资料</p>
            </div>
            <div class="nochoose-block" @click="assetProof()">
              <img src='@/assets/pic_assets.png' class="icon">
              <p class="txt-nochoose">资产证明</p>
            </div>
            <div class="nochoose-block" @click="honestyProof()">
              <img src='@/assets/pic_reputation.png' class="icon">
              <p class="txt-nochoose">声誉记录</p>
            </div>
            <div class="nochoose-block" @click="myCreditRecord()">
              <img src='@/assets/pic_credit.png' class="icon">
              <p class="txt-nochoose">信贷记录</p>
            </div>
            <div class="nochoose-block" @click="myReport()">
              <img src='@/assets/pic_report.png' class="icon">
              <p class="txt-nochoose">信用报告</p>
            </div>
            <div class="choose-block">
              <img src='@/assets/pic_account1.png' class="icon">
              <p class="txt-choose">账号安全</p>
            </div>
          </div>
        </el-col>
        <el-col :span="22">
          <div class="grid-content bg-purple-light">
            <el-row class="bg-white">
              <p class="txt-title">账号资料</p>
              <el-col :span="6">
                <div class="grid-content">
                  <img v-if="avatar!=''" :src="avatar" class="avatar-pic">
                  <img v-else src='@/assets/avatar.png' class="avatar-pic">
                  <p><el-button type="text" class="btn-text" @click="headDialogVisible = true">点击更换头像</el-button></p>
                  <el-dialog title="修改头像" :visible.sync="headDialogVisible" width="25%" center append-to-body>
                    <span>
                      <el-upload
                        class="avatar-uploader"
                        action="http://localhost:8899/user/uploadAvatar"
                        :headers="headers"
                        :show-file-list="false"
                        :on-success="handleAvatarSuccess">
                        <img v-if="imageUrl" :src="imageUrl" class="avatar">
                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                      </el-upload>
                    </span>
                    <span slot="footer" class="dialog-footer">
                      <el-button @click="headDialogVisible = false">取 消</el-button>
                      <el-button type="primary" @click="postAvatar()">确 定</el-button>
                    </span>
                  </el-dialog>
                </div>
              </el-col>
              <el-col :span="18">
                <div class="grid-content mar-top1">
                  <p class="txt-content">账号：{{userID}}</p>
                  <p class="txt-content">绑定邮箱：{{userEmail}}</p>
                </div>
              </el-col>
            </el-row>
            <el-row class="bg-white mar-top">
              <p class="txt-title">账号安全</p>
              <div class="grid-content">
                <el-col :span="16">
                  <p class="txt-content mar-top2">账号：{{userID}}</p>
                  <p class="txt-content">绑定邮箱：{{userEmail}}</p>
                  <p class="txt-content mar-bot">密码：*<el-button type="text" class="btn-pwd" @click="pwdDialogVisible = true">修改</el-button></p>
                  <el-dialog title="修改密码" :visible.sync="pwdDialogVisible" width="30%">
                    <el-form :model="pwdForm" :rules="rules" ref="pwdForm" label-width="80px">
                      <el-form-item label="请输入原密码" prop="oldPwd">
                        <el-input type="password" v-model="pwdForm.oldPwd"></el-input>
                      </el-form-item>
                      <el-form-item label="请输入新密码" prop="newPwd">
                        <el-input type="password" v-model="pwdForm.newPwd"></el-input>
                      </el-form-item>
                      <el-form-item label="请确认新密码" prop="newPwdAgain">
                        <el-input type="password" v-model="pwdForm.newPwdAgain"></el-input>
                      </el-form-item>
                    </el-form>
                    <span slot="footer" class="dialog-footer">
                      <el-button type="primary" @click="submitForm('pwdForm')">提交</el-button>
                      <el-button @click="resetForm('pwdForm')">重置</el-button>
                    </span>
                  </el-dialog>
                </el-col>
                <el-col :span="8">
                  <p class="txt-content mar-top2">身份验证问题：{{question}}</p>
                  <p class="txt-content"><el-button type="text" class="btn-que" @click="SecurityQuestion()" v-if="question=='暂未设置'">点击设置身份验证问题</el-button></p>
                </el-col>
              </div>
            </el-row>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
    name:'AccountSecurity',
    data() {
      var checkOldPwd = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入旧密码'));
        } else {
          callback();
        }
      };
      var checkNewPwd = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入新密码'));
        } else {
          if (this.pwdForm.newPwdAgain !== '') {
            this.$refs.pwdForm.validateField('newPwdAgain');
          }
          callback();
        }
      };
      var checkNewPwdAgain = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('请再次输入新密码'));
        } else if (value !== this.pwdForm.newPwd) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      return {
        userID:'',
        userEmail:'',
        avatar:'',
        headDialogVisible: false,
        imageUrl: '',
        headers:{'token':window.sessionStorage.getItem("token")},
        pwdDialogVisible: false,
        innerVisible: false,
        pwdForm: {
          oldPwd: '',
          newPwd: '',
          newPwdAgain: '',
        },
        rules: {
          oldPwd: [{validator: checkOldPwd,trigger: "blur"}],
          newPwd: [{validator: checkNewPwd,trigger: "blur"}],
          newPwdAgain: [{validator: checkNewPwdAgain,trigger: "blur"}]
        },
        question:'暂未设置',
      };
    },
    mounted:function(){
      this.getUserInfo();
      this.selectSecurityQuestion();
    },
    methods: {
      selectSecurityQuestion() {
        this.$axios({
          method:"get",
          url: 'http://localhost:8899/user/selectSecurityQuestion',
          headers: { token:window.sessionStorage.getItem("token")}
        }).then(res=>{
          console.log('我的安全问题数据：', res.data);
          if(res.data.data.msg==1) {
            this.question='已设置'
          }
        },err=>{
          console.log(err);
        })
      },
      changePwd() {
        this.$axios({
          method:"post",
          url: 'http://localhost:8899/user/changePassword',
          params:{
            oldPwd:this.pwdForm.oldPwd,
            newPwd:this.pwdForm.newPwd,
          },
          headers: { token:window.sessionStorage.getItem("token")}
        }).then(res=>{
          if (res.data.msg != "关键数据缺失") {
            console.log(res.data);
            if(res.data.msg=="Fail") {
              this.$message.error('原密码错误！');
            } else {
              this.$message({
                message: '密码修改成功！',
                type: 'success'
              });
              this.$router.push('/')
            }
          } else{
            this.$message({
              showClose: true,
              message: '缺少关键信息!',
              type: 'error'
            })
          }
        },err=>{
          console.log(err);
        })
      },
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            console.log('submit!!');
            this.changePwd();
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      getUserInfo() {
        this.$axios({
          method:"get",
          url: 'http://localhost:8899/user/getUserName',
          headers: { token:window.sessionStorage.getItem("token")}
        }).then(res=>{
          console.log('我的信息数据：', res.data);
          this.userID=res.data.data.userID;
          this.userEmail=res.data.data.userEmail;
          this.avatar=res.data.data.userAvatar;
        },err=>{
          console.log(err);
        })
      },
      postAvatar() {
        this.headDialogVisible = false;
        this.$message({
              message: '头像上传成功！',
              type: 'success'
            });
        location.reload();
      },
      handleAvatarSuccess(res, file) {
        this.imageUrl = URL.createObjectURL(file.raw);
      },
      personalCenter() {
        this.$router.push({path: '/PersonalCenter'});
      },
      assetProof() {
        this.$router.push({path: '/AssetProof'});
      },
      honestyProof() {
        this.$router.push({path: '/HonestyProof'});
      },
      myCreditRecord() {
        this.$router.push({path: '/MyCreditRecord'});
      },
      myReport() {
        this.$router.push({path: '/MyReport'});
      },
      AccountSecurity() {
        this.$router.push({path: '/AccountSecurity'});
      },
      SecurityQuestion() {
        this.$router.push({path: '/SecurityQuestion'});
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
  padding-top: 40px;
}
#body {
  background-color:#ffffff;
  width: 70%;
  margin-left: 15%;
  margin-right: 15%;
  border-radius: 20px;
  padding: 10px;
  box-shadow: 0px 0px 20px #6a8a84bb;
}
  .el-col {
    border-radius: 20px;
  }
  .bg-purple {
    background: #1f7c69;
    padding-top: 20px;
    padding-bottom: 20px;
  }
  .bg-purple-light {
    background: #F1F6F9;
    padding: 10px;
  }
  .grid-content {
    border-radius: 20px;
    min-height: 36px;
  }
  .choose-block {
    background: #ffffff;
    border-radius: 60px;
    margin-left: 10px;
    margin-right: 10px;
    padding-top: 40px;
    padding-bottom: 40px;
  }
  .txt-choose {
    font-size: 18px;
    color: #EFB346;
    margin-top: 10px;
  }
  .nochoose-block {
    margin-top: 40px;
    margin-bottom: 40px;
    cursor: pointer;
  }
  .nochoose-block:hover {
    background-color: #186051;
    border-radius: 20px;
  }
  .icon {
    height: 27px;
    width: 27px;
  }
  .txt-nochoose {
    font-size: 18px;
    color: #ffffff;
    margin-top: 10px;
  }
  .bg-white {
    background: #ffffff;
    padding: 45px 100px;
    border-radius: 20px;
  }
  .txt-title {
    font-size: 28px;
    font-weight: bold;
    color: #1F7C69;
    text-align: left;
  }
  .btn-text {
    font-size: 16px;
    margin-top: 20px;
  }
  .mar-top {
    margin-top: 10px;
  }
  .sub-title {
    text-align: left;
    font-size: 20px;
    font-weight: bold;
    color: #52AD9C;
    margin-top: 30px;
    margin-bottom: 5px;
  }
  .txt-content {
    text-align: left;
    font-size: 18px;
    margin-top: 30px;
  }
  .el-input {
    width: 80%;
  }
  .avatar-pic {
    width: 150px;
    height: 150px;
    border-radius: 50%;
    margin-top: 50px;
  }
  .mar-top1 {
    margin-top: 90px;
    margin-left: 80px;
  }
  >>>.avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    margin-left: 15%;
  }
  >>>.avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 300px;
    height: 300px;
    line-height: 300px;
    text-align: center;
  }
  .avatar {
    width: 300px;
    height: 300px;
    display: block;
  }
  .btn-pwd {
    font-size: 16px;
    margin-left: 300px;
  }
  .el-form-item__label {
    padding: 0px;
  }
  .mar-top2 {
    padding-top: 15px;
  }
  .mar-bot {
    margin-bottom: 10px;
  }
  .btn-que {
    padding:0px;
    font-size: 16px;
  }
</style>