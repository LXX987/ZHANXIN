<template>
    <div>
        <div class="box" id="">
            <ul>
                <li v-for="item in list" :key="item.value">
                    <div class="illu" :style="backgroundDiv">
                    <img class="cover" v-bind:src="item.videoCover" alt="">
                    <h4 class="title">视频标题：{{item.videoName}}</h4>
                    <p class="time">简介：{{item.videoIntro}}</p>
                    <div class="detail"><el-button type="text" v-on:click="onclick(item.videoId)">查看详情</el-button></div>
                    </div>
                </li>
            </ul>
	    </div>
        <!-- <ul class="spfl_list">
            <li>
                <el-card>
                    <el-row>
                        <el-col :span="12">
                            <img alt="借呗欠了5万半年没还会怎样？" @click="index=1,gotoVideo()"  tag="a" target="_blank" src="https://img.qudayun.com/csai_cms/site/2022-03/1394/20220307174846578.jpg" style="width:80%;height:80%">
                            <img id="startVideo" alt="播放" @click="index=1,gotoVideo()" src="@/assets/video.png"/>
                        </el-col>
                        <el-col :span="12">
                            <div>
                            <h1 style="font-size:20px">借呗欠了5万半年没还会怎样？</h1>
                            <span>借呗欠了5万半年没有还，贷款人的征信会受到影响，在征信报告上面会留下不良的贷款记录，逾期半年算比较严重的行为了，就算是结清了逾期贷款也会对贷款人以后的信贷活动造成影响。</span>
                            <p>2022-03-07</p><span></span>
                            </div>
                        </el-col>
                    </el-row>
                </el-card>
            </li>
            <li>
                <el-card>
                    <el-row>
                        <el-col :span="12">
                            <img alt="申请房贷个人需要准备什么？" @click="index=2,gotoVideo()"  target="_blank" src="https://img.qudayun.com/csai_cms/site/2022-03/71/20220307174615329.jpg" style="width:80%;height:80%">
                            <img id="startVideo2" alt="播放" @click="index=2,gotoVideo()" src="@/assets/video.png"/>
                        </el-col>
                        <el-col :span="12">
                            <div>
                            <h1 style="font-size:20px">申请房贷个人需要准备什么？</h1>
                            <span>在申请房贷的时候，个人需要准备征信报告、收入流水、收入证明、银行卡、有效身份证件、户口本等等，如果是异地购房，可能还需要贷款人准备居住证、社保缴纳证明等等。一般来说，客户需要准备什么资料开发商都会详细告知，按照开发商的提醒来操作就好了，按照提示来准备资料一般就不会有问题。</span>
                            <p>2022-03-07</p><span></span>
                            </div>
                            <div style="height:50px"></div>
                        </el-col>
                    </el-row>
                </el-card>
            </li>
            <li>
                <el-card>
                    <el-row>
                        <el-col :span="12">
                            <img alt="车贷逾期三个月的后果？" @click="index=3,gotoVideo()" target="_blank" src="https://img.qudayun.com/csai_cms/site/2022-03/1422/20220307173513634.jpg" style="width:80%;height:80%">
                            <img id="startVideo3" alt="播放" @click="index=3,gotoVideo()" src="@/assets/video.png"/>
                        </el-col>
                        <el-col :span="12">
                            <h1 style="font-size:20px">车贷逾期三个月的后果？</h1>
                            <span>车贷逾期三个月，贷款人的征信就会受到影响，因为贷款机构会将贷款人的逾期记录上报个人征信系统，并且逾期三个月算比较严重的逾期行为，会对贷款人以后申请房贷等贷款造成不便。</span>
                            <p>2022-03-07</p><span></span>
                            
                        </el-col>
                    </el-row>
                </el-card>
            </li>
       </ul> -->
    </div>
</template>
<script>

export default ({
    data() {
        return{
            backgroundDiv: {
            backgroundImage: 'url(' + require('@/assets/recback.png') + ')',  },
            num:'1',
            index: 1,
            videoUrl:'',
            videoId:'',
            list:[]
        }
    },
    methods:{
        onclick(value){
            for(let i in this.list){
                if(this.list[i].videoId == value ){
                    this.videoUrl = this.list[i].videoUrl
                    this.videoId = value
                    console.log(this.videoId)
                }
            }
            this.num = 2
            let newUrl = this.$router.resolve({name:'Video', query:{videoUrl:this.videoUrl,videoId:this.videoId}});
            window.open(newUrl.href, "_blank");
            //this.$router.push({name:'Video', query:{videoUrl:this.videoUrl}})
        },
        getVideo(){
            this.$axios({
            method:"get",
            url: 'http://localhost:8899/common/videos/fundamental'
            }).then(res=>{
                console.log(res.data.data)
                this.list = res.data.data
            })
        }
    },
    mounted(){
        this.getVideo()
    }
})
</script>
<style scoped>
.illu{
    height:350px;
    text-align: left;
    background-repeat:no-repeat;
    background-size:100%;
}
.box {
    width:80%;
    margin-top:15px;
    margin-left: 18%;
}
ul{
    display:flex;
    width:1500px;
    list-style-type: none;
    flex-flow: wrap;
}
li {
    align-items:center;
    width:320px;
    margin-bottom:20px;
}
.detail{
    text-align: right;
    vertical-align: bottom;
    margin-right: 25px;
}
.cover{
    margin-left: 40px;
    margin-top: 50px;
    height: 160px;
    width: 240px;
}
.title{
    margin-top: 10px;
    font-weight: bold;
    font-size: 20px;
    margin-left: 35px;
}
.time{
    margin-left: 35px;
    margin-top: 10px;
    margin-bottom: 15px;
}
.el-button{
    font-size: 18px;
    color: #fff;
}
</style>