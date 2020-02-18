<template>
    <div id="from-user" >
        <el-form  :model="role"  label-width="100px" class="demo-ruleForm">
            <el-form-item label="id" prop="id">
                <el-input  v-model="role.id" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="角色" prop="roleName">
                <el-input  v-model="role.roleName" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="addSubmit">提交</el-button>
                <el-button >重置</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
export default {
    name: 'addRole',
    data() {
        return{
            role:{
                id: '',
                roleName: '',
            },
        }
    },
    props:{
        roleList:{
            type:Array
        },
    },
    created(){
        console.log("初始化")
    },
    methods:{
        addSubmit:function(){
            this.roleList.push(this.role)
            //清空数据
            this.role = this.$options.data().role
        }
    },
}


</script>