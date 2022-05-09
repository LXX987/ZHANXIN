<template>
    <div>
        <div class="guidebar">
            <el-menu default-active="/HonestyVideo" class="el-menu-demo" mode="horizontal" :router="true">
                <el-menu-item><img src='@/assets/logo.png' class="logo"></el-menu-item>
                <el-menu-item index="/HomePage">首页</el-menu-item>
                <el-menu-item index="/CreditReport">信用报告</el-menu-item>
                <el-menu-item index="/PsychologyGuidebar">心理测试</el-menu-item>
                <el-menu-item index="/HonestyVideo">金融教育</el-menu-item>
                <el-menu-item index="">好友人脉</el-menu-item>
                <el-menu-item index="">意见反馈</el-menu-item>
                <el-menu-item index="/PersonalCenter">
                    <img src='@/assets/avatar.png' class="avatar">
                </el-menu-item>
            </el-menu>
        </div>
        <div class="body">
            <div class="content">
                <p class="vqt">视频测试题</p>
                <div class="question">
                    <div v-for="(item) in questions" :key="item.questionId" class="text_item">
                        <p >{{item.testID}}&nbsp;{{item.questionText}}</p>
                        <el-radio-group :disabled="able" v-model="item.answer" class="option">
                        <ul>
                        <li class="li" v-for="(option) in item.option" :key="option.optionID">
                            <el-radio :label="option.optionID">{{option.text}}</el-radio>
                            </li>
                        </ul>
                        </el-radio-group>
                    </div>
                    <div style="display:none" id="answer">
                        <p>正确答案{{answer.toString()}}</p>
                        <p>得分{{score}}</p>
                    </div> 
                </div>
                <el-button @click="cancel()">取消</el-button>
                <el-button @click="submit()" id="submit">提交</el-button> 
                <el-button @click="commit()" style="display:none" id="commit">确认</el-button>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    data(){
        return{
            able:false,
            answer:[],
            videoId:'',
            questions: [],
            score:0,
        }
    },
    mounted(){
        this.videoId = this.$route.query.videoId
        console.log(this.videoId)
        this.getVideoQuestion()
    },
    methods:{
        cancel()
        {
            this.$router.go(-1)
        },
        submit(){
            // 控制组件的出现
            var traget=document.getElementById("answer")
            var traget2=document.getElementById("submit")
            var traget3=document.getElementById("commit")
            if(traget.style.display=="none"){  
                traget.style.display="";
            }else{  
                traget.style.display="none";  
            }
            if(traget2.style.display==""){  
                traget2.style.display="none";
            }
            if(traget3.style.display=="none"){  
                traget3.style.display="";
            }
            // 算分数
            console.log(this.answer[0].charCodeAt())
            var i=0
            for(i=0;i<this.answer.length;i++){
                if(this.questions[i].answer == this.answer[i].charCodeAt()-64){
                    this.score+=1
                }
            }
            this.able = true
        },
        commit(){
            // 跳转回诚信视频界面
            this.$router.push({path:'/HonestyVideo'})
            
            // 分数提交到后端
            this.$axios({
                method:"post",
                url: 'http://localhost:8888/videoquestion',
                headers: { token:window.sessionStorage.getItem("token")},
                params:{
                video_id:this.videoId,
                score:this.score,
                watch:1,
                }
            }).then(res=>{
                    console.log(res)
                })
        },
        getVideoQuestion(){
        // 获取id对应的视频题目
            this.$axios({
            method:"get",
            url: 'http://localhost:8888/common/questions'+'/'+this.videoId,
            }).then(res=>{
                var i = 0
                for(i = 0; i< res.data.data.length;i++){
                    var array=new Array();
                    var j = 1
                    for(j = 1; j<res.data.data[i].questionText.trim().split(/\s+/).length;j++)
                    {
                        let obj1 = {optionID: j, text: res.data.data[i].questionText.trim().split(/\s+/)[j]};
                        array.push(obj1)
                    }
                    res.data.data[i].questionText = res.data.data[i].questionText.trim().split(/\s+/)[0]
                    res.data.data[i]["option"]=array
                    console.log(res.data.data[i])
                    this.answer.push(res.data.data[i].questionAnswer)
                    console.log(this.answer)
                }
                this.questions = res.data.data
            })
        }
    }
}
</script>
<style scoped>
.guidebar {
    height:100px;
    padding-left: 18%;
    padding-right: 250px;
    float:center;
    display:flex;
    align-items:center;/*所有子元素都垂直居中了*/
  }
   .el-menu-item {
    padding:0px 0;
    color: #000;
    list-style-type:none;
    text-align:center;
    padding-left:30px;
    padding-right:30px;
    font-size:20px;
    height: 100px;
    display:flex;
    align-items:center;/*所有子元素都垂直居中了*/
  }
  .el-menu--horizontal>.el-menu-item.is-active {
    border-bottom: 0.010417rem solid #409EFF;
    color: #fff;
    background-color: #409EFF;
}
  .el-menu-item:hover{
    border-bottom-style:solid;
    border-color: #6969FC;
  }
.logo{
  height: 100px
}
.el-menu{
  height: 100px;
}
.el-menu.el-menu--horizontal {
    border-bottom: solid 0.005208rem #fff8f8;
}
.avatar{
  height: 50%;
}
.body{
    background-image: url(../assets/background.png);
    height: 850px;
    width: 100%;
    background-size:100% 100%
}
.content{
    height: 750px;
    background-color: #fff;
    margin-left: 28%;
    margin-right: 28%;
    padding-bottom: 160px;
    padding-top: 50px;
    border-radius: 25px;
    box-shadow:0 0 0 15px  rgba(255, 255, 255, 0.286)
}
.vqt{
    font-size: 28px;
    color:#32BDFE;
    font-weight: bold;
    text-align: center;
    padding-top: 50px;
}
.question{
    margin-top: 20px;
    text-align: left;
    margin-left: 15%;
    font-size: 20px;
    margin-bottom: 20px;
}
.el-radio{
    font-size: 28px;
}
>>>.el-radio__label {
    padding-left: 0.052083rem;
    font-size: 18px;
}
.option{
    word-break: break-all;
    word-wrap: break-word
}
.question{
    margin-right: 10%;
}
.text_item{
    white-space:normal
}
.li{
    list-style: none;
}
</style>