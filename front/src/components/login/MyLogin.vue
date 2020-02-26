<template>
<el-row type="flex" class="row-bg" justify="center">
  <!-- <el-col > -->

  <el-form :model="ruleForm" status-icon  label-width="100px" class="demo-ruleForm">
    <el-form-item label="用户名" >
      <el-input v-model.number="ruleForm.username"></el-input>
    </el-form-item>
    
    <el-form-item label="密码" prop="password">
      <el-input type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
    </el-form-item>
    <el-row type="flex" class="row-bg" justify="center">
    <el-form-item>
      <el-button type="primary" @click="submitForm">登陆</el-button>
      <!-- <el-button @click="resetForm">重置</el-button> -->
    
    </el-form-item>
    </el-row>
  </el-form>  

  <!-- </el-col> -->
</el-row>
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