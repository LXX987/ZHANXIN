<template>
  <div align="customerService">
    <div class="hideBoard"></div>
    <div class="chatBoard">
      <br>
      <div class="chatBox">
        <el-row id="cstitle">
          <div class="talkTitle">
            <el-col :span="2" id="customerServiceAvator"><img id="avator" src="@/assets/cs.png" /></el-col>
            <el-col :span="22" id="customerServiceName">客服小助手</el-col>
          </div>
        </el-row>
        <el-row>
          <!--      聊天室-->
          <el-col :span="18">
            <div class="talkBox">
              <el-card class="talkCard">
                <div class="talkContent">
                  <div v-for="(item,index) in msgList" :key="index">
                    <div align="right" v-if="item.from===user" class="rightTalk">
                      {{item.time}}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{item.msg}}
                      <el-tag size="mini">
                        {{item.from}}
                      </el-tag>
                    </div>
                    <div align="left" v-else class="leftTalk">
                      <el-tag size="mini" type="danger">
                        {{item.from}}
                      </el-tag>
                      {{item.msg}}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{item.time}}
                    </div>
                  </div>
                </div>
                <el-input @keyup.enter.native="send" type="textarea" v-model="message.msg" :autosize="{ minRows: 2, maxRows: 4}" placeholder="请输入聊天内容"></el-input>
                <div align="right">
                  <el-button type="primary" class="sentButton" @click="send">发送</el-button>
                </div>
              </el-card>
            </div>
          </el-col>
          <!--      用户列表-->
          <el-col :span="6">
            <el-card class="talkList">
              <ul>
                <li><img src="@/assets/circle.png"/>&ensp;&ensp;<span>我想知道如何查看信用分？</span></li>
                <li><img src="@/assets/circle.png"/>&ensp;&ensp;<span>我的信用分数算高还是低？</span></li>
                <li><img src="@/assets/circle.png"/>&ensp;&ensp;<span>如何评信用分？</span></li>
                <li><img src="@/assets/circle.png"/>&ensp;&ensp;<span>我想咨询相关问题（以下）</span></li>
                <el-row>
                  <el-col :span="8"><div class="choicebox"></div></el-col>
                  <el-col :span="8"></el-col>
                  <el-col :span="8"></el-col>
                </el-row>
                <el-row>
                  <el-col :span="8"></el-col>
                  <el-col :span="8"></el-col>
                  <el-col :span="8"></el-col>
                </el-row>
                <el-row>
                  <el-col :span="8"></el-col>
                  <el-col :span="8"></el-col>
                  <el-col :span="8"></el-col>
                </el-row>
                <li><img src="@/assets/circle.png"/>&ensp;&ensp;<span>心理测试对分数的影响？</span></li>
                <li><img src="@/assets/circle.png"/>&ensp;&ensp;<span>看视频的方式？</span></li>
              </ul>
              <!-- <div class="numberlist">
                <div v-for="(item,index) in userList" :key="index" class="numberitem">
                  {{item}}
                </div>
              </div> -->
            </el-card>
          </el-col>
        </el-row>
      </div>
      <br>
    </div>
  </div>
</template>
<script>
  let socket;
  export default {
    name: 'chat',
    data() {
      return {
        // 登录用户
        user: '',
        // 消息记录列表
        msgList: [],
        // 发送的消息
        message: {
          time:null,//时间
          to: '',//发给谁
          from: '',
          msg: ''
        },
        // 在线用户列表
        userList: []
      }
    },
    methods: {
      init() {
          
        // 如果sessionStorage中没有用户信息，则跳转登录页面
        // this.user = sessionStorage.getItem('user')
        // if (!this.user) {
        //   this.$router.push('/')
        // }
        let that = this;
        if (typeof (WebSocket) == "undefined") {
          console.log("您的浏览器不支持WebSocket");
        } else {
          console.log("您的浏览器支持WebSocket");
          let socketUrl = "ws://localhost:8899/socket/" + this.user;
          if (socket != null) {
            socket.close();
            socket = null;
          }
          // 开启一个websocket服务
          socket = new WebSocket(socketUrl);
          //打开事件
          socket.onopen = function () {
            console.log("websocket已打开");
          };
          //  浏览器端收消息，获得从服务端发送过来的文本消息
          socket.onmessage = function (msg) {
            console.log("收到数据====" + msg.data)
            let data = JSON.parse(msg.data)
            if (data.userNames) {
              // userNames存在则是有人登录，获取在线人员信息
              that.userList = data.userNames
            } else {
              // userNames不存在则是有人发消息
              that.msgList.push(data)
            }
          };
          //关闭事件
          socket.onclose = function () {
            console.log("websocket已关闭");
          };
          //发生了错误事件
          socket.onerror = function () {
            console.log("websocket发生了错误");
          }
        }
      },
      send() {
        if (!this.message.msg) {
          this.$message({
            message: '大兄弟，请输入聊天消息！',
            type: 'warning'
          });
        } else {
          if (typeof (WebSocket) == "undefined") {
            console.log("您的浏览器不支持WebSocket");
          } else {
            console.log("您的浏览器支持WebSocket");
            this.message.from=this.user;
            this.message.time=new Date().toLocaleTimeString();
            socket.send(JSON.stringify(this.message));
            this.message.msg = '';
          }
        }
      }
    },
    mounted() {
        this.$axios({
                method:"get",
                url: 'http://localhost:8899/user/getUserName',
                headers:{token:window.sessionStorage.getItem("token")},
                params:{
                }
            }).then(res=>{
                console.log(res);
                this.user = res.data.data.userName;
                console.log(this.user)
                this.init()
                // sessionStorage.setItem('user', this.username)
            })
      
    }
  }
</script>
<style scoped>
.customerService {
   
}
.choicebox {
  
}
ul {
  margin-top: 70px;
}
li {
  margin: 15px 5px 5px 15px;
  font-size: 18px;
}
#cstitle {
  border-radius: 25px 25px 0px 0px;
  background-color: #ffffff;
  box-shadow: 0 0.010417rem 0.0625rem 0 rgb(0 0 0 / 10%);
}
#customerServiceAvator {
  text-align: left;
}
#customerServiceName {
  padding: 1% 0%;
  text-align: left;
}
#avator {
  width: 55px;
  height: 55px;
  border-radius: 50%;
  border: 1px solid #c0c0c0;
}
.sentButton {
  margin-top: 10px;
}
.leftTalk {
  color: coral;
}
.rightTalk {
  color: dodgerblue;
}
.talkContent {
  width: 100%;
  height: 550px;
  border:1px solid #000000;
  border-radius: 5px;
  overflow-y:auto;
  margin-bottom: 10px;
}
.talkTitle {
  padding-left: 15px;
  height: 45px;
  text-align: center;
  font-size: 22px;
  margin-bottom: 15px;
  margin-top: 15px;
}
.talkCard {
  width: 100%;
  height: 800px; 
}
.talkBox {
  width: 100%;
}
.numberitem {
  padding: 10px;
  margin-top: 10px;
  font-size: 20px;
}
.numberlist {
  height: 700px;
  overflow-y:auto;
  border:1px solid #000000;
  border-radius: 5px;
}
.numberListTitle {
  text-align: center;
  font-size: 28px;
  margin-bottom: 10px;
}
.talkList {
  text-align: left;
  width: 100%;
  height: 800px;
  background: url('../assets/csguiderbar.png') no-repeat;
  background-size: 125% 100%;
}
.chatBoard {
  width: 70%;
  margin-left: 15%;
  margin-top: 3%;
  border-radius: 25px;
  text-align: center;
  background-color: #afafaf30;
}
.chatBox {
  width: 96%;
  height: 96%;
  margin-left: 2%;
  margin-top: 1%;
  border-radius: 25px;
}
.hideBoard {
  position: absolute;
  width: 2000px;
  height: 1170px;
  left: 0px;
  top: 0px;
  background-color: #a5a5a543;
}
</style>