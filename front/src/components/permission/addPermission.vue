<template>
    <div id="from-user" >
        <el-form  :model="permission"  label-width="100px" class="demo-ruleForm">
            <el-form-item label="部门" prop="permissionName">
                <el-input  v-model="permission.groupName" autocomplete="off"></el-input>
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
            permission:{},
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
            this.$ajax.post("api/user/group/",this.permission).then(res => {
                if (res.data.code == 10000) {
                    this.permissionList.push(this.permission)
                    //清空数据
                    this.permission = this.$options.data().permission      
                }
            })
        }
    },
}


</script>