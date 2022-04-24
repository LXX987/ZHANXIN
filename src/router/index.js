import Vue from 'vue'
import Router from 'vue-router'
import GuideBar from '@/components/GuideBar'
import Login from '@/components/Login'
import Footer from '@/components/Footer'
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
      path: '/',
      name: 'GuideBar',
      component: GuideBar,
      children: [   // 添加子路由
        {
          path: 'HonestyVideo', 
          name: 'HonestyVideo',
          components: {   
            table: () => import('@/components/HonestyVideo')  // 这里的table跟首页的router-view标签的name一致，才会在首页的路由视图进行跳转，看3.2
          },
          children:[
            {
              path: 'BasicVideo', 
              name: 'BasicVideo',
              components: {   
              video: () => import('@/components/BasicVideo')  // 这里的table跟首页的router-view标签的name一致，才会在首页的路由视图进行跳转，看3.2
          },
            }
          ]
        },
        {
          path: 'CreditReport', 
          name: 'CreditReport',
          components: {   
            table: () => import('@/components/CreditReport')  // 这里的table跟首页的router-view标签的name一致，才会在首页的路由视图进行跳转，看3.2
          }
        },
        {
          path: 'PsychologyHome', 
          name: 'PsychologyHome',
          components: {   
            table: () => import('@/components/PsychologyHome')  // 这里的table跟首页的router-view标签的name一致，才会在首页的路由视图进行跳转，看3.2
          }
        },
        {
          path: 'HomePage',
          name: 'HomePage',
          components: {
            table: () => import('@/components/HomePage')
          }
        }
      ]
    },
    {
      path: 'Footer',
      name: 'Footer',
      components: Footer
    }
  ]
})
