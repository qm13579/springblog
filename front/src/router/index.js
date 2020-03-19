import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import MyLogin from '@/components/login/MyLogin'
import Main from '@/components/vMain'
import Maintenance from '@/components/maintenance/Maintenance'
import User from '@/components/user/User'
import Role from '@/components/role/Role'
import Permission from '@/components/permission/Permission'
import Equipment from '@/components/equipment/Equipment'
import UserEquipment from '@/components/useEquipment/UserEquipment'
import Log from '@/components/system/Log'
import Contract from '@/components/contract/Contract'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Main',
      component: Main,
      children:[
        {path: '/maintenance',name: 'maintenance',component: Maintenance,},
        {path: '/user',name: 'user',component: User},
        {path:'/equipment',name:'equipment',component:Equipment},    
        {path: '/userEquipment',name: 'userEquipment',component:UserEquipment},
        {path: '/log',name: 'log',component:Log},
        {path: '/contract',name:'contract',component:Contract},
      ]

    },
    {
      path: '/login',
      name: 'login',
      component: MyLogin
    },
  ],
})
