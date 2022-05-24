<template>
  <div class="body">
    <BankGuideBar></BankGuideBar>
    <div class="main">
      <el-row :gutter="20">
        <el-col :span="4">
          <div class="grid-content bg-purple left">
            <p class="choose-txt">概况统计</p>
            <p class="nochoose-txt" @click="BankData()">信用数据</p>
            <p class="nochoose-txt" @click="BankSearch()">搜索列表</p>
          </div>
        </el-col>
        <el-col :span="20">
          <div class="grid-content">
            <el-row class="bg-purple">
              <el-col :span=20>
                <p class="title-txt">概率统计</p>
              </el-col>
              <el-col :span=4>
                <p class="tip-txt">数据每日00:00更新</p>
              </el-col>
            </el-row>
            <el-row class="bg-purple">
              
            </el-row>
            <el-row class="bg-purple">
              
            </el-row>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import BankGuideBar from './BankGuideBar.vue';
export default {
  name: 'BankMain',
  components: { BankGuideBar },
  methods: {
    BankMain() {
      this.$router.push({path: '/BankMain'});
    },
    BankSearch() {
      this.$router.push({path: '/BankSearch'});
    },
    BankData() {
      this.$router.push({path: '/BankData'});
    }
  }
}
</script>

<style scoped>
  .title-txt {
    text-align: left;
    font-size: 24px;
    color: #101010;
    margin: 20px;
    padding-left: 60px;
    border-left-style:solid;
    border-left-color:#0079FE;
  }
  .tip-txt {
    font-size: 14px;
    color: #039BE5;
    margin-top: 25px;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
.body {
  background-color: #f2f2f2;
}
.main {
  padding: 50px;
}
 .el-row {
    margin-bottom: 20px;
  }
  .el-col {
    border-radius: 4px;
  }
  .bg-purple {
    background: #ffffff;
  }
  .left {
    height:750px;
  }
  .choose-txt {
    color: #0079FE;
    font-size: 28px;
    padding-top: 20px;
    padding-bottom: 20px;
    font-weight: bold;
    background: #E6F2FF;
    border-right-style:solid;
    border-right-color:#0079FE;
  }
  .nochoose-txt {
    color: #101010;
    font-size: 28px;
    padding-top: 20px;
    padding-bottom: 20px;
    font-weight: bold;
  }
  .nochoose-txt:hover {
    background: #f2f8ff;
  }
</style>