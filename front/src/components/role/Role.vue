<template>
    <div>
            <el-button type="seccess" style="float:right" @click="addRole">添加角色</el-button>
            <br>
            <br>
            <br>
        <el-collapse-transition>
            <el-table v-show="showTable" :data="roleList" height="500" style="width: 100%">
            <el-table-column prop="id" label="id" width="180"></el-table-column>
            <el-table-column prop="roleName" label="角色" width="180"></el-table-column>
            <el-table-column prop="desc" label="角色描述" width="180"></el-table-column>
            <el-table-column  label="操作">
                <template slot-scope="scope">
                    <el-button @click="handleCilck(scope.row)" type="text" size="small">查看</el-button>
                    <el-button type="text" size="small">编辑</el-button>
                </template>
            </el-table-column>   
            </el-table>
        </el-collapse-transition>
        
        <transition name="el-zoom-in-top">
            <addRole  v-show="add" :roleList="roleList" :watchRole="watchRole"></addRole>
        </transition>
        <transition name="el-zoom-in-center">
            <updataRole v-show="update" :watchRole="watchRole" :role="role"></updataRole>
        </transition>
    </div>
</template>

<script>
//实现角色的增删改查
import addRole from './addRole'
import updataRole from "./updateRole"

export default {
    name:'role',
    components:{
        addRole,
        updataRole,
    },
    data(){
        return{
            update:false,
            watchRole:[],
            add:false,
            showTable: true,
            showFrom: false,
            role:{},
            roleList:[
            ]
        }
    },
    methods:{
        addRole:function(){
            console.log("this is add role")
            this.showTable = false;
            this.update = false;
            this.add = true;

        },
        handleCilck(data){
            this.showTable = false;
            this.update = true;
            this.add = false;
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
         var _this = this;
         this.$ajax.get("api/role/").then(res => {
             console.log(res)
             if(res.data.code == 10000){
                 _this.roleList = res.data.data
             }
         })
    },
    watch:{
        watchRole(){
            this.showTable = true;
            this.add = false;
            this.update = false
        }
    }
}
</script>