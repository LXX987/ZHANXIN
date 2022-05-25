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
            <div class="nochoose-block"  @click="gotoLoan()">
              <img src='@/assets/pic_credit.png' class="icon">
              <p class="txt-nochoose">信贷历史</p>
            </div>
            <div class="nochoose-block" @click="gotoSocial()">
              <img src='@/assets/pic_reputation.png' class="icon">
              <p class="txt-nochoose">好友圈</p>
            </div>
            <div class="choose-block"  @click="gotoPossession()">
              <img src='@/assets/pic_assets1.png' class="icon">
              <p class="txt-choose">资产证明</p>
            </div>
          </div>
        </el-col>
        <el-col :span="22">
          <div class="grid-content bg-purple-light">
            <el-row class="bg-white">
                <div class="box">
                    <div class="intro">
                        <h1 style="text-align:left;font-size:20px;font-weight:bold">资产证明得分详情<el-button type="text" style="margin-left:10px" @click="gotoFinish()">去完善</el-button></h1>
                        <div class="tip"><p>资产证明是指你自主提交的资产相关数据，包括房产、车产等。该维度对信贷记录以及行为积累较少的用户的评分较为重要。</p></div>
                    </div>
                    <div>
                        <el-table :data="tableData" height="227" stripe class="table">
                          <el-table-column prop="bank" label="开户行" width="180"></el-table-column>
                          <el-table-column prop="money" label="资产数额" width="180"></el-table-column>
                        </el-table>
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
        tableData: [{
          userID: '',
          bank: '',
          money: '',
          addTime: ''
        }],
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
     this.getAsset()
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
        getAsset() {
        this.$axios({
          method:"get",
          url: 'http://localhost:8899/asset/assetList',
          headers: { token:window.sessionStorage.getItem("token")}
        }).then(res=>{
          console.log(res.data);
          this.tableData=res.data;
          for(var i=0;i<res.data.length;i++) {
            if(this.tableData[i].money==500) { this.tableData[i].money=="0-1000元"; }
            else if (this.tableData[i].money==2500) { this.tableData[i].money="1000-5000元"; }
            else if (this.tableData[i].money==7500) { this.tableData[i].money="5000-10000元"; }
            else if (this.tableData[i].money==25000) { this.tableData[i].money="1万-5万元"; }
            else if (this.tableData[i].money==75000) { this.tableData[i].money="5万-10万元"; }
            else if (this.tableData[i].money==150000) { this.tableData[i].money="10万-20万元"; }
            else if (this.tableData[i].money==350000) { this.tableData[i].money="20万-50万元"; }
            else if (this.tableData[i].money==750000) { this.tableData[i].money="50万-100万元"; }
            else if (this.tableData[i].money==1000000) { this.tableData[i].money="100万元及以上"; }
          }
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
    min-height: 630px;
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
  font-size:18px;
  font-weight: bold;
}
</style>