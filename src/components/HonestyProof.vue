<template>
  <div id="hello">
    <div id="body">
      <el-row>
        <el-col :span="2">
          <div class="grid-content bg-purple">
            <div class="nochoose-block">
              <img src='@/assets/pic_personalInfo1.png'>
              <p class="txt-nochoose">基本资料</p>
            </div>
            <div class="nochoose-block">
              <img src='@/assets/pic_assets.png' class="icon">
              <p class="txt-nochoose">资产证明</p>
            </div>
            <div class="choose-block">
              <img src='@/assets/pic_reputation1.png' class="icon">
              <p class="txt-choose">声誉记录</p>
            </div>
            <div class="nochoose-block">
              <img src='@/assets/pic_credit.png' class="icon">
              <p class="txt-nochoose">信贷记录</p>
            </div>
            <div class="nochoose-block">
              <img src='@/assets/pic_report.png' class="icon">
              <p class="txt-nochoose">信用报告</p>
            </div>
            <div class="nochoose-block">
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
                      <!-- <span>
                        <p>姓&emsp;&emsp;名：<el-input v-model="inputName" placeholder="请输入内容"></el-input></p>
                        <p>职&emsp;&emsp;业：
                          <el-select v-model="value" placeholder="请选择">
                            <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"></el-option>
                          </el-select>
                        </p>
                        <p>年&ensp;收&ensp;入：<el-input v-model="inputIncome" placeholder="请输入内容"></el-input></p>
                        <p>就业年限：<el-input v-model="inputWorkYear" placeholder="请输入内容"></el-input></p>
                      </span> -->
                      <el-form ref="form" :model="form" label-width="80px">
                        <el-form-item label="姓名">
                          <el-input v-model="form.name"></el-input>
                        </el-form-item>
                        <el-form-item label="职业">
                          <el-select v-model="form.region" placeholder="请选择职业类型">
                            <el-option label="身份证" value="shanghai"></el-option>
                          </el-select>
                        </el-form-item>
                        <el-form-item label="年收入">
                          <el-input v-model="form.name"></el-input>
                        </el-form-item>
                        <el-form-item label="就业年限">
                          <el-input v-model="form.name"></el-input>
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
                    <el-descriptions-item label="姓名">&emsp;&emsp;{{userName}}</el-descriptions-item>
                    <el-descriptions-item label="职业">&emsp;&emsp;{{occupation_str}}</el-descriptions-item>
                    <el-descriptions-item label="年收入">&emsp;{{annual_income}}元</el-descriptions-item>
                    <el-descriptions-item label="就业年限">{{working_years}}</el-descriptions-item>
                  </el-descriptions>
                </div>
              </el-col>
              <el-col :span="12">
                <div class="grid-content">
                  <p class="txt-title">实名信息<el-button type="text" class="btn-text" @click="shimingDialogVisible = true">去实名</el-button>
                    <el-dialog
                      title="编辑个人资料"
                      :visible.sync="shimingDialogVisible"
                      width="30%">
                      <!-- <span>
                        <p>姓&emsp;&emsp;名：<el-input v-model="inputName1" placeholder="请输入内容"></el-input></p>
                        <p>证件类型：
                          <el-select v-model="chooseIDtype" placeholder="请选择">
                            <el-option v-for="item in IDtypeOptions" :key="item.value" :label="item.label" :value="item.value"></el-option>
                          </el-select>
                        </p>
                        <p>证&ensp;件&ensp;号：<el-input v-model="inputIDcard" placeholder="请输入内容"></el-input></p>
                      </span> -->
                      <el-form ref="form" :model="form" label-width="80px">
                        <el-form-item label="姓名">
                          <el-input v-model="form.name"></el-input>
                        </el-form-item>
                        <el-form-item label="证件类型">
                          <el-select v-model="form.region" placeholder="请选择证件类型">
                            <el-option label="身份证" value="shanghai"></el-option>
                          </el-select>
                        </el-form-item>
                        <el-form-item label="证件号">
                          <el-input v-model="form.name"></el-input>
                        </el-form-item>
                      </el-form>
                      <span slot="footer" class="dialog-footer">
                        <el-button @click="shimingDialogVisible = false">取 消</el-button>
                        <el-button type="primary" @click="shiMing()">确 定</el-button>
                      </span>
                    </el-dialog>
                  </p>
                  <el-descriptions title="" :column="1">
                    <el-descriptions-item label="实名认证">{{authentication}}</el-descriptions-item>
                    <el-descriptions-item label="证件类型">{{IDtype}}</el-descriptions-item>
                    <el-descriptions-item label="证件号">&emsp;{{IDcard}}</el-descriptions-item>
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
    name:'HonestyProof',
    data() {
      return {
        dialogVisible: false,
        innerVisible: false,
        shimingDialogVisible:false,
        form: {
          name: '',
          region: '',
          date1: '',
          date2: '',
          delivery: false,
          type: [],
          resource: '',
          desc: ''
        }
      };
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
  .el-row {
    /* margin-bottom: 20px; */
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