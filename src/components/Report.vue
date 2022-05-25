<template>
<div>
  <div class="home" >
    <!-- 转Pdf的容器 -->
      <div id="pdf-container">
        <!-- <iframe src="/Report" width="1050" height="500">
        </iframe> -->
        <div class="report">
          <div>
            <ul id="top">
              <li class="index">报告编号： {{num}}</li>
              <li class="time">生成时间： {{date}}</li>
              </ul>
              </div>
            <div><p class="title">个人信用报告</p></div>
            <div class="info">
              <el-descriptions title="用户信息" border>
                <el-descriptions-item label="姓名">{{userName}}</el-descriptions-item>
                <el-descriptions-item label="证件类型">{{IDtype}}</el-descriptions-item>
                <el-descriptions-item label="身份证号">{{IDcard}}</el-descriptions-item>
              </el-descriptions>
            </div>
            <el-row>
              <div class="credit">
                <div><p class="subtitle">信用得分：{{score}}</p></div>
                <el-col :span="12">
                  <!-- 雷达图 -->  
                  <div class="grid-content bg-purple">
                    <div id="leiDaTu" class="echart" style="width: 300px;height: 300px;"></div>
                  </div>
                </el-col>
                <el-col :span="12">
                  <div class="detail">
                    <el-descriptions title="得分详情" :column="1" border>
                        <el-descriptions-item label="身份证明得分">{{idfication}}</el-descriptions-item>
                        <el-descriptions-item label="信贷记录得分">{{credit_histort}}</el-descriptions-item>
                        <el-descriptions-item label="资产证明得分">{{asset}}</el-descriptions-item>
                        <el-descriptions-item label="行为积累得分">{{behavior}}</el-descriptions-item>
                        <el-descriptions-item label="人脉关系得分">{{social}}</el-descriptions-item>
                    </el-descriptions>
                  </div>
                </el-col>
              </div>
            </el-row>
            <div class="credit">
             <p class="paragraph">信贷记录</p>
             <p class="tip">这部分包含您的信用卡信息和历史贷款信息，金额类型数据均以人民币计算</p>
              <el-descriptions title="重要记录" :column="1" border>
                <el-descriptions-item label="负债比率">{{DebtRatio}}</el-descriptions-item>
                <el-descriptions-item label="90天逾期次数">{{NumberOfTime90DaysLate}}</el-descriptions-item>
                <el-descriptions-item label="60-89天逾期次数">{{NumberOfTime60To89DaysPastDueNotWorse}}</el-descriptions-item>
                <el-descriptions-item label="35-55天逾期次数">{{NumberOfTime30To59DaysPastDueNotWorse}}</el-descriptions-item>
                <el-descriptions-item label="贷款数量">{{NumberOfOpenCreditLinesAndLoans}}</el-descriptions-item>
              </el-descriptions>
            </div>
            <div class="asset"><p class="paragraph">资产证明</p>
            <p class="tip">这部分包含您的信用卡信息和历史贷款信息，金额类型数据均以人民币计算</p>
            <el-descriptions title="资产记录" :column="1" border>
                <el-descriptions-item label="查询到存款">{{money}}</el-descriptions-item>
              </el-descriptions>
              </div>
            <div class="behavior"><p class="paragraph">行为积累</p>
            <p class="tip">这部分包含您的信用卡信息和历史贷款信息，金额类型数据均以人民币计算</p>
            <el-descriptions title="基本信息" :column="1" border>
                <el-descriptions-item label="志愿次数">{{activeVolunteer}}</el-descriptions-item>
                <el-descriptions-item label="捐款数额">{{activeDonation}}</el-descriptions-item>
                <el-descriptions-item label="献血次数">{{activeBlood}}</el-descriptions-item>
              </el-descriptions>
              <el-descriptions title="心理评分" :column="1" border>
                <el-descriptions-item label="人格特质">{{idfication}}</el-descriptions-item>
                <el-descriptions-item label="详细介绍">{{credit_histort}}</el-descriptions-item>
              </el-descriptions>
            </div>
            <div class="social"><p class="paragraph">人脉关系</p>
            <p class="tip">这部分包含您的信用卡信息和历史贷款信息，金额类型数据均以人民币计算</p>
            <el-descriptions  :column="1" border>
                <el-descriptions-item label="好友圈人数">{{socialnum}}</el-descriptions-item>
                <el-descriptions-item label="好友均分">{{socialsocre}}</el-descriptions-item>
              </el-descriptions>
            </div>
          <div class="rank"><p class="paragraph">评分等级</p>
          <div class="tip"><p>我们的信用评级分为六个等级，分别是Excellent(极优秀)、VeryGood(很优秀)、Good(良好)、Fair(一般)、Poor(较差)、VeryBad(很差)。不同的信用等级可以进行不同额度的贷款。</p></div>
          <div>
            <el-row>
                <el-col :span="4">
                    <div class="excellent">
                        <li style="list-style:none;font-size:20px;padding-top:25px"><span>excellent</span><p style="font-size:16px">430~500</p></li>
                        <li style="list-style:none" v-if="excellenthere"><i class="el-icon-location"></i>您的信用等级</li>
                        </div></el-col>
                <el-col :span="4">
                    <div class="veryGood">
                        <li style="list-style:none;font-size:20px;padding-top:25px"><span>veryGood</span><p style="font-size:16px">360~430</p></li>
                        <li v-if="verygoodhere" style="list-style:none"><i class="el-icon-location"></i>您的信用等级</li></div></el-col>
                <el-col :span="4">
                    <div class="good"><li style="list-style:none;font-size:20px;padding-top:25px"><span>good</span><p style="font-size:16px">290~360</p></li><li v-if="goodhere" style="list-style:none"><i class="el-icon-location"></i>您的信用等级</li></div></el-col>
                <el-col :span="4">
                    <div class="fair"><li style="list-style:none;font-size:20px;padding-top:25px"><span>fair</span><p style="font-size:16px">220~290</p></li><li v-if="fairhere" style="list-style:none"><i class="el-icon-location"></i>您的信用等级</li></div></el-col>
                <el-col :span="4">
                    <div class="poor"><li style="list-style:none;font-size:20px;padding-top:25px"><span>poor</span><p style="font-size:16px">150~220</p></li><li v-if="poorhere" style="list-style:none"><i class="el-icon-location"></i>您的信用等级</li></div></el-col>
                <el-col :span="4">
                    <div class="veryBad"><li style="list-style:none;font-size:20px;padding-top:25px"><span>veryBad</span><p style="font-size:16px">小于150</p></li><li v-if="verybadhere" style="list-style:none"><i class="el-icon-location"></i>您的信用等级</li></div></el-col>
                </el-row>
           <div class="quota"> {{quota}}</div>
          </div>
          </div>
        </div>
      </div>
      <button @click="getOnePdf()" class="downloadIcon" ></button>
  </div>
</div>
</template>
<script>
import html2Canvas from "html2canvas";
import JsPDF from "jspdf";
export default({
    data() {
      return{
        socialnum:'暂无好友',
        socialsocre:'暂无数据',
        quota:'',
        date:'',
        userName:'',
        IDcard:'',
        phoneNumber:'',
        score:0,
        IDtype:'',
        behavior:'',
        credit_histort:'',
        asset:'',
        idfication:'',
        social:'',
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
        money:0,
        activeBlood:0,
        activeDonation:0,
        activeVolunteer:0,
        num:'',
      }
    },
    methods:{
      // 雷达图作画
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
                    this.behavior = res.data.data.behavior_score
                    this.credit_histort = res.data.data.credit_score
                    this.idfication =  res.data.data.identity_score
                    this.asset = res.data.data.asset_score
                    this.social = res.data.data.social_score
                    if(this.score<=150){
                        this.verybadhere = true
                        this.quota="目前您的信用分较低，不建议您进行贷款"
                        }
                        else if(this.score<=220){
                        this.poorhere=true
                        this.quota="目前您的信用分较低，不建议您进行贷款"
                        }else if(this.score<=290){
                        this.fairhere=true
                        this.quota="目前您的信用分一般，推荐您的消费贷款额度在100元到5000元之间"
                        }else if(this.score<=360){
                        this.goodhere=true
                        this.quota="目前您的信用分良好，推荐您的消费贷款额度在500元到1000元之间"
                        }else if(this.score<=430){
                        this.verygoodhere=true
                        this.quota="目前您的信用分优秀，推荐您的消费贷款额度在1000元到5000元之间"
                        }else if(this.score<=500){
                        this.excellenthere=true
                        this.quota="您的信用等级为极优秀，您的推荐贷款额度为5000及以上"
                        console.log(this.excellenthere);
                        }
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
      showDate(){
        var today = new Date()
        var y = today.getFullYear()
        var mon = today.getMonth()+1
        var d = today.getDate()
        var h = today.getHours()
        var m = today.getMinutes()
        var clock = y + "-"
        if(mon<10){
          clock += "0"
        }
        clock += mon+ "-"
        if(d<10){
          clock+="0"
        }
        clock += d + " "
        if(h<10){
          clock+="0"
        }
        clock += h + ":"
        if(m<10)clock+="0"
        clock += m
        this.date = clock
      },
      // 单页pdf：css高度自适应即可（此处用的一个css，为了实现多页pdf同时不让分页分割图片，css中写死了每页的高度.a4page）
      getOnePdf() {
        var title = "个人信用报告";
        var dom = document.getElementById("pdf-container");  // 生成pdf的html内容
        window.pageYoffset = 0
        document.documentElement.scrollTop = 0
        document.body.scrollTop = 0
        html2Canvas(dom, {
          allowTaint: true,
          taintTest : false,
          useCORS: true, // 开启跨域
          width: dom.offsetWidth, 
          height: dom.offsetHeight
        }).then(function(canvas) {
          
          var contentWidth = canvas.width;
          var contentHeight = canvas.height;
          //var pdfWidth = (contentWidth + 10) / 2 * 0.65;
          var pdfHeight =contentWidth / 592.28 * 841.89;
        //未生成pdf的html页面高度
          var leftHeight = contentHeight;
          //页面偏移
          var position = 0;
          var imgWidth = 595.28;
          var imgHeight = 592.28 / contentWidth * contentHeight;
          var pageData = canvas.toDataURL("image/jpeg", 1.0);
          var pdf = new JsPDF("", "pt", 'a4');
          
          if (leftHeight < pdfHeight) {
              pdf.addImage(pageData, 'jpeg', 0, 0, imgWidth, imgHeight);
            } else {
              while (leftHeight > 0) {
                pdf.addImage(pageData, 'jpeg', 0, position,
                    imgWidth, imgHeight)
                leftHeight -= pdfHeight;
                position -= 841.89;
                //避免添加空白页
                if (leftHeight > 0) {
                  pdf.addPage();
                }
              }
            }
          //pdf.addImage(pageData, "jpeg", 0, 0, imgWidth, imgHeight);
          pdf.save(title + ".pdf");
        });
      },
      getUserInfo(){
        this.$axios({
          method:"get",
          url: 'http://localhost:8899/user/userInfo',
          headers: { token:window.sessionStorage.getItem("token")}
        }).then(res=>{
          console.log('我的信息数据：', res.data);
          this.userName=res.data.data.userName;
          if(this.userName!=null) {
            this.name=this.userName.substr(0,1);
          }
          if(res.data.data.IDtype==1) {
            this.IDtype='身份证';
          } else {
            this.IDtype=res.data.data.IDtype;
          }
          this.IDcard=res.data.data.IDcard;
        },err=>{
          console.log(err);
        })
      },
      getCreditHistory(){
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
      getAsset(){
        this.$axios({
          method:"get",
          url: 'http://localhost:8899/asset/getMoney',
          headers: { token:window.sessionStorage.getItem("token")}
        }).then(res=>{
          console.log("资产信息数据",res.data);
          console.log(res.data.data.money);
          this.money = res.data.data.money
        },err=>{
          console.log(err);
        })
      },
      getBehavior(){
        this.$axios({
          method:"get",
          url: 'http://localhost:8899/picture/getBlood',
          headers: { token:window.sessionStorage.getItem("token")}
        }).then(res=>{
          console.log('我的献血记录数据：', res.data);
          if(res.data.data.picState==0) {
            this.activeBlood=1;
          } else if(res.data.data.picState==1) {
            this.activeBlood=3;
          }
        },err=>{
          console.log(err);
        }),
        this.$axios({
          method:"get",
          url: 'http://localhost:8899/picture/getVolunteer',
          headers: { token:window.sessionStorage.getItem("token")}
        }).then(res=>{
          console.log('我的志愿服务记录数据：', res.data);
          if(res.data.data.picState==0) {
            this.activeVolunteer=1;
          } else if(res.data.data.picState==1) {
            this.activeVolunteer=3;
          }
        },err=>{
          console.log(err);
        }),
        this.$axios({
          method:"get",
          url: 'http://localhost:8899/picture/getDonation',
          headers: { token:window.sessionStorage.getItem("token")}
        }).then(res=>{
          console.log('我的捐款记录数据：', res.data);
          if(res.data.data.picState==0) {
            this.activeDonation=1;
          } else if(res.data.data.picState==1) {
            this.activeDonation=3;
          }
        },err=>{
          console.log(err);
        })
      },
      getPsycho(){

      },
      getSocial(){
      this.$axios({
          method:"get",
          url: 'http://localhost:8899/user/friends',
          headers: { token:window.sessionStorage.getItem("token")}
        }).then(res=>{

        })
      },
      initialSerial(){
        var num = "";   //定义用户编号
        for (var i = 0; i < 4; i++) //4位随机数，用以加在时间戳后面。
        {
            num += Math.floor(Math.random() * 10);
        }
        num = new Date().getTime() + num;  //时间戳，用来生成用户编号。
        this.num = num
      }

    },
    mounted(){
      this.drawPie('leiDaTu')
      this.showDate()
      this.getUserInfo()
      this.getCreditHistory()
      this.getAsset()
      this.getBehavior()
      this.getPsycho()
      this.getSocial()
      this.initialSerial()
    }
})
</script>
<style scoped>
.report{
  background-image: url(../../src/assets/print.png);
  background-size:50% 30%;
  background-color: #fff;
  min-width: 1000px;
  margin-left: 300px;
  margin-right: 300px;
  padding-top: 50px;
  padding-left: 20px;
  padding-right: 20px;
}
.echart{
  margin-left: 100px;
}
.detail{
  margin-right: 200px;
}
.paragraph{
  font-size:24px;
  text-align:center;
  background-color: #758bac;
  color: #fff;
  margin-top: 10px;
  padding: 10px;
}
.title{
  font-size: 36px;
  font-weight: bold;
  text-align: center;
  letter-spacing:5px;
  padding: 40px;

}
#top li{
  display: inline;
  font-size: 18px;
  color: #999999;
  margin-bottom: 20px;
  padding-top: 20px;
}
.index{
  text-align: left;
  padding-right: 200px;
}
.time{
  text-align: right;
}
.downloadIcon{
  background-color: #e5e5e5ad;
  background-image: url(../../src/assets/download.png);
  background-size:100% 100%;
  border: 0;
  height: 129px;
  width: 100px;
  cursor:pointer;
  margin: 20px;
}
.subtitle{
  font-size: 32px;
  font-weight: bold;
  padding: 40px;
}
>>>.el-descriptions__title {
    font-size: 24px;
    font-weight: 700;
}
.tip{
  background-color: #99999970;
  font-size: 18px;
  padding:5px;
  margin-bottom: 20px;
}
.home{
  background-color: #e5e5e5ad;
}
>>>.el-descriptions .is-bordered .el-descriptions-item__cell {
    border: 0.005208rem solid #212121;
    padding: 0.0625rem 0.052083rem;
    font-size: 20px;
}
>>>.el-descriptions-item__label.is-bordered-label {
    color: #373737;
    background: #fafafa;
    font-weight: 500;
}
.quota{
  font-size: 20px;
  text-align: left;
  padding-left: 20px;
  padding-top: 20px;
}
</style>