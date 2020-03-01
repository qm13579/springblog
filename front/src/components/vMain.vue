<template>
    <el-container id="app" >
    <el-aside width="200px">
      <NavMenu></NavMenu>
    </el-aside>
    <el-container>
      <el-header >
          
         <span class="title">电子设备管理系统 </span>
        
        <el-col :span="2" class="homeHeader">
          <span class="el-icon-user"></span>
        </el-col>
      </el-header>

      <el-main>
        <div v-show="showContext" class="homeWelcome">欢迎使用电子设备管理系统</div>
        <router-view></router-view>
      </el-main>

      <!-- <el-footer>
        Footer
      </el-footer> -->
    </el-container>
  </el-container>
</template>

<script>
import NavMenu from '@/components/navMenu/NavMenu'
export default {
  name: 'App',
  data(){
    return{
      username:"",
      showContext:true
    }
  },
  components: {
    NavMenu,
  },
  methods:{
    //获取用户名
    getUserName(){
      var user = JSON.parse(sessionStorage.getItem("user"))
      this.username = user.username
    }
  },
  created(){
    this.getUserName()
  },
  watch:{
    $route:{
      handler(newName,oldName){
        console.log(newName)
        if (newName.path == "/") {
          this.showContext=true
        }else{
          this.showContext=false
        }
      },
      deep: true,
      immediate: true
    }
  }

}
</script>

<style>
  html,body,#app,.el-container{
    padding: 0px;
    margin: 0px;
    height: 100%;
  }
  .el-header, .el-footer {
    background-color: #409EFF;
    color: #333;
    text-align: center;
    line-height: 60px;
  }
    .el-aside {
    background-color: #545c64;
    color: #333;
    text-align: center;
    line-height: 200px;
  }
.title {
        font-size: 30px;
        font-family: 华文行楷;
        color: #ffffff
    }
  .homeWelcome {
        text-align: center;
        font-size: 30px;
        font-family: 华文行楷;
        color: #409eff;
        padding-top: 50px;
    }
</style>