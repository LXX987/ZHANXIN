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
            <div class="choose-block" @click="gotoSocial()">
              <img src='@/assets/pic_reputation1.png' class="icon">
              <p class="txt-choose">好友圈</p>
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
                <h1 style="text-align:left;font-size:20px;font-weight: bold;">人脉圈得分详情<el-button type="text" style="margin-left:10px" @click="gotoFinish()">去完善</el-button></h1>
                 <div class="tip"><p>人脉圈是指你通过邀请码授权的好友关系。该维度用于帮助你获得很好的信用评价。</p></div>
              </div>
              <h2 class="subheadline">邀请人数</h2>
              <el-descriptions title="" :column="1">
                <el-descriptions-item label="人数">&emsp;&emsp;{{num}}</el-descriptions-item>
              </el-descriptions>
              <h2 class="subheadline">好友平均信用得分</h2>
              <el-descriptions title="" :column="1">
                <el-descriptions-item label="好友平均信用分">&emsp;{{avgScore}}</el-descriptions-item>
              </el-descriptions>
              <!-- <h2 class="subheadline">我的好友</h2> -->
              <!-- <el-row>
                <el-col :span="4">
                  <div >
                  <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
                  <p>张三 450分</p>
                  </div>
                </el-col>
                <el-col :span="4">
                  <div >
                    <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
                    <p>李四 350分</p>
                  </div>
                </el-col>
                <el-col :span="4">
                  <div>
                    <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
                    <p>张伟 500分</p>
                  </div>
                </el-col>
                <el-col :span="4">
                  <div>
                    <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
                    <p>王五 435分</p>
                    
                  </div>
                </el-col>
              </el-row> -->
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
        num:'暂无信息',
        avgScore:'暂无信息',
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
    min-height: 660px;
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
    font-size:18px;
}
.subheadline{
    text-align:left;
    font-size:20px;
    font-weight: bold;
}
</style>