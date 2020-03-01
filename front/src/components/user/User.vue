<template>
  <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
    <el-tab-pane label="用户管理" name="first"><FindAllUser></FindAllUser></el-tab-pane>
    <el-tab-pane label="部门管理" name="second"></el-tab-pane>
    <el-tab-pane label="角色管理" name="third">角色管理</el-tab-pane>
    <el-tab-pane label="设备类型管理" name="fourth">设备类型管理</el-tab-pane>
  </el-tabs>
</template>

<script>
import FindAllUser from './findAllUser'

export default {
    name:"user",
    components: {
        FindAllUser,
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