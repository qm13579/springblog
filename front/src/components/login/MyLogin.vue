<template>
<main>
  <br>
    <br>
  <br>
  <br>
  <br>

<el-row >
    <br>

  <el-form id="from" :model="ruleForm" status-icon  label-width="100px" class="demo-ruleForm">
    <el-form-item  label="用户名" id="label_top">
      <el-input id="input" v-model.number="ruleForm.username"></el-input>
    </el-form-item>
    
    <el-form-item label="密码" prop="password" id="label">
      <el-input  id="input" type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
    </el-form-item>
    <!-- <el-row type="flex" class="row-bg" justify="center"> -->
    <el-form-item>
      <el-button type="primary" @click="submitForm">登陆</el-button>
      <el-button >忘记密码</el-button>
    
    </el-form-item>
    <!-- </el-row> -->
  </el-form>  

  <!-- </el-col> -->
</el-row>
</main>
</template>

<script>
  export default {
    data() {
     
      return {
        ruleForm: {
        },
      };
    },
    methods: {
      submitForm(){
        sessionStorage.setItem("user",JSON.stringify(this.ruleForm))
        this.$router.push("/")
      }
    }
  }
</script>

<style>
  #from{
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.61), 0 0 6px rgba(0, 0, 0, .04);
    height: 260px;
    padding: 120px;
    margin:0 auto;
    text-align: center;
    width:25%;
  }
  #input{
    margin:0 auto;
  }
  #label_top{
    padding: 40px 40px 0 0;
  }
  #label{
    padding: 0 40px 0 0;
  }
</style>
