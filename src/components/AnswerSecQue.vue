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
            <div class="choose-block" @click="AccountSecurity()">
              <img src='@/assets/pic_account1.png' class="icon">
              <p class="txt-choose">账号安全</p>
            </div>
          </div>
        </el-col>
        <el-col :span="22">
          <div class="grid-content bg-purple-light">
            <el-row class="bg-white">
              <p class="txt-title">身份验证</p>
              <div class="mar-top2">
                <el-form :model="pwdForm" :rules="rules" ref="pwdForm" label-width="170px">
                  <el-form-item label="您的小学班主任姓名是？" prop="oldPwd">
                    <el-input v-model="pwdForm.oldPwd"></el-input>
                  </el-form-item>
                  <el-form-item label="您在小学居住的城市是？" prop="newPwd">
                    <el-input v-model="pwdForm.newPwd"></el-input>
                  </el-form-item>
                </el-form>
                <div class="mar-top2">
                <el-button type="primary" @click="submitForm('pwdForm')">提交</el-button>
                <el-button @click="resetForm('pwdForm')">重置</el-button>
                </div>
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
    name:'AnswerSecQue',
    data() {
      var checkOldPwd = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入您的回答'));
        } else {
          callback();
        }
      };
      var checkNewPwd = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入您的回答'));
        } else {
          callback();
        }
      };
      return {
        pwdForm: {
          oldPwd: '',
          newPwd: '',
        },
        rules: {
          oldPwd: [{validator: checkOldPwd,trigger: "blur"}],
          newPwd: [{validator: checkNewPwd,trigger: "blur"}],
        },
        set:0,
      };
    },
    mounted:function(){
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
          this.set=res.data.data.msg;
          if(res.data.data.msg==0) {
            this.$alert('您还未设置安全问题', {
                  confirmButtonText: '去设置',
                });
            this.$router.push('/SecurityQuestion')
          }
        },err=>{
          console.log(err);
        })
      },
      insertSecurityQuestion() {
        this.$axios({
          method:"post",
          url: 'http://localhost:8899/user/answerSecurityQuestion',
          params:{
            teacher:this.pwdForm.oldPwd,
            city:this.pwdForm.newPwd,
          },
          headers: { token:window.sessionStorage.getItem("token")}
        }).then(res=>{
          if (res.data.msg != "关键数据缺失") {
            console.log(res.data);
            if(res.data.msg=="Fail") {
              this.$message.error('回答错误！');
            } else {
              this.$message({
                message: '回答正确！',
                type: 'success'
              });
              this.$router.push({path: '/Report'});
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
            this.insertSecurityQuestion();
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
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
    height: 733px;
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
  .btn-pwd {
    font-size: 16px;
    margin-left: 300px;
  }
  .mar-top2 {
    margin-top: 50px;
  }
  >>>.el-form-item__label {
    font-size: 18px;
  }
</style>