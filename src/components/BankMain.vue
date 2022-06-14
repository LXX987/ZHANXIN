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
            <el-row class="bg-purple" id="dataUpload">
               <input  type="file" id="files" ref="refFile" v-on:change="importCsv">
               <el-button @click="uploadData">上传</el-button>
               <el-divider></el-divider>
                 <el-table
    :data="tableData"
    height="520"
    border
    stripe
    style="width: 100%">
    <el-table-column
      prop="email"
      label="邮箱账号"
      width="160">
    </el-table-column>
    <el-table-column
      prop="pwd"
      label="初始密码"
      width="130">
    </el-table-column>
    <el-table-column
      prop="type"
      label="账户类型">
    </el-table-column>
    <el-table-column
      prop="DebtRatio"
      label="负债比率">
    </el-table-column>
    <el-table-column
      prop="NumberRealEstateLoansOrLines"
      label="不动产贷款或额度数量">
    </el-table-column>
    <el-table-column
      prop="NumberOfOpenCreditLinesAndLoans"
      label="开放式信贷和贷款数量">
    </el-table-column>
    <el-table-column
      prop="NumberOfTime30_59DaysPastDueNotWorse"
      label="30-59天逾期但不糟糕次数">
    </el-table-column>
    <el-table-column
      prop="RevolvingUtilizationOfUnsecuredLines"
      label="无担保放款的循环利用">
    </el-table-column>
    <el-table-column
      prop="seriousDIqin2yrs"
      label="用户潜力">
    </el-table-column>
    <el-table-column
      prop="MonthlyIncome"
      label="月收入">
    </el-table-column>
    <el-table-column
      prop="NumberOfTime90DaysLate"
      label="90天逾期次数">
    </el-table-column>
    <el-table-column
      prop="NumberOfDependents"
      label="家属数量">
    </el-table-column>
    <el-table-column
      prop="NumberOfTimes60_89DaysPastDueNotWorse"
      label="60-89天逾期但不糟糕次数">
    </el-table-column>
  </el-table>
            </el-row>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import BankGuideBar from './BankGuideBar.vue';
import Papa from 'papaparse'
export default {
  name: 'BankMain',
  components: { BankGuideBar },
  data() {
    return {
      tableData: [],
      actionUrl : 'https://jsonplaceholder.typicode.com/posts/',
    }
  },
  methods: {
    uploadData() {
      this.$axios({
          method:"post",
          url: 'http://localhost:8899/upload/uploadInformation',
          params:{
            informationList: this.tableData
          },
        }).then(res=>{
          console.log(res);
        },err=>{
          console.log(err);
        })
    },
	 importCsv(){
        let selectedFile = null
        selectedFile = this.$refs.refFile.files[0];
        if (selectedFile === undefined){
          return
        }
        var reader = new FileReader();
        reader.readAsDataURL(selectedFile);
        reader.onload = evt => {
          // 检查编码
          // let encoding = this.checkEncoding(evt.target.result);
          // 将csv转换成二维数组
          Papa.parse(selectedFile, {
            encoding:"ANSI",
            complete: res => {
              // UTF8 \r\n与\n混用时有可能会出问题
              let data = res.data;
              if (data[data.length - 1] == "") {
                //去除最后的空行
                data.pop();
              }
              console.log(data);  // data就是文件里面的数据
              // console.log(data[1]);
              // console.log(data[1][0]);
              for(var i=0;i<data.length;i++) {
                this.tableData.push({
                  email: data[i][0],
                  pwd: data[i][1],
                  type: data[i][2],
                  DebtRatio: data[i][3],
                  NumberRealEstateLoansOrLines: data[i][4],
                  NumberOfOpenCreditLinesAndLoans: data[i][5],
                  NumberOfTime30_59DaysPastDueNotWorse: data[i][6],
                  RevolvingUtilizationOfUnsecuredLines: data[i][7],
                  seriousDIqin2yrs: data[i][8],
                  MonthlyIncome: data[i][9],
                  NumberOfTime90DaysLate: data[i][10],
                  NumberOfDependents: data[i][11],
                  NumberOfTimes60_89DaysPastDueNotWorse: data[i][12]
                })
              }
              console.log(this.tableData[0].type);
            }
          });
        };
      },
    importExcel (param) {
      console.log(param);
      let _this = this;
      _this.file2Xce(param).then(item => {
        if (item && item.length > 0) {
          // xlsxJson就是解析出来的json数据,数据格式如下
          // [{sheetName: sheet1, sheet: sheetData }]
          if (item[0] && item[0].sheet && item[0].sheet.length) {
            // _this.tableData = item[0].sheet  //把数据塞到表格预览
            console.log(item[0].sheet, 'item[0].sheet');
            if (item[0].sheet.length > 10) {
              return this.$message.warning('导入的数据大于10条')
            }
          }
        }
      })
        .catch(error => {
          loading.close();
        });
    },
    /**
     * 解析文件
     * @param {Object} file
     */
    file2Xce (file) {
      return new Promise(function (resolve, reject) {
        const reader = new FileReader();
        reader.onload = function (e) {
          const data = e.target.result;
          this.wb = XLSX.read(data, {
            type: "binary"
          });
          const result = [];
          this.wb.SheetNames.forEach(sheetName => {
            result.push({
              sheetName: sheetName,
              sheet: XLSX.utils.sheet_to_json(this.wb.Sheets[sheetName])
            });
          });
          console.log(result) 
          resolve(result);
        };
        reader.readAsBinaryString(file.raw);
      });
    },
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
#dataUpload {
  height: 657px;
  width: 100%;
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