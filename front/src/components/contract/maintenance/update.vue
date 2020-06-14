<template>
    <el-form  :model="updatMaintenanceContract"   label-width="100px" class="demo-ruleForm">
    <el-row>
      <el-col :span="8">
        <el-form-item label="合同名称" prop="contractName">
            <el-input  size="small"  v-model="updatMaintenanceContract.contractName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="合同金额" prop="money">
            <el-input size="small" v-model="updatMaintenanceContract.money"  autocomplete="off"></el-input>
        </el-form-item>   

        <el-form-item label="签订时间" prop="startTime">
            <el-input size="small" v-model="updatMaintenanceContract.startTime"  autocomplete="off"></el-input>
        </el-form-item>     
        
        <el-form-item label="到期时间" prop="endTime">
            <el-input size="small" v-model="updatMaintenanceContract.endTime"  autocomplete="off"></el-input>
        </el-form-item>     

        <el-form-item label="甲方" prop="partA">
            <el-input size="small" v-model="updatMaintenanceContract.partyA" autocomplete="off"></el-input>
        </el-form-item> 

        <el-form-item label="甲方负责人" prop="partALegal">
            <el-input size="small" v-model="updatMaintenanceContract.partyALegal"  autocomplete="off"></el-input>
        </el-form-item> 
      </el-col>
      <el-col :span="8" :offset="2">
        <el-form-item label="甲方联系人" prop="partALinkMan">
            <el-input size="small" v-model="updatMaintenanceContract.partyALinkman"  autocomplete="off"></el-input>
        </el-form-item> 

        <el-form-item label="甲方联系电话" prop="partAlinkMobile">
            <el-input size="small" v-model="updatMaintenanceContract.partyALinkmanMobile" autocomplete="off"></el-input>
        </el-form-item> 

        <el-form-item label="乙方" prop="partB">
            <el-input size="small" v-model="updatMaintenanceContract.partyB"  autocomplete="off"></el-input>
        </el-form-item> 

        <el-form-item label="乙方负责人" prop="partBLegal">
            <el-input size="small" v-model="updatMaintenanceContract.partyBLegal"  autocomplete="off"></el-input>
        </el-form-item> 

        <el-form-item label="乙方联系人" prop="partBLinkMan">
            <el-input size="small" v-model="updatMaintenanceContract.partyBLinkman"  autocomplete="off"></el-input>
        </el-form-item> 

        <el-form-item label="乙方联系电话" prop="partBlinkMobile">
            <el-input size="small" v-model="updatMaintenanceContract.partyBLinkmanMobile"  autocomplete="off"></el-input>
        </el-form-item> 
        </el-col>
      </el-row>
      <el-row>
      <el-col :span="10" :offset="6">
        <el-form-item>
            <el-button type="primary" size="small" @click="addSubmit">提交</el-button>
            <el-button @click="back" size="small">返回</el-button>
        </el-form-item>
      </el-col>
      </el-row>

    </el-form>
</template>
<script>
// import Upload from './Upload'
export default {
    name:"add",
    data () {
        return{
            showTable:false,
        }
    },
    methods:{
        addSubmit(){
            var _this = this;
            this.$ajax.put("api/contract/maintenance/",this.updatMaintenanceContract).then(res => {
                if (res.data.code ==10000) {
                    _this.watchList.push(true);
                    _this.successMes();                    
                }else{
                    _this.errorMes();   
                }
            })
        },
        back(){
            this.watchList.push(true)
        },
        errorMes(){
            this.$message.error("错了哦，更新失败")
        },
        successMes(){
            this.$message({
                message:"设备更新成功",
                type:"success"
            })
        }
    },
    props:{
        updatMaintenanceContract:{
            type:Object
        },
        watchList:{
            type:Array
        }
    },
    components:{
        // Upload,
    }
}
</script>