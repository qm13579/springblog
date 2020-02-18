<template>
    <div>
        <el-button type="seccess" style="float:right" @click="addRole">添加角色</el-button>
        <br>
        <br>
        <br>
        <el-table v-show="showTable" :data="roleList" height="500" style="width: 100%">
        <el-table-column prop="id" label="id" width="180"></el-table-column>
        <el-table-column prop="roleName" label="角色" width="180"></el-table-column>
        <el-table-column  label="操作">
            <template slot-scope="scope">
                <el-button @click="handleCilck(scope.row)" type="text" size="small">查看</el-button>
                <el-button type="text" size="small">编辑</el-button>
            </template>
        </el-table-column>   
        </el-table>
        <div id="from-user" >
            <el-form v-show="showFrom" :model="role"  label-width="100px" class="demo-ruleForm">
                <el-form-item label="id" prop="id">
                    <el-input  v-model="role.id" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="角色" prop="roleName">
                    <el-input  v-model="role.roleName" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary"  @click="submitUpdate">提交</el-button>
                    <el-button >重置</el-button>
                </el-form-item>
            </el-form>
        </div>
        <addRole  v-show="add" :roleList=roleList></addRole>
    </div>
</template>

<script>
//实现角色的增删改查
import addRole from './addRole'
export default {
    name:'role',
    components:{
        addRole
    },
    data(){
        return{
            add:false,
            showTable: true,
            showFrom: false,
            role:{
                id:'',
                roleName:''
            },
            roleList:[
                {
                id:'1',
                roleName:'USER'
            },
                {
                id:'2',
                roleName:'ADMIN'
            },
                 {
                id:'3',
                roleName:'TEST'
            },
            ]
        }
    },
    methods:{
        addRole:function(){
            console.log("this is add role")
            this.showTable = false;
            this.showFrom = false;
            this.add = true;

        },
        handleCilck(data){
            this.showTable = false;
            this.showFrom = true;
            this.showUser = this.user;
            this.role = data;
        },
        submitUpdate:function(){
            console.log("this is roleInfo Update")
            this.showTable = true;
            this.showFrom = false;

        }
    },
    created(){
        console.log("向后端请求数据，查询角色数据")
    },
    watch:{
        roleList(){
            this.showTable = true;
            this.add = false;
        }
    }
}
</script>