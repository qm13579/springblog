<template>
    <el-menu background-color="#545c64" text-color="#fff" router :default-active="activeIndex"  class="el-menu-demo"  @select="handleSelect">
    <el-menu-item index="/" >首页</el-menu-item>
    <el-menu-item index="/personal" >个人设备信息</el-menu-item>
    <el-menu-item index="/department" >部门设备信息</el-menu-item>
    <el-menu-item  index="/maintenance" >维保信息</el-menu-item>
    <el-submenu index="2">
        <template slot="title">电子设备中心</template>
        <el-menu-item index="/equipment">设备信息</el-menu-item>
        <el-menu-item index="/userEquipment">使用信息</el-menu-item>
    </el-submenu>

    <el-submenu index="3">
        <template slot="title">用户中心</template>
        <el-menu-item index="/user">用户管理</el-menu-item>
        <el-menu-item index="/role">角色管理</el-menu-item>
        <el-menu-item index="/permission">访问管理</el-menu-item>
    </el-submenu>
    
    <el-menu-item  index="/log" >日志中心</el-menu-item>
    <el-menu-item  index="/control" >监控中心</el-menu-item>
    <el-menu-item  index="/ops" >运维中心</el-menu-item>

    </el-menu>

</template>

<script>
  export default {
    data() {
      return {
        activeIndex: '0',
        activeIndex2: '0'
      };
    },
    methods: {
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      }
    }
  }
</script>