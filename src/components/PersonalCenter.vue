<template>
  <div id="hello">
    <div id="body">
      <el-row>
        <el-col :span="2">
          <div class="grid-content bg-purple">
            <div class="choose-block">
              <img src='@/assets/pic_personalInfo1.png'>
              <p class="txt-choose">基本资料</p>
            </div>
            <div class="nochoose-block" @click="assetProof()">
              <img src='@/assets/pic_assets.png' class="icon">
              <p class="txt-nochoose">资产证明</p>
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
            <el-row class="bg-white">
              <el-col :span="12">
                <div class="grid-content">
                  <p class="txt-title">个人资料<el-button type="text" class="btn-text" @click="dialogVisible = true">编辑</el-button>
                    <el-dialog
                      title="编辑个人资料"
                      :visible.sync="dialogVisible"
                      width="30%">
                      <el-form ref="infoForm" :model="infoForm" label-width="80px">
                        <el-form-item label="姓名">
                          <el-input v-model="infoForm.name"></el-input>
                        </el-form-item>
                        <el-form-item label="职业">
                          <el-select v-model="infoForm.occupation" placeholder="请选择职业类型">
                            <el-option label="采矿业" value="110000"></el-option>
                            <el-option label="制造业" value="106600"></el-option>
                            <el-option label="电力、热力、燃气及水生产和供应业" value="138000"></el-option>
                            <el-option label="建筑业" value="760000"></el-option>
                            <el-option label="批发和零售业" value="106000"></el-option>
                            <el-option label="交通运输、仓储和邮政业" value="136000"></el-option>
                            <el-option label="住宿和餐饮业" value="55000"></el-option>
                            <el-option label="信息传输、软件和信息技术服务业" value="190000"></el-option>
                            <el-option label="房地产业" value="100000"></el-option>
                            <el-option label="租赁和商务服务业" value="130000"></el-option>
                            <el-option label="科学研究和技术服务业" value="140000"></el-option>
                            <el-option label="水利、环境和公共设施管理业" value="97000"></el-option>
                            <el-option label="居民服务、修理和其他服务业" value="73000"></el-option>
                            <el-option label="教育" value="91000"></el-option>
                            <el-option label="卫生和社会工作" value="89000"></el-option>
                            <el-option label="文化、体育和娱乐业" value="150000"></el-option>
                            <el-option label="其他" value="10000"></el-option>
                          </el-select>
                        </el-form-item>
                        <el-form-item label="年收入">
                          <el-input v-model="infoForm.annual_income"></el-input>
                        </el-form-item>
                        <el-form-item label="就业年限">
                          <el-input v-model="infoForm.working_years"></el-input>
                        </el-form-item>
                        <el-form-item label="联系电话">
                          <el-input v-model="infoForm.phone"></el-input>
                        </el-form-item>
                      </el-form>
                      <span slot="footer" class="dialog-footer">
                        <el-button @click="dialogVisible = false">取 消</el-button>
                        <el-button type="primary" @click="commitchange()">确 定</el-button>
                        <el-dialog
                          width="30%"
                          title="确认修改？"
                          :visible.sync="innerVisible"
                          append-to-body center>
                          <span slot="footer" class="dialog-footer">
                            <el-button @click="innerVisible = false">取 消</el-button>
                            <el-button type="primary" @click="changeInfo()">确 定</el-button>
                          </span>
                        </el-dialog>
                      </span>
                    </el-dialog>
                  </p>
                  <el-descriptions title="" :column="1">
                    <el-descriptions-item label="姓名">&emsp;&emsp;{{name}}</el-descriptions-item>
                    <el-descriptions-item label="职业">&emsp;&emsp;{{occupation_str}}</el-descriptions-item>
                    <el-descriptions-item label="年收入">&emsp;{{annual_income}}元</el-descriptions-item>
                    <el-descriptions-item label="就业年限">{{working_years}}</el-descriptions-item>
                    <el-descriptions-item label="联系电话">{{phone}}</el-descriptions-item>
                  </el-descriptions>
                </div>
              </el-col>
              <el-col :span="12">
                <div class="grid-content">
                  <p class="txt-title">实名信息
                    <el-button type="text" class="btn-text" disabled v-if="authentication=='已实名'">已实名</el-button>
                    <el-button type="text" class="btn-text" @click="shimingDialogVisible = true" v-else>去实名</el-button>
                    <el-dialog
                      title="编辑个人资料"
                      :visible.sync="shimingDialogVisible"
                      width="30%">
                      <el-form ref="idenForm" :model="idenForm" label-width="80px">
                        <el-form-item label="姓名">
                          <el-input v-model="idenForm.name"></el-input>
                        </el-form-item>
                        <el-form-item label="证件类型">
                          <el-select v-model="idenForm.type" placeholder="请选择证件类型">
                            <el-option label="身份证" value="1"></el-option>
                          </el-select>
                        </el-form-item>
                        <el-form-item label="证件号">
                          <el-input v-model="idenForm.IDcard"></el-input>
                        </el-form-item>
                      </el-form>
                      <span slot="footer" class="dialog-footer">
                        <el-button @click="shimingDialogVisible = false">取 消</el-button>
                        <el-button type="primary" @click="shiMing()">确 定</el-button>
                      </span>
                    </el-dialog>
                  </p>
                  <el-alert :closable="false" title="未成年，暂无信贷资格" type="warning" v-if="adult=='未成年'"></el-alert>
                  <el-descriptions title="" :column="1">
                    <el-descriptions-item label="实名认证">{{authentication}}</el-descriptions-item>
                    <el-descriptions-item label="证件类型">{{IDtype}}</el-descriptions-item>
                    <el-descriptions-item label="证件号">&emsp;{{IDcard}}</el-descriptions-item>
                    <el-descriptions-item label="是否成年">&emsp;{{adult}}</el-descriptions-item>
                  </el-descriptions>
                </div>
              </el-col>
            </el-row>
            <el-row class="bg-white mar-top">
              <p class="txt-title">实名认证权益</p>
              <el-col :span="12">
                <div class="grid-content">
                  <p class="sub-title">实名</p>
                  <p class="txt-content">1. 可以获得信用评分。</p>
                  <p class="txt-content">2. 完善的个人资料有助于更准确的信用评分。</p>
                  <p class="txt-content">3. 进行心理测评后会获得相应信用得分。</p>
                  <p class="txt-content">4. 观看金融教育视频会获得相应信用加分。</p>
                  <p class="txt-content">5. 可以绑定银行账户，查询资产得分、信贷记录。</p>
                </div>
              </el-col>
              <el-col :span="12">
                <div class="grid-content">
                  <p class="sub-title">未实名</p>
                  <p class="txt-content">1. 无法获得信用评分。</p>
                  <p class="txt-content">2. 可以进行心理测评。</p>
                  <p class="txt-content">3. 可以观看金融教育视频</p>
                </div>
              </el-col>
            </el-row>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
    name:'PersonalCenter',
    data() {
      return {
        dialogVisible: false,
        innerVisible: false,
        shimingDialogVisible:false,
        name:'',
        userName:'',
        userEmail:'',
        occupation:'',
        occupation_str:'',
        annual_income:0,
        working_years:'',
        authentication:'',
        IDtype:'',
        IDcard:'',
        phone:'',
        adult:'',
        infoForm: {
          name: '',
          occupation: '',
          annual_income: '',
          working_years: '',
          phone:'',
        },
        idenForm: {
          name: '',
          type: '',
          IDcard: '',
        }
      };
    },
    mounted:function(){
      this.getMyInfo()
    },
    methods: {
      getMyInfo() {
        this.$axios({
          method:"get",
          url: 'http://localhost:8899/user/userInfo',
          headers: { token:window.sessionStorage.getItem("token")}
        }).then(res=>{
          console.log('我的信息数据：', res.data);
          this.userName=res.data.data.userName;
          if(this.userName!=null) {
            this.name=this.userName.substr(0,1);
            var len=this.userName.length;
            if(len==2) {
              this.name=this.name+"*";
            } else {
              for (var i=1;i<len-1;i++) {
                this.name=this.name+"*";
              }
              this.name=this.name+this.userName.substr(len-1,1);
            }
          } else {
            this.name='暂无数据';
          }
          this.userEmail=res.data.data.userEmail;
          this.occupation=res.data.data.occupation;
          if(this.occupation==10000){
            this.occupation_str='其他';
          } else if(this.occupation==150000){
            this.occupation_str='文化、体育和娱乐业';
          } else if(this.occupation==89000){
            this.occupation_str='卫生和社会工作';
          } else if(this.occupation==91000){
            this.occupation_str='教育';
          } else if(this.occupation==73000){
            this.occupation_str='居民服务、修理和其他服务业';
          } else if(this.occupation==97000){
            this.occupation_str='水利、环境和公共设施管理业';
          } else if(this.occupation==140000){
            this.occupation_str='科学研究和技术服务业';
          } else if(this.occupation==130000){
            this.occupation_str='租赁和商务服务业';
          } else if(this.occupation==100000){
            this.occupation_str='房地产业';
          } else if(this.occupation==190000){
            this.occupation_str='信息传输、软件和信息技术服务业';
          } else if(this.occupation==55000){
            this.occupation_str='住宿和餐饮业';
          } else if(this.occupation==136000){
            this.occupation_str='交通运输、仓储和邮政业';
          } else if(this.occupation==106000){
            this.occupation_str='批发和零售业';
          } else if(this.occupation==760000){
            this.occupation_str='建筑业';
          } else if(this.occupation==138000){
            this.occupation_str='电力、热力、燃气及水生产和供应业';
          } else if(this.occupation==106600){
            this.occupation_str='制造业';
          } else if(this.occupation==110000){
            this.occupation_str='采矿业';
          }
          if (res.data.data.annual_income!=null) {
            this.annual_income=res.data.data.annual_income;
          }
          this.working_years=res.data.data.working_years;
          if(res.data.data.IDtype==1) {
            this.IDtype='身份证';
          } else {
            this.IDtype=res.data.data.IDtype;
          }
          if(res.data.data.IDcard!=null) {
            this.IDcard=res.data.data.IDcard.substr(0,2)+"**************"+res.data.data.IDcard.substr(16,2);
          } else {
            this.IDcard=res.data.data.IDcard;
          }
          if(res.data.data.authentication) {
            this.authentication="已实名";
          } else {
            this.authentication="未实名";
          }
          this.phone=res.data.data.phone;
          this.adult=res.data.data.adult;
          this.infoForm.name=this.userName;
          this.infoForm.occupation=this.occupation_str;
          this.infoForm.annual_income=this.annual_income;
          this.infoForm.working_years=this.working_years;
          this.infoForm.phone=this.phone;
          this.idenForm.name=this.userName;
          this.idenForm.type=this.IDtype;
          this.idenForm.IDcard=this.IDcard;
        },err=>{
          console.log(err);
        })
      },
      shiMing() {
        //console.log(this.idenForm.name+" "+this.idenForm.IDcard+" "+this.idenForm.type)
        if(this.idenForm.name==''||this.idenForm.IDcard=='') {
          this.$message({
            message: '姓名或身份证号不能为空！',
            type: 'warning'
          });
        } else {
          this.$axios({
            method:"post",
            url: 'http://localhost:8899/user/updateAuthentication',
            params:{
                userName:this.idenForm.name,
                IDcard:this.idenForm.IDcard,
                IDtype:this.idenForm.type
            },
            headers: { token:window.sessionStorage.getItem("token")}
          }).then(res=>{
            console.log('实名认证数据：', res.data);
            if(res.data.msg=="Success"){
              if(res.data.data.success==1) {
                this.shimingDialogVisible=false,
                this.$message({
                  message: '实名认证成功！',
                  type: 'success'
                });
                location.reload();
              } else if(res.data.data.success==2){
                this.$message.error('姓名或身份证号错误！');
              } else {
                this.$message({
                  message: res.data.data.result,
                  type: 'warning'
                });
              }
            }
          },err=>{
            console.log(err);
          })
        }
      },
      changeInfo() {
        //console.log(this.infoForm.name+" "+this.infoForm.occupation+" "+this.infoForm.annual_income+" "+ this.infoForm.working_years);
        this.$axios({
          method:"post",
          url: 'http://localhost:8899/user/updateUserInfo',
          params:{
              userName:this.infoForm.name,
              annualIncome:this.infoForm.annual_income,
              workingYears:this.infoForm.working_years,
              occupation:this.infoForm.occupation,
              userEmail:this.userEmail,
              phone:this.infoForm.phone,
          },
          headers: { token:window.sessionStorage.getItem("token")}
        }).then(res=>{
          console.log('修改用户信息数据：', res.data);
          if(res.data.msg=="Success"){
            console.log(this.input_name),
            this.infoDialogVisible=false,
            this.confirmDialogVisible=false,
            this.$message({
              message: '信息修改成功！',
              type: 'success'
            });
            location.reload();
          }
        },err=>{
          console.log(err);
        })
      },
      commitchange() {
        if(this.infoForm.occupation==this.occupation_str) {
          this.infoForm.occupation=this.occupation;
        }
        if(this.infoForm.name=='') {
          this.$message({
            message: '姓名不能为空！',
            type: 'warning'
          });
        } else {
          this.innerVisible = true
        }
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
    height: 788px;
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
  .btn-text {
    font-size: 16px;
    margin-left: 50px;
  }
  .el-descriptions {
    margin-top: 30px;
  }
  >>>span.el-descriptions-item__label:not(.is-bordered-label) {
    font-size: 18px;
  }
  >>>span.el-descriptions-item__content {
    font-size: 18px;
  }
  .mar-top {
    margin-top: 10px;
  }
  .sub-title {
    text-align: left;
    font-size: 20px;
    font-weight: bold;
    color: #52AD9C;
    margin-top: 30px;
    margin-bottom: 5px;
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
</style>