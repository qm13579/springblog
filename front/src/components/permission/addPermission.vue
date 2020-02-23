<template>
    <div id="from-user" >
        <el-form  :model="permission"  label-width="100px" class="demo-ruleForm">
            <el-form-item label="id" prop="id">
                <el-input  v-model="permission.id" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="权限" prop="permissionName">
                <el-input  v-model="permission.permissionName" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="URL" prop="url">
                <el-input  v-model="permission.url" autocomplete="off"></el-input>
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
    name: 'addPermissoon',
    data() {
        return{
            permission:{
                id: '',
                permissionName: '',
                url:'',
            },
        }
    },
    props:{
        permissionList:{
            type:Array
        },
    },
    created(){
        console.log("初始化")
    },
    methods:{
        addSubmit:function(){
            this.permissionList.push(this.permission)
            //清空数据
            this.permission = this.$options.data().permission
        }
    },
}


</script>