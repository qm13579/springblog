<template>
    <el-menu background-color="#545c64" text-color="#fff" router :default-active="activeIndex"  class="el-menu-demo"  @select="handleSelect">
    
        <el-submenu index="1">
        <template slot="title">系统管理</template>
        <el-menu-item index="/user">基础信息</el-menu-item>

        <!-- <el-menu-item index="/equipmentGroup">日志管理</el-menu-item> -->
    </el-submenu>

    <el-menu-item  index="/maintenance" >维保信息</el-menu-item>

    <el-submenu index="2">
        <template slot="title">电子设备中心</template>
        <el-menu-item index="/equipment">设备信息</el-menu-item>
        <el-menu-item index="/userEquipment">使用信息</el-menu-item>
    </el-submenu>
    

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