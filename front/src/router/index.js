import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import MyLogin from '@/components/login/MyLogin'
import Main from '@/components/vMain'
import Processing from '@/components/processing/Processing'
import User from '@/components/user/User'
import Role from '@/components/user/Role'
import Permission from '@/components/user/Permission'
import Equipment from '@/components/equipment/Equipment'
import UserEquipment from '@/components/equipment/UserEquipment'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Main',
      component: Main
    },
    {
      path: '/login',
      name: 'login',
      component: MyLogin
    },
    {
      path: '/process',
      name: 'process',
      component: Processing,
    },
    {
      path: '/user',
      name: 'user',
      component: User
    },
    {
      path: '/role',
      name: 'role',
      component: Role
    },
    {
      path: '/permission',
      name: 'permission',
      component:Permission
    },
    {
      path:'/equipment',
      name:'equipment',
      component:Equipment
    },
    {
      path: '/userEquipment',
      name: 'userEquipment',
      component:UserEquipment
    }
  ]
})
