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
            <div class="choose-block">
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
              <el-col :span="12">
                <div class="grid-content bg-white">
                  <p class="txt-title">信用分</p>
                  <el-tooltip class="item" effect="dark" content="信用分总分" placement="right">
                  <el-button type="text" class="sub_score">总分：{{score}}</el-button></el-tooltip>
                  <div id="leiDaTu" class="echart"></div>
                </div>
              </el-col>
              <el-col :span="12">
                <div class="grid-content bg-white mar-left">
                  <p class="txt-title">信用分解读<el-button type="text" class="btn-text" @click="CreditAppeal()">有疑问？点击申诉</el-button></p>
                  <el-tooltip class="item" effect="dark" content="信用分各维度得分" placement="right">
                      <el-button type="text" class="sub_score">总分：{{score}}</el-button></el-tooltip>
                  <p class="txt-score">身份证明：{{idfication}}</p>
                  <p class="txt-score">资产证明：{{asset}}</p>
                  <p class="txt-score">信贷记录：{{credit_histort}}</p>
                  <p class="txt-score">行为积累：{{behavior}}</p>
                  <p class="txt-score">人脉关系：{{social}}</p>
                </div>
              </el-col>
            </el-row>
            <el-row class="bg-white mar-top">
              <p class="txt-title">贷款等级</p>
              <p class="txt-content">贷款等级：{{rank}}</p>
              <p class="txt-content">建议贷款额度：{{quota}}</p>
              <el-button @click="goto_detail()">查看详细报告</el-button>
            </el-row>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
  name: 'MyReport',
  mounted:function(){
    this.drawPie('leiDaTu')
  },
    data() {
      return {
        house:'0',
      car:'0',
      bill:'暂无数据',
      dialogTableVisible: false,
            score:0,
            gridData:[],
            nameofsearch:'',
            idofsearch:'',
            show:false,
            searchresult:'',
            behavior:'',
            credit_histort:'',
            asset:'',
            idfication:'',
            social:'',
            tableData: [],
            excellenthere:false,
            verygoodhere:false,
            goodhere:false,
            fairhere:false,
            poorhere:false,
            verybadhere:false,
            rank:'',
            quota:'暂无数据',
      };
    },
    methods: {
      drawPie() {
        let charts = this.$echarts.init(document.getElementById('leiDaTu'));
        //charts.setOption(option);
        this.$axios({
            method:"get",
            url: 'http://localhost:8899/credit/userCredit',
            headers:{
            token:window.sessionStorage.getItem("token")},
        }).then(res=>{
            console.log(res.data.data.asset_score);
            this.score=res.data.data.total_score
            if(this.score<=150){
            this.verybadhere = true
              this.rank = 'VeryBad(很差)'
              this.quota="目前您的信用分较低，不建议您进行贷款"
            }
            else if(this.score<=220){
              this.poorhere=true
              this.rank = 'Poor(较差)'
              this.quota="目前您的信用分较低，不建议您进行贷款"
            }else if(this.score<=290){
              this.fairhere=true
              this.rank = 'Fair(一般)'
              this.quota="目前您的信用分一般，推荐您的消费贷款额度在100元到500元之间"
            }else if(this.score<=360){
              this.goodhere=true
              this.rank = 'Good(良好)'
              this.quota="目前您的信用分良好，推荐您的消费贷款额度在500元到1000元之间"
            }else if(this.score<=430){
              this.verygoodhere=true
              this.rank = 'VeryGood(很优秀)'
              this.quota="目前您的信用分优秀，推荐您的消费贷款额度在1000元到5000元之间"
            }else if(this.score<=500){
              this.excellenthere=true
              this.rank = 'Excellent(极优秀)'
              this.quota="您的信用等级为极优秀，您的推荐贷款额度为5000及以上"
              console.log(this.excellenthere);
            }
            this.behavior = res.data.data.behavior_score
            this.credit_histort = res.data.data.credit_score
            this.idfication =  res.data.data.identity_score
            this.asset = res.data.data.asset_score
            this.social = res.data.data.social_score
            charts.setOption({
            tooltip: {},//提示层
            legend: {
                data: ['name1']
            },
            radar: {
                name: {
                    textStyle: {
                        color: '#fff', //字体颜色
                        backgroundColor: '#999', //背景色
                        borderRadius: 3, //圆角
                        padding: [3, 5] //padding
                    }
                },
                center: ['50%', '50%'],
                radius: '60%',
                startAngle: 270,
                indicator: [{
                        name: '身份证明',
                        max: 100
                    },
                    {
                        name: '资产证明',
                        max: 100
                    },
                    {
                        name: '信贷记录',
                        max: 100
                    },
                    {
                        name: '行为积累',
                        max: 100
                    },
                    {
                        name: '人脉圈',
                        max: 100
                    }
                ],
            },
            series: [{
                name: '测试标题名字',
                type: 'radar',
                areaStyle: {normal: {}},
                data: [{
                    value: [res.data.data.identity_score,res.data.data.asset_score,res.data.data.credit_score,res.data.data.behavior_score,res.data.data.social_score],
                    name: "信用得分"
                }]
            }]
          });
        })
      },
      goto_detail() {
        this.$router.push({path: '/AnswerSecQue'});
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
      CreditAppeal() {
        this.$router.push({path: '/CreditAppeal'});
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
  padding-bottom: 40px;
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
  .el-row {
    /* margin-bottom: 20px; */
  }
  .el-col {
    border-radius: 20px;
  }
  .bg-purple {
    background: #1f7c69;
    padding-top: 20px;
    padding-bottom: 20px;
    height: 965px;
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
    padding: 45px 30px;
    border-radius: 20px;
  }
  .txt-title {
    font-size: 28px;
    font-weight: bold;
    color: #1F7C69;
    text-align: left;
    margin-left: 50px;
  }
  .btn-text {
    font-size: 16px;
    margin-left: 200px;
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
  .mar-left {
    margin-left: 10px;
    height: 678px;
  }
  .sub-title {
    text-align: left;
    font-size: 20px;
    font-weight: bold;
    color: #52AD9C;
    margin-top: 30px;
    margin-bottom: 5px;
    margin-left: 50px;
  }
  .txt-content {
    text-align: left;
    font-size: 17px;
    margin-top: 20px;
    margin-left: 50px;
  }
  .el-input {
    width: 80%;
  }
  .el-select {
    width: 80%;
  }
  .sub_score{
    font-size: 38px;
    font-weight: bold;
    color: #303133;
  }
  .echart{
    margin-left: 0px;
    width: 480px;
    height: 480px;
  }
  .txt-score {
    font-size: 17px;
    margin-top: 20px;
  }
</style>