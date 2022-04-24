<template>
    <!-- <el-main> -->
        <div>
            <el-row>
                <el-col :span="14">
                    <el-row class="measureHomeHead">
                        <span id="measureHomeHeading">测评结果</span>
                    </el-row>
                    <el-row class="psychologyIntro">
                        <el-col :span="14">
                            <div class="psychologyIntroWord">
                                <ul>
                                    <li>开放性（openness）：{{this.opennessScore}}分</li>
                                    <li>责任心（conscientiousness）：{{this.conscientiousnessScore}}分</li>
                                    <li>外倾性（extraversion）：{{this.extraversionScore}}分</li>
                                    <li>宜人性（agreeableness）：{{this.agreeablenessScore}}分</li>
                                    <li>神经质性（neuroticism）：{{this.neuroticismScore}}分</li>
                                </ul>
                            </div>
                        </el-col>
                        <el-col :span="10">
                            <div class="Echarts" id="resultEchart"></div>
                        </el-col>
                    </el-row>
                    <el-divider id="divider"></el-divider>
                    <el-row>
                        <div class="introBox">
                            <el-button id="startButton">开始测试</el-button>
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
#startButton {
    width: 115px;
    height: 50px;
    border-color: rgb(54, 185, 255);
    border-width: 1px;
    border-style: solid;
    border-radius: 4px;
    font-size: 14px;
    text-align: left;
    font-weight: normal;
    font-style: normal;
    position: absolute;
    left: 15%;
    top: 65%;
}
#divider {
    margin-left: 3%;
}
.introBox {
    height: 265px;
    width: 865px;
    background: url('../assets/4.png') no-repeat;
    background-size: 100% 100%;
    margin: 2% 7%;
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
    name: 'PsychologyHome',
    data() {
        return {
            opennessScore: 0,
            conscientiousnessScore: 0,
            extraversionScore: 0,
            agreeablenessScore: 0,
            neuroticismScore: 0,
            option: {
  color: ['#67F9D8', '#FFE434', '#56A3F1', '#FF917C'],
  title: {
    text: 'Customized Radar Chart'
  },
  legend: {},
  radar: [
    {
      indicator: [
        { text: 'Indicator1', max: 150 },
        { text: 'Indicator2', max: 150 },
        { text: 'Indicator3', max: 150 },
        { text: 'Indicator4', max: 120 },
        { text: 'Indicator5', max: 108 },
        { text: 'Indicator6', max: 72 }
      ],
      center: ['75%', '50%'],
      radius: 120,
      axisName: {
        color: '#fff',
        backgroundColor: '#666',
        borderRadius: 3,
        padding: [3, 5]
      }
    }
  ],
  series: [
    {
      type: 'radar',
      radarIndex: 1,
      data: [
        {
          value: [100, 93, 50, 90, 70, 60],
          name: 'Data D',
          areaStyle: {
            color: new echarts.graphic.RadialGradient(0.1, 0.6, 1, [
              {
                color: 'rgba(255, 145, 124, 0.1)',
                offset: 0
              },
              {
                color: 'rgba(255, 145, 124, 0.9)',
                offset: 1
              }
            ])
          }
        }
      ]
    }
  ]
}
    }
    },
    methods: {
        myEcharts(){
		  // 基于准备好的dom，初始化echarts实例
		  var myChart = this.$echarts.init(document.getElementById('resultEchart'));
		  myChart.setOption(this.option);
		  }
    },
    mounted() {
        // 在通过mounted调用即可
        this.myEcharts();
    }
}
</script>