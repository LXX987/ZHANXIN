// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import * as echarts from 'echarts'
import axios from 'axios'
import './utils/flexible'
Vue.prototype.$axios = axios// 全局注册，之后可在其他组件中通过 this.$axios 发送数据
axios.defaults.headers.post["Content-type"] = "application/json"

//引入elementui
import 'element-ui/lib/theme-chalk/index.css';
Vue.config.productionTip = false
Vue.use(ElementUI);
Vue.prototype.$echarts = echarts

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})

 
router.beforeEach((to, from, next) => {undefined
  if (to.meta.requireAuth) { // 判断该路由是否需要登录权限
  if (sessionStorage.getItem('token')) { // 通过vuex state获取当前的token是否存在,通过一个变量（vuex中或localstorage中），如果为真，那么跳转
  
  next();
  }else {undefined
  alert('请输入账号和密码')
  next({undefined,
  path: '/',//如果为假，则重定向到这个路由路劲
  query: {redirect: to.fullPath} // 将跳转的路由path作为参数，登录成功后跳转到该路由
  })
  }
  }else {undefined
  next()
  }
  })