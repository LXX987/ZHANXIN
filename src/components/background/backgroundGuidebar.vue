<template>
    <div class="guideBar">
        <el-row>
            <el-col :span="2">
                <img src="@/assets/backgroundLogo.png">
            </el-col>
            <el-col :span="12">
                <h1>瞻信信用评估大数据监管平台</h1>
            </el-col>
            <el-col :span="3">
                <el-avatar icon="el-icon-user-solid"></el-avatar>
            </el-col>
            <el-col :span="3">
                <span>Admin</span>
            </el-col>
            <el-col :span="2">
                <el-badge :value="12" class="item">
                    <el-button size="small">评论</el-button>
                </el-badge>
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
    }
}
</script>
<style scoped>

</style>