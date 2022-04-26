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
            <div class="choose-block"  @click="gotoBehavior()">
              <img src='@/assets/pic_report1.png' class="icon">
              <p class="txt-choose">行为积累</p>
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
                        <h1 style="text-align:left;font-size:20px;font-weight:bold">行为积累得分详情<el-button type="text" style="margin-left:10px" @click="gotoFinish()">去完善</el-button></h1>
                        <div class="tip"><p>行为积累是指你在移动支付、购物、生活缴费、信用卡还款、公益、心理测评等场景的汇总数据，该维度对信贷记录较少的用户评分非常重要。</p></div>
                        </div>
                        <div>
                            <p class="subtitle-txt">生活缴费记录</p>
                            <el-descriptions title="" border>
                            <el-descriptions-item label="水费">已按时缴费</el-descriptions-item>
                            <el-descriptions-item label="电费">已按时缴费</el-descriptions-item>
                            <el-descriptions-item label="话费">已按时缴费</el-descriptions-item>
                            </el-descriptions>
                            <p class="subtitle-txt">守约记录</p>
                            <el-descriptions title="" border class="tax-form">
                            <el-descriptions-item label="缴税情况">已正常纳税</el-descriptions-item>
                            </el-descriptions>
                            <p class="subtitle-txt">守法记录</p>
                            <el-descriptions title="" border class="tax-form">
                            <el-descriptions-item label="犯罪记录">{{crime}}</el-descriptions-item>
                            </el-descriptions>

                            <p class="subtitle-txt">公益</p>
                            <el-descriptions title="" border>
                            <el-descriptions-item label="献血">{{bloodDonation}}次</el-descriptions-item>
                            <el-descriptions-item label="志愿服务">{{volunteer}}小时</el-descriptions-item>
                            <el-descriptions-item label="捐款">{{donation}}元</el-descriptions-item>
                            <el-descriptions-item label="其他">暂无</el-descriptions-item>
                            </el-descriptions>
                        </div>
                    </div>
                    <div>
                        <h2 style="text-align:left;font-size:18px;font-weight:bold;padding-top: 10px;">心理测评</h2>
                        <div class="board" style="text-align:left">
                            <p class="psycho">测试结果：</p>
                            <p class="result">您的人格特性为：温柔善良、严谨冷静、有责任心</p>
                            <p class="psycho">人格特质：</p>
                            <el-descriptions title="" border>
                            <el-descriptions-item label="神经质">{{score1}}</el-descriptions-item>
                            <el-descriptions-item label="外向性">{{score2}}</el-descriptions-item>
                            <el-descriptions-item label="开放性">{{score3}}</el-descriptions-item>
                            <el-descriptions-item label="顺同性">{{score4}}</el-descriptions-item>
                            <el-descriptions-item label="严谨性">{{score5}}</el-descriptions-item>
                            </el-descriptions>
                        <p class="psycho">详细介绍：</p>
                        <p class="result">您的总分为：{{totalScore}}</p>
                        <p class="result">有较强的自我控制能力以及延迟需求满足的能力，行为规范可靠，有能力、有责任心；</p>
                        <p class="result">同时，重视和他人的和谐相处，因此体贴友好，大方乐于助人，愿意谦让，性格随和。</p>
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
.psycho{
    font-size: 20px;
    font-weight: bold;
    padding-top: 10px;
    margin-top: 30px;
    margin-bottom: 5px;
}
.result{
    font-size: 18px;
    margin-top: 10px;
}
</style>