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
            <el-form-item>
                <el-button type="primary" @click="addSubmit">提交</el-button>
                <el-button >重置</el-button>
            </el-form-item>
        </el-form>
        {{showTable}}
    </div>
</template>

<script>
export default {
    name: 'addUser',
    data() {
        return{
            status:false,
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
    created:function(){
        console.log("初始化")
    },
    methods:{
        addSubmit:function(){
            this.tableData.push(this.userInfo)
            this.status = true
            //清空数据
            this.userInfo = this.$options.data().userInfo
        }
    },
}


</script>