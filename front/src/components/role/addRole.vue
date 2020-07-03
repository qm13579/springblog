<template>
    <div id="from-user" >
        <el-form  :model="role"  label-width="100px" class="demo-ruleForm">
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
                <el-button type="primary" @click="addSubmit">提交</el-button>
                <el-button  @click="back">返回</el-button>
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

            },
        }
    },
    props:{
        roleList:{
            type:Array
        },
        watchRole:{
            type:Array
        }
    },
    created(){
        console.log("初始化")
    },
    methods:{
        back(){
            this.watchRole.push(true)
        },
        addSubmit(){
            this.$ajax.post("api/role/",this.role).then(res => {
                if(res.data.code == 10000 ){
                    this.roleList.push(this.role)
                    //清空数据
                    this.role = this.$options.data().role
                    this.watchRole.push(true)
                    this.$router.go(0);
                }
            })
        }
    },
}


</script>