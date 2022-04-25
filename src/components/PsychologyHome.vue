<template>
    <!-- <el-main> -->
        <div>
            <el-row>
                <el-col :span="14">
                    <el-row class="measureHomeHead">
                        <span id="measureHomeHeading">大五人格心理测试</span>
                    </el-row>
                    <el-row class="psychologyIntro">
                        <el-col :span="14">
                            <div class="psychologyIntroWord">
                                <p>&ensp;&ensp;&ensp;&ensp;&ensp;近年来，研究者们在人格描述模式上形成了比较一致的共识，提出了人格的大五模式， Goldberg（1992）称之为人格心理学中的一场革命。</p>
                                <p>&ensp;&ensp;&ensp;&ensp;&ensp;研究者通过词汇学的方法，发现大约有五种特质可以涵盖人格描述的所有方面。大五人格（OCEAN），也被称之为人格的海洋。</p>
                                <p>&ensp;&ensp;&ensp;&ensp;&ensp;人格理论为：</p>
                                <ul>
                                    <li>开放性（openness）</li>
                                    <li>责任心（conscientiousness）</li>
                                    <li>外倾性（extraversion）</li>
                                    <li>宜人性（agreeableness）</li>
                                    <li>神经质性（neuroticism）</li>
                                </ul>
                            </div>
                        </el-col>
                        <el-col :span="10">
                            <img id="psychologyMeasureIntroImg" src="@/assets/psychologyMeasureHome.jpg">
                        </el-col>
                    </el-row>
                    <el-divider id="divider"></el-divider>
                    <el-row>
                        <div class="introBox">
                            <el-button id="startButton" @click="jumpToPsychologyQuestions">开始测试</el-button>
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
    name: 'PsychologyHome',
    data() {
        return {
            
        }
    },
    methods: {
      jumpToPsychologyQuestions(){
          this.$router.push('/PsychologyGuidebar/PsychologyQuestions')
      }
    }
}
</script>