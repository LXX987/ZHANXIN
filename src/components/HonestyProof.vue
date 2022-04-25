<template>
  <div id="hello">
    <div id="body">
      <el-row>
        <el-col :span="2">
          <div class="grid-content bg-purple">
            <div class="nochoose-block">
              <img src='@/assets/pic_personalInfo.png'>
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
              <div class="grid-content">
                <p class="txt-title">声誉记录</p>
                <p class="sub-title">守法情况</p>
                <p class="txt-content">犯罪记录：</p>
                <div class="upload-btn">
                  <el-upload
                    class="avatar-uploader"
                    action="http://localhost:8888/picture/uploadDonation"
                    :headers="headers"
                    :show-file-list="false"
                    :on-success="handleAvatarSuccess"
                    :before-upload="beforeAvatarUpload">
                    <p class="txt-upload">
                      <el-button type="text" class="btn-text">点击上传无犯罪记录证明</el-button>
                      只能上传jpg/png文件，且不超过1mb
                    </p>
                  </el-upload>
                </div>
                <el-row>
                  <el-col :span="4"><p class="txt-tip">材料审核情况：</p></el-col>
                  <el-col :span="20">
                    <el-steps :active="activeCrime" finish-status="success">
                      <el-step title="步骤 1：上传照片"></el-step>
                      <el-step title="步骤 2：审核中"></el-step>
                      <el-step title="步骤 3：审核通过"></el-step>
                    </el-steps>
                  </el-col>
                </el-row>
                <p class="sub-title">公益记录</p>
                <p class="txt-content">献血次数：</p>
                <div class="upload-btn">
                  <el-upload
                    class="avatar-uploader"
                    action="http://localhost:8888/picture/uploadDonation"
                    :headers="headers"
                    :show-file-list="false"
                    :on-success="handleAvatarSuccess"
                    :before-upload="beforeAvatarUpload">
                    <p class="txt-upload">
                      <el-button type="text" class="btn-text">点击上传献血证明</el-button>
                      只能上传jpg/png文件，且不超过1mb
                    </p>
                  </el-upload>
                </div>
                <el-row>
                  <el-col :span="4"><p class="txt-tip">材料审核情况：</p></el-col>
                  <el-col :span="20">
                    <el-steps :active="activeBlood" finish-status="success">
                      <el-step title="步骤 1：上传照片"></el-step>
                      <el-step title="步骤 2：审核中"></el-step>
                      <el-step title="步骤 3：审核通过"></el-step>
                    </el-steps>
                  </el-col>
                </el-row>
                <p class="txt-content">志愿服务时长：</p>
                <div class="upload-btn">
                  <el-upload
                    class="avatar-uploader"
                    action="http://localhost:8888/picture/uploadDonation"
                    :headers="headers"
                    :show-file-list="false"
                    :on-success="handleAvatarSuccess"
                    :before-upload="beforeAvatarUpload">
                    <p class="txt-upload">
                      <el-button type="text" class="btn-text">点击上传志愿服务证明</el-button>
                      只能上传jpg/png文件，且不超过1mb
                    </p>
                  </el-upload>
                </div>
                <el-row>
                  <el-col :span="4"><p class="txt-tip">材料审核情况：</p></el-col>
                  <el-col :span="20">
                    <el-steps :active="activeVolunteer" finish-status="success">
                      <el-step title="步骤 1：上传照片"></el-step>
                      <el-step title="步骤 2：审核中"></el-step>
                      <el-step title="步骤 3：审核通过"></el-step>
                    </el-steps>
                  </el-col>
                </el-row>
                <p class="txt-content">捐款：</p>
                <div class="upload-btn">
                  <el-upload
                    class="avatar-uploader"
                    action="http://localhost:8888/picture/uploadDonation"
                    :headers="headers"
                    :show-file-list="false"
                    :on-success="handleAvatarSuccess"
                    :before-upload="beforeAvatarUpload">
                    <p class="txt-upload">
                      <el-button type="text" class="btn-text">点击上传捐款证明</el-button>
                      只能上传jpg/png文件，且不超过1mb
                    </p>
                  </el-upload>
                </div>
                <el-row>
                  <el-col :span="4"><p class="txt-tip">材料审核情况：</p></el-col>
                  <el-col :span="20">
                    <el-steps :active="activeDonation" finish-status="success">
                      <el-step title="步骤 1：上传照片"></el-step>
                      <el-step title="步骤 2：审核中"></el-step>
                      <el-step title="步骤 3：审核通过"></el-step>
                    </el-steps>
                  </el-col>
                </el-row>
                <p class="sub-title">缴费记录</p>
                <p class="txt-content">话费缴费：</p>
                <div class="upload-btn">
                  <el-upload
                    class="avatar-uploader"
                    action="http://localhost:8888/picture/uploadDonation"
                    :headers="headers"
                    :show-file-list="false"
                    :on-success="handleAvatarSuccess"
                    :before-upload="beforeAvatarUpload">
                    <p class="txt-upload">
                      <el-button type="text" class="btn-text">点击上传话费缴费记录</el-button>
                      只能上传jpg/png文件，且不超过1mb
                    </p>
                  </el-upload>
                </div>
                <el-row>
                  <el-col :span="4"><p class="txt-tip">材料审核情况：</p></el-col>
                  <el-col :span="20">
                    <el-steps :active="activeBlood" finish-status="success">
                      <el-step title="步骤 1：上传照片"></el-step>
                      <el-step title="步骤 2：审核中"></el-step>
                      <el-step title="步骤 3：审核通过"></el-step>
                    </el-steps>
                  </el-col>
                </el-row>
              </div>
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
        activeCrime: 0,
        activeBlood: 0,
        activeVolunteer: 0,
        activeDonation: 0,
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
    height: 1123px;
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
    border: 0px;
    margin: 0px;
    padding: 0px;
    text-align: left;
    margin-right: 40px;
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
    font-size: 18px;
    margin-top: 20px;
  }
  .el-input {
    width: 80%;
  }
  .el-select {
    width: 80%;
  }
  .txt-tip {
    text-align: left;
    font-size: 16px;
    margin-top: 20px;
    color: #666666;
  }
  .el-steps {
    margin-top: 20px;
  }
  >>>.el-step__icon {
    display: grid;
  }
  .upload-btn {
    text-align:left;
  }
  .txt-upload {
    text-align: left;
    font-size: 12px;
    margin-top: 20px;
    color:#828282;
  }
</style>