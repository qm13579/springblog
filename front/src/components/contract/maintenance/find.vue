<template>
    <el-row>
        <el-button type="info"  size="small" plain class="el-icon-plus butten font"  @click="buttunAdd">添加合同</el-button>
        <!-- <el-button type="info" size="small" plain class="el-icon-printer butten" @click="printer" >信息</el-button> -->
        <el-table :data="maintenanceContract" height="500" style="width: 100%">
            <el-table-column label="序号" type="index" width="100px" ></el-table-column>
            <el-table-column prop="contractName" label="合同名称" ></el-table-column>
            <el-table-column prop="money" label="合同金额"></el-table-column>
            <el-table-column prop="startTime" label="签订时间"></el-table-column>
            <el-table-column prop="endTime" label="到期时间"></el-table-column>
            <el-table-column prop="partyA" label="甲方"></el-table-column>
            <el-table-column prop="partyALegal" label="甲方负责人"></el-table-column>
            <el-table-column prop="partyALinkman" label="甲方联系人"></el-table-column>
            <el-table-column prop="partyALinkmanMobile" label="甲方联系人电话"></el-table-column>
            <el-table-column prop="partyB" label="乙方"></el-table-column>
            <el-table-column prop="partyBLegal" label="乙方负责人"></el-table-column>
            <el-table-column prop="partyBLinkman" label="乙方联系人"></el-table-column>
            <el-table-column prop="partyBLinkmanMobile" label="乙方联系人电话"></el-table-column>
            <el-table-column  label="操作" width="180">
                <template slot-scope="scope" >
                    <el-button @click="handleCilck(scope.row)" type="text" size="small">编辑</el-button>
                    <el-button  @click="handleEquipment(scope.row)" type="text" size="small">废止</el-button>
                </template>
            </el-table-column>   
        </el-table>
        
        <el-dialog title="新增合同" :visible.sync="showTable">
            <add :watchList="watchList"></add>
        </el-dialog>
        
        <el-dialog title="编辑合同" :visible.sync="showUpdate">
            <update :watchList="watchList" 
                 :updatMaintenanceContract="updatMaintenanceContract"></update>
        </el-dialog>

    </el-row>
</template>
<script>
import add from './add'
import update from './update'
export default {
    name:'find',
    data () {
        return{
            showUpdate:false,
            watchList:[],
            showTable:false,
            maintenanceContract:[],
            updatMaintenanceContract:'',
        }
    },
    methods:{
        handleCilck(data){
            this.updatMaintenanceContract = data;
            this.showUpdate = true;
        },
        handleEquipment(){

        },
        buttunAdd(){
            this.showTable=true
        },
        getEquipmentContract(){
            var _this = this;
            this.$ajax.get("api/contract/maintenance").then(res => {
                _this.maintenanceContract = res.data.data 
            })
        }
    },
    components:{
       add,
       update,
    },
    watch:{
        watchList(){
            this.showTable = false
        }
    },
    created(){
        this.getEquipmentContract()
    }
}
</script>
<style scoped>
.font{
    font-family: 华文行楷;
}
</style>