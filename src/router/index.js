import Vue from 'vue'
import Router from 'vue-router'
import GuideBar from '@/components/GuideBar'
import Login from '@/components/Login'
import Register from '@/components/Register'
import ForgetPsw from '@/components/ForgetPsw'
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
import customerservice from '@/components/customerservice'
import cs from '@/components/cs'
import csLogin from '@/components/csLogin'

import FriendList from '@/components/FriendList'
import BankLogin from '@/components/BankLogin'
import BankGuideBar from '@/components/BankGuideBar'
import BankMain from '@/components/BankMain'
import BankSearch from '@/components/BankSearch'
import BankUser from '@/components/BankUser'
import BankData from '@/components/BankData'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/Register',
      name: 'Register',
      component: Register
    },
    {
      path: '/ForgetPsw',
      name: 'ForgetPsw',
      component: ForgetPsw
    },
    {
      path: '/csLogin',
      name: 'csLogin',
      component: csLogin
    },
    {
      path: '/cs',
      name: 'cs',
      component: cs
    },
    {
      path: '/BankLogin',
      name: 'BankLogin',
      component: BankLogin
    },
    {
      path: '/BankGuideBar',
      name: 'BankGuideBar',
      component: BankGuideBar
    },
    {
      path: '/BankMain',
      name: 'BankMain',
      component: BankMain
    },
    {
      path: '/BankSearch',
      name: 'BankSearch',
      component: BankSearch
    },
    {
      path: '/BankUser',
      name: 'BankUser',
      component: BankUser
    },
    {
      path: '/BankData',
      name: 'BankData',
      component: BankData
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
          path: 'CreditAppeal', 
          name: 'CreditAppeal',
          components: {   
            table: () => import('@/components/CreditAppeal')  // 这里的table跟首页的router-view标签的name一致，才会在首页的路由视图进行跳转，看3.2
          }
        },
        {
          path: 'SecurityQuestion', 
          name: 'SecurityQuestion',
          components: {   
            table: () => import('@/components/SecurityQuestion')  // 这里的table跟首页的router-view标签的name一致，才会在首页的路由视图进行跳转，看3.2
          }
        },
        {
          path: 'AnswerSecQue', 
          name: 'AnswerSecQue',
          components: {   
            table: () => import('@/components/AnswerSecQue')  // 这里的table跟首页的router-view标签的name一致，才会在首页的路由视图进行跳转，看3.2
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
          },
          meta: {  requireAuth:true  }
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
        },
        {
          path: 'Chat',
          name: 'Chat',
          components: {
            table: () => import('@/components/Chat')
          }
        },{
          path: '/Report',
          name: 'Report',
          components: {
            table: () => import('@/components/Report')
          }
        }
      ]
    },
    {
      path: '/customerservice',
      name: '/customerservice',
      component: customerservice,
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
