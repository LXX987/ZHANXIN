<template>
  <div class="rate">
    <div class="body">

        <ul>
          <li v-for="(friend) in friends">
            姓名：{{friend.user_name}} , 邮箱：{{friend.user_email}} , 信用分{{friend.total_score}}
          </li>
        </ul>
    </div>
  </div>
</template>

<script>
export default {
  name: "FriendList",
  data() {
    return {
      friends: []
    }
  },
  mounted(){
    this.getFriendList()
  },
  methods: {
    getFriendList() {
      this.$axios({
        method:"get",
        url: 'http://localhost:8888/user/friends',
        // headers:{token:window.sessionStorage.getItem("token")},
        params:{
          id: '5'
        }
      }).then(res=>{
        console.log(res);
        this.friends=res.data.data;
      },err=>{
        console.log(err);
      })
    }
  }

}
</script>

<style scoped>
.illu{
  height:150px;
  text-align: left;
}
.title{
  color:#fff;
  font-size: 48px;
  font-weight:bold;
  margin-top: 0px;
  margin-left: 220px;
  line-height:150px;
}
.video{
  width: 900px;
  height: 600px;
}
.guidebar {
  height:100px;
  padding-left: 18%;
  padding-right: 250px;
  float:center;
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
.el-menu-item:hover{
  border-bottom-style:solid;
  border-color: #6969FC;
}
.logo{
  height: 100px
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
.content{
  background-color: #fff;
  margin-left: 15%;
  padding-left: 20px;
  padding-right: 20px;
}
.side{
  background-color: #fff;
  margin-left: 50px;
  padding-left: 20px;
}
.videotitle{
  font-size: 24px;
  font-weight: bold;
  text-align: left;
  margin-top: 20px;
}

</style>
