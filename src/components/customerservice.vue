<template>
    <div class="guideBar">
        <el-row id="guidebar">
            <el-col :span="1" id="logo">
                <img src="@/assets/backgroundLogo.png">
            </el-col>
            <el-col :span="15" id="guidebarTitle">
                <h1>瞻信信用评估平台客服端</h1>
            </el-col>
            <el-col :span="2" id="avatorpic">
                <el-avatar icon="el-icon-user-solid"></el-avatar>
            </el-col>
            <el-col :span="2" id="csName">
                <span>客服小易</span>
            </el-col>
            <el-col :span="1" id="messageBox">
                <el-badge :value="1" class="item">
                    <el-button size="large">消息</el-button>
                </el-badge>
            </el-col>
            <el-col :span="1" id="logoutpic">
                <img @click="logout" src="@/assets/cslogout.png" />
            </el-col>
            <el-col :span="2"></el-col>
        </el-row>
    </div>
</template>
<script>
export default {
    name: 'backgroundGuidebar',
    data() {
        return {
            
        }
    },
    methods: {
        logout() {
            this.$message('已退出账号！');
            this.$router.push("/csLogin")
        }
    }
}
</script>
<style scoped>
#logoutpic {
    margin: 30px 0px 0px 70px;
}
#messageBox {
    margin-top: 30px;
    text-align: left;
}
#csName {
    font-size: 23px;
    line-height: 100px;
    text-align: left;
    margin-left: 20px;
}
.el-avatar--icon {
    width: 50px;
    height: 50px;
    font-size: 30px;
    line-height: 50px;
}
#avatorpic {
    text-align: right;
    line-height: 140px;
}
#guidebarTitle {
    text-align: left;
    font-size: 28px;
    line-height: 100px;
}
#guidebarTitle h1 {
    font-weight: 550;
}
#logo {
    margin: 20px 0px 20px 40px;
}
#guidebar {
    width: 100%;
    height: 100px;
    background-color: #82c2ac;
}
</style>