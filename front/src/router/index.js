import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import MyLogin from '@/components/login/MyLogin'
import Main from '@/components/vMain'
import Maintenance from '@/components/maintenance/Maintenance'
import User from '@/components/user/findAllUser'
import Role from '@/components/role/Role'
import Permission from '@/components/permission/Permission'
import Equipment from '@/components/equipment/Equipment'
import UserEquipment from '@/components/useEquipment/UserEquipment'
import Personal from '@/components/personal/Personal'
import Department from '@/components/department/Department'

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
      path: '/maintenance',
      name: 'maintenance',
      component: Maintenance,
    },
    {
      path: '/user',
      name: 'findAllUser',
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
    },
    {
      path:"/personal",
      name:"personal",
      component:Personal,
    },
    {
      path:"/department",
      name:"department",
      component:Department,
    }
  ]
})
