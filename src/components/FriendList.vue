<template>
  <div class="rate">
    <div class="body">
      <div class="illu" :style="backgroundDiv"></div>
      <el-card shadow="always" class="top-card">
        <div class="abstract">
          <h1 style="font-size:28px">邀请朋友进行信用评分，有助于我们更好地为您服务</h1>
          <div class="tip"><p>人脉圈的大小与朋友的信用分，是我们对您进行评分的重要依据之一。</p></div>
        </div>
        <div class="generation">
          <h2 style="font-size:20px">我的邀请码</h2>
          <el-descriptions class="margin-top"  :column="1"  border style="margin-bottom:20px;font-size:18px">
            <el-descriptions-item>
              <template slot="label" style="width:120px;font-size:18px">
                <i class="el-icon-user"></i>
                我的邀请码
              </template>
              {{my_code}}
            </el-descriptions-item>
          </el-descriptions>
          <el-button type="primary" style="margin-bottom:20px" @click="copy()">一键复制</el-button>
        </div>
        <div class="fillin">
          <h2 style="font-size:20px">好友邀请码</h2>
          <el-input v-model="invited_code" placeholder="请输入好友的验证码" style="width: 300px;"></el-input>
          <el-button type="primary" style="margin-bottom:50px" @click="inputInviteCode()">确定</el-button>
        </div>
      </el-card>

      <div class="illu" :style="backgroundDiv"><span class="title">好友列表</span></div>
      <div class="friend-list">
        <div class="hint" v-if="!friends">暂无好友</div>
        <el-row :gutter="20">
          <el-col :span="8" v-for="(friend) in friends"  :key="friend.user_id">
            <el-card class="friend-card" >
              <p class="name-txt"><el-avatar :size="50" :src="friend.user_avatar"></el-avatar>
              <span class="name">{{friend.user_name}}</span>
                <el-button type="danger" icon="el-icon-delete" class="delete_friend" circle @click="deleteFriend(friend.user_id)"></el-button></p>
              <div class="text item">
                <p class="descri-txt">邮箱：{{friend.user_email}}</p>
                <p class="descri-txt">信用分：{{friend.total_score}}分</p>
              </div>
            </el-card>
          </el-col>
        </el-row>
        
      </div>
    </div>
    <!-- <div class="body">
      <div class="my-code">
        <i class="el-icon-star-on"></i>
        我的邀请码：{{my_code}}
      </div>
      <div class="tool-bar">
        <div class="invite-code">
          <i class="el-icon-edit"></i>
        <input type="text" placeholder="在此处填写他人的邀请码" v-model="invite_code">
        <button @click="inputInviteCode()">提交</button>
        </div>
      </div>
    </div> -->
  </div>
</template>

<script>
export default {
  name: "FriendList",
  data() {
    return {
      friends: [      ],
      user_id: -1,
      invited_code: "",
      my_code: "",
      circleUrl: ""
    }
  },
  created(){

  },
  mounted(){
    this.getInviteode();
    this.getUserId();
  },
  methods: {
    getUserId() {
      this.$axios({
        method: "get",
        url: 'http://localhost:8899/user/userInfo',
        headers: {token: window.sessionStorage.getItem("token")},
      }).then(res => {
        console.log(res);
        this.user_id = res.data.data.user_id;
        this.getFriendList();
        // this.getMyCode();
      }, err => {
        console.log(err);
      })
    },
    getFriendList() {
      this.$axios({
        method:"get",
        url: 'http://localhost:8899/user/friends',
        headers:{token:window.sessionStorage.getItem("token")}
        // params:{
        //   id: this.user_id
        // }
      }).then(res => {
        console.log("我的好友数据："+res);
        this.friends = res.data.data;
      }, err => {
        console.log(err);
      })
    },
    inputInviteCode() {
      if(this.invited_code != this.my_code)
            { 
               this.$axios({
                method:"post",
                url: 'http://localhost:8899/InviteCode/friendCode',
                params:{
                    invitedCode:this.invited_code
                },
                headers: { token:window.sessionStorage.getItem("token")}
            }).then(res=>{
                this.$message({
                  type: 'success',
                  message: '填写成功!'
                });
            }, err => {
             this.$message.error('该邀请码不存在！')
              })
            }else{
                this.$message.error('不能填写自己的邀请码')
            }
    },
    copy(){
      //创建input标签
      var input = document.createElement('input')
      //将input的值设置为需要复制的内容
      input.value = this.my_code;
      //添加input标签
      document.body.appendChild(input)
      //选中input标签
      input.select()
      //执行复制
      document.execCommand('copy')
      //成功提示信息
      this.$message.success('复制成功')
      //移除input标签
      document.body.removeChild(input)
    },
    deleteFriend(index) {
      console.log("删除好友："+index);
      this.$axios({
        method: "delete",
        url: 'http://localhost:8899/user/friends',
        params:{
          friend_id:index
        },
        headers: {token: window.sessionStorage.getItem("token")},
      }).then(res => {
        console.log(res);
        location.reload();
      }, err => {
        console.log(err);
      })
    },
    getInviteode() {
          this.$axios({
          method:"post",
          url: 'http://localhost:8899/InviteCode/generareCode',
          headers: { token:window.sessionStorage.getItem("token")}
      }).then(res=>{
          console.log(res.data.data.inviteCode)
          this.my_code = res.data.data.inviteCode
      })
    },
  }
}
</script>

<style scoped>

.my-code{
  padding-right: 40%;
  font-size: 18px;
}
.hint{
  font-size: 30px;
  font-weight:bold;
  padding-right: 18%;
  padding-top: 18%;
}
.friend-list{
  padding-left: 220px;
  padding-right: 220px;
  /* height:800px; */
  padding-bottom: 50px;
}
.invite-code {
  padding-left: 65%;
  display:flex;
  align-items:center;/*所有子元素都垂直居中了*/
}
.el-menu-item {
  padding:0px 0;
  color: #000;
  list-style-type:none;
  text-align:center;
  padding-left:30px;
  padding-right:30px;
  font-size:20px;
  height: 100px;
  display:flex;
  align-items:center;/*所有子元素都垂直居中了*/
}
.el-menu--horizontal>.el-menu-item.is-active {
  border-bottom: 0.010417rem solid #409EFF;
  color: #fff;
  background-color: #409EFF;
}
.name-txt {
  text-align: left;
  margin-left: 40px;
}
.name {
  font-size: 34px;
  margin-left: 20px;
  font-weight:bold;
  bottom: 10px;
  position: relative;
}
.text {
  font-size: 18px;
}
.item {
  padding: 18px 0;
}
.descri-txt {
  text-align: left;
  margin-left: 50px;
  margin-bottom: 10px;
}
.friend-card {
  /* width: 480px; */
  margin-top: 40px;
}

.el-menu{
  height: 100px;
}
.el-menu.el-menu--horizontal {
  border-bottom: solid 0.005208rem #fff8f8;
}
.avatar{
  height: 50%;
}
.body{
  background-color: #F7F9FB;
}
.delete_friend{
  position: relative;
  left: 150px;
  bottom: 20px;
}
.illu{
    height:60px;
    text-align: left;
}
.title{
    color:rgb(59, 59, 59);
    font-size: 30px;
    font-weight:bold;
    margin-top: 0px;
    margin-left: 220px;
    line-height:100px;
    margin-bottom: 20px;
}
.tip {
    padding: 8px 16px;
    background-color: #ecf8ff;
    border-radius: 4px;
    border-left: 5px solid #50bfff;
    margin: 20px 0;
    margin-bottom: 50px;
}
.top-card{
    margin-left: 220px;
    margin-right: 220px;
    padding-right: 50px;
    text-align: left;
    padding-left: 50px;
}
>>>.el-descriptions__body .el-descriptions__table {
    border-collapse: collapse;
    width: inherit;
    table-layout: fixed;
}
</style>
