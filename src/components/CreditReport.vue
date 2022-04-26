<template>
<div>
   <div class="content">  
        <!-- 信用分部分 -->
      <div class="creditSearch">
        <div><h1>我目前的信用分</h1></div>
            <el-card class="bodycard">
                <el-row :gutter="18">
                    <el-col :span="10">
                        <!-- 雷达图 -->
                        <div class="grid-content bg-purple">
                            <el-tooltip class="item" effect="dark" content="总分" placement="right">
                                <el-button type="text" @click="goto_detail" class="sub_score">{{score}}</el-button>
                            </el-tooltip>
                            <div id="leiDaTu" class="echart" style="width: 300px;height: 300px;">
                            </div>
                            <el-button>下载报告</el-button>
                        </div>
                    </el-col>
                    <el-col :span="12" style="padding-left:50px;padding-top:50px">
                        <!-- 信用分数解读 -->
                        <div class="score_detail"> 
                            <el-row>
                                <el-col :span="10"><el-card class="square" @click.native="gotoBehavior()"><div class="behavior">行为积累方面：{{behavior}}分</div><img class="right" src="@/assets/right.png"/></el-card></el-col>
                                <el-col :span="10"><el-card class="square" @click.native="gotoLoan()"><div class="credit_histort" >信贷记录方面：{{credit_histort}}分</div><img class="right" src="@/assets/right.png"/></el-card></el-col>
                            </el-row>  
                            <el-row>
                                <el-col :span="10"><el-card class="square" @click.native="gotoPossession()"><div class="asset" >资产证明方面：{{asset}}分</div><img class="right" src="@/assets/right.png"/></el-card></el-col>
                                <el-col :span="10"><el-card class="square" @click.native="gotoScoreDetail()"><div class="asset" >身份证明方面：{{idfication}}分</div><img class="right" src="@/assets/right.png"/></el-card></el-col>
                            </el-row> 
                            <el-row>
                                <el-col :span="10"><el-card class="square" @click.native="gotoSocial()"><div class="asset">人脉圈方面：{{social}}分</div><img class="right" src="@/assets/right.png"/></el-card></el-col>
                                <el-col :span="10"><el-card class="square"><div class="asset">总分：{{score}}分</div></el-card></el-col>
                            </el-row>       
                        </div>
                    </el-col>
                </el-row>
            </el-card>
        </div>

        <div class="ratelevel">
            <h1>评分依据</h1>
            <el-card class="bodycard">
                <div class="proportion">
                    <img style="width:110%" src="@/assets/proportion.png"/>
                </div>
                <div class="instruction">
                    <div>&ensp;&ensp;&ensp;&ensp;瞻信网融合多学科领域交叉知识，分析普惠金融市场现状，制定针对20~25岁金融弱势信贷白户群体的创新评估指标，降低普惠金融对信用背书的依赖。在传统信用评估指标的基础上，加入以下创新性指标。</div>
                    <div>&ensp;&ensp;&ensp;&ensp;瞻信网采用目前最新的信用评估手段——“大数据风控”，将多维度不同角度的信息数据引入到信贷信用风险评估中，利用统计学计算，分析不同数据特征与信贷信用风险评估目标存在的数据关联关系，说明关联背后的因果关系，确立数据使用的合理性和合规性。</div>
                </div>
            </el-card>
        </div>
        <!-- 信用等级部分 -->
         
        <div class="ratelevel">
            <h1>我的信用等级</h1>
            <el-card class="bodycard">
            <div class="tip"><p>&ensp;&ensp;&ensp;&ensp;我们的信用评级分为六个等级，分别是Excellent(极优秀)、VeryGood(很优秀)、Good(良好)、Fair(一般)、Poor(较差)、VeryBad(很差)。不同的信用等级可以进行不同额度的贷款。</p></div>
            <div class="rank">
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
            </div>
            <div class="quota">
                {{quota}}
            </div>
            </el-card>
        </div>
    
   </div>
</div>
</template>
<script>
  export default {
    name:'CreditReport',
   data(){  
         return{
            quota:'暂无数据',
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
         }
     },
     methods:{
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
        // 雷达图作画
      drawPie() {
          let charts = this.$echarts.init(document.getElementById('leiDaTu'));
                //charts.setOption(option);
                this.$axios({
                    method:"get",
                    url: 'http://localhost:8888/credit/userCredit',
                    headers:{
                    token:window.sessionStorage.getItem("token")},
                }).then(res=>{
                    console.log(res.data.data.asset_score);
                    this.score=res.data.data.total_score
                    if(this.score<=150){
                        this.verybadhere = true
                        }
                        else if(this.score<=220){
                        this.poorhere=true
                        this.quota="目前您的信用分较低，不建议您进行贷款"
                        }else if(this.score<=290){
                        this.fairhere=true
                        this.quota=""
                        }else if(this.score<=360){
                        this.goodhere=true
                        this.quota=""
                        }else if(this.score<=430){
                        this.verygoodhere=true
                        this.quota=""
                        }else if(this.score<=500){
                        this.excellenthere=true
                        this.quota=""
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
     },
     mounted() {
        this.drawPie('leiDaTu')    
        }
  }
</script>
<style lang="css" scoped>
.rank{
    padding-left: 180px;
    padding-right: 180px;
}
.excellent{
    height: 200px;
    background-color: #57f1a9;
}
.veryGood{
     height: 200px;
    background-color: #74e25f;
}
.good{
     height: 200px;
    background-color: #f0ed5c;
}
.fair{
    height: 200px;
    background-color: #fab151;
}
.poor{
    height: 200px;
    background-color: #f78133;
}
.veryBad{
     height: 200px;
    background-color: #F44646;
}
.sub_title{
    font-size: 28px;
    font-weight: bold;
    color: #303133;
    text-align: left;
}
.sub_score{
    font-size: 38px;
    font-weight: bold;
    color: #303133;
}
 ul li::marker{
            color: #6969FC;
            font-size: 40px;
        }
ul{
    text-align: left;
}
li{
    font-size: 18px;
}
.score_detail{
    text-align: left;
}
.line{
 	background:#6969FC;/*背景色为浅灰色*/
	width:112px;/*设置宽高*/
	height:3px;
    position: relative;
    text-align: center;
}

.echart{
    margin-left: 30px;
}
.search{
    text-align: left;
    margin-left:50px;
    margin-top: 50px;
    margin-bottom: 100px;
}
.content{
    padding-top: 20px;
    background-color: #F7F9FB;
}
.tip {
    font-size: 18px;
    text-align: left;
    color: #606266;
    padding-left: 2%;
    padding-top: 2%;
    padding-right: 2%;
    line-height:1.8em;
}
.rate{
    background-color: #fff;
}
.illu{
    height:150px;
    text-align: left;
}
.title{
    color:#fff;
    font-size: 48px;
    font-weight:bold;
    margin-top: 0px;
    margin-left: 220px;
    line-height:150px;
}
h1{
    padding-top: 30px;
    padding-bottom: 30px;
    padding-left: 1%;
    margin-left: 15%;
    margin-right: 15%;
    background-color: #BFDDE3;
    font-weight: bold;
    font-size: 20px;
    text-align: left;
}
.creditSearch{
    margin-left: 50px;
}
.ratelevel{
    margin-left: 50px;
}
.result{
    margin-top: 20px;
}
.span{
    font-size: 20px;
}
.square{
    font-size: 16px;
    width: 200px;
    height: 100px;
    background-color: #BFDDE3;
    margin-bottom: 20px;
}
.bodycard{
    margin-left: 15%;
    margin-right: 15%;
    padding: 0;
    
}
.instruction{
    text-align: left;
    font-size: 18px;
    color: #606266;
    padding-left: 2%;
    padding-top: 2%;
    padding-right: 2%;
    line-height:1.8em;
}
.quota{
    text-align: left;
    font-size: 18px;
    color: #606266;
    padding-left: 2%;
    padding-top: 2%;
    padding-right: 2%;
    line-height:1.8em;
}
.proportion{
    width: 1000px;
}
.right{
    height:50px;
    width: 50px;
    margin-left: 70%;
    cursor: pointer;
}
</style>