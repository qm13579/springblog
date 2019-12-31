<template>
  <el-form :model="ruleForm" status-icon  ref="ruleForm" label-width="100px" class="demo-ruleForm">
    <el-form-item label="用户名" prop="username">
      <el-input v-model.number="ruleForm.username"></el-input>
    </el-form-item>
    
    <el-form-item label="密码" prop="password">
      <el-input type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
      <el-button @click="resetForm('ruleForm')">重置</el-button>
    </el-form-item>
  </el-form>  
</template>

<script>
  export default {
    data() {
      var checkUser = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('用户名不能为空'));
        }
      };
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } 
      };
      return {
        ruleForm: {
          password: '',
          username: ''
        },
      };
    },
    methods: {
      submitForm(formName) {
        this.$ajax.post('/api/login',this.qs.stringify(this.ruleForm ))
        .then(res =>{
          if(res.data.code == '200'){
            console.log('登录成功')
            //跳转页面
          }else{
            alert("用户名或密码错误")
          }
        })
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    }
  }
</script>