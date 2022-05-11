<template>
  <div class="customerService">
    <customerservice></customerservice>
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
          <!--      用户列表-->
          <el-col :span="6">
            <el-card class="talkList">
              <el-row>
                <p>用户列表</p>
              </el-row>
              <el-row>
              <div class="numberlist">
                <div v-for="(item,index) in userList" :key="index" class="numberitem">
                  <div v-if="index/2 === 1" class="itemstyle1">
                  {{item}}
                  </div>
                  <div v-else class="itemstyle2">
                    {{item}}
                  </div>
                </div>
              </div>
              </el-row>
            </el-card>
          </el-col>
          <!--      聊天室-->
          <el-col :span="18">
            <div class="talkBox">
              <el-card class="talkCard">
                <div class="talkContent">
                  <div v-for="(item,index) in msgList" :key="index">
                    <div align="right" v-if="item.from===user" class="rightTalk">
                      <div class="britem">
                        <el-tag size="large">
                          {{item.from}}
                        </el-tag>
                      </div>
                      {{item.time}}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{item.msg}}
                    </div>
                    <div align="left" v-else class="leftTalk">
                      <div class="britem">
                        <el-tag size="large" type="danger">
                          {{item.from}}
                        </el-tag>
                      </div>
                      {{item.msg}}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{item.time}}
                    </div>
                  </div>
                </div>
                <el-input @keyup.enter.native="send" type="textarea" v-model="message.msg" :autosize="{ minRows: 6, maxRows: 8}" placeholder="请输入聊天内容"></el-input>
                <div align="right">
                  <el-button type="primary" class="sentButton" @click="send">发送</el-button>
                </div>
              </el-card>
            </div>
          </el-col>
          
        </el-row>
      </div>
      <br>
    </div>
  </div>
</template>
<script>
import Customerservice from './customerservice.vue';
  let socket;
  export default {
  components: { Customerservice },
    name: 'cs',
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
      messagebutton() {
        this.$alert('<strong>请拨打</strong>13912344321<strong>进行留言，我们会尽快处理。</strong>', '留言方式', {
          dangerouslyUseHTMLString: true
        });
      },
      complaintbutton() {
        this.$alert('<strong>投诉电话</strong>： 13912344321', '投诉渠道', {
          dangerouslyUseHTMLString: true
        });
      },
      callbutton() {
        this.$alert('<strong>电话</strong>： 13912344321', '联系电话', {
          dangerouslyUseHTMLString: true
        });
      },
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
      creditScore() {
        this.message.msg = "信用分数",
        this.send()
      },
      videoEducation() {
        this.message.msg = "视频教育",
        this.send()
      },
      psychologyMeasure() {
        this.message.msg = "心理测评",
        this.send()
      },
      social() {
        this.message.msg = "好友人脉",
        this.send()
      },
      dimend() {
        this.message.msg = "评分维度",
        this.send()
      },
      updateScore() {
        this.message.msg = "提升分数",
        this.send()
      },
      send() {
        if (!this.message.msg) {
          this.$message({
            message: '请输入聊天消息！',
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
#message {
  margin: 25px 10px 20px 25px;
  width: 60px;
  height: 60px;
  background: url('../assets/message.png') no-repeat;
  background-size: 100% 100%;
}
#complaint {
  margin: 25px 10px 20px 10px;
  width: 60px;
  height: 60px;
  background: url('../assets/complaint.png') no-repeat;
  background-size: 100% 100%;
}
#call {
  margin: 25px 10px 20px 10px;
  width: 60px;
  height: 60px;
  background: url('../assets/call.png') no-repeat;
  background-size: 100% 100%;
}
.connect {
  height: 230px;
  width: 100%;
  background-color: #ffffff;
  font-size: 18px;
  color: #42badb;
  font-weight: 550;
}
.connect p {
  margin-top: 70px;
}
.connectTitle {
  height: 30px;
  width: 30%;
  background: url('../assets/connect.png') no-repeat;
  background-size: 100% 100%;
  margin-left: -20px;
}
.britem {
  margin: 15px 5px 10px 5px;
}
.el-button {
  padding: 0px;
  margin: 10px 2px 5px 5px;
  width: 73px;
  height: 50px;
  border: 1px solid #7bc4db;
  background: #c2e0e3;
  border-radius: 5px;
}
.el-button:focus, .el-button:hover {
    color: #ffffff;
    border-color: #7192b5;
    background-color: #a5bcd3;
}
/* .el-button :hover {
  margin: 10px 5px 5px 5px;
  width: 70px;
  height: 50px;
  border: 1px solid #51a3bd;
  background: #000000;
  border-radius: 5px;
} */
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
  font-size: 18px;
}
.talkTitle {
  padding-left: 15px;
  height: 45px;
  text-align: center;
  font-size: 22px;
  margin-bottom: 18px;
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
  height: 65px;
  line-height: 65px;
  font-size: 20px;
  border: 2px solid #9ab2cb;
  border-radius: 8px 8px 0px 0px;
  text-indent: 50px;
}
.itemstyle1 {
  background-color: #ffffff;
}
.itemstyle2 {
  background-color: #dee8f3;
}
.numberlist {
  height: 646px;
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
  /* background: url('../assets/csguiderbar.png') no-repeat; */
  /* background-size: 125% 105%; */
}
.talkList p {
  text-align: center;
  font-size: 20px;
  margin: 10px 0px 20px 0px;
}
.chatBoard {
  width: 70%;
  margin-left: 15%;
  margin-top: 1%;
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
  height: 970px;
  left: 0px;
  top: 100px;
  /* background-color: #ffffff43; */
  background: url('../assets/background.png') no-repeat;
  opacity: 0.9;
  background-size: 100% 100%;
}
</style>