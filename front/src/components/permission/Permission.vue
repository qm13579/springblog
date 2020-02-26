<template>
    <el-main>
        <el-row>
            <el-button type="seccess" style="float:right" @click="addDepartment">添加部门</el-button>
        </el-row>
        <br>
        <br>
        <br>
        <el-row type="flex" class="row-bg" justify="center">

        <el-table v-show="showTable" :data="permissionList" height="500" style="width: 100%">
        <el-table-column prop="id" label="id" width="180"></el-table-column>
        <el-table-column prop="groupName" label="部门" width="180"></el-table-column>

        <el-table-column  label="操作">
            <template slot-scope="scope">
                <el-button @click="handleCilck(scope.row)" type="text" size="small">查看</el-button>
                <el-button type="text" size="small">编辑</el-button>
            </template>
        </el-table-column>   
        </el-table>
        <div id="from-user" >
            <el-form v-show="showFrom" :model="permission"  label-width="100px" class="demo-ruleForm">
                
                <el-form-item label="部门" prop="groupName">
                    <el-input  v-model="permission.groupName" autocomplete="off"></el-input>
                </el-form-item>

                <el-form-item>
                    <el-button type="primary"  @click="submitUpdate">提交</el-button>
                    <el-button >重置</el-button>
                </el-form-item>
            </el-form>
        </div>
        <addPermission  v-show="add" :permissionList=permissionList></addPermission>
        </el-row>
    </el-main>
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
            permission:{},
            permissionList:[]
        }
    },
    methods:{
        addDepartment:function(){
            console.log("this is add department")
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
            this.$ajax.post("api/user/group/",this.permission).then(res => {
                if (res.data.code == 10000) {
                    console.log("this is roleInfo Update")
                    this.showTable = true;
                    this.showFrom = false;
                            
                }else{
                    console.log("更新失败")
                }

            })

        },
        getGroupData(){
            var _this = this
            this.$ajax.get("api/user/group/").then(res => {
                console.log(res)
                if (res.data.code == 10000) {
                    _this.permissionList = res.data.data
                }
            })
        }
    },
    created(){
        this.getGroupData()
    },
    watch:{
        permissionList(){
            this.showTable = true;
            this.add = false;
        }
    }
}
</script>