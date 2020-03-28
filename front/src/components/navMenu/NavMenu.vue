<template>
    <el-menu background-color="#545c64" text-color="#fff" router :default-active="activeIndex"  class="el-menu-demo"  @select="handleSelect">
    
        <el-submenu index="1">
        <template slot="title"> <span class="el-icon-s-platform"></span>系统管理</template>
        <el-menu-item index="/user">基础信息</el-menu-item>
        <el-menu-item index="/log">日志管理</el-menu-item>
        <!-- <el-menu-item index="/log">在线用户</el-menu-item> -->
    </el-submenu>

    <!-- <el-menu-item  index="/maintenance" ><span class="el-icon-loading"></span>运维管理</el-menu-item> -->
    
    <el-submenu index="2">
        <template slot="title"><span class="el-icon-set-up"></span>运维管理</template>
        <el-menu-item index="/maintenance">维保信息</el-menu-item>
        <el-menu-item index="/druid">数据库监控</el-menu-item>
    </el-submenu>

    <el-submenu index="3">
        <template slot="title"><span class="el-icon-cpu"></span>设备中心</template>
        <el-menu-item index="/equipment">设备信息</el-menu-item>
        <el-menu-item index="/userEquipment">使用信息</el-menu-item>
    </el-submenu>

    <el-submenu index="4">
        <template slot="title"><span class="el-icon-document-copy"></span>合同管理</template>
        <el-menu-item index="/contract">设备合同</el-menu-item>
        <el-menu-item index="/contractDraft">起草合同</el-menu-item>
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