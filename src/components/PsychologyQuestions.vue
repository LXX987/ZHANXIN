<template>
            <div>
            <el-row>
                <el-col :span="14">
                    <el-row class="measureHomeHead">
                        <span id="measureHomeHeading">心理测试</span>
                    </el-row>
                    <el-row class="QuestionNotice">
                        <span>答题须知：</span>
                        <ul>
                            <li>1. 请在心态平和及时间充足的情况下才开始答题。</li>
                            <li>2. 每道题目均有五个答案： A、B、C、D、E，请仔细阅读题目，按照与你性格相符的程度选择答案即可。</li>
                            <li>3. 请注意，题目的答案无对错之分，你不需要考虑哪个答案“应该”更好，而且不要在任何问题上思考太久，而是应该凭你心里的第一反应做出选择。本测试为免费测试，需要用时20-30分钟。</li>
                        </ul>
                    </el-row>
                    <el-divider id="divider"></el-divider>
                    <el-row>
                        <div v-for="(item) in questions" :key="item.testID" class="text item">
                            <p style="width: 90%;font-size: 16px;margin-bottom: 5px;text-align: left;margin-left: 5%;margin-bottom: 3%;">{{item.testID}}.&nbsp;{{item.testContent}}</p>
                            <el-radio-group v-model="item.item_score">
                            <el-radio :label="1">{{item.option1}}</el-radio>
                            <el-radio :label="2">{{item.option2}}</el-radio>
                            <el-radio :label="3">{{item.option3}}</el-radio>
                            <el-radio :label="4">{{item.option4}}</el-radio>
                            <el-radio :label="5">{{item.option5}}</el-radio>
                            </el-radio-group>
                                <el-divider></el-divider>
                        </div>
            <el-button @click="submit">提交</el-button>
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
</template>
<style scoped>
.QuestionNotice{
    text-align: left;
    padding: 2% 0% 1% 10%;
}
.QuestionNotice span{
    font-size: 20px;
    font-weight: 550;
    line-height: 28px;
}
.QuestionNotice ul{
    font-size: 18px;
    line-height: 28px;
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
.measureHomeHead {
    margin-top: 3%;
    margin-bottom: 3%;
}
#psychologyMeasureIntroImg {
    margin: 10% 10%;
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
    name: 'PsychologyQuestions',
    data() {
        return {
            questions: [],
         scoreList: [],
         score1: 0,
         score2: 0,
         score3: 0,
         score4: 0,
         score5: 0,
         totalScore: 0
        }
    },
    mounted(){
    this.$axios({
        method:"post",
        url: 'api/psychologyQuestions/getQuestions',
        params:{
        }
        }).then(res=>{
            console.log(res);
            this.questions = res.data.questions;
        },err=>{
            console.log(err);
        })
    },
    methods: {
    submit(){
        console.log(this.questions);
        for(let i=0;i<60;i++){
            this.scoreList.push({
                score: this.questions[i].item_score
            })
        }
        console.log(this.scoreList);
        this.score1 += 6 - this.scoreList[0].score;
        this.score1 += this.scoreList[5].score;
        this.score1 += this.scoreList[10].score;
        this.score1 += this.scoreList[10].score;
        this.score1 += 6 - this.scoreList[15].score;
        this.score1 += this.scoreList[20].score;
        this.score1 += this.scoreList[25].score;
        this.score1 += 6 - this.scoreList[30].score;
        this.score1 += this.scoreList[35].score;
        this.score1 += this.scoreList[40].score;
        this.score1 += 6 - this.scoreList[45].score;
        this.score1 += this.scoreList[50].score;
        this.score1 += this.scoreList[55].score;
        this.score2 += this.scoreList[1].score;
        this.score2 += this.scoreList[6].score;
        this.score2 += 6 - this.scoreList[11].score;
        this.score2 += this.scoreList[16].score;
        this.score2 += this.scoreList[21].score;
        this.score2 += 6 - this.scoreList[26].score;
        this.score2 += this.scoreList[31].score;
        this.score2 += this.scoreList[36].score;
        this.score2 += 6 - this.scoreList[41].score;
        this.score2 += this.scoreList[46].score;
        this.score2 += this.scoreList[51].score;
        this.score2 += 6 - this.scoreList[56].score;
        this.score3 += this.scoreList[2].score;
        this.score3 += this.scoreList[7].score;
        this.score3 += this.scoreList[12].score;
        this.score3 += 6 - this.scoreList[17].score;
        this.score3 += 6 - this.scoreList[22].score;
        this.score3 += 6 - this.scoreList[27].score;
        this.score3 += 6 - this.scoreList[32].score;
        this.score3 += this.scoreList[37].score;
        this.score3 += this.scoreList[42].score;
        this.score3 += 6 - this.scoreList[47].score;
        this.score3 += this.scoreList[52].score;
        this.score3 += this.scoreList[57].score;
        this.score4 += this.scoreList[3].score;
        this.score4 += 6 - this.scoreList[8].score;
        this.score4 += 6 - this.scoreList[13].score;
        this.score4 += 6 - this.scoreList[18].score;
        this.score4 += 6 - this.scoreList[23].score;
        this.score4 += this.scoreList[28].score;
        this.score4 += this.scoreList[33].score;
        this.score4 += 6 - this.scoreList[38].score;
        this.score4 += 6 - this.scoreList[43].score;
        this.score4 += this.scoreList[48].score;
        this.score4 += 6 - this.scoreList[53].score;
        this.score4 += 6 - this.scoreList[58].score;
        this.score5 += this.scoreList[4].score;
        this.score5 += this.scoreList[9].score;
        this.score5 += 6 - this.scoreList[14].score;
        this.score5 += this.scoreList[19].score;
        this.score5 += this.scoreList[24].score;
        this.score5 += 6 - this.scoreList[29].score;
        this.score5 += this.scoreList[34].score;
        this.score5 += this.scoreList[39].score;
        this.score5 += 6 - this.scoreList[44].score;
        this.score5 += this.scoreList[49].score;
        this.score5 += 6 - this.scoreList[54].score;
        this.score5 += this.scoreList[59].score;
        this.totalScore = this.score1 + this.score2 + this.score3 + this.score4 + this.score5;
        console.log('score1', this.score1);
        console.log('score2', this.score2);
        console.log('score3', this.score3);
        console.log('score4', this.score4);
        console.log('score5', this.score5);
        console.log('totalSocre', this.totalScore);
        this.$axios({
            method:"post",
            url: 'api/psychology/getScore',
            params:{
                score1: this.score1,
                score2: this.score2,
                score3: this.score3,
                score4: this.score4,
                score5: this.score5,
                totalScore: this.totalScore
            },
            headers:{
         token:window.sessionStorage.getItem("token")}
            }).then(res=>{
                console.log(res);
                //  this.$router.push('/PsychoMeasureRecord?totalScore=this.totalScore&score1=this.score1&score2=this.score2&score3=this.score3&score4=this.score4&score5=this.score5')
                // this.$router.push({name:'PsychoMeasureRecord',query:{totalScore: this.totalScore, score1: this.score1, score2: this.score2,score3: this.score3,score4: this.score4,score5: this.score5}});
            },err=>{
                console.log(err);
            })
            this.$router.push('/PsychologyGuidebar/PsychologyResult')
    } 
  }
}
</script>