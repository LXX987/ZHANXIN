<template>
  <div id="hello">
    <div id="body">
      <el-row>
        <el-col :span="2">
          <div class="grid-content bg-purple">
            <div class="nochoose-block" @click="personalCenter()">
              <img src='@/assets/pic_personalInfo.png'>
              <p class="txt-nochoose">基本资料</p>
            </div>
            <div class="choose-block">
              <img src='@/assets/pic_assets1.png' class="icon">
              <p class="txt-choose">资产证明</p>
            </div>
            <div class="nochoose-block" @click="honestyProof()">
              <img src='@/assets/pic_reputation.png' class="icon">
              <p class="txt-nochoose">声誉记录</p>
            </div>
            <div class="nochoose-block" @click="myCreditRecord()">
              <img src='@/assets/pic_credit.png' class="icon">
              <p class="txt-nochoose">信贷记录</p>
            </div>
            <div class="nochoose-block" @click="myReport()">
              <img src='@/assets/pic_report.png' class="icon">
              <p class="txt-nochoose">信用报告</p>
            </div>
            <div class="nochoose-block" @click="AccountSecurity()">
              <img src='@/assets/pic_account.png' class="icon">
              <p class="txt-nochoose">账号安全</p>
            </div>
          </div>
        </el-col>
        <el-col :span="22">
          <div class="grid-content bg-purple-light">
            <el-row>
              <div class="grid-content bg-white">
                <p class="txt-title">资产数额</p>
                <p class="txt-highlight">{{asset}}</p>
                <el-button type="primary" plain class="btn-text" @click="centerDialogVisible = true">绑定银行账户</el-button>
                <el-dialog title="绑定银行账户" :visible.sync="centerDialogVisible" width="30%" center>
                  <div>
                    <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                      <el-form-item label="开户银行" prop="bank">
                        <el-input v-model="form.bank"></el-input>
                      </el-form-item>
                      <el-form-item label="账户余额" prop="money">
                        <el-select v-model="form.money" placeholder="请选择账户余额">
                          <el-option label="0-1000元" value="500"></el-option>
                          <el-option label="1000-5000元" value="2500"></el-option>
                          <el-option label="5000-10000元" value="7500"></el-option>
                          <el-option label="1万-5万元" value="25000"></el-option>
                          <el-option label="5万-10万元" value="75000"></el-option>
                          <el-option label="10万-20万元" value="150000"></el-option>
                          <el-option label="20万-50万元" value="350000"></el-option>
                          <el-option label="50万-100万元" value="750000"></el-option>
                          <el-option label="100万元及以上" value="1000000"></el-option>
                        </el-select>
                      </el-form-item>
                    </el-form>
                  </div>
                  <span slot="footer" class="dialog-footer">
                    <el-button @click="resetForm('form')">取 消</el-button>
                    <el-button type="primary" @click="submitForm('form')">确 定</el-button>
                  </span>
                </el-dialog>
              </div>
            </el-row>
            <el-row class="bg-white mar-top">
              <p class="txt-title">已绑定银行账户</p>
              <el-table :data="tableData" height="227" stripe class="table">
                <el-table-column prop="bank" label="开户行" width="180"></el-table-column>
                <el-table-column prop="money" label="资产数额" width="180"></el-table-column>
                <el-table-column label="操作">
                  <template slot-scope="scope">
                    <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">管理</el-button>
                    <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                  </template>
                </el-table-column>
              </el-table>
              <el-dialog title="绑定银行账户" :visible.sync="editDialogVisible" width="30%" center>
                <div>
                  <el-form ref="editform" :model="editform" :rules="editrules" label-width="80px">
                    <el-form-item label="开户银行" prop="bank">
                      <el-input v-model="editform.bank"></el-input>
                    </el-form-item>
                    <el-form-item label="账户余额" prop="money">
                      <el-select v-model="editform.money" placeholder="请选择账户余额">
                        <el-option label="0-1000元" value="500"></el-option>
                        <el-option label="1000-5000元" value="2500"></el-option>
                        <el-option label="5000-10000元" value="7500"></el-option>
                        <el-option label="1万-5万元" value="25000"></el-option>
                        <el-option label="5万-10万元" value="75000"></el-option>
                        <el-option label="10万-20万元" value="150000"></el-option>
                        <el-option label="20万-50万元" value="350000"></el-option>
                        <el-option label="50万-100万元" value="750000"></el-option>
                        <el-option label="100万元及以上" value="1000000"></el-option>
                      </el-select>
                    </el-form-item>
                  </el-form>
                </div>
                <span slot="footer" class="dialog-footer">
                  <el-button @click="resetEditForm('editform')">取 消</el-button>
                  <el-button type="primary" @click="submitEditForm('editform')">确 定</el-button>
                </span>
              </el-dialog>
            </el-row>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
    name:'AssetProof',
    data() {
      return {
        centerDialogVisible: false,
        editDialogVisible: false,
        asset:'0',
        editAddTime:'',
        form: {
          bank: '',
          money: '',
        },
        rules: {
          bank: [{ required: true, message: '请输入开户银行', trigger: 'blur' }],
          money: [{ required: true, message: '请选择账户余额范围', trigger: 'change' }],
        },
        tableData: [{
          userID: '',
          bank: '',
          money: '',
          addTime: ''
        }],
        editform: {
          bank: '',
          money: '',
        },
        editrules: {
          bank: [{ required: true, message: '请输入开户银行', trigger: 'blur' }],
          money: [{ required: true, message: '请选择账户余额范围', trigger: 'change' }],
        },
      };
    },
    mounted:function(){
      this.getMoney();
      this.getAsset();
    },
    methods: {
      getMoney() {
        this.$axios({
          method:"get",
          url: 'http://localhost:8899/asset/getMoney',
          headers: { token:window.sessionStorage.getItem("token")}
        }).then(res=>{
          console.log(res.data);
          console.log(res.data.data.money);
          var money=res.data.data.money;
          console.log("hhh"+money);
          if(money<=1000) { this.asset="0-1000元"; }
          else if (money>1000&&money<=5000) {this.asset="1000 - 5000 元"; }
          else if (money>5000&&money<=10000) { this.asset="5000 - 10000 元"; }
          else if (money>10000&&money<=50000) { this.asset="1万 - 5万 元"; }
          else if (money>50000&&money<=100000) { this.asset="5万 - 10万 元"; }
          else if (money>100000&&money<=200000) { this.asset="10万 - 20万 元"; }
          else if (money>200000&&money<=500000) { this.asset="20万 - 50万 元"; }
          else if (money>500000&&money<=1000000) { this.asset="50万 - 100万 元"; }
          else if (money>1000000) { this.asset="100万元及以上"; }
        },err=>{
          console.log(err);
        })
      },
      getAsset() {
        this.$axios({
          method:"get",
          url: 'http://localhost:8899/asset/assetList',
          headers: { token:window.sessionStorage.getItem("token")}
        }).then(res=>{
          console.log(res.data);
          this.tableData=res.data;
          for(var i=0;i<res.data.length;i++) {
            if(this.tableData[i].money==500) { this.tableData[i].money=="0-1000元"; }
            else if (this.tableData[i].money==2500) { this.tableData[i].money="1000-5000元"; }
            else if (this.tableData[i].money==7500) { this.tableData[i].money="5000-10000元"; }
            else if (this.tableData[i].money==25000) { this.tableData[i].money="1万-5万元"; }
            else if (this.tableData[i].money==75000) { this.tableData[i].money="5万-10万元"; }
            else if (this.tableData[i].money==150000) { this.tableData[i].money="10万-20万元"; }
            else if (this.tableData[i].money==350000) { this.tableData[i].money="20万-50万元"; }
            else if (this.tableData[i].money==750000) { this.tableData[i].money="50万-100万元"; }
            else if (this.tableData[i].money==1000000) { this.tableData[i].money="100万元及以上"; }
          }
        },err=>{
          console.log(err);
        })
      },
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            alert('submit!');
            this.addBank();
            this.centerDialogVisible=false;
            location.reload();
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
        this.centerDialogVisible=false;
      },
      addBank() {
        this.$axios({
          method:"post",
          url: 'http://localhost:8899/asset/insertAsset',
          params:{
            bank:this.form.bank,
            money:this.form.money,
          },
          headers: { token:window.sessionStorage.getItem("token")}
        }).then(res=>{
          if (res.data.msg != "关键数据缺失") {
            console.log(res.data);
            this.$message({
              message: '绑定成功！',
              type: 'success'
            });
          } else{
            this.$message({
              showClose: true,
              message: '缺少关键信息!',
              type: 'error'
            })
          }
        },err=>{
          console.log(err);
        })
      },
      handleEdit(index, row) {
        console.log(index, row);
        this.editDialogVisible=true;
        this.editform.bank=row.bank;
        this.editform.money=row.money;
        this.editAddTime=row.addTime;
      },
      handleDelete(index, row) {
        console.log(index, row);
        this.$axios({
          method:"post",
          url: 'http://localhost:8899/asset/deleteAsset',
          params:{
            addTime:row.addTime,
          },
          headers: { token:window.sessionStorage.getItem("token")}
        }).then(res=>{
          if (res.data.msg != "关键数据缺失") {
            console.log(res.data);
            this.$message({
              message: '删除成功！',
              type: 'success'
            });
            location.reload();
          } else{
            this.$message({
              showClose: true,
              message: '缺少关键信息!',
              type: 'error'
            })
          }
        },err=>{
          console.log(err);
        })
      },
      submitEditForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            if(this.editform.money=="0-1000元") {this.editform.money=500}
            else if(this.editform.money=="1000-5000元") {this.editform.money=2500}
            else if(this.editform.money=="5000-10000元") {this.editform.money=7500}
            else if(this.editform.money=="1万-5万元") {this.editform.money=25000}
            else if(this.editform.money=="5万-10万元") {this.editform.money=75000}
            else if(this.editform.money=="10万-20万元") {this.editform.money=150000}
            else if(this.editform.money=="20万-50万元") {this.editform.money=350000}
            else if(this.editform.money=="50万-100万元") {this.editform.money=750000}
            else if(this.editform.money=="100万元及以上") {this.editform.money=1000000}
            this.editAsset();
            this.editDialogVisible=false;
            location.reload();
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetEditForm(formName) {
        this.$refs[formName].resetFields();
        this.editDialogVisible=false;
      },
      editAsset() {
        this.$axios({
          method:"post",
          url: 'http://localhost:8899/asset/updateAsset',
          params:{
            bank:this.editform.bank,
            money:this.editform.money,
            addTime:this.editAddTime,
          },
          headers: { token:window.sessionStorage.getItem("token")}
        }).then(res=>{
          if (res.data.msg != "关键数据缺失") {
            console.log(res.data);
            this.$message({
              message: '修改成功！',
              type: 'success'
            });
          } else{
            this.$message({
              showClose: true,
              message: '缺少关键信息!',
              type: 'error'
            })
          }
        },err=>{
          console.log(err);
        })
      },
      personalCenter() {
        this.$router.push({path: '/PersonalCenter'});
      },
      assetProof() {
        this.$router.push({path: '/AssetProof'});
      },
      honestyProof() {
        this.$router.push({path: '/HonestyProof'});
      },
      myCreditRecord() {
        this.$router.push({path: '/MyCreditRecord'});
      },
      myReport() {
        this.$router.push({path: '/MyReport'});
      },
      AccountSecurity() {
        this.$router.push({path: '/AccountSecurity'});
      },
    },
  }
</script>

<style scoped>
#hello{
  min-height:1000px;
  background-image: url(../../src/assets/background.png);
  background-size:100% 100%;
  background-attachment:fixed;
  padding-top: 40px;
}
#body {
  background-color:#ffffff;
  width: 70%;
  margin-left: 15%;
  margin-right: 15%;
  border-radius: 20px;
  padding: 10px;
  box-shadow: 0px 0px 20px #6a8a84bb;
}
  .el-col {
    border-radius: 20px;
  }
  .bg-purple {
    background: #1f7c69;
    padding-top: 20px;
    padding-bottom: 20px;
  }
  .bg-purple-light {
    background: #F1F6F9;
    padding: 10px;
  }
  .grid-content {
    border-radius: 20px;
    min-height: 36px;
  }
  .choose-block {
    background: #ffffff;
    border-radius: 60px;
    margin-left: 10px;
    margin-right: 10px;
    padding-top: 40px;
    padding-bottom: 40px;
  }
  .txt-choose {
    font-size: 18px;
    color: #EFB346;
    margin-top: 10px;
  }
  .nochoose-block {
    margin-top: 40px;
    margin-bottom: 40px;
    cursor: pointer;
  }
  .nochoose-block:hover {
    background-color: #186051;
    border-radius: 20px;
  }
  .icon {
    height: 27px;
    width: 27px;
  }
  .txt-nochoose {
    font-size: 18px;
    color: #ffffff;
    margin-top: 10px;
  }
  .bg-white {
    background: #ffffff;
    padding: 45px 100px;
    border-radius: 20px;
  }
  .txt-title {
    font-size: 28px;
    font-weight: bold;
    color: #1F7C69;
    text-align: left;
  }
  .txt-highlight {
    color: #32BDFE;
    font-size: 40px;
    font-weight: bold;
    margin-top: 50px;
  }
  .btn-text {
    font-size: 16px;
    padding-top: 10px;
    margin-top: 30px;
    color: #32BDFE;
    height: 50px;
  }
  >>>.el-dialog__title {
    font-size: 20px;
    font-weight: bold;
  }
  >>>.el-form-item__label {
    font-size: 18px;
  }
  .table {
    margin-top: 40px;
    width: 100%;
  }
  .mar-top {
    margin-top: 10px;
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
  .txt-content {
    text-align: left;
    font-size: 17px;
    margin-top: 20px;
  }
  .el-input {
    width: 80%;
  }
  .el-select {
    width: 80%;
  }
  .mar-left {
    margin-left: 10px;
  }
</style>