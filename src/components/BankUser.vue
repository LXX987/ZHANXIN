<template>
  <div class="body">
    <BankGuideBar></BankGuideBar>
    <div class="main">
      <el-row :gutter="20">
        <el-col :span="4">
          <div class="grid-content bg-purple left">
            <p class="nochoose-txt" @click="BankMain()">概况统计</p>
            <p class="nochoose-txt" @click="BankData()">信用数据</p>
            <p class="choose-txt" @click="BankSearch()">搜索列表</p>
          </div>
        </el-col>
        <el-col :span="20">
          <div class="grid-content">
            <el-row class="bg-purple">
              <el-col :span=20>
                <p class="title-txt1">用户信息</p>
              </el-col>
              <el-col :span=4>
                <p class="tip-txt">数据每日00:00更新</p>
              </el-col>
            </el-row>
            <el-row class="bg-purple pad">
              <p class="title-txt">用户基本信息</p>
              <el-alert :closable="false" title="未成年，暂无信贷资格" type="warning" v-if="adult=='未成年'"></el-alert>
              <el-col :span="12">
                <el-descriptions title="" :column="1">
                  <el-descriptions-item label="姓名">&emsp;&emsp;{{name}}</el-descriptions-item>
                  <el-descriptions-item label="职业">&emsp;&emsp;{{occupation_str}}</el-descriptions-item>
                  <el-descriptions-item label="年收入">&emsp;{{annual_income}}元</el-descriptions-item>
                  <el-descriptions-item label="就业年限">{{working_years}}</el-descriptions-item>
                  <el-descriptions-item label="联系电话">{{phone}}</el-descriptions-item>
                </el-descriptions>
              </el-col>
              <el-col :span="12">
                <el-descriptions title="" :column="1">
                  <el-descriptions-item label="实名认证">{{authentication}}</el-descriptions-item>
                  <el-descriptions-item label="证件类型">{{IDtype}}</el-descriptions-item>
                  <el-descriptions-item label="证件号">&emsp;{{IDcard}}</el-descriptions-item>
                  <el-descriptions-item label="是否成年">&emsp;{{adult}}</el-descriptions-item>
                </el-descriptions>
              </el-col>
            </el-row>
            <el-row class="bg-purple pad">
              <p class="title-txt">用户信用分</p>
              <el-descriptions title="" :column="1">
                <el-descriptions-item label="总分">&emsp;&emsp;{{score}}</el-descriptions-item>
              </el-descriptions>
              <el-descriptions title="维度分" :column="2">
                <el-descriptions-item label="身份证明">&emsp;&emsp;{{idfication}}</el-descriptions-item>
                <el-descriptions-item label="资产证明">&emsp;&emsp;{{asset}}</el-descriptions-item>
                <el-descriptions-item label="信贷记录">&emsp;&emsp;{{credit_histort}}</el-descriptions-item>
                <el-descriptions-item label="行为积累">&emsp;&emsp;{{behavior}}</el-descriptions-item>
                <el-descriptions-item label="人脉关系">&emsp;&emsp;{{social}}</el-descriptions-item>
              </el-descriptions>
            </el-row>
            <el-row class="bg-purple pad">
              <p class="title-txt">用户资产信息</p>
              <el-descriptions title="" :column="1">
                <el-descriptions-item label="资产数额">&emsp;&emsp;{{assetNum}}</el-descriptions-item>
              </el-descriptions>
            </el-row>
            <el-row class="bg-purple pad">
              <p class="title-txt">用户信贷记录</p>
              <el-descriptions title="资产信息" column="2">
                    <el-descriptions-item label="月债务支出、赡养费、生活费除以总收入">{{DebtRatio}}</el-descriptions-item>
                    <el-descriptions-item label="抵押和房地产数量">{{house}}</el-descriptions-item>
                    <el-descriptions-item label="月收入">{{MonthlyIncome}}</el-descriptions-item>
                  </el-descriptions>
              <el-descriptions title="贷款信息" column="2">
                    <el-descriptions-item label="公开贷款（如汽车和抵押贷款）和信用限额（信用卡）数量">{{NumberOfOpenCreditLinesAndLoans}}</el-descriptions-item>
                    <el-descriptions-item label="信用卡循环利用率">{{RevolvingUtilizationOfUnsecuredLines}}</el-descriptions-item>
                  </el-descriptions>
              <el-descriptions title="逾期记录" column="2">
                    <el-descriptions-item label="贷款者过去两年中有30-59天逾期但不糟糕的次数">{{NumberOfTime30To59DaysPastDueNotWorse}}</el-descriptions-item>
                    <el-descriptions-item label="是否存在超过90天或者更长时间逾期未还的不良行为">{{seriousDlqin2yrs}}</el-descriptions-item>
                    <el-descriptions-item label="90天或以上贷款者逾期未还的次数">{{NumberOfTime90DaysLate}}</el-descriptions-item>
                    <el-descriptions-item label="贷款者过去两年中有60-89天逾期但不糟糕的次数">{{NumberOfTime60To89DaysPastDueNotWorse}}</el-descriptions-item>
                  </el-descriptions>
              <el-descriptions title="家庭信息">
                    <el-descriptions-item label="家庭成员数量">{{NumberOfDependents}}</el-descriptions-item>
                  </el-descriptions>
            </el-row>
            <el-row class="bg-purple pad">
              <p class="title-txt">用户声誉记录</p>
              <el-descriptions title="守法情况" column="1">
                    <el-descriptions-item label="犯罪记录">{{crime}}</el-descriptions-item>
                  </el-descriptions>
              <el-descriptions title="公益记录" column="2">
                    <el-descriptions-item label="献血次数">{{blood}}</el-descriptions-item>
                    <el-descriptions-item label="志愿服务时长">{{volunteer}}</el-descriptions-item>
                    <el-descriptions-item label="捐款">{{donation}}</el-descriptions-item>
                  </el-descriptions>
              <el-descriptions title="缴费记录" column="1">
                <el-descriptions-item label="话费缴费">{{phoneCost}}</el-descriptions-item>
              </el-descriptions>
            </el-row>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import BankGuideBar from './BankGuideBar.vue';
export default {
  name: 'BankUser',
  components: { BankGuideBar },
  data() {
    return {
      userID:'',
      userName:'',
      userEmail:'',
      occupation:'',
      occupation_str:'',
      annual_income:'',
      working_years:'',
      authentication:'',
      IDtype:'',
      IDcard:'',
      phone:'',
      adult:'',
      score:0,
      idfication:'',
      social:'',
      behavior:'',
      credit_histort:'',
      asset:'',
      assetNum:0,
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
      crime:'',
      blood:'',
      volunteer:'',
      donation:'',
      phoneCost:'',
    }
  },
  mounted:function(){
      console.log(this.$route.query.id);
      this.userID=this.$route.query.id;
      this.getUserInfo();
    },
  methods: {
    getUserInfo() {
      this.$axios({
          method:"post",
          url: 'http://localhost:8899/bank/userBasicInfo',
          params:{
            userID:this.userID,
          },
        }).then(res=>{
          console.log(res.data);
          this.userName=res.data.data.userName;
          if(this.userName!=null) {
            this.name=this.userName.substr(0,1);
            var len=this.userName.length;
            if(len==2) {
              this.name=this.name+"*";
            } else {
              for (var i=1;i<len-1;i++) {
                this.name=this.name+"*";
              }
              this.name=this.name+this.userName.substr(len-1,1);
            }
          }
          this.userEmail=res.data.data.userEmail;
          this.occupation=res.data.data.occupation;
          if(this.occupation==10000){
            this.occupation_str='其他';
          } else if(this.occupation==150000){
            this.occupation_str='文化、体育和娱乐业';
          } else if(this.occupation==89000){
            this.occupation_str='卫生和社会工作';
          } else if(this.occupation==91000){
            this.occupation_str='教育';
          } else if(this.occupation==73000){
            this.occupation_str='居民服务、修理和其他服务业';
          } else if(this.occupation==97000){
            this.occupation_str='水利、环境和公共设施管理业';
          } else if(this.occupation==140000){
            this.occupation_str='科学研究和技术服务业';
          } else if(this.occupation==130000){
            this.occupation_str='租赁和商务服务业';
          } else if(this.occupation==100000){
            this.occupation_str='房地产业';
          } else if(this.occupation==190000){
            this.occupation_str='信息传输、软件和信息技术服务业';
          } else if(this.occupation==55000){
            this.occupation_str='住宿和餐饮业';
          } else if(this.occupation==136000){
            this.occupation_str='交通运输、仓储和邮政业';
          } else if(this.occupation==106000){
            this.occupation_str='批发和零售业';
          } else if(this.occupation==760000){
            this.occupation_str='建筑业';
          } else if(this.occupation==138000){
            this.occupation_str='电力、热力、燃气及水生产和供应业';
          } else if(this.occupation==106600){
            this.occupation_str='制造业';
          } else if(this.occupation==110000){
            this.occupation_str='采矿业';
          }
          this.annual_income=res.data.data.annual_income;
          this.working_years=res.data.data.working_years;
          this.phone=res.data.data.phone;
          this.adult=res.data.data.adult;
          if(res.data.data.IDtype==1) {
            this.IDtype='身份证';
          } else {
            this.IDtype=res.data.data.IDtype;
          }
          if(res.data.data.IDcard!=null) {
            this.IDcard=res.data.data.IDcard.substr(0,2)+"**************"+res.data.data.IDcard.substr(16,2);
          } else {
            this.IDcard=res.data.data.IDcard;
          }
          if(res.data.data.authentication) {
            this.authentication="已实名";
          } else {
            this.authentication="未实名";
          }
          if(res.data.data.criminal==0) {
            this.crime="无犯罪记录";
          } else if(res.data.data.criminal==1){
            this.crime="有犯罪记录";
          } else {
            this.crime="暂无数据";
          }
          if(res.data.data.bloodDonation!=null) {
            this.blood=res.data.data.bloodDonation;
          } else {
            this.blood="暂无数据";
          }
          if(res.data.data.volunteer!=null) {
            this.volunteer=res.data.data.volunteer;
          } else {
            this.volunteer="暂无数据";
          }
          if(res.data.data.contribution!=null) {
            this.donation=res.data.data.contribution;
          } else {
            this.donation="暂无数据";
          }
          if(res.data.data.phoneCost!=null) {
            if(res.data.data.phoneCost==0) {
              this.phoneCost="已按时缴费";
            } else {
              this.phoneCost="逾期"+res.data.data.phoneCost+"次";
            }
          } else {
            this.phoneCost="暂无数据";
          }
          this.asset=res.data.data.money;
          this.score=res.data.data.total_score;
          this.behavior = res.data.data.behavior_score;
          this.credit_histort = res.data.data.credit_score;
          this.idfication =  res.data.data.identity_score;
          this.asset = res.data.data.asset_score;
          this.social = res.data.data.social_score;
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
    BankMain() {
      this.$router.push({path: '/BankMain'});
    },
    BankSearch() {
      this.$router.push({path: '/BankSearch'});
    },
    BankData() {
      this.$router.push({path: '/BankData'});
    }
  },
}
</script>

<style scoped>
.body {
  background-color: #f2f2f2;
}
.main {
  padding: 50px;
}
 .el-row {
    margin-bottom: 20px;
  }
  .el-col {
    border-radius: 4px;
  }
  .bg-purple {
    background: #ffffff;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
  .left {
    height:750px;
  }
  .pad {
    padding-bottom: 20px;
  }
  .choose-txt {
    color: #0079FE;
    font-size: 28px;
    padding-top: 20px;
    padding-bottom: 20px;
    font-weight: bold;
    background: #E6F2FF;
    border-right-style:solid;
    border-right-color:#0079FE;
  }
  .nochoose-txt {
    color: #101010;
    font-size: 28px;
    padding-top: 20px;
    padding-bottom: 20px;
    font-weight: bold;
  }
  .nochoose-txt:hover {
    background: #f2f8ff;
  }
  .title-txt {
    text-align: left;
    font-size: 24px;
    color: #101010;
    margin: 20px;
    padding-left: 60px;
    border-left-style:solid;
    border-left-color:#0079FE;
  }
  .title-txt1 {
    text-align: left;
    font-size: 28px;
    font-weight: bold;
    color: #101010;
    margin: 20px;
    padding-left: 60px;
    border-left-style:solid;
    border-left-color:#0079FE;
  }
  .tip-txt {
    font-size: 14px;
    color: #039BE5;
    margin-top: 25px;
  }
  .content-txt {
    font-size: 18px;
    margin-top: 50px;
  }
  .el-input{
    width:250px;
    margin-left: 50px;
    margin-right: 80px;
  }
  .btn-div {
    margin-top: 40px;
    margin-bottom: 40px;
  }
  .btn-mar-left {
    margin-left: 60px;
  }
  .el-alert {
    width: 60%;
    margin-left: 80px;
  }
  .el-descriptions {
    margin-top: 20px;
    margin-left: 80px;
  }
  >>>span.el-descriptions-item__label:not(.is-bordered-label) {
    font-size: 18px;
  }
  >>>span.el-descriptions-item__content {
    font-size: 18px;
  }
</style>