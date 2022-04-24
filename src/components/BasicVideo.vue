<template>
    <div>
        <ul class="spfl_list">
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
                            <div>
                            <img id="watched" src="@/assets/watched.png"/><span style="vertical-align: top;line-height: 50px;">已观看</span></div>
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
                            <div style="height:50px">
                                <img id="watched" :src="watched"/>
                                <span style="vertical-align: top;line-height: 50px;">{{watch}}</span></div>
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
                            <img id="watched" src='@/assets/unwatch.png'/><span style="vertical-align: top;line-height: 50px;">未观看</span>
                        </el-col>
                    </el-row>
                </el-card>
            </li>
       </ul>
    </div>
</template>
<script>

export default ({
    data() {
        return{
            num:'1',
            watched:require('@/assets/unwatch.png'),
            index: 1,
            videoUrl:'',
        }
    },
    methods:{
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
        }
    }
})
</script>
<style scoped>

</style>