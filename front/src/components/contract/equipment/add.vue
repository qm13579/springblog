<template>
    <el-row type="flex" class="row-bg" justify="center">
        <el-col :span="14" :offset="2">
    <el-form  :model="equipmentContract"   label-width="100px" class="demo-ruleForm">
        <el-form-item label="合同名称" prop="contractName">
            <el-input  size="small" style="width:200px" v-model="equipmentContract.contractName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="合同金额" prop="money">
            <el-input size="small" v-model="equipmentContract.money" style="width:200px" autocomplete="off"></el-input>
        </el-form-item>   

        <el-form-item label="签订时间" prop="startTime">
            <el-input size="small" v-model="equipmentContract.startTime" style="width:200px" autocomplete="off"></el-input>
        </el-form-item>     

        <el-form-item label="甲方" prop="partA">
            <el-input size="small" v-model="equipmentContract.partyA" style="width:200px" autocomplete="off"></el-input>
        </el-form-item> 

        <el-form-item label="甲方负责人" prop="partALegal">
            <el-input size="small" v-model="equipmentContract.partyALegal" style="width:200px" autocomplete="off"></el-input>
        </el-form-item> 

        <el-form-item label="甲方联系人" prop="partALinkMan">
            <el-input size="small" v-model="equipmentContract.partyALinkman" style="width:200px" autocomplete="off"></el-input>
        </el-form-item> 

        <el-form-item label="甲方联系电话" prop="partAlinkMobile">
            <el-input size="small" v-model="equipmentContract.partyALinkmanMobile" style="width:200px" autocomplete="off"></el-input>
        </el-form-item> 

        <el-form-item label="乙方" prop="partB">
            <el-input size="small" v-model="equipmentContract.partyB" style="width:200px" autocomplete="off"></el-input>
        </el-form-item> 

        <el-form-item label="乙方负责人" prop="partBLegal">
            <el-input size="small" v-model="equipmentContract.partyBLegal" style="width:200px" autocomplete="off"></el-input>
        </el-form-item> 

        <el-form-item label="乙方联系人" prop="partBLinkMan">
            <el-input size="small" v-model="equipmentContract.partyBLinkman" style="width:200px" autocomplete="off"></el-input>
        </el-form-item> 

        <el-form-item label="乙方联系电话" prop="partBlinkMobile">
            <el-input size="small" v-model="equipmentContract.partyBLinkmanMobile" style="width:200px" autocomplete="off"></el-input>
        </el-form-item> 

        <el-form-item>
            <el-button type="primary" size="small" @click="addSubmit">提交</el-button>
            <el-button @click="back" size="small">返回</el-button>
        </el-form-item>

    </el-form>
        </el-col>
    </el-row>
</template>
<script>
export default {
    name:"add",
    data () {
        return{
            showTable:false,
            equipmentContract:{},
        }
    },
    methods:{
        addSubmit(){
            var _this = this;
            this.$ajax.post("api/contract/equipment/",this.equipmentContract).then(res => {
                console.log("添加合同成功")
                _this.watchList.push(true)
                // _this.showTable = true;
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
    }
}
</script>