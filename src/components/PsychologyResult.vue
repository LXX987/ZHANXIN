<template>
    <!-- <el-main> -->
        <div>
            <el-row>
                <el-col :span="14">
                    <el-row class="measureHomeHead">
                        <span id="measureHomeHeading">测评结果</span>
                    </el-row>
                    <el-row class="psychologyIntro">
                        <el-col :span="12">
                            <div class="psychologyIntroWord">
                                <ul>
                                    <li>开放性（openness）：{{opennessScore}}分</li>
                                    <li>责任心（conscientiousness）：{{conscientiousnessScore}}分</li>
                                    <li>外倾性（extraversion）：{{extraversionScore}}分</li>
                                    <li>宜人性（agreeableness）：{{agreeablenessScore}}分</li>
                                    <li>神经质性（neuroticism）：{{neuroticismScore}}分</li>
                                </ul>
                                <span>您的测评总分为：{{totalScore}}分</span>
                            </div>
                        </el-col>
                        <el-col :span="12">
                            <div id="radarChart" class="Echarts"></div>
                        </el-col>
                    </el-row>
                    <el-divider id="divider"></el-divider>
                    <el-row>
                        <div class="ResultContent">
                            <p>您的心理测评报告：</p>
                            <p>&ensp;&ensp;&ensp;&ensp;根据您的选择，我们分析您的性格是{{personality}}。</p>
                        </div>
                    </el-row>
                    <el-row>
                        <div id="Link"><el-link @click="openDetails">查看详情</el-link></div>
                    </el-row>
                    <el-row>
                        <div class="RateBlock">
                            <el-col id="RateTitle" :span="12"><span>请您为本次测评打分：&ensp;&ensp;&ensp;&ensp;</span></el-col>
                            <el-col id="Rateline" :span="12"><el-rate v-model="value1" show-score></el-rate></el-col>
                        </div>
                    </el-row>
                </el-col>
                <el-col :span="10">
                    <el-row>
                        <div class="Notice">
                            <el-row>
                                <span id="NoticeHeading">测评提示</span>
                            </el-row>
                            <el-divider></el-divider>
                            <el-row>
                                <ul>
                                    <li>性格乜有好与坏，测试的目的是反映最真实的自己。</li>
                                    <li>请最大程度放松下来，根据您的直觉进行测试</li>
                                    <li>我们会加密保存您的答案，保护您的隐私安全</li>
                                </ul>
                            </el-row>
                        </div>
                    </el-row>
                    <el-row>
                        <div class="recommend"></div>
                    </el-row>
                </el-col>
            </el-row>
            <div class="Details" v-show="popshow">
                <el-row>
                    <el-col :span="14">
                        <p id="userMessage">用户信息</p>
                    </el-col>
                    <el-col id="closeButton" :span="10">
                        <el-button icon="el-icon-close" circle @click="closeDetails"></el-button>
                    </el-col>
                </el-row>
                <el-divider></el-divider>
                <el-row>
                    <p>用户名：{{userName}}&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;测试时间：{{testTime}}</p>
                    <ul>
                        <li>开放性（openness）：{{opennessScore}}分</li>
                        <li>责任心（conscientiousness）：{{conscientiousnessScore}}分</li>
                        <li>外倾性（extraversion）：{{extraversionScore}}分</li>
                        <li>宜人性（agreeableness）：{{agreeablenessScore}}分</li>
                        <li>神经质性（neuroticism）：{{neuroticismScore}}分</li>
                    </ul>
                    <p>总分数：{{score}}分</p>
                </el-row>
            </div>
        </div>
    <!-- </el-main> -->
</template>
<style scoped>
#closeButton {
    text-align: right;
}
#userMessage {
    font-size: 20px;
    font-weight: 550;
}
.Details {
    position: absolute;
    left: 500px;
    top: 150px;
    width: 700px;
    height: 500px;
    box-shadow: 0 2px 12px 0 rgb(0 0 0 / 10%);
    border-radius: 4px;
    border: 1px solid #ebeef5;
    background-color: #fff;
    overflow: hidden;
    color: #303133;
    transition: .3s;
    padding: 2% 4%;
    text-align: left;
}
.Details li {
    font-size: 18px;
    line-height: 45px;
    margin-left: 3%;
}
.Details p {
    margin-top: 4%;
}
.Details ul {
    list-style-type: circle;
}
#Link {
    margin-top: 120px;
    text-align: right;
}
.el-link {
    font-size: 18px;
    font-weight: 500;
}
.RateBlock {
    margin-top: 30px;
    font-size: 17px;
    font-weight: 500;
}
#Rateline {
    text-align: left;
}
#RateTitle {
    text-align: right;
}
.ResultContent {
    text-align: left;
    margin-left: 8%;
    line-height: 28px;
    font-size: 18px;
    font-weight: 500;
}
.Echarts {
    width: 500px;
    height: 500px;
}
#resultEchart {
    width: auto;
    height: 400px;
}
#NoticeHeading {
    font-weight: 550;
    font-size: 20px;
    color: rgb(34, 34, 34);
    font-style: normal;
    letter-spacing: 0px;
    line-height: 19px;
    text-decoration: none;
}
.Notice ul{
    list-style: disc;
}
.Notice li{
    margin-bottom: 5px;
    line-height: 28px;
    padding-left: 3%;
    padding-right: 3%;
    margin-left: 5%;
    font-size: 17px;
}
.Notice {
    width: 342px;
    height: 313px;
    border-color: rgba(222, 222, 222, 0.8);
    border-width: 1px;
    border-style: solid;
    border-radius: 25px;
    /* font-size: 14px; */
    padding: 6% 5%;
    text-align: left;
    line-height: 20px;
    /* font-weight: normal;
    font-style: normal; */
    margin-left: 18%;
    margin-top: 6%;
}
.recommend {
    width: 342px;
    height: 456px;
    border-color: rgba(222, 222, 222, 0.8);
    border-width: 1px;
    border-style: solid;
    border-radius: 25px;
    font-size: 14px;
    padding: 0px;
    text-align: center;
    line-height: 20px;
    font-weight: normal;
    font-style: normal;
    margin-left: 18%;
    margin-top: 10%;
    background: url('../assets/recommendGroup.png') no-repeat;
    background-size: 100% 100%;
}
#divider {
    margin-left: 3%;
}
.measureHomeHead {
    margin-top: 3%;
    margin-bottom: 3%;
}
.psychologyIntroWord p {
    line-height: 2;
    margin-bottom: 5px;
    font-family: SourceHanSansSC;
    font-weight: 400;
    font-size: 18px;
}
.psychologyIntroWord {
    text-align: left;
    font-weight: normal;
    font-style: normal;
    padding: 5% 4%;
}
.psychologyIntroWord ul {
    list-style: disc;
}
.psychologyIntroWord span {
    line-height: 4;
    margin-bottom: 5px;
    font-family: SourceHanSansSC;
    font-weight: 400;
    font-size: 18px;
    margin-left: 8%;
}
.psychologyIntroWord li{
    line-height: 2;
    margin-bottom: 5px;
    font-family: SourceHanSansSC;
    font-weight: 400;
    font-size: 18px;
    margin-left: 12%;
}
#measureHomeHeading {
    font-weight: 550;
    font-size: 36px;
    color: rgb(16, 16, 16);
    font-style: normal;
    letter-spacing: 0px;
    line-height: 100%;
    text-decoration: none;
}
.NavMenu {
    margin-left: 50%;
    margin-top: 5%;
    width: 143px;
    height: 233px;
    border-color: rgba(222, 222, 222, 0.8);
    border-width: 1px;
    border-style: solid;
    border-radius: 25px 0px 0px 25px;
    font-size: 14px;
    padding: 0px;
    text-align: center;
    line-height: 20px;
    font-weight: normal;
    font-style: normal;
}
</style>
<script>
export default {
    name: 'PsychologyResult',
    data() {
        return {
            userName: '',
            testTime: '',
            popshow: 0,
            value1: 3,
            personality: '',
            personalityScore: 0,
            score: 0,
            totalScore: 0,
            opennessScore: 0,
            conscientiousnessScore: 0,
            extraversionScore: 0,
            agreeablenessScore: 0,
            neuroticismScore: 0
        }
    },
    methods: {
        closeDetails() {
            console.log('close');
            this.popshow = 0;
        },
        openDetails() {
            console.log('open');
            this.popshow = 1;
        },
        drawPie() {
          let charts = this.$echarts.init(document.getElementById('radarChart'));
                //charts.setOption(option);
                this.$axios({
                    method:"post",
                    url: 'http://localhost:8899/psychology/getResult',
                    headers:{
                    token:window.sessionStorage.getItem("token")},
                }).then(res=>{
                    console.log(res.data.data);
                    this.score = res.data.data.ResultScore.testScore
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
                        center: ['47%', '40%'],
                        radius: '65%',
                        startAngle: 270,
                        indicator: [{
                                name: '神经质性',
                                max: 100
                            },
                            {
                                name: '外倾性',
                                max: 100
                            },
                            {
                                name: '开放性',
                                max: 100
                            },
                            {
                                name: '宜人性',
                                max: 100
                            },
                            {
                                name: '责任心',
                                max: 100
                            }
                        ],
                    },
                    series: [{
                        name: '测试标题名字',
                        type: 'radar',
                        areaStyle: {normal: {}},
                        data: [{
                            value: [res.data.data.ResultScore.neuroticism, res.data.data.ResultScore.extraversion, res.data.data.ResultScore.openness, res.data.data.ResultScore.agreeableness, res.data.data.ResultScore.conscientiousness],
                            name: "测评得分"
                        }]
                    }]
                    });
                })
        },
        getResult() {
            this.$axios({
                method:"post",
                url: 'http://localhost:8899/psychology/getResult',
                headers:{token:window.sessionStorage.getItem("token")},
                params:{
                }
            }).then(res=>{
                console.log(res);
                this.testTime = res.data.data.ResultScore.testTime;
                this.totalScore = res.data.data.ResultScore.testScore;
                this.neuroticismScore = res.data.data.ResultScore.neuroticism,
                this.extraversionScore = res.data.data.ResultScore.extraversion;
                this.opennessScore = res.data.data.ResultScore.openness;
                this.agreeablenessScore = res.data.data.ResultScore.agreeableness;
                this.conscientiousnessScore = res.data.data.ResultScore.conscientiousness;
                this.max();
                console.log(this.personalityScore);
            },err=>{
                console.log(err);
            })
        },
        getUserName() {
            this.$axios({
                method:"get",
                url: 'http://localhost:8899/user/getUserName',
                headers:{token:window.sessionStorage.getItem("token")},
                params:{
                }
            }).then(res=>{
                console.log(res);
                this.userName = res.data.data.userName;
            })
        },
        max(){
            if(this.opennessScore > this.personalityScore) {
                this.personalityScore = this.opennessScore;
            }
            if(this.extraversionScore > this.personalityScore) {
                this.personalityScore = this.extraversionScore;
            }
            if(this.conscientiousnessScore > this.personalityScore) {
                this.personalityScore = this.conscientiousnessScore;
            }
            if(this.agreeablenessScore > this.personalityScore) {
                this.personalityScore = this.agreeablenessScore;
            }
            if(this.neuroticismScore > this.personalityScore) {
                this.personalityScore = this.neuroticismScore;
            }
            switch(this.personalityScore){
                case(this.neuroticismScore):this.personality="神经质性（neuroticism）";break;
                case(this.opennessScore):this.personality="开放性（openness）";break;
                case(this.extraversionScore):this.personality="外倾性（extraversion）";break;
                case(this.conscientiousnessScore):this.personality="责任心（conscientiousness）";break;
                case(this.agreeablenessScore):this.personality="宜人性（agreeableness）";break;
                    
            }
        }
    },
    mounted() {
        console.log(this.$route.path)
        this.getResult()
        this.getUserName()
        this.drawPie('radarChart')
    }
}
</script>