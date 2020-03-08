<template>
    <main>
        <div>
            <el-form :model="roleForm" ref="roleForm" :rules="rules" status-icon class="aa">
                <el-form-item  prop="roleName" class="padd" style="float:left;">
                    <el-input v-model="roleForm.roleName" size="small" class="padd" style="float:left;width:320px;" placeholder="ROLE">
                        <template  style="padding:0 5px 0 0" slot="prepend">ROLE_+</template>
                    </el-input>
                </el-form-item>

                <el-form-item  prop="desc" style="float:left;padding:0 10px 0 0;">
                    <el-input v-model="roleForm.desc"  size="small" class="padd" placeholder="请输入角色描述"></el-input>
                </el-form-item>

                <el-button type="primary" size="small"  icon="el-icon-edit" @click="addRole('roleForm')">添加</el-button>
                </el-form>
        </div>
            <br>
            <br>
        <el-collapse-transition>
            <el-table v-show="showTable" :data="roleList" height="500" style="width: 100%">
            <el-table-column label="序号" type="index" width="180"></el-table-column>
            <el-table-column prop="roleName" label="角色" width="180"></el-table-column>
            <el-table-column prop="desc" label="角色描述" width="180"></el-table-column>
            <el-table-column  label="操作">
                <template slot-scope="scope">
                    <el-button @click="handleCilck(scope.row)" type="text" size="small">查看</el-button>
                </template>
            </el-table-column>   
            </el-table>
        </el-collapse-transition>
        
        <!-- <el-dialog title="增加角色" :visible.sync="add">
            <addRole   :roleList="roleList" :watchRole="watchRole"></addRole>
        </el-dialog> -->

        <el-dialog title="更新角色" :visible.sync="update">
            <updataRole  :watchRole="watchRole" :role="role"></updataRole>
        </el-dialog>

    </main>
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
        var chechRoleName = (rule, value,callback) => {
            if (!value) {
                return callback(new Error("角色名不能为空"))
            }else{
                callback();
            }
        }
        var checkDesc = (rule ,value ,callback) => {
            if(!value){
                return callback(new Error("角色描述不能为空"))
            }else{
                callback()
            }
        }
        return{
            update:false,
            watchRole:[],
            add:false,
            showTable: true,
            showFrom: false,
            role:{},
            roleForm:{
                id:'',
                roleName: '',
                desc: '',

            },
            roleList:[],

            rules:{
                roleName:[
                    { validator: chechRoleName, trigger: 'blur'}
                ],
                desc:[
                    { validator: checkDesc, trigger: 'blur'}
                ]
            },
        }
    },
    methods:{
        addRole(formName){
            // const ROLE = this.roleForm
            console.log(this.roleForm)
            this.roleForm.id= this.roleForm.roleName.toLowerCase()
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    this.$ajax.post("api/role/",this.roleForm).then(res => {
                        if (res.data.code == 10000) {
                            // console.log(ROLE)
                            // this.roleList.push(ROLE)
                            this.open2()
                            this.$refs[formName].resetFields();
                        }else{
                            this.open4()
                        }
                    })
                }else{
                    return false;
                }
            })

        },
        handleCilck(data){
            // this.showTable = false;
            this.update = true;
            // this.add = false;
            this.showUser = this.user;
            this.role = data;
        },
        submitUpdate:function(){
            this.showTable = true;
            // this.showFrom = false;

        },
        open4() {
          this.$message({
          showClose: true,
          message: '错了哦，新增角色失败',
          type: 'error'
        });
      },
      open2() {
          this.$message({
          showClose: true,
          message: '成功，新增角色成功',
          type: 'success'
        });
      },
    },
    created(){
         var _this = this;
         this.$ajax.get("api/role/").then(res => {
             if(res.data.code == 10000){
                 _this.roleList = res.data.data
             }
         })
    },
    watch:{
        watchRole(){
            // this.showTable = true;
            // this.add = false;
            this.update = false
        }
    }
}
</script>
<style scoped>
    .aa >>> .el-form-item__content {
        line-height: 20px;
        /* position: relative;
        font-size: 14px; */
    }
    .padd{
        padding: 0 10px 0 0;
    }

</style>
