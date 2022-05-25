<template>
  <div class="body">
    <BankGuideBar></BankGuideBar>
    <div class="main">
      <el-row :gutter="20">
        <el-col :span="4">
          <div class="grid-content bg-purple left">
            <p class="nochoose-txt" @click="BankMain()">概况统计</p>
            <p class="nochoose-txt" @click="BankData()">信用数据</p>
            <p class="choose-txt">搜索列表</p>
          </div>
        </el-col>
        <el-col :span="20">
          <div class="grid-content">
            <el-row class="bg-purple">
              <el-col :span=20>
                <p class="title-txt">搜索列表</p>
              </el-col>
              <el-col :span=4>
                <p class="tip-txt">数据每日00:00更新</p>
              </el-col>
            </el-row>
            <el-row class="bg-purple">
              <p class="content-txt">
                用户ID：
                <el-input v-model="searchID" placeholder="请输入内容"></el-input>
                用户姓名：
                <el-input v-model="searchName" placeholder="请输入内容"></el-input>
              </p>
              <div class="btn-div">
                <el-button @click="clear()">清空</el-button>
                <el-button type="primary" class="btn-mar-left" @click="searchUserID()">搜索</el-button>
              </div>
            </el-row>
            <el-row class="bg-purple">
              <el-table :data="tableData.slice((currentPage-1)*pageSize,currentPage*pageSize)" stripe class="table">
                <el-table-column prop="userID" label="用户ID"></el-table-column>
                <el-table-column prop="userName" label="用户姓名"></el-table-column>
                <el-table-column prop="idcard" label="身份证号"></el-table-column>
                <el-table-column label="操作">
                  <template slot-scope="scope">
                    <el-button @click="handleClick(scope.row)" type="text">查看详细信息</el-button>
                  </template>
                </el-table-column>
              </el-table>
              <el-pagination align='center' @size-change="handleSizeChange" @current-change="handleCurrentChange"
                :current-page="currentPage" :page-sizes="[1,5,10,20]" :page-size="pageSize" 
                layout="total, sizes, prev, pager, next, jumper" :total="tableData.length" class="pagination">
              </el-pagination>
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
  name: 'BankSearch',
  components: { BankGuideBar },
  data() {
    return {
      tableData: [{
        userID: '2016-05-02',
        userName: '王小虎',
        idcard: '上海市普陀区金沙江路 1518 弄'
      }],
      currentPage: 1, // 当前页码
      total: 20, // 总条数
      pageSize: 10, // 每页的数据条数
      searchID:null,
      searchName:null,
    }
  },
  mounted:function(){
      this.getUserList();
    },
  methods: {
    //每页条数改变时触发 选择一页显示多少行
    handleSizeChange(val) {
        console.log(`每页 ${val} 条`);
        this.currentPage = 1;
        this.pageSize = val;
    },
    //当前页改变时触发 跳转其他页
    handleCurrentChange(val) {
        console.log(`当前页: ${val}`);
        this.currentPage = val;
    },
    getUserList() {
      this.$axios({
          method:"get",
          url: 'http://localhost:8899/bank/bankSearchList',
        }).then(res=>{
          console.log(res.data);
          this.tableData=res.data;
        },err=>{
          console.log(err);
        })
    },
    searchUserID() {
      if(this.searchID==null&&this.searchName==null) {
        this.$message({
              message: '请输入查询信息!',
              type: 'error'
            })
      } else {
        this.$axios({
          method:"post",
          url: 'http://localhost:8899/bank/bankSearchID',
          params:{
            userID:this.searchID,
            userName:this.searchName,
          },
        }).then(res=>{
          console.log(res.data);
          this.tableData=res.data;
        },err=>{
          console.log(err);
        })
      }
      
    },
    clear() {
      this.searchID=null;
      this.searchName=null;
    },
    BankMain() {
      this.$router.push({path: '/BankMain'});
    },
    BankSearch() {
      this.$router.push({path: '/BankSearch'});
    },
    BankData() {
      this.$router.push({path: '/BankData'});
    },
    handleClick(index) {
      console.log(index.userID)
      this.$router.push({path: '/BankUser',query:{ id:index.userID}});
    }
  },
}
</script>

<style scoped>
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
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
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
  .content-txt {
    font-size: 18px;
    margin-top: 50px;
  }
  .el-input{
    width:250px;
    margin-left: 50px;
    margin-right: 80px;
  }
  .btn-div {
    margin-top: 40px;
    margin-bottom: 40px;
  }
  .btn-mar-left {
    margin-left: 60px;
  }
  >>>.el-table th.el-table__cell>.cell {
    font-size: 18px;
    font-weight: bold;
    text-align: center;
  }
  >>>.el-table .cell {
    font-size: 18px;
    text-align: center;
  }
  .table {
    width: 100%;
    margin-top: 10px;
    margin-bottom: 20px;
  }
  .pagination {
    margin-bottom: 20px;
  }
</style>