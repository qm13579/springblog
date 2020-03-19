<template>
    <el-row type="flex" class="row-bg" justify="center">

    <el-form  :model="updatEquipmentContract"   label-width="100px" class="demo-ruleForm">
        <el-form-item label="合同名称" prop="contractName">
            <el-input  size="small"  v-model="updatEquipmentContract.contractName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="合同金额" prop="money">
            <el-input size="small" v-model="updatEquipmentContract.money"  autocomplete="off"></el-input>
        </el-form-item>   

        <el-form-item label="签订时间" prop="startTime">
            <el-input size="small" v-model="updatEquipmentContract.startTime"  autocomplete="off"></el-input>
        </el-form-item>     

        <el-form-item label="甲方" prop="partA">
            <el-input size="small" v-model="updatEquipmentContract.partyA"  autocomplete="off"></el-input>
        </el-form-item> 

        <el-form-item label="甲方负责人" prop="partALegal">
            <el-input size="small" v-model="updatEquipmentContract.partyALegal" autocomplete="off"></el-input>
        </el-form-item> 

        <el-form-item label="甲方联系人" prop="partyALinkMan">
            <el-input size="small" v-model="updatEquipmentContract.partyALinkman"  autocomplete="off"></el-input>
        </el-form-item> 

        <el-form-item label="甲方联系电话" prop="partAlinkMobile">
            <el-input size="small" v-model="updatEquipmentContract.partyALinkmanMobile"  autocomplete="off"></el-input>
        </el-form-item> 

        <el-form-item label="乙方" prop="partB">
            <el-input size="small" v-model="updatEquipmentContract.partyB" autocomplete="off"></el-input>
        </el-form-item> 

        <el-form-item label="乙方负责人" prop="partBLegal">
            <el-input size="small" v-model="updatEquipmentContract.partyBLegal"  autocomplete="off"></el-input>
        </el-form-item> 

        <el-form-item label="乙方联系人" prop="partBLinkMan">
            <el-input size="small" v-model="updatEquipmentContract.partyBLinkman"  autocomplete="off"></el-input>
        </el-form-item> 

        <el-form-item label="乙方联系电话" prop="partBlinkMobile">
            <el-input size="small" v-model="updatEquipmentContract.partyBLinkmanMobile"  autocomplete="off"></el-input>
        </el-form-item> 

        <el-form-item>
            <el-button type="primary" size="small" @click="addSubmit">提交</el-button>
            <el-button size="small" @click="back">返回</el-button>
        </el-form-item>
        
    </el-form>
    </el-row>
</template>
<script>
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
            this.$ajax.post("api/contract/equipment",this.updatEquipmentContract).then(res => {
                console.log("编辑合同成功")
                _this.watchList.push(true)
                // _this.showTable = true;
            })
        },
        back(){
            this.watchList.push(true)
        }
    },
    props:{
        updatEquipmentContract:{
            type:Object
        },
        watchList:{
            type:Array
        }
    },
    components:{
    }
}
</script>