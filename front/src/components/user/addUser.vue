<template>
    <div id="from-user" >
        <el-form  :model="userInfo"  label-width="100px" class="demo-ruleForm">
            <el-form-item label="用户名" prop="username">
                <el-input  v-model="userInfo.username" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
                <el-input  v-model="userInfo.password" autocomplete="off"></el-input>
            </el-form-item>   

            <el-form-item label="部门">
                <el-select v-model="groupValue" placeholder="请选择活动区域">
                    <el-option v-for="item in group" :key="item.id" :label="item.groupName" :value="item.id"></el-option>
                </el-select>
            </el-form-item>

                <el-form-item label="角色">
                    <el-checkbox-group v-model="rolesValue" >
                        <el-checkbox v-for="role in roles"  :label="role.id" :key="role.id">{{role.roleName}}</el-checkbox>
                    </el-checkbox-group>
                </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="addSubmit">提交</el-button>
                <el-button @click="back">返回</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
export default {
    name: 'addUser',
    data() {
        return{
            status:true,
            rolesValue:["user"],
            roleName:'',
            groupValue:'',
            userInfo:{},
            roles:[{
                id:'user',
                roleName:'USER'   
            },
            {
                id:'admin',
                roleName:'ADMIN'   
            }]
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
        }
    },
    created(){
        console.log("初始化")
    },
    methods:{
        addSubmit(){
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
        },
        back(){
            this.watchaddUser.push(true)
    },
    },

}


</script>