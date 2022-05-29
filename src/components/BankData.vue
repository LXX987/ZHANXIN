<template>
  <div class="body">
    <BankGuideBar></BankGuideBar>
    <div class="main">
      <el-row :gutter="20">
        <el-col :span="4">
          <div class="grid-content bg-purple left">
            <p class="nochoose-txt" @click="BankMain()">概况统计</p>
            <p class="choose-txt">信用数据</p>
            <p class="nochoose-txt" @click="BankSearch()">搜索列表</p>
          </div>
        </el-col>
        <el-col :span="20">
          <div class="grid-content">
            <el-row class="bg-purple">
              <el-col :span=20>
                <p class="title-txt">信用数据</p>
              </el-col>
              <el-col :span=4>
                <p class="tip-txt">数据每日00:00更新</p>
              </el-col>
            </el-row>
            <el-row class="bg-purple" id="condition">
              <!-- 用户数量-->
              <!-- 总体信用变化情况（上升多少）-->
              <el-col :span="12">
                <el-row><h1>用户数量</h1></el-row>
                <el-row><p>{{NumberOfPerple}}</p></el-row>
              </el-col>
              <el-col :span="12">
                <el-row><h1>分数变动</h1></el-row>
                <el-row><p>{{change}}</p></el-row>
              </el-col>
            </el-row>
            <el-row class="bg-purple">
              <el-col :span="12"><div id="PieChart"></div></el-col>
              <el-col :span="12"><div id="BarChart"></div></el-col>
            </el-row>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import BankGuideBar from './BankGuideBar.vue';
import * as echarts from 'echarts';
export default {
  name: 'BankData',
  components: { BankGuideBar },
  data() {
    return {
      num1822: 0,
      num2225: 0,
      num2530: 0,
      num30: 0,
      unauthentication: 0,
      NumberOfPerple: 0,
      change: 0,
       excellentCount: 0,
       veryGoodCount: 0,
       goodCount: 0,
       fairCount: 0,
       poorCount: 0,
       veryBadCount: 0
    }
  },
  mounted:function() {
    this.getPie();
    this.getBar();
    this.getScoreData();
  },
  methods: {
    getBar() {
      var myChart = echarts.init(document.getElementById('BarChart'))
      this.$axios({
          method:"post",
          url: 'http://localhost:8899/user/getUsersAges',
        }).then(res=>{
          console.log(res);
          this.unauthentication = res.data.data.unauthentication;
          this.num1822 = res.data.data.num1822;
          this.num2225 = res.data.data.num2225;
          this.num2530 = res.data.data.num2530;
          this.num30 = res.data.data.num30;
          myChart.setOption({
			title: { 
        text: '年龄分布情况', 
        x: 'center', 
      },
			tooltip: {},
			xAxis: {
			data: ["18-22岁","22-25岁","25-30岁","30岁以上","未实名认证"]
			},
			yAxis: {},
			series: [{
			name: '人数',
			type: 'bar',
			data: [this.num1822, this.num2225, this.num2530, this.num30, this.unauthentication]
			}]
			});})
    },
    getPie() {
      // 绘制图表
      var myChart = echarts.init(document.getElementById('PieChart'))
      this.$axios({
          method:"get",
          url: 'http://localhost:8899/credit/getAllCredit',
        }).then(res=>{
          console.log(res.data);
          this.NumberOfPerple = res.data.data.totalCount;
          this.excellentCount = res.data.data.excellentCount;
          this.veryGoodCount = res.data.data.veryGoodCount;
          this.goodCount = res.data.data.goodCount;
          this.fairCount = res.data.data.fairCount;
          this.poorCount = res.data.data.poorCount;
          this.veryBadCount = res.data.data.veryBadCount;
      // 指定图表的配置项和数据
      var option = {
        //标题
        title: {
          text: '信用分数统计',
          x: 'center' ,              //标题位置
          // textStyle: { //标题内容的样式
          //   color: '#000',
          //   fontStyle: 'normal',
          //   fontWeight: 100,
          //   fontSize: 16 //主题文字字体大小，默认为18px
          // },
        },
        stillShowZeroSum: true,
        //鼠标划过时饼状图上显示的数据
        tooltip: {
          trigger: 'item',
          formatter: '{a}<br/>{b}:{c} ({d}%)'
        },
        //图例
        legend: {//图例  标注各种颜色代表的模块
          // orient: 'vertical',//图例的显示方式  默认横向显示
          bottom: 10,//控制图例出现的距离  默认左上角
          left: 'center',//控制图例的位置
          // itemWidth: 16,//图例颜色块的宽度和高度
          // itemHeight: 12,
          textStyle: {//图例中文字的样式
            color: '#000',
            fontSize: 16
          },
          data: ['excellent', 'veryGood', 'good', 'fair', 'poor', 'veryBad']//图例上显示的饼图各模块上的名字
          /* excellent430~500  * veryGood360~430   * good290~360   * fair220~290
        * poor150~220   * veryBad小于150*/
        },
        //饼图中各模块的颜色
        color: ['#32dadd', '#b6a2de', '#5ab1ef'],
        // 饼图数据
        series: {
          // name: 'bug分布',
          type: 'pie',             //echarts图的类型   pie代表饼图
          radius: '70%',           //饼图中饼状部分的大小所占整个父元素的百分比
          center: ['50%', '50%'],  //整个饼图在整个父元素中的位置
          // data:''               //饼图数据
          data: [                  //每个模块的名字和值
            { name: 'excellent', value: this.excellentCount },
            { name: 'veryGood', value: this.veryGoodCount },
            { name: 'good', value: this.goodCount },
            { name: 'fair', value: this.fairCount },
            { name: 'poor', value: this.poorCount },
            { name: 'veryBad', value: this.veryBadCount }
          ],
          itemStyle: {
              label: {
                show: true,//饼图上是否出现标注文字 标注各模块代表什么  默认是true
                // position: 'inner',//控制饼图上标注文字相对于饼图的位置  默认位置在饼图外
              },
              labelLine: {
                show: true//官网demo里外部标注上的小细线的显示隐藏    默认显示
              }
          },
        }
      }
      // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option);
      },err=>{
          console.log(err);
        })
    },
    getScoreData() {
      this.$axios({
          method:"get",
          url: 'http://localhost:8899/credit/getAllCredit',
        }).then(res=>{
          console.log(res.data);
          this.NumberOfPerple = res.data.data.totalCount;
        },err=>{
          console.log(err);
        })
    },
    BankMain() {
      this.$router.push({path: '/BankMain'});
    },
    BankSearch() {
      this.$router.push({path: '/BankSearch'});
    },
    BankData() {
      this.$router.push({path: '/BankData'});
    }
  },
}
</script>

<style scoped>
#condition h1 {
  font-size: 20px;
  font-weight: 550;
}
#condition p {
  font-size: 20px;
}
#BarChart {
  width:100%;
  height:500px;
}
#PieChart {
  width:100%;
  height:500px;
}
.body {
  background-color: #f2f2f2;
}
.main {
  padding: 50px;
}
.el-row {
  margin-bottom: 20px;
}
.el-col {
  border-radius: 4px;
}
.bg-purple {
  background: #ffffff;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.left {
  height:750px;
}
.choose-txt {
  color: #0079FE;
  font-size: 28px;
  padding-top: 20px;
  padding-bottom: 20px;
  font-weight: bold;
  background: #E6F2FF;
  border-right-style:solid;
  border-right-color:#0079FE;
}
.nochoose-txt {
  color: #101010;
  font-size: 28px;
  padding-top: 20px;
  padding-bottom: 20px;
  font-weight: bold;
}
.nochoose-txt:hover {
  background: #f2f8ff;
}
.title-txt {
  text-align: left;
  font-size: 24px;
  color: #101010;
  margin: 20px;
  padding-left: 60px;
  border-left-style:solid;
  border-left-color:#0079FE;
}
.tip-txt {
  font-size: 14px;
  color: #039BE5;
  margin-top: 25px;
}
</style>