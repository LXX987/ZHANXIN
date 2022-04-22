<template>
  <div class="hello">
     
    <div class="content">
      <el-row>
        <el-col :span="14" class="background">
        
            </el-col>
        <el-col :span="10">
            <div class="log_block" style="text-align:center">
              <div class="login">
                <h1>登录</h1>
                <div class="editview">
                  <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="0px" class="demo-ruleForm">
                    <el-form-item prop="userid"> <li><el-input v-model="ruleForm.userid" placeholder="请输入邮箱" style="width: 300px;"></el-input></li></el-form-item>
                    <el-form-item prop="password"> <li><el-input placeholder="请输入密码" v-model="ruleForm.password" show-password style="width: 300px;"></el-input></li></el-form-item>
                    <li><el-button type="primary" round class="btn_login" @click="login">登录</el-button></li>
                     <br>
                     <el-radio v-model="radio" label="1" id="protocol"><u>用户隐私服务协议</u></el-radio>
                  </el-form>
                </div>
              </div>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Login',
  data () {
    var checkId = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('用户邮箱不能为空!'));
        }
        };
        var checkPass = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('密码不能为空!'));
          }
        };
     return {
       radio: '0',
      ruleForm: {
      userid: '',
      password:'',
      },
      rules: {
           userid: [
          {validator: checkId,trigger: "blur"}
          ],
           password: [
          {validator: checkPass,trigger: "blur"}
          ]
        }
      }  
  },
}
</script>

<style scoped>
.body {
  background-image: url(../../src/assets/background.png);
  background-size:1024px;
}
.logocenter{
  text-align: center;
  margin-left: 200dp;
}

.log_block{
   background-color: #BFDDE3;
    margin-top:0px;
    height: 830px;
}
.login{
  height:600px;
    padding-top: 200px;
   
}
.editview{
    margin-top: 0px;
    margin-bottom: 0px;
    text-align:center;
}
>>>.el-input__inner{
    border-radius:20px;
    border-width:0px;
    height: 46px;
    background-color: #EFEEEE;
}
li{
     list-style-type:none;
     padding-top:10px;
}
.btn_login{
    height: 46px;
    width: 300px;
    background-color: #214375;
    border-width:0px;
}
.el-row {
    margin-bottom: 20px;
  }
  .el-col {
    border-radius: 4px;
  }
h1, h2 {
  font-weight: normal;
  font-size: 20px;
  margin: 8px;
}

.hello{
  height:800px;
}

.description{
  margin-top: 20px;
}
>>>.el-form-item__error{
  position:inherit;
}
</style>