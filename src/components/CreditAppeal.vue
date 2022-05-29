<template>
  <div id="hello">
    <div id="body">
      <el-row>
        <el-col :span="2">
          <div class="grid-content bg-purple ">
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
            <div class="choose-block" @click="myReport()">
              <img src='@/assets/pic_report1.png' class="icon">
              <p class="txt-choose">信用报告</p>
            </div>
            <div class="nochoose-block" @click="AccountSecurity()">
              <img src='@/assets/pic_account.png' class="icon">
              <p class="txt-nochoose">账号安全</p>
            </div>
          </div>
        </el-col>
        <el-col :span="22">
          <div class="grid-content bg-purple-light">
            <el-row>
              <div class="grid-content bg-white">
                <p class="txt-title">已提交的信用申诉</p>
                <p class="txt-highlight">申请状态：{{appealState}}</p>
              </div>
            </el-row>
            <el-row>
              <div class="grid-content bg-white mar-top">
                <p class="txt-title">信用申诉申请</p>
                <div class="txt-content">
                  <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                    <el-form-item label="申诉原因" prop="reason">
                      <el-select v-model="ruleForm.reason" placeholder="请选择申诉原因">
                        <el-option label="声誉记录有误" value="声誉记录有误"></el-option>
                        <el-option label="信贷记录有误" value="信贷记录有误"></el-option>
                      </el-select>
                    </el-form-item>
                    <el-form-item label="问题描述" prop="question">
                      <el-select v-model="ruleForm.question" placeholder="请选择问题描述">
                        <el-option label="犯罪记录有误" value="犯罪记录有误" v-if="ruleForm.reason=='声誉记录有误'"></el-option>
                        <el-option label="献血次数有误" value="献血次数有误" v-if="ruleForm.reason=='声誉记录有误'"></el-option>
                        <el-option label="志愿服务时长有误" value="志愿服务时长有误" v-if="ruleForm.reason=='声誉记录有误'"></el-option>
                        <el-option label="捐款数有误" value="捐款数有误" v-if="ruleForm.reason=='声誉记录有误'"></el-option>
                        <el-option label="话费缴费有误" value="话费缴费有误" v-if="ruleForm.reason=='声誉记录有误'"></el-option>
                        <el-option label="月债务支出、赡养费、生活费除以总收入有误" value="月债务支出、赡养费、生活费除以总收入有误" v-if="ruleForm.reason=='信贷记录有误'"></el-option>
                        <el-option label="抵押和房地产数量有误" value="抵押和房地产数量有误" v-if="ruleForm.reason=='信贷记录有误'"></el-option>
                        <el-option label="月收入有误" value="月收入有误" v-if="ruleForm.reason=='信贷记录有误'"></el-option>
                        <el-option label="公开贷款（如汽车和抵押贷款）和信用限额（信用卡）数量有误" value="公开贷款（如汽车和抵押贷款）和信用限额（信用卡）数量有误" v-if="ruleForm.reason=='信贷记录有误'"></el-option>
                        <el-option label="信用卡循环利用率有误" value="信用卡循环利用率有误" v-if="ruleForm.reason=='信贷记录有误'"></el-option>
                        <el-option label="贷款者过去两年中有30-59天逾期但不糟糕的次数有误" value="贷款者过去两年中有30-59天逾期但不糟糕的次数有误" v-if="ruleForm.reason=='信贷记录有误'"></el-option>
                        <el-option label="是否存在超过90天或者更长时间逾期未还的不良行为有误" value="是否存在超过90天或者更长时间逾期未还的不良行为有误" v-if="ruleForm.reason=='信贷记录有误'"></el-option>
                        <el-option label="90天或以上贷款者逾期未还的次数有误" value="90天或以上贷款者逾期未还的次数有误" v-if="ruleForm.reason=='信贷记录有误'"></el-option>
                        <el-option label="贷款者过去两年中有60-89天逾期但不糟糕的次数有误" value="贷款者过去两年中有60-89天逾期但不糟糕的次数有误" v-if="ruleForm.reason=='信贷记录有误'"></el-option>
                        <el-option label="家庭成员数量有误" value="家庭成员数量有误" v-if="ruleForm.reason=='信贷记录有误'"></el-option>
                      </el-select>
                    </el-form-item>
                    <el-form-item label="修改请求" prop="require">
                      <el-input v-model="ruleForm.require" placeholder="请输入修改目标值"></el-input>
                    </el-form-item>
                    <el-form-item>
                      <el-button type="primary" @click="submitForm('ruleForm')">提交申请</el-button>
                      <el-button @click="resetForm('ruleForm')">重置</el-button>
                    </el-form-item>
                  </el-form>
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
    name:'CreditAppeal',
    data() {
      var checkRequire = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('修改请求不能为空'));
        }
        setTimeout(() => {
          if (!Number.isInteger(value)) {
            if(!(/(^[0-9]+.?[0-9]*$)/.test(value))) {
              callback(new Error('请输入数字值或浮点值'));
            } else {
              callback();
            }
            
          } 
        }, 1000);
      };
      return {
        appealState:'暂未提交申请',
        ruleForm: {
          reason: '',
          question: '',
          require: '',
        },
        rules: {
          reason: [{ required: true, message: '请填写申诉原因', trigger: 'blur' }],
          question: [{ required: true, message: '请填写问题描述', trigger: 'blur' }],
          require: [{ required: true,validator: checkRequire}]
        },
        tableData: [{
          appealState: '0',
          appealTime: '',
          question: '',
          reason: '',
          require: '',
          userID: '',
        }],
      };
    },
    mounted:function(){
      this.getCreditRecord();
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            console.log('submit!!');
            this.updateAppeal();
            location.reload();
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      updateAppeal() {
        this.$axios({
          method:"post",
          url: 'http://localhost:8899/appeal/insertAppeal',
          params:{
            reason:this.ruleForm.reason,
            question:this.ruleForm.question,
            require:this.ruleForm.require,
          },
          headers: { token:window.sessionStorage.getItem("token")}
        }).then(res=>{
          if (res.data.msg != "关键数据缺失") {
            console.log(res.data);
            this.$message({
              message: '提交成功！',
              type: 'success'
            });
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
      getCreditRecord() {
        this.$axios({
          method:"get",
          url: 'http://localhost:8899/appeal/appealList',
          headers: { token:window.sessionStorage.getItem("token")}
        }).then(res=>{
          console.log(res.data);
          this.tableData=res.data;
          var index=res.data.length-1;
          var state=this.tableData[index].appealState;
          if(state==0){ this.appealState="暂未审核"; }
          else if(state==1){ this.appealState="申请已通过审核"; }
          else if(state==2){ this.appealState="申请未通过审核，已驳回"; }
        },err=>{
          console.log(err);
        })
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
  }
  .txt-title {
    font-size: 28px;
    font-weight: bold;
    color: #1F7C69;
    text-align: left;
  }
  .txt-highlight {
    color: #32BDFE;
    font-size: 40px;
    font-weight: bold;
    margin-top: 30px;
    text-align: left;
  }
  .mar-top {
    margin-top: 10px;
  }
  .txt-content {
    text-align: left;
    font-size: 17px;
    margin-top: 80px;
    margin-bottom: 118px;
  }
  >>>.el-form-item__label {
    font-size: 18px;
  }
  >>>.el-textarea__inner {
    height: 90px;
    font-size: 18px;
  }
  .el-input {
    width: 500px;
  }
  .el-select {
    width: 500px;
  }
</style>