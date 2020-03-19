<template>
<el-row>
    <el-form  :model="maintenanceContract"   label-width="100px" class="demo-ruleForm">
        <el-col :span="8">
            <el-form-item label="合同名称" prop="contractName">
                <el-input  size="small"  v-model="maintenanceContract.contractName" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="合同金额" prop="money">
                <el-input size="small" v-model="maintenanceContract.money"  autocomplete="off"></el-input>
            </el-form-item>  
        
            <el-form-item label="签订时间" prop="startTime">
                <el-input size="small" v-model="maintenanceContract.startTime"  autocomplete="off"></el-input>
            </el-form-item>   

            <el-form-item label="到期时间" prop="endTime">
                <el-input size="small" v-model="maintenanceContract.endTime"  autocomplete="off"></el-input>
            </el-form-item>     

            <el-form-item label="甲方" prop="partA">
                <el-input size="small" v-model="maintenanceContract.partyA" autocomplete="off"></el-input>
            </el-form-item> 

            <el-form-item label="甲方负责人" prop="partALegal">
                <el-input size="small" v-model="maintenanceContract.partyALegal" autocomplete="off"></el-input>
            </el-form-item> 

        </el-col>
        <el-col :span="8" :offset="2">

            <el-form-item label="甲方联系人" prop="partALinkMan">
                <el-input size="small" v-model="maintenanceContract.partyALinkman" autocomplete="off"></el-input>
            </el-form-item> 

            <el-form-item label="甲方联系电话" prop="partAlinkMobile">
                <el-input size="small" v-model="maintenanceContract.partyALinkmanMobile" autocomplete="off"></el-input>
            </el-form-item> 

            <el-form-item label="乙方" prop="partB">
                <el-input size="small" v-model="maintenanceContract.partyB" autocomplete="off"></el-input>
            </el-form-item> 

            <el-form-item label="乙方负责人" prop="partBLegal">
                <el-input size="small" v-model="maintenanceContract.partyBLegal" autocomplete="off"></el-input>
            </el-form-item> 

            <el-form-item label="乙方联系人" prop="partBLinkMan">
                <el-input size="small" v-model="maintenanceContract.partyBLinkman"  autocomplete="off"></el-input>
            </el-form-item> 

            <el-form-item label="乙方联系电话" prop="partBlinkMobile">
                <el-input size="small" v-model="maintenanceContract.partyBLinkmanMobile"  autocomplete="off"></el-input>
            </el-form-item> 

            <el-form-item>
                <el-button type="primary" size="small" @click="addSubmit">提交</el-button>
                <el-button @click="back" size="small" >返回</el-button>
            </el-form-item>
        </el-col>
    </el-form>
</el-row>
</template>
<script>
export default {
    name:"add",
    data () {
        return{
            showTable:false,
            maintenanceContract:{},
        }
    },
    methods:{
        addSubmit(){
            var _this = this;
            this.$ajax.post("api/contract/maintenance",this.maintenanceContract).then(res => {
                console.log("添加合同成功")
                _this.watchList.push(true)
            })
        },
        back(){
            this.watchList.push(true)
        }
    },
    props:{
        watchList:{
            type:Array
        }
    },
    components:{
        // Upload,
    }
}
</script>