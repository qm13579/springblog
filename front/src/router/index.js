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
import equipmentGroup from '@/components/equipment/group/Group'
import userGroup from '@/components/user/group/Group'

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
        {path: '/role',name: 'role',component: Role},
        {path: '/permission',name: 'permission',component:Permission},
        {path:'/equipment',name:'equipment',component:Equipment},    
        {path: '/userEquipment',name: 'userEquipment',component:UserEquipment},
        {path: '/equipmentGroup',name:"equipmentGroup",component:equipmentGroup},
        {path: '/userGroup',name : 'userGroup',component:userGroup}
      ]
    },
    {
      path: '/login',
      name: 'login',
      component: MyLogin
    },
  ],
})
