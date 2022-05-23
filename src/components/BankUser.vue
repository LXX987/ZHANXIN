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
                <p class="title-txt">用户信息</p>
              </el-col>
              <el-col :span=4>
                <p class="tip-txt">数据每日00:00更新</p>
              </el-col>
            </el-row>
            <el-row class="bg-purple">
              <p class="title-txt">用户基本信息</p>
              <el-col :span="12">
                <el-descriptions title="" :column="1">
                  <el-descriptions-item label="姓名">&emsp;&emsp;{{name}}</el-descriptions-item>
                  <el-descriptions-item label="职业">&emsp;&emsp;{{occupation_str}}</el-descriptions-item>
                  <el-descriptions-item label="年收入">&emsp;{{annual_income}}元</el-descriptions-item>
                  <el-descriptions-item label="就业年限">{{working_years}}</el-descriptions-item>
                </el-descriptions>
              </el-col>
              <el-col :span="12">
                <el-descriptions title="" :column="1">
                  <el-descriptions-item label="实名认证">{{authentication}}</el-descriptions-item>
                  <el-descriptions-item label="证件类型">{{IDtype}}</el-descriptions-item>
                  <el-descriptions-item label="证件号">&emsp;{{IDcard}}</el-descriptions-item>
                </el-descriptions>
              </el-col>
            </el-row>
            <el-row class="bg-purple">
              <p class="title-txt">用户信用分</p>
              <el-descriptions title="" :column="1">
                <el-descriptions-item label="总分">&emsp;&emsp;{{name}}</el-descriptions-item>
              </el-descriptions>
              <el-descriptions title="维度分" :column="2">
                <el-descriptions-item label="总分">&emsp;&emsp;{{name}}</el-descriptions-item>
                <el-descriptions-item label="总分">&emsp;&emsp;{{name}}</el-descriptions-item>
                <el-descriptions-item label="总分">&emsp;&emsp;{{name}}</el-descriptions-item>
                <el-descriptions-item label="总分">&emsp;&emsp;{{name}}</el-descriptions-item>
                <el-descriptions-item label="总分">&emsp;&emsp;{{name}}</el-descriptions-item>
              </el-descriptions>
            </el-row>
            <el-row class="bg-purple">
              <p class="title-txt">用户资产信息</p>
              <el-descriptions title="" :column="1">
                <el-descriptions-item label="资产数额">&emsp;&emsp;{{name}}</el-descriptions-item>
              </el-descriptions>
            </el-row>
            <el-row class="bg-purple">
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
            <el-row class="bg-purple">
              <p class="title-txt">用户声誉记录</p>
              <el-descriptions title="守法情况" column="1">
                    <el-descriptions-item label="犯罪记录">{{DebtRatio}}</el-descriptions-item>
                  </el-descriptions>
              <el-descriptions title="公益记录" column="2">
                    <el-descriptions-item label="献血次数">{{DebtRatio}}</el-descriptions-item>
                    <el-descriptions-item label="志愿服务时长">{{DebtRatio}}</el-descriptions-item>
                    <el-descriptions-item label="捐款">{{DebtRatio}}</el-descriptions-item>
                  </el-descriptions>
              <el-descriptions title="缴费记录" column="1">
                <el-descriptions-item label="话费缴费">{{DebtRatio}}</el-descriptions-item>
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
    }
  },
  mounted:function(){
      console.log(this.$route.query.id);
    },
  methods: {
    getUserList() {
      this.$axios({
          method:"post",
          url: 'http://localhost:8899/bank/bankSearchList',
          params:{
            userID:this.searchID,
          },
        }).then(res=>{
          console.log(res.data);
          this.tableData=res.data;
        },err=>{
          console.log(err);
        })
    },
    searchUserID() {
      if(this.searchID==null&&this.searchName==null) {
        this.$message({
              message: '请输入查询信息!',
              type: 'error'
            })
      } else {
        this.$axios({
          method:"post",
          url: 'http://localhost:8899/bank/bankSearchID',
          params:{
            userID:this.searchID,
            userName:this.searchName,
          },
        }).then(res=>{
          console.log(res.data);
          this.tableData=res.data;
        },err=>{
          console.log(err);
        })
      }
      
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