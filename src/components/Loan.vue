<template>
  <div id="hello">
      <div id="body">
      <el-row>
        <el-col :span="2">
          <div class="grid-content bg-purple">
            <div class="nochoose-block"  @click="gotoScoreDetail()">
              <img src='@/assets/pic_personalInfo.png' class="icon">
              <p class="txt-nochoose">身份证明</p>
            </div>
            <div class="nochoose-block"  @click="gotoBehavior()">
              <img src='@/assets/pic_report.png' class="icon">
              <p class="txt-nochoose">行为积累</p>
            </div>
            <div class="choose-block"  @click="gotoLoan()">
              <img src='@/assets/pic_credit1.png' class="icon">
              <p class="txt-choose">信贷历史</p>
            </div>
            <div class="nochoose-block" @click="gotoSocial()">
              <img src='@/assets/pic_reputation.png' class="icon">
              <p class="txt-nochoose">好友圈</p>
            </div>
            <div class="nochoose-block"  @click="gotoPossession()">
              <img src='@/assets/pic_assets.png' class="icon">
              <p class="txt-nochoose">资产证明</p>
            </div>
          </div>
        </el-col>
        <el-col :span="22">
          <div class="grid-content bg-purple-light">
            <el-row class="bg-white">
                <div class="box">
              <div class="intro">
                <h1 style="text-align:left;font-size:20px;font-weight:bold">信贷记录得分详情<el-button type="text" style="margin-left:10px" @click="gotoFinish()">去完善</el-button></h1>
                 <div class="tip"><p>信贷记录是指你以往在银行进行借贷的数据信息，是评价你的过往信用的重要方面。</p></div>
              </div>
              <div>
                  <p class="subtitle-txt">资产信息</p>
                  <el-descriptions title="" column="1">
                    <el-descriptions-item label="月债务支出、赡养费、生活费除以总收入">{{DebtRatio}}</el-descriptions-item>
                    <el-descriptions-item label="房地产数量">{{house}}</el-descriptions-item>
                    <el-descriptions-item label="月收入">{{MonthlyIncome}}</el-descriptions-item>
                  </el-descriptions>
                  <p class="subtitle-txt">贷款信息</p>
                  <el-descriptions title="" column="1">
                    <el-descriptions-item label="公开贷款（如汽车和抵押贷款）和信用限额（信用卡）数量">{{NumberOfOpenCreditLinesAndLoans}}</el-descriptions-item>
                    <el-descriptions-item label="信用卡循环利用率">{{RevolvingUtilizationOfUnsecuredLines}}</el-descriptions-item>
                  </el-descriptions>
                  <p class="subtitle-txt">逾期记录</p>
                  <p style="text-align:left;font-size:14px;">无</p>
                  <!-- <el-descriptions title="" column="1">
                    <el-descriptions-item label="贷款者过去两年中有30-59天逾期但不糟糕的次数">{{NumberOfTime30To59DaysPastDueNotWorse}}</el-descriptions-item>
                    <el-descriptions-item label="是否存在超过90天或者更长时间逾期未还的不良行为">{{seriousDlqin2yrs}}</el-descriptions-item>
                    <el-descriptions-item label="90天或以上贷款者逾期未还的次数">{{NumberOfTime90DaysLate}}</el-descriptions-item>
                    <el-descriptions-item label="贷款者过去两年中有60-89天逾期但不糟糕的次数">{{NumberOfTime60To89DaysPastDueNotWorse}}</el-descriptions-item>
                  </el-descriptions> -->
                  <p class="subtitle-txt">家庭信息</p>
                  <el-descriptions title="">
                    <el-descriptions-item label="家庭成员数量">{{NumberOfDependents}}</el-descriptions-item>
                  </el-descriptions>
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
    name:'PersonalCenter',
    data() {
      return {
        house:'0',
        DebtRatio:'0',
        MonthlyIncome:'0',
        NumberOfOpenCreditLinesAndLoans:'0',
        RevolvingUtilizationOfUnsecuredLines:'0',
        NumberOfTime30To59DaysPastDueNotWorse:'0',
        seriousDlqin2yrs:'0',
        NumberOfTime90DaysLate:'0',
        NumberOfTime60To89DaysPastDueNotWorse:'0',
        NumberOfDependents:'0',
        userName:'',
        userName:'',
        userEmail:'',
        occupation:'',
        occupation_str:'',
        annual_income:'',
        working_years:'',
        authentication:'',
        IDtype:'',
        IDcard:'',
      };
    },
     mounted:function(){
    this.getMyInfo()
    this.getCreditRecord()
    },
    methods:{
        gotoFinish(){
            this.$router.push({path: '/PersonalCenter'});
        },
        gotoScoreDetail(){
            this.$router.push({path: '/ScoreDetail'});
        },
        gotoLoan(){
            this.$router.push({path: '/Loan'});
        },
        gotoSocial(){
            this.$router.push({path: '/Social'});
        },
        gotoPossession(){
            this.$router.push({path: '/Possession'});
        },
        gotoBehavior(){
            this.$router.push({path: '/Behavior'});
        },
        getCreditRecord() {
        this.$axios({
          method:"get",
          url: 'http://localhost:8899/user/getCreditRecord',
          headers: { token:window.sessionStorage.getItem("token")}
        }).then(res=>{
          console.log('信用记录数据：', res.data);
          if(res.data.data.numberRealEstateLoansOrLines!=null) {this.house=res.data.data.numberRealEstateLoansOrLines;}
          if(res.data.data.DebtRatio!=null) {this.DebtRatio=res.data.data.DebtRatio;}
          if(res.data.data.monthlyIncome!=null) {this.MonthlyIncome=res.data.data.monthlyIncome;}
          if(res.data.data.numberOfOpenCreditLinesAndLoans!=null) {this.NumberOfOpenCreditLinesAndLoans=res.data.data.numberOfOpenCreditLinesAndLoans;}
          if(res.data.data.revolvingUtilizationOfUnsecuredLines!=null) {this.RevolvingUtilizationOfUnsecuredLines=res.data.data.revolvingUtilizationOfUnsecuredLines;}
          if(res.data.data.numberOfTime30To59DaysPastDueNotWorse!=null) {this.NumberOfTime30To59DaysPastDueNotWorse=res.data.data.numberOfTime30To59DaysPastDueNotWorse;}
          if(res.data.data.seriousDlqin2yrs!=null) {this.seriousDlqin2yrs=res.data.data.seriousDlqin2yrs;}
          if(res.data.data.numberOfTime90DaysLate!=null) {this.NumberOfTime90DaysLate=res.data.data.numberOfTime90DaysLate;}
          if(res.data.data.numberOfTime60To89DaysPastDueNotWorse!=null) {this.NumberOfTime60To89DaysPastDueNotWorse=res.data.data.numberOfTime60To89DaysPastDueNotWorse;}
          if(res.data.data.numberOfDependents!=null) {this.NumberOfDependents=res.data.data.numberOfDependents;}
        },err=>{
          console.log(err);
        })
      },
    }
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
    cursor: pointer;
  }
  .txt-choose {
    font-size: 18px;
    color: #EFB346;
    margin-top: 10px;
  }
  .nochoose-block {
    margin-top: 40px;
    margin-bottom: 40px;
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
    margin-left: 50px;
  }
  .el-descriptions {
    margin-top: 30px;
  }
  >>>span.el-descriptions-item__label:not(.is-bordered-label) {
    font-size: 18px;
  }
  >>>span.el-descriptions-item__content {
    font-size: 18px;
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
    font-size: 17px;
    margin-top: 20px;
  }
  .el-input {
    width: 80%;
  }
  .el-select {
    width: 80%;
  }
  .tip {
    padding: 8px 16px;
    background-color: #ecf8ff;
    border-radius: 4px;
    border-left: 5px solid #50bfff;
    margin: 20px 0;
    text-align: left;
    font-size: 18px;
}
.subtitle-txt{
  text-align:left;
  font-size:20px;
  font-weight: bold;
  padding-top: 10px;
}
</style>