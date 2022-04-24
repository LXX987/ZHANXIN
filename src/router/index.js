import Vue from 'vue'
import Router from 'vue-router'
import GuideBar from '@/components/GuideBar'
import Login from '@/components/Login'
import Register from '@/components/Register'
import PsychologyHome from '@/components/PsychologyHome'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/Login',
      name: 'Login',
      component: Login
    },
    {
      path: '/Register',
      name: 'Register',
      component: Register
    },
    {
      path: '/PsychologyHome',
      name: 'PsychologyHome',
      component: PsychologyHome
    },
    {
      path: '/',
      name: 'GuideBar',
      component: GuideBar,
      children: [   // 添加子路由
        {
          path: 'HonestyVideo', 
          name: 'HonestyVideo',
          components: {   
            table: () => import('@/components/HonestyVideo')  // 这里的table跟首页的router-view标签的name一致，才会在首页的路由视图进行跳转，看3.2
          }
        },
        {
          path: 'CreditReport', 
          name: 'CreditReport',
          components: {   
            table: () => import('@/components/CreditReport')  // 这里的table跟首页的router-view标签的name一致，才会在首页的路由视图进行跳转，看3.2
          }
        },
        {
          path: 'PersonalCenter', 
          name: 'PersonalCenter',
          components: {   
            table: () => import('@/components/HonestyProof')  // 这里的table跟首页的router-view标签的name一致，才会在首页的路由视图进行跳转，看3.2
          }
        }
      ]
    }
  ]
})
