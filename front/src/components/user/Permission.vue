<template>
    <div>
        <el-button type="seccess" style="float:right" @click="addPermission">添加访问路径</el-button>
        <br>
        <br>
        <br>
        <el-table v-show="showTable" :data="permissionList" height="500" style="width: 100%">
        <el-table-column prop="id" label="id" width="180"></el-table-column>
        <el-table-column prop="permissionName" label="权限" width="180"></el-table-column>
        <el-table-column prop="url" label="URL" width="180"></el-table-column>

        <el-table-column  label="操作">
            <template slot-scope="scope">
                <el-button @click="handleCilck(scope.row)" type="text" size="small">查看</el-button>
                <el-button type="text" size="small">编辑</el-button>
            </template>
        </el-table-column>   
        </el-table>
        <div id="from-user" >
            <el-form v-show="showFrom" :model="permission"  label-width="100px" class="demo-ruleForm">
                <el-form-item label="id" prop="id">
                    <el-input  v-model="permission.id" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="角色" prop="permissionName">
                    <el-input  v-model="permission.permissionName" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary"  @click="submitUpdate">提交</el-button>
                    <el-button >重置</el-button>
                </el-form-item>
            </el-form>
        </div>
        <addPermission  v-show="add" :permissionList=permissionList></addPermission>
    </div>
</template>

<script>
//实现角色的增删改查
import addPermission from './addPermission'
export default {
    name:'permission',
    components:{
        addPermission
    },
    data(){
        return{
            add:false,
            showTable: true,
            showFrom: false,
            permission:{
                id:'',
                permissionName:'',
                url:''
            },
            permissionList:[
                {
                id:'1',
                permissionName:'用户访问',
                url:'/user'
            },
                {
                id:'2',
                permissionName:'角色访问',
                url:'/role'
            },
                 {
                id:'3',
                permissionName:'权限访问',
                url:'/permission'
            },
            ]
        }
    },
    methods:{
        addPermission:function(){
            console.log("this is add role")
            this.showTable = false;
            this.showFrom = false;
            this.add = true;

        },
        handleCilck(data){
            this.showTable = false;
            this.showFrom = true;
            this.permission = data;
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
        permissionList(){
            this.showTable = true;
            this.add = false;
        }
    }
}
</script>