<template>
    <el-container>
        <el-main>
            <div class="imageBlock">
                <el-carousel :interval="2000" arrow="always" :height="bannerH+'px'">
                    <el-carousel-item v-for="(bitem,bindex) in images" :key="bindex">
                    <div class="carousel">
                        <img class="bannertt" :src="bitem.url" >
                    </div>
                </el-carousel-item>
                </el-carousel>
            </div>
            <div class="assistantBlock">
                <el-row>
                    <el-row><img id="assistantImg" src="@/assets/assistantGroup.png"></el-row>
                    <el-row id="assistantMain">
                        <el-col :span="2"><div class="whiteassistantBlock"></div></el-col>
                        <el-col :span="5">
                            <div class="assistantPiece"><el-card shadow="hover"><img class="assistantPieceImg" src="@/assets/read.png"><div class="assistantWord">信用贷款</div></el-card></div>
                        </el-col>
                        <el-col :span="5">
                            <div class="assistantPiece"><el-card shadow="hover"><img class="assistantPieceImg" src="@/assets/shopping.png"><div class="assistantWord">小额消费贷</div></el-card></div>
                        </el-col>
                        <el-col :span="5">
                            <div class="assistantPiece"><el-card shadow="hover"><img class="assistantPieceImg" src="@/assets/work.png"><div class="assistantWord">信用评估</div></el-card></div>
                        </el-col>
                        <el-col :span="5">
                            <div class="assistantPiece"><el-card shadow="hover"><img class="assistantPieceImg" src="@/assets/car.png"><div class="assistantWord">金融教育</div></el-card></div>
                        </el-col>
                        <el-col :span="2"><div class="whiteassistantBlock"></div></el-col>
                    </el-row>
                    <br><br>
                </el-row>
            </div>
            <div class="creditEvaluateGroup">
                <el-row>
                    <el-row><img id="evaluateImg" src="@/assets/creditEvaluateGroup.png"></el-row>
                    <el-row id="assistantMain">
                        <el-col :span="2"><div class="whiteassistantBlock"></div></el-col>
                        <el-col :span="4">
                            <div class="assistantPiece"><el-card class="evaluateCard" shadow="hover"><img class="assistantPieceImg" src="@/assets/evaluate1.gif"></el-card></div>
                        </el-col>
                        <el-col :span="4">
                            <div class="assistantPiece"><el-card class="evaluateCard" shadow="hover"><img class="assistantPieceImg" src="@/assets/evaluate2.gif"></el-card></div>
                        </el-col>
                        <el-col :span="4">
                            <div class="assistantPiece"><el-card class="evaluateCard" shadow="hover"><img class="assistantPieceImg" src="@/assets/evaluate3.gif"></el-card></div>
                        </el-col>
                        <el-col :span="4">
                            <div class="assistantPiece"><el-card class="evaluateCard" shadow="hover"><img class="assistantPieceImg" src="@/assets/evaluate4.gif"></el-card></div>
                        </el-col>
                        <el-col :span="4">
                            <div class="assistantPiece"><el-card class="evaluateCard" shadow="hover"><img class="assistantPieceImg" src="@/assets/evaluate5.gif"></el-card></div>
                        </el-col>
                        <el-col :span="2"><div class="whiteassistantBlock"></div></el-col>
                    </el-row>
                    <br><br><br><br>
                </el-row>
            </div>
            <div class="dataSourceBlock">
                <el-row>
                    <el-col :span="5"><img id="dataSourceImg" src="@/assets/dataSourceGroup.png"></el-col>
                    <el-col :span="19">
                        <div class="or-container">
                            <div class="box" :class="eleindex ==i?'eleactive':''" v-for="(ele,i) in piclist" :key="i" @mouseenter="enter(i)" @mouseleave="out(i)">
                                <img :src="ele.img">
                            </div>
                        </div>
                    </el-col>
                    <br><br><br><br>
                </el-row>
            </div>
        </el-main>
        <el-footer>Footer</el-footer>
    </el-container>
</template>
<script>
export default {
    name: 'PsychologyHome',
    data() {
        return {
            eleindex: 0,
        piclist: [
          {text: '播放/录制页面', bg: require('../assets/aliyun.jpg'), img:  require('../assets/aliyun.jpg')},
          {text: '播放/录制页面', bg: require('../assets/kaggle.jpg'), img:  require('../assets/kaggle.jpg')},
          {text: '播放/录制页面', bg: require('../assets/GitHub.jpeg'), img:  require('../assets/GitHub.jpeg')},
          {text: '播放/录制页面', bg: require('../assets/kaggle.jpg'), img:  require('../assets/kaggle.jpg')}

        ],
            activeName: '1',
            bannerH: this.setBannerH1,
            images: [
                { url: require("@/assets/1.png")},
                { url: require("@/assets/4.png")},
                { url: require("@/assets/5.png")},
         ]
        };
    },
    methods: {
        enter: function (i) {
        // console.log(i)
        this.eleindex = i
        // if (this.eleindex === i) {
        //   this.eleindex = -1
        // } else {
        //   this.eleindex = i
        // }
      },
      out: function (i) {
        this.imgindex = -1
      },
        setBannerH(){
			this.bannerH=document.getElementsByClassName("bannertt")[0].height
		},
		setBannerH1(){
			var bwidth=document.getElementsByClassName("bannertt")[0].width;
			var bheight=document.getElementsByClassName("bannertt")[0].height;
			var ssss=document.body.clientWidth;
			this.bannerH=ssss*(bheight/bwidth);
		}
    },
    mounted(){
	    this.setBannerH1()//初始值根据图片的缩放比率和屏幕大小来定，不然走马灯开始时候就会默认一个值
		window.addEventListener('resize',()=>{
			this.setBannerH()
		},false)
	},
}
</script>
<style scoped>
/*手风琴样式*/
.or-container {
  display: flex;
  width: 97%;
  padding-top: 4%;
  padding-bottom: 4%;
  padding-left: 0%;
  padding-right: 6%;
  box-sizing: border-box;
  height: 48vh;
}
.or-container:before {
  background-color: rgba(0,0,0,0.4);
}

.box {
  flex: 1;
  overflow: hidden;
  transition: .5s;
  box-shadow: 0 20px 30px rgba(0, 0, 0, 0.1);
  line-height: 0;
}

.box > img {
  width: 100%;
  /* height: calc(100% - 10vh); */
  height: calc(100%);
  -o-object-fit: cover;
  object-fit: cover;
  transition: .5s;
}

.box > span {
  font-size: 3.8vh;
  display: block;
  text-align: center;
  height: 10vh;
  line-height: 2.6;
}

.eleactive {
  flex: 1 1 40%;
}

.eleactive > img {
  width: 100%;
  height: 100%;
}
  /*end*/

#dataSourceImg {
    height: 300px;
}
.evaluateCard {
    background-color: #f1f8ff;
    border-color: #f1f8ff;
}
.dataSourceBlock {
    background-color: #ffffff;
}
.creditEvaluateGroup {
    background-color: #f1f8ff;
}
.el-card {
    border-color: #ffffff;
}
.assistantWord {
    font-size: medium;
}
#assistantMain {
    margin-top: 2%
}
.whiteassistantBlock {
    height: 213.88px;

}
.assistantPiece {
    padding: 10px;
    border-radius: 4px;
}
.assistantPieceImg {
    width: 80%;
}
#evaluateImg {
    width: 20%;
    margin-top: 2%;
}
#assistantImg {
    width: 20%;
    margin-top: 2%;
}
.bannertt {
    width: 100%;
    height: auto;
}
.carousel {
    color: #000000;
    text-align: center;
    overflow: hidden;
    width: 100%;
}
.el-carousel__item h3 {
    color: #475669;
    font-size: 18px;
    opacity: 0.75;
    line-height: 500px;
    margin: 0;
}
.el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
}
.el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
}
.el-header {
    background-color: #758dad;
    color: #333;
    text-align: center;
    /* line-height: 100px; */
}
.el-footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    /* line-height: 30px; */
}
.el-main {
    background-color: #ffffff;
    color: #333;
    text-align: center;
    padding: 0px;
}
body > .el-container {
    margin-bottom: 40px;
}
</style>