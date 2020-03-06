<template>
    <el-row type="flex" class="row-bg" justify="center">
        <el-form  :model="user"  label-width="100px" class="demo-ruleForm">
            
            <el-form-item label="用户名">
                <el-col :span="12">
                    <el-input  v-model="user.username" autocomplete="off"></el-input>
                </el-col>
            </el-form-item>

            <el-form-item label="姓名" >
                <el-col :span="12">
                    <el-input  v-model="user.name" autocomplete="off"></el-input>
                </el-col>
            </el-form-item>

            <el-form-item label="部门">
                <el-col :span="12">
                    <el-select v-model="groupValue" placeholder="请选择活动区域">
                        <el-option v-for="item in group" :key="item.id" :label="item.groupName" :value="item.id"></el-option>
                    </el-select>
                </el-col>
            </el-form-item>

            <el-form-item label="用户状态" prop="status">
                <el-col :span="12">
                    <el-select v-model="user.status" placeholder="请选择活动区域">
                        <el-option v-for="item in statusList" :key="item.id" :label="item.name" :value="item.id"></el-option>
                    </el-select>
                </el-col>
            </el-form-item>

            <el-form-item label="角色">
                <el-checkbox-group v-model="rolesValue" >
                    <el-checkbox v-for="role in roles"  :label="role.id" :key="role.id">{{role.roleName}}</el-checkbox>
                </el-checkbox-group>
            </el-form-item>

            <el-form-item>
                <el-button type="primary"  @click="submit">提交</el-button>
                <el-button @click="back">返回</el-button>
            </el-form-item>
        </el-form>
    </el-row>
</template>
<script>
export default {
    name:"updateUser",
    props:{
        user:{
            type:Object
        },
        groupValue:{

        },
        rolesValue:{
            type:Array
        },
        group:{
            type:Array
        },
        roles:{
            type:Array
        },
        watchaddUser:{
            type:Array
        }
    },
    data(){
        return{
            statusList:[
                {id:0,name:"启用"},
                {id:2,name:"停用"},
            ]

        }
    },
    methods:{
        submit(){
            this.showTable = true;
            this.showFrom = false;
            for(var i=0;i<this.tableData.length;i++){
                 var currentUser = this.tableData[i]
                 if(currentUser.id == this.user.id){
                     currentUser.group.id = this.groupValue
                     this.tableData[i] = this.user
                     this.tableData[i] = currentUser
                     currentUser.roles=[]
                     this.rolesValue.forEach(element => {
                         currentUser.roles.push({"id":element})
                     });
                     this.$ajax.put("api/user/",currentUser).then(res => {
                         console.log(res)
                     })
                     break
                 }
            }
        },
        back(){
            console.log("asadadas")
            this.watchaddUser.push(true)
        }
    }
}
</script>