<template>
<div class="rate">
    <div class="imageBlock">
        <el-carousel :interval="2000" arrow="always" :height="bannerH+'px'">
            <el-carousel-item v-for="(bitem,bindex) in images" :key="bindex">
                <div class="carousel">
                    <img class="bannertt" :src="bitem.url" >
                </div>
            </el-carousel-item>
        </el-carousel>
    </div>
<div class="body">
    <div class="illu">
        <div class="recommend">
            <img src="@/assets/recommend.png"/>
        </div>
        <div class="recommendvideo">
            <el-card shadow="none">
                <el-row>
                    <el-col :span="12">
                        <img id="video1" alt="借呗欠了5万半年没还会怎样？" @click="index=1,gotoVideo()"  tag="a" target="_blank" src="https://img.qudayun.com/csai_cms/site/2022-03/1394/20220307174846578.jpg">
                        <img id="startVideo" alt="播放" @click="index=1,gotoVideo()" src="@/assets/button_watch.png"/>
                    </el-col>
                    <el-col :span="12">
                        <div class="videointro">视频介绍</div>
                        <div class="intro">
                            <h1 style="font-size:20px;margin-bottom:10px">借呗欠了5万半年没还会怎样？</h1>
                            <span style="font-size:16px">&emsp;&emsp;借呗欠了5万半年没有还，贷款人的征信会受到影响，在征信报告上面会留下不良的贷款记录，逾期半年算比较严重的行为了，就算是结清了逾期贷款也会对贷款人以后的信贷活动造成影响。</span>
                            <p style="font-size:16px">视频长度：5：25</p>
                            <p style="font-size:16px">发布时间：2022-2-5</p>
                            <span></span>
                        </div>
                    </el-col>
                </el-row>
            </el-card>
        </div>
    </div>
    <div class="video">
        <div class="video_guide">
            <el-menu default-active="/HonestyVideo" class="video_guide" mode="horizontal" :router="true">
                <el-menu-item index="/HonestyVideo">基本知识</el-menu-item>
                <el-menu-item index="/CreditVideo">信贷知识</el-menu-item>
                <el-menu-item index="/HonestyCulture">诚信文化</el-menu-item>
                <el-menu-item index="/VideoRecord">我的观看记录</el-menu-item>
            </el-menu>
        </div>
        <div>
            <router-view name="video"></router-view>
        </div>
    </div>
</div>
</div>   
</template>
 
<script>
export default {
    components:{
     },
    data(){
        return{
            video_index:'/HonestyVideo/BasicVideo',
            num:'1',
            watched:require('@/assets/unwatch.png'),
            index: 1,
            videoUrl:'',
            bannerH: this.setBannerH1,
            images: [
                { url: require("@/assets/1.png")},
                { url: require("@/assets/4.png")},
                { url: require("@/assets/5.png")},
         ]
        }
    },
    computed: {
        defaultActive() {
            return '/' + this.$route.path.split('/').reverse()[0];
        }
    },
    methods:{
        // setvideoindex(){
        //     console.log(this.$route.path)
        // },
        setBannerH(){
			this.bannerH=document.getElementsByClassName("bannertt")[0].height
		},
		setBannerH1(){
			var bwidth=document.getElementsByClassName("bannertt")[0].width;
			var bheight=document.getElementsByClassName("bannertt")[0].height;
			var ssss=document.body.clientWidth;
			this.bannerH=ssss*(bheight/bwidth);
		},
        gotoAnswer(){
           this.$router.push('/VideoAnswer')
        },
        gotoVideo(){
            switch(this.index){
                case 1:this.videoUrl = "https://cmsvideo.csai.cn/6c9b4bdavodcq1500007119/7bd55f31387702297167054251/unrmykY7bwAA.mp4";break;
                case 2:this.videoUrl = "https://cmsvideo.csai.cn/6c9b4bdavodcq1500007119/7b79cbcd387702297167002245/7wxAa9zuY4UA.mp4";break;
                case 3:this.videoUrl = "https://cmsvideo.csai.cn/6c9b4bdavodcq1500007119/3c7d65cb387702297166600141/ZCmgEvI3zs0A.mp4";break;
                default:break;
            }
            this.num = 2
            this.watched = require('@/assets/watched.png')
            this.watch = '已观看'
            let newUrl = this.$router.resolve({name:'Video', query:{videoUrl:this.videoUrl}});
            window.open(newUrl.href, "_blank");
            //this.$router.push({name:'Video', query:{videoUrl:this.videoUrl}})
        },
        
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
#watched{
    width: 50px;
    height: 50px;
    opacity: 0.8s;
    margin-left: 450px;
    margin-top: 0px;
}
#startVideo{
    position: absolute;
    left: 100px;
    top: 110px;
    height: 40%;
}
#startVideo2{
    position: absolute;
    left: 195px;
    top: 100px;
    width: 55px;
    height: 55px;
}
#startVideo3{
    position: absolute;
    left: 195px;
    top: 100px;
}
.illu{
    height:150px;
    text-align: left;
}
.tip {
    padding: 8px 16px;
    background-color: #ecf8ff;
    border-radius: 4px;
    border-left: 5px solid #50bfff;
    margin: 20px 0;
    margin-left: 200px;
    margin-right: 120px;
}
li{
    list-style: none;
    padding:20px;
    text-align: left;
    margin-left: 120px;
    margin-right: 120px;
    padding-left: 50px;
}
.el-card{
    padding-left: 50px;
    padding-right: 120px;

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
body > .el-container {
    margin-bottom: 40px;
}
.recommend{
    margin-top: 20px;
    margin-bottom: 20px;
    font-size: 28px;
    color: #32BDFE;
    text-align: center;
}
.video{
    margin-top: 300px;
}
.recommendvideo{
    margin-left: 15%;
    margin-right: 15%;

}
.el-card{
    border: 0;
}
#video1{
    height: 90%;
    width: 100%;
}
.videointro{
    display:flex;
    align-items:center;/*所有子元素都垂直居中了*/
    height: 80px;
    font-size: 20px;
    font-weight: bold;
    color: #666666;
    border-left: #32BDFE solid 11px;
    padding-left: 20px;
}
.intro{
    background-color: #F3F9FF;
    height: 250px;
    padding-left: 5%;
    padding-top: 20px;
    padding-right: 5%;
    line-height:1.6em;
}
.el-menu-item{
    font-size: 24px;
}
.video_guide{
    padding-top: 30px;
    margin-left: 10%;
    color: #999999;
}
.el-menu--horizontal>.el-menu-item.is-active {
    border-bottom: 0;
    font-size: 34px;
    color: #303133;
    margin-bottom: 60px;
}
.el-menu.el-menu--horizontal {
    border-bottom: solid 0;
}
</style>