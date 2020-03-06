<template>
    <el-main>
        <el-row>
            <div>
                <div>
                    <el-form :model="dataForm" ref="dataForm" :rules="rules" status-icon>
                    <el-form-item  prop="groupName" style="float:left;width:380px;padding:0 10px 0 0;">
                        <el-input v-model="dataForm.groupName"  size="small" style="float:left;width:380px;padding:0 10px 0 0;" placeholder="添加设备类型">
                            <template  style="padding:0 5px 0 0" slot="prepend">TYPE+</template>
                        </el-input>
                    </el-form-item>
                    
                    <el-button type="primary"  size="small" icon="el-icon-edit" @click="addGroup('dataForm')">添加</el-button>
                    </el-form>
                </div>
            </div>
        </el-row>
        <br>
        <el-row type="flex" class="row-bg" justify="center">

        <el-table v-show="groupShow" :data="groupList" height="500" style="width: 100%">
        <el-table-column label="序号" type="index" width="180"></el-table-column>
        <el-table-column prop="groupName" label="设备类型" width="180"></el-table-column>

        <el-table-column  label="操作">
            <template slot-scope="scope">
                <el-button @click="handleCilck(scope.row)" type="text" size="small">查看</el-button>
                <el-button type="text" size="small">编辑</el-button>
            </template>
        </el-table-column>   
        </el-table>
            <!-- <addGroup v-show="addShow" :watchData="watchData" ></addGroup> -->
            <el-dialog title="设备类型" :visible.sync="updateShow">
            
                <updateGroup :watchData="watchData" :group="group"></updateGroup>
            </el-dialog>
        </el-row>
    </el-main>
</template>

<script>
import addGroup from './addGroup'
import updateGroup from './updateGroup'

export default {
    name:"equipmentGroup",
    components:{
        addGroup,
        updateGroup,
    },
    data(){
        var checkGroupName = (rule, value,callback) =>{
            if (!value) {
                return callback(new Error("设备类型不能为空"))
            }else{
                callback()
            }
        }
        return{
            addShow:false,
            updateShow:false,
            groupShow:true,
            groupList:[],
            watchData:[],
            group:{},
            dataForm:{
                id:'',
                groupName:"",
                gid:""
            },
            rules:{
                groupName:[
                    { validator: checkGroupName, trigger: 'blur'}
                ],
            },
        }
    },
    created(){
        var _this = this;
        this.$ajax.get("api/equipment/group").then(res => {
            if(res.data.code == 10000){
                _this.groupList = res.data.data
            }
        });
    },
    methods:{
        handleCilck(data){
            this.group = data
            // this.addShow = false
            this.updateShow = true
            // this.groupShow = false
        },
        //添加设备类型
        addGroup(form){
            var _this = this;
            console.log(this.dataForm)
            this.$refs[form].validate((valid) => {
            if (valid) {
                this.$ajax.post("api/equipment/group",this.dataForm).then(res => {
                    if (res.data.code == 10000) {
                        this.open2();
                        this.$refs[form].resetFields();
                        _this.groupList.push(_this.dataForm)
                        console.log(_this.dataForm)
                    }
                })
            }else{
                return false;
            }
         })
        },
        open2() {
            this.$message({
            showClose: true,
            message: '新增设备类型成功',
            type: 'success'
            });
        },
    },
    watch:{
        watchData(){
            this.addShow = false
            this.updateShow = false
            this.groupShow = true
        }
    }

}
</script>