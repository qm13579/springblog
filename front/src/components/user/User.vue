<template>
  <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
    <el-tab-pane label="用户管理" name="first"><FindAllUser></FindAllUser></el-tab-pane>
    <el-tab-pane label="部门管理" name="second"><Department></Department></el-tab-pane>
    <el-tab-pane label="角色管理" name="third"><Role></Role></el-tab-pane>
    <el-tab-pane label="设备类型管理" name="fourth"><EquipmentGroup></EquipmentGroup></el-tab-pane>
  </el-tabs>
</template>

<script>
import FindAllUser from './findAllUser'
import Department from "./group/Group"
import Role from '@/components/role/Role'
import EquipmentGroup from '@/components/equipment/group/Group'

export default {
    name:"user",
    components: {
        FindAllUser,
        Department,
        Role,
        EquipmentGroup,
    },
    data () {
        return{
            title : "this is user--",
            activeName:"first"
        }
    },
    methods:{
        handleClick(tab,event){
            console.log(tab,event)
            console.log(this.activeName)
        }
    }
    
}
</script>