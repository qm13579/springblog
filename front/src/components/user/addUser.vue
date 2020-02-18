<template>
    <div id="from-user" >
        <el-form  :model="userInfo"  label-width="100px" class="demo-ruleForm">
            <el-form-item label="日期" prop="date">
                <el-input  v-model="userInfo.date" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="姓名" prop="name">
                <el-input  v-model="userInfo.name" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="地址" prop="address">
                <el-input v-model="userInfo.address"></el-input>
            </el-form-item>
            
            <el-form-item label="选择部门">
                <el-select v-model="group" placeholder="请选择活动区域">
                    <el-option label="会计" value="1"></el-option>
                    <el-option label="科技" value="2"></el-option>
                </el-select>
            </el-form-item>

            <el-form-item label="分配角色">
                <el-select v-model="roleName" placeholder="请选择活动区域">
                    <el-option label="管理员" value="shanghai"></el-option>
                    <el-option label="一般用户" value="baijing"></el-option>
                </el-select>
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
    name: 'addUser',
    data() {
        return{
            roleName:'',
            group:'',
            userInfo:{
                date: '',
                username: '',
                address: '',
                id:'',
                status:''
            },
        }
    },
    props:{
        tableData:{
            type:Array
        },
        showTable:{
        }
    },
    created(){
        console.log("初始化")
    },
    methods:{
        addSubmit:function(){
            this.tableData.push(this.userInfo)
            this.status = true
            //清空数据
            this.userInfo = this.$options.data().userInfo
            this.roleName = this.$options.data().roleName
        }
    },
}


</script>