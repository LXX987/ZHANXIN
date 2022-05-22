<template>
    <div>
        <div class="box" id="">
            <ul>
                <li v-for="item in list" :key="item.value">
                    <div class="illu" :style="backgroundDiv">
                    <img class="cover" v-bind:src="item.src" alt="">
                    <h4 class="title">{{item.des}}</h4>
                    <p class="time">时长：{{item.price}}</p>
                    <div class="detail"><el-button type="text" v-on:click="onclick(item.value)">查看详情</el-button></div>
                    </div>
                </li>
            </ul>
	    </div>
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
            url: 'http://localhost:8899/common/videos/financial'
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