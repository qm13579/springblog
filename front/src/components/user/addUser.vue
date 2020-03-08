<template>
    <el-row type="flex" class="row-bg" justify="center">        
        <el-form  :model="userInfo"  :rules="rules" ref="userInfo" label-width="100px" class="demo-ruleForm">
            <el-form-item label="用户名" prop="username">
                <el-input  size="small" style="width:200px" v-model="userInfo.username" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
                <el-input size="small" v-model="userInfo.password" style="width:200px" autocomplete="off"></el-input>
            </el-form-item>   

            <el-form-item label="部门" prop="groupValue">
                <el-select size="small" v-model="groupValue" style="width:200px"  placeholder="请选择活动区域">
                    <el-option v-for="item in group" :key="item.id" :label="item.groupName" :value="item.id"></el-option>
                </el-select>
            </el-form-item>

                <el-form-item label="角色">
                    <el-checkbox-group v-model="rolesValue" :min="1">
                        <el-checkbox v-for="role in roles"  :label="role.id" :key="role.id">{{role.roleName}}</el-checkbox>
                    </el-checkbox-group>
                </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="addSubmit('userInfo')">提交</el-button>
                <el-button @click="back">返回</el-button>
            </el-form-item>
        </el-form>
    </el-row>
</template>

<script>
export default {
    name: 'addUser',
    data() {
        var checkUsername=(rule,value,callback) =>{
            if (!value) {
                callback(new Error("用户名不能为空"))
            }else{
                callback()
            }
        }
        var checkpassword=(rule,value,callback) =>{
            if (!value) {
                callback(new Error("密码不能为空"))
            }else{
                callback()
            }
        }
        var checkgroupValue=(rule,value,callback) =>{
            if (!value) {
                callback(new Error("部门不能为空"))
            }else{
                callback()
            }
        }
        return{
            
            status:true,
            rolesValue:["user"],
            roleName:'',
            groupValue:'',
            userInfo:{},
            rules:{
                username:[{validator: checkUsername, trigger: 'blur'}],
                password:[{validator: checkpassword, trigger: 'blur'}],
                groupValue:[{validator: checkgroupValue, trigger: 'blur'}],
            }
        }
    },
    props:{
        tableData:{
            type:Array
        },
        group:{
            type:Array
        },
        watchaddUser:{
            type:Array
        },
        roles:{
            type:Array
        }
    },
    created(){
        console.log("初始化")
    },
    methods:{
        addSubmit(user){
            this.$refs[user].validate((valid) =>{
                if (valid) {
                    this.tableData.push(this.userInfo)
                    //角色增加
                    this.userInfo.roles=[]
                    this.rolesValue.forEach(element => {
                        console.log(element)
                        this.userInfo.roles.push({id:element})
                    });
                    //部门增加
                    this.userInfo.group = {id:this.groupValue}
                    //更新页面

                    this.$ajax.post("/api/user/",this.userInfo).then(res => {
                        console.log(res)
                    })
                    //清空数据
                    this.userInfo = this.$options.data().userInfo
                    this.roleName = this.$options.data().roleName     
                }else{
                    console.log("请输入值")
                }
            } )
        },
        back(){
            this.watchaddUser.push(true)
    },
    },

}
</script>

<style scoped>
.input{
    width:100px;
}
</style>
