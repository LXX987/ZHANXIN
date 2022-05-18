<template>
    <div class="loginbody">
        <el-card>
            <el-row>
                <img id="logo" src="@/assets/csLoginLogo.png" />
            </el-row>
            <el-row>
                <h1>瞻信信用评估平台客服端</h1>
            </el-row>
            <el-row>
                <el-form :model="ruleForm" ref="ruleForm" label-width="0px" class="demo-ruleForm">
                    <el-form-item prop="userid">
                        <el-input v-model="ruleForm.userid" placeholder="请输入邮箱" class="input_box"></el-input>
                    </el-form-item>
                    <el-form-item prop="password">
                        <el-input placeholder="请输入密码" v-model="ruleForm.password" show-password class="input_box"></el-input>
                    </el-form-item>
                    <el-button type="primary" round class="btn_login" @click="submit">登录</el-button>
                    <br>
                </el-form>
            </el-row>
        </el-card>
    </div>
</template>
<script>
export default {
    name: 'csLogin',
    data() {
        return {
            input3: '',
            input4: '',
            ruleForm: {
        userid: '',
        password:'',
      },
        }
    },
    methods: {
        submit() {
            this.$axios({
        method:"post",
        url: 'http://localhost:8899/user/login',
        params:{
            userEmail:this.ruleForm.userid,
            userPassword:this.ruleForm.password,
            userType: 1
        }
      }).then(res=>{
          console.log(res.data.msg);
          if(res.data.msg=="登录成功"){
              console.log(res);
              window.sessionStorage.setItem("token",res.data.token);
              this.$router.push('/cs')
          }
          else{
            this.$message({
              showClose: true,
              message: '登录失败!'
              });
          }
      },err=>{
          console.log(err);
      })
        }
    },
    mounted() {

    }
}
</script>
<style scoped>
.el-form-item {
    margin-top: 40px;
}
.input_box {
  width: 80%;
}
.el-button {
    width: 80%;
    height: 65px;
    background: #456aee;
    font-size: 22px;
    color: #fff;
    margin-top: 20px;
}
/* .el-input__icon {
    font-size: 25px;
    margin: 0px 15px 0px 15px;
    line-height: 60px;
}
.el-input {
    width: 80%;
    margin: 30px 0px 0px 0px;
}
.el-input--prefix .el-input__inner {
    padding-left: 55px;
    height: 60px;
    font-size: 18px;
} */
h1 {
    font-size: 30px;
    font-weight: 500;
    color: #0079FE;
}
#logo {
    width: 200px;
    height: 200px;
    margin: 30px 0px 30px 0px;
}
.loginbody {
    width: 100%;
    height: 100%;
}
.el-card {
    width: 600px;
    height: 700px;
    margin: 6% 0% 0% 34.5%;
}
</style>