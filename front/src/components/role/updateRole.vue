<template>
    <el-form :model="role"  label-width="100px" class="demo-ruleForm">
        <el-form-item label="id" >
            <el-input  v-model="role.id" autocomplete="off"></el-input>
        </el-form-item>
        
        <el-form-item label="角色" >
            <el-input  v-model="role.roleName" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="角色描述" >
            <el-input  v-model="role.desc" autocomplete="off"></el-input>
        </el-form-item>
        
        <el-form-item>
            <el-button type="primary"  @click="submitUpdate">提交</el-button>
            <el-button @click="back">返回</el-button>
        </el-form-item>
    </el-form>
</template>

<script>
export default {
    name:"updataRole",
    data(){
        return{

        }
    },
    props:{
        watchRole:{
            type:Array
        },
        role:{
            type:Object
        }
    },
    methods:{
        back(){
           this.watchRole.push(true);
        },
        submitUpdate(){
            this.$ajax.put("api/role/",this.role).then(res => {
                if(res.data.code == 10000){
                    this.watchRole.push(true);
                }
            } )
        }
    }

}
</script>