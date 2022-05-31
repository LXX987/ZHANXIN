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

 
router.beforeEach((to, from, next) => {
  if (to.meta.requireAuth) { // 判断该路由是否需要登录权限
    if (sessionStorage.getItem("token") == 'true') { // 判断本地是否存在token
      next()
    } else {
      // 未登录,跳转到登陆页面
      next({
        path: '/'
      })
    }
  } else {
    if(sessionStorage.getItem("token") == 'true'){
      next();
    }else{
      next();
    }
  }
});