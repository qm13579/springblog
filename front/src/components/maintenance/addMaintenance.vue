<template>
    <el-row type="flex" class="row-bg" justify="center">

        <el-form  :model="maintenance"  label-width="100px" class="demo-ruleForm">
            <el-form-item label="运维人员姓名" >
                <el-input  v-model="maintenance.name" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="人员联系方式" >
                <el-input  v-model="maintenance.mobile" autocomplete="off"></el-input>
            </el-form-item>   
            <el-form-item label="设备信息" >
                <el-input  v-model="equipment.brand" disabled autocomplete="off"></el-input>
            </el-form-item> 

            <el-form-item label="设备状态" >
                <el-select v-model="statusValue" placeholder="请选择设备状态">
                    <el-option v-for="item in status" :key="item.id" :label="item.name" :value="item.id"></el-option>
                </el-select>
            </el-form-item> 
            
            <el-form-item label="描述" >
                <el-input  v-model="maintenance.describe" autocomplete="off"></el-input>
            </el-form-item> 

            <el-form-item>
                <el-button type="primary" @click="addSubmit">提交</el-button>
                <el-button @click="back">返回</el-button>
            </el-form-item>
        </el-form>
    </el-row>
</template>

<script>
export default {
    name:"addMaintenance",
    data(){
        return{
            statusValue:0,
            status:[
                {id:0,name:"正常"},
                {id:1,name:"故障"}
            ],
            maintenance:{},
        }
    },
    methods:{
        back(){

        },
        addSubmit(){
            var _this = this
            this.maintenance.id = "0321313"
            this.maintenance.status = this.statusValue
            this.maintenance.equipment={};
            this.maintenance.equipment.id=this.equipment.id;
            this.$ajax.post("api/maintenance/",this.maintenance).then(res => {
                if(res.data.code == 10000){
                    _this.$router.go(0)
                    this.open2()
                }else{
                    this.open4()
                }
            })
        },
        open4() {
            this.$message.error('错了哦，更新失败');
        },
        open2() {
            this.$message({
            message: '设备更新成功',
            type: 'success'
            });
        },
    },
    props:{
        equipment:{
            type:Object
        },
        functionList:{
            type:Array
        }
    }
}
</script>