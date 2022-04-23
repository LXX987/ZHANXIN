import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Login from '@/components/Login'
import PsychologyHome from '@/components/PsychologyHome'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/PsychologyHome',
      name: 'PsychologyHome',
      component: PsychologyHome
    }
  ]
})
