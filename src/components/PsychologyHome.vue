<template>
    <el-container>
        <el-header>Header</el-header>
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
        </el-main>
        <el-footer>Footer</el-footer>
    </el-container>
</template>
<script>
export default {
    name: 'PsychologyHome',
    data() {
        return {
            bannerH: this.setBannerH1,
            images: [
                { url: require("@/assets/1.png")},
                { url: require("@/assets/4.png")},
                { url: require("@/assets/5.png")},
         ]
        };
    },
    methods: {
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
.el-container {
    /* height: 1000px; */
}
body > .el-container {
    margin-bottom: 40px;
}
</style>