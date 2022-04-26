<template>
    <el-main>
        <div>
            <el-row>
                <el-col :span="4">
                    <div class="NavMenu">
                        <el-menu default-active="/PsychologyGuidebar" class="el-menu-demo" :router="true">
                            <el-menu-item index="/PsychologyGuidebar">
                                <i class="el-icon-date"></i>
                                <span slot="title">测试介绍</span>
                            </el-menu-item>
                            <el-menu-item index="/PsychologyGuidebar/PsychologyQuestions">
                                <i class="el-icon-document"></i>
                                <span slot="title">测试题目</span>
                            </el-menu-item>
                            <el-menu-item index="/PsychologyGuidebar/PsychologyResult">
                                <i class="el-icon-data-analysis"></i>
                                <span slot="title">测试结果</span>
                            </el-menu-item>
                            <el-menu-item index="/PsychologyGuidebar/PsychologyHistory">
                                <i class="el-icon-user"></i>
                                <span slot="title">历史记录</span>
                            </el-menu-item>
                        </el-menu>
                    </div>
                </el-col>
                <el-col :span="20">
                    <router-view name="subtable"></router-view>
                </el-col>
            </el-row>
        </div>
    </el-main>
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
    margin-left: 48%;
    margin-top: 5%;
    width: 155px;
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
    name: 'PsychologyGuidebar',
    data() {
        return {
            
        }
    },
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      }
    }
}
</script>