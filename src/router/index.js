import Vue from 'vue'
import Router from 'vue-router'
import GuideBar from '@/components/GuideBar'
import Login from '@/components/Login'
import Register from '@/components/Register'
import Footer from '@/components/Footer'
import PsychologyHome from '@/components/PsychologyHome'
import Video from '@/components/Video'
import VideoRecord from '@/components/VideoRecord'
import VideoQuestion from '@/components/VideoQuestion'
import PsychologyQuestions from '@/components/PsychologyQuestions'
import PsychologyResult from '@/components/PsychologyResult'
import PsychologyHistory from '@/components/PsychologyHistory'
import PsychologyGuidebar from '@/components/PsychologyGuidebar'
import ScoreDetail from '@/components/ScoreDetail'
import backgroundGuidebar from '@/components/background/backgroundGuidebar'

import FriendList from '@/components/FriendList'

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
              path: '',
              name: 'BasicVideo',
              components: {
              video: () => import('@/components/BasicVideo')  // 这里的table跟首页的router-view标签的name一致，才会在首页的路由视图进行跳转，看3.2
              }
            },
            {
              path: '/CreditVideo',
              name: 'CreditVideo',
              components: {
              video: () => import('@/components/CreditVideo')
              }
            },
            {
              path: '/HonestyCulture',
              name: 'HonestyCulture',
              components: {
              video: () => import('@/components/HonestyCulture')
              }
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
          path: 'PersonalCenter',
          name: 'PersonalCenter',
          components: {   
            table: () => import('@/components/PersonalCenter')  // 这里的table跟首页的router-view标签的name一致，才会在首页的路由视图进行跳转，看3.2
          }
        },
        {
          path: 'AssetProof', 
          name: 'AssetProof',
          components: {   
            table: () => import('@/components/AssetProof')  // 这里的table跟首页的router-view标签的name一致，才会在首页的路由视图进行跳转，看3.2
          }
        },
        {
          path: 'HonestyProof', 
          name: 'HonestyProof',
          components: {   
            table: () => import('@/components/HonestyProof')  // 这里的table跟首页的router-view标签的name一致，才会在首页的路由视图进行跳转，看3.2
          }
        },
        {
          path: 'MyCreditRecord', 
          name: 'MyCreditRecord',
          components: {   
            table: () => import('@/components/MyCreditRecord')  // 这里的table跟首页的router-view标签的name一致，才会在首页的路由视图进行跳转，看3.2
          }
        },
        {
          path: 'MyReport', 
          name: 'MyReport',
          components: {   
            table: () => import('@/components/MyReport')  // 这里的table跟首页的router-view标签的name一致，才会在首页的路由视图进行跳转，看3.2
          }
        },
        {
          path: 'AccountSecurity', 
          name: 'AccountSecurity',
          components: {   
            table: () => import('@/components/AccountSecurity')  // 这里的table跟首页的router-view标签的name一致，才会在首页的路由视图进行跳转，看3.2
          }
        },
        {
          path: 'PsychologyGuidebar',
          name: 'PsychologyGuidebar',
          components: {
            table: () => import('@/components/PsychologyGuidebar')  // 这里的table跟首页的router-view标签的name一致，才会在首页的路由视图进行跳转，看3.2
          },
          children: [
            {
              path: '',
              name: 'PsychologyHome',
              components: {
                subtable: () =>  import('@/components/PsychologyHome')
              }
            },
            {
              path: '/PsychologyGuidebar/PsychologyQuestions',
              name: 'PsychologyQuestions',
              components: {
                subtable: () =>  import('@/components/PsychologyQuestions')
              }
            },
            {
              path: '/PsychologyGuidebar/PsychologyResult',
              name: 'PsychologyResult',
              components: {
                subtable: () =>  import('@/components/PsychologyResult')
              }
            },
            {
              path: '/PsychologyGuidebar/PsychologyHistory',
              name: 'PsychologyHistory',
              components: {
                subtable: () =>  import('@/components/PsychologyHistory')
              }
            }
          ]
        },
        {
          path: 'HomePage',
          name: 'HomePage',
          components: {
            table: () => import('@/components/HomePage')
          }
        },
        {
          path: 'ScoreDetail',
          name: 'ScoreDetail',
          components: {
            table: () => import('@/components/ScoreDetail')
          }
        },
        {
          path: 'Possession',
          name: 'Possession',
          components: {
            table: () => import('@/components/Possession')
          }
        },{
          path: 'Loan',
          name: 'Loan',
          components: {
            table: () => import('@/components/Loan')
          }
        },{
          path: 'Behavior',
          name: 'Behavior',
          components: {
            table: () => import('@/components/Behavior')
          }
        },{
          path: 'Social',
          name: 'Social',
          components: {
            table: () => import('@/components/Social')
          }},
          {
          path: '/Friends',
          name: 'FriendList',
          components: {
            table: () => import('@/components/FriendList')
          }
        }
      ]
    },
    {
      path: '/background',
      name: '/background',
      component: backgroundGuidebar,
      children: [  // 添加子路由
      ]
    },
    {
      path: '/Video',
      name: 'Video',
      component: Video
    },
    {
      path: '/VideoRecord',
      name: 'VideoRecord',
      component: VideoRecord
    },
    {
      path: '/VideoQuestion',
      name: 'VideoQuestion',
      component: VideoQuestion
    },
    {
      path: 'Footer',
      name: 'Footer',
      components: Footer
    }
  ]
})
