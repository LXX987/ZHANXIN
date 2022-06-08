<template>
        <!-- <el-main> -->
        <div>
            <el-row>
                <el-col :span="14">
                    <el-row>
                        <div class="HistoryBox">
                            <div v-for="(item, index) in HistoryList" :key="item.testID" class="HistoryItem">
                                <div>
                                    <el-col :span="8">
                                    <!-- 雷达图 -->
                                        <div id="radarChart" class="Echarts"></div>
                                        <el-divider></el-divider>
                                    </el-col>
                                    <el-col id="HistoryTitle" :span="16">
                                        <p id="title">历史测评{{index + 1}}</p>
                                        <p id="content">&emsp;&emsp;&emsp;&emsp;本次测试结果显示，您的总分为：{{item.testScore}}</p>
                                        <br><br><br>
                                        <el-col :span="6">
                                            <el-link>查看<i class="el-icon-view el-icon--right"></i> </el-link>
                                        </el-col>
                                        <el-col :span="18">
                                            <span id="testTime">{{item.testTime}}</span>
                                        </el-col>
                                        <el-divider id="eldivider"></el-divider>
                                    </el-col>
                                    
                                </div>
                            </div>
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
        </div>
    <!-- </el-main> -->
</template>
<style scoped>
#eldivider {
    margin-top: 77px;
    width: 130%;
}
#testTime {
    font-size: 17px;
    font-weight: 500;
    line-height: 28px;
}
#HistoryTitle {
    text-align: left;;
    width: 50%;
    height: 250px;
}
#title {
    font-size: 18px;
    font-weight: 550;
    margin-top: 3%;
}
#content {
    font-size: 17px;
    font-weight: 500;
    line-height: 45px;
}
.canvas {
    width: 200px;
    height: 200px;
}
.HistoryBox {
    margin-left: 5%;
    margin-top: 4%;
    width: 100%;
    height: 100%;
    /* border: 1px solid #bababa; */
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
    width: 200px;
    height: 200px;
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
    name: 'PsychologyHistory',
    data() {
        return {
            HistoryList: []
        }
    },
    mounted() {
        this.getHistoryRecord(),
        this.drawPie()
    },
    methods: {
        drawPie() {
            console.log('index');
            
            var echarts = require("echarts");
            var roseCharts = document.getElementsByClassName('Echarts');  // 对应地使用ByClassName
            // console.log(roseCharts.length);
            this.$axios({
                    method:"post",
                    url: 'http://localhost:8899/psychology/getHistory',
                    headers:{
                    token:window.sessionStorage.getItem("token")},
                }).then(res=>{
                    console.log(res.data.data);
                    var length = res.data.data.historyList.length;
            for(var i = 0; i< length; i++) {  // 通过for循环，在相同class的dom内绘制元素
                var myChart = echarts.init(roseCharts[i]);

                myChart.setOption({
                    tooltip: {},//提示层
                    legend: {
                        data: ['name1']
                    },
                    radar: {
                        // name: {
                        //     textStyle: {
                        //         color: '#fff', //字体颜色
                        //         backgroundColor: '#999', //背景色
                        //         borderRadius: 3, //圆角
                        //         padding: [3, 5] //padding
                        //     }
                        // },
                        center: ['50%', '45%'],
                        radius: 55,
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
                            value: [res.data.data.historyList[i].neuroticism, res.data.data.historyList[i].extraversion, res.data.data.historyList[i].openness, res.data.data.historyList[i].agreeableness, res.data.data.historyList[i].conscientiousness],
                            name: "测评得分"
                        }]
                    }]
                    })
                }
            })
            },
        getHistoryRecord() {
            this.$axios({
                method:"post",
                url: 'http://localhost:8899/psychology/getHistory',
                headers:{token:window.sessionStorage.getItem("token")},
                params:{
                }
            }).then(res=>{
                console.log(res);
                this.HistoryList = res.data.data.historyList;
            },err=>{
                console.log(err);
            })
        }
    }
}
</script>