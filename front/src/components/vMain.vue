<template>
    <el-container id="app" >
    <el-aside width="200px">
      <div class="head1">EQUIPMENT-设备管理</div>
      <NavMenu></NavMenu>
    </el-aside>
    <el-container>
      <el-header >
          
         <span class="title">电子设备管理系统 </span>

          <!-- <span class="el-icon-user head"></span> -->
        <el-dropdown style="float: right;">
          <span class="el-dropdown-link">
            <i class="el-icon-user head"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item @click.native ="cancellation">注销</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </el-header>

      <el-main>
        <!-- <div v-show="showContext" class="homeWelcome">欢迎使用电子设备管理系统</div> -->
        <div v-show="showContext" >
          <br>
          <el-row :gutter="18"  type="flex" class="row-bg"  justify="center">
            
            <el-col  :span="6" >
              <el-card shadow="hover" class="card card-font">
                <span>日流量</span>
                <br>
                <span><countTo :startVal='startVal' :endVal='first' :duration='3000'></countTo></span>
              </el-card>
            </el-col>
            
            <el-col  :span="6">
              <el-card shadow="hover" class="card card-font">
                <span>日流量</span>
                <br>
                <span><countTo :startVal='startVal' :endVal='first' :duration='3000'></countTo></span>
              </el-card>
            </el-col>

            <el-col  :span="6" >
              <el-card shadow="hover" class="card card-font">
                <span>周流量</span>
                <br>
                <span><countTo :startVal='startVal' :endVal='list' :duration='3000'></countTo></span>
              </el-card>
            </el-col>

            <el-col  :span="6" >
              <el-card shadow="hover" class="card card-font">
                <span>周IP量</span>
                <br>
                <span><countTo :startVal='startVal' :endVal='list' :duration='3000'></countTo></span>
              </el-card>
            </el-col>
          </el-row>

          <br>
          <br>
          <!-- <el-card shadow="hover" class="chart">
            <HomeChart :list='list'></HomeChart>
          </el-card> -->
        </div>


        <router-view></router-view>
      </el-main>

      <!-- <el-footer>
        Footer
      </el-footer> -->
    </el-container>
  </el-container>
</template>

<script>
import countTo from 'vue-count-to';
import NavMenu from '@/components/navMenu/NavMenu'
import HomeChart from '@/components/chart/HomeChart'
export default {
  name: 'App',
  data(){
    return{
      username:"",
      showContext:true,
      first:0,
      last:"",
      list:0,
      startVal:0,
      orgOptions:{},
    }
  },
  components: {
    NavMenu,
    countTo,
    HomeChart,
  },
  methods:{
    //获取用户名
    getUserName(){
      var user = JSON.parse(sessionStorage.getItem("user"))
      this.username = user.username
    },
    cancellation(){
      console.log("asda")
      sessionStorage.removeItem("user");
      this.$router.push("/login")
    },
    getWeekData(){
      var _this = this;
      this.$ajax.get("/api/info/pv").then(res => {
        
        res.data.data.week.forEach(element => {
          _this.list += parseInt(res.data.data.week)    
        });
        _this.first =parseInt(res.data.data.first)
        _this.last = res.data.data.last
       

      })
    },
     
  
  },
  created(){
    this.getUserName()
    this.getWeekData()
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
  .head{
    float: right;
    padding: 20px 0 0 0;
  }
  .head1{
    background-color: #545c64;
    color: rgb(230, 221, 221);
    text-align: center;
    line-height: 60px;
    font-size: 10px;
    font-family: 华文行楷;

  }
  .card{
    width: 100%;
    height: 100px;
  }
  .card-font{
    font-size: 20px;
    font-family: 华文行楷;
    float:right;
    color: #2852a5;
  }
  .chart{
    width: 100%;
    height: 500px;
  }
</style>