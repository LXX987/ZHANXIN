<template>
  <div class="rate">
    <div class="body">
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

        <div class="friend-list">
          <div class="hint" v-if="!friends">
            暂无好友
          </div>
            <el-card class="friend-card" v-for="(friend) in friends">
              <el-row class="avatar">
                    <el-avatar :size="50" :src="friend.user_avatar"></el-avatar>
                <el-button type="danger" icon="el-icon-delete" class="delete_friend" circle></el-button>
              </el-row>
              <div class="text item">
                姓名：{{friend.user_name}} , 邮箱：{{friend.user_email}} , 信用分{{friend.total_score}}
              </div>
            </el-card>
        </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "FriendList",
  data() {
    return {
      friends: [],
      user_id: -1,
      invite_code: "",
      my_code: "",
      circleUrl: ""
    }
  },
  created(){

  },
  mounted(){
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
        this.getMyCode();
      }, err => {
        console.log(err);
      })
    },
    getFriendList() {
      this.$axios({
        method:"get",
        url: 'http://localhost:8899/user/friends',
        // headers:{token:window.sessionStorage.getItem("token")},
        params:{
          id: this.user_id
        }
      }).then(res => {
        console.log(res);
        this.friends = res.data.data;
      }, err => {
        console.log(err);
      })
    },
    inputInviteCode() {
      this.$axios({
        method: "post",
        url: 'http://localhost:8899/InviteCode/friendCode',
        headers: {
          token: window.sessionStorage.getItem("token")
        },
        params: {
          invitedCode: this.invite_code
        },
      }).then(res => {
        this.$message({
          type: 'success',
          message: '填写成功!'
        });
        this.invite_code = ''
      }, err => {
        console.log(err);
        this.$message({
          type: 'success',
          message: '填写失败!'
        });
      })
    },
    getMyCode(){
      this.$axios({
        method: "get",
        url: 'http://localhost:8899/user/codes',
        headers: {token: window.sessionStorage.getItem("token")},
      }).then(res => {
        console.log(res);
        this.my_code=res.data.data
      }, err => {
        console.log(err);
      })
    }

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
  padding-left: 18%;
  height:800px;
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

/*.el-icon-edit{*/
/*  */
/*}*/
.text {
  font-size: 14px;
}

.item {
  padding: 18px 0;
}

.friend-card {
  width: 480px;
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
  left: 130px;
  bottom: 40px;
}

</style>
