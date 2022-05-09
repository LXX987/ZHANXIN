<template>
  <div id="hello">
    <div id="body">
      <el-row>
        <el-col :span="2">
          <div class="grid-content bg-purple">
            <div class="choose-block"  @click="gotoScoreDetail()">
              <img src='@/assets/pic_personalInfo1.png'>
              <p class="txt-choose">身份证明</p>
            </div>
            <div class="nochoose-block"  @click="gotoBehavior()">
              <img src='@/assets/pic_report.png' class="icon">
              <p class="txt-nochoose">行为积累</p>
            </div>
            <div class="nochoose-block"  @click="gotoLoan()">
              <img src='@/assets/pic_credit.png' class="icon">
              <p class="txt-nochoose">信贷历史</p>
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
                <h1 style="text-align:left;font-size:20px;font-weight:bold">身份证明得分详情<el-button type="text" style="margin-left:10px" @click="gotoFinish()">去完善</el-button></h1>
                 <div class="tip"><p>身份证明是指能验证你身份和工作状态的数据，该维度是我们为你评分的基础维度，完善身份信息有助于更准确的信用分评估。</p></div>
              </div>
              <h2 style="text-align:left;font-size:18px;font-weight:bold">基础信息</h2>
              <el-descriptions title="" :column="2">
                <el-descriptions-item label="姓名">&emsp;&emsp;{{userName}}</el-descriptions-item>
                <el-descriptions-item label="邮箱">&emsp;&emsp;{{userEmail}}</el-descriptions-item>
              </el-descriptions>
              <h2 style="text-align:left;font-size:18px;font-weight:bold">就业信息</h2>
              <el-descriptions title="" :column="1">
                <el-descriptions-item label="年收入">&emsp;{{annual_income}}元</el-descriptions-item>
                <el-descriptions-item label="就业年限">{{working_years}}</el-descriptions-item>
                <el-descriptions-item label="职业">&emsp;&emsp;{{occupation_str}}</el-descriptions-item>
              </el-descriptions>
              <h2 style="text-align:left;font-size:18px;font-weight:bold">实名认证信息</h2>
              <el-descriptions title="" :column="1">
              <el-descriptions-item label="实名认证">{{authentication}}</el-descriptions-item>
              <el-descriptions-item label="证件号">&emsp;{{IDcard}}</el-descriptions-item>
              <el-descriptions-item label="证件类型">{{IDtype}}</el-descriptions-item>  
              </el-descriptions>
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
        getMyInfo() {
            this.$axios({
                method:"get",
                url: 'http://localhost:8899/user/userInfo',
                headers: { token:window.sessionStorage.getItem("token")}
            }).then(res=>{
                console.log('我的信息数据：', res.data);
                this.userName=res.data.data.userName;
                this.inputName=this.userName;
                this.userEmail=res.data.data.userEmail;
                this.inputEmail=this.userEmail;
                this.occupation=res.data.data.occupation;
                if(this.occupation==20000){
                this.occupation_str='其他';
                } else if(this.occupation==54120){
                this.occupation_str='文化、体育和娱乐业';
                } else if(this.occupation==61700){
                this.occupation_str='卫生和社会工作';
                } else if(this.occupation==56800){
                this.occupation_str='教育';
                } else if(this.occupation==50400){
                this.occupation_str='居民服务、修理和其他服务业';
                } else if(this.occupation==56000){
                this.occupation_str='水利、环境和公共设施管理业';
                } else if(this.occupation==82000){
                this.occupation_str='科学研究和技术服务业';
                } else if(this.occupation==59000){
                this.occupation_str='租赁和商务服务业';
                } else if(this.occupation==53000){
                this.occupation_str='房地产业';
                } else if(this.occupation==81000){
                this.occupation_str='信息传输、软件和信息技术服务业';
                } else if(this.occupation==39000){
                this.occupation_str='住宿和餐饮业';
                } else if(this.occupation==79000){
                this.occupation_str='交通运输、仓储和邮政业';
                } else if(this.occupation==57700){
                this.occupation_str='批发和零售业';
                } else if(this.occupation==57800){
                this.occupation_str='建筑业';
                } else if(this.occupation==100000){
                this.occupation_str='电力、热力、燃气及水生产和供应业';
                } else if(this.occupation==61000){
                this.occupation_str='制造业';
                } else if(this.occupation==82000){
                this.occupation_str='采矿业';
                }
                this.value=this.occupation.toString();
                this.annual_income=res.data.data.annual_income;
                this.inputIncome=this.annual_income;
                this.working_years=res.data.data.working_years;
                this.inputWorkYear=this.working_years;
                if(res.data.data.IDtype==1) {
                this.IDtype='身份证';
                } else if(res.data.data.IDtype==2) {
                this.IDtype='护照';
                } else {
                this.IDtype=res.data.data.IDtype;
                }
                this.IDcard=res.data.data.IDcard;
                if(res.data.data.authentication) {
                this.authentication="已实名";
                } else {
                this.authentication="未实名";
                }
                this.inputName1=this.userName;
                this.chooseIDtype=res.data.data.IDtype.toString();
                this.inputIDcard=this.IDcard;
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
</style>