<template>
    <el-row>
        <el-button type="success"   size="small" round  class="el-icon-plus butten font"  @click="buttunAdd">添加合同</el-button>
        <!-- <el-button type="info" size="small" plain class="el-icon-printer butten" @click="printer" >信息</el-button> -->
        <el-table v-loading="loading" :data="equipmentContract" height="500" style="width: 100%">
            <el-table-column label="序号" type="index"  ></el-table-column>
            <el-table-column prop="contractName" label="合同名称" ></el-table-column>
            <el-table-column prop="money" label="合同金额"></el-table-column>
            <el-table-column prop="startTime" label="签订时间"></el-table-column>
            <el-table-column prop="partyA" label="甲方"></el-table-column>
            <el-table-column prop="partyALegal" label="甲方负责人"></el-table-column>
            <el-table-column prop="partyALinkman" label="甲方联系人"></el-table-column>
            <el-table-column prop="partyALinkmanMobile" label="甲方联系人电话"></el-table-column>
            <el-table-column prop="partyB" label="乙方"></el-table-column>
            <el-table-column prop="partyBLegal" label="乙方负责人"></el-table-column>
            <el-table-column prop="partyBLinkman" label="乙方联系人"></el-table-column>
            <el-table-column prop="partyBLinkmanMobile" label="乙方联系人电话"></el-table-column>
            <el-table-column  label="操作" >
                <template slot-scope="scope" >
                    <el-button @click="handleCilck(scope.row)" type="text" size="small">编辑</el-button>
                    <!-- <el-button  @click="stopEquipment(scope.row)" type="text" size="small">废止</el-button> -->
                </template>
            </el-table-column>   

            <el-table-column  label="合同" width="100">
                <template slot-scope="scope" >
                    <el-button  @click="handleUpload(scope.row)" type="text" size="small">上传</el-button>
                    <el-button v-if="scope.row.file" @click="handlePreview(scope.row)" type="text" size="small">预览</el-button>
                </template>
            </el-table-column>   

            <el-table-column  label="关联设备" width="100">
                <template slot-scope="scope" >
                    <el-button  @click="handleEquipment(scope.row)" type="text" size="small">查看</el-button>
                </template>
            </el-table-column> 

        </el-table>
        
        <el-dialog title="新增合同" :visible.sync="showTable">
            <add :watchList="watchList"></add>
        </el-dialog>
        
        <el-dialog title="编辑合同" :visible.sync="showUpdate">
            <update :watchList="watchList" 
                 :updatEquipmentContract="updatEquipmentContract"></update>
        </el-dialog>
        <el-dialog title="上传合同" :visible.sync="showUpload">
            <upload :watchList="watchList" 
                 :cid="cid"></upload>
        </el-dialog>
        <el-dialog title="查看设备" :visible.sync="showEquipment">
            <equipment :watchList="watchList" 
                 :cid="cid" :key="timer"></equipment>
        </el-dialog>
    </el-row>
</template>
<script>
import add from './add'
import update from './update'
import upload from './Upload'
import equipment from './equipment'

export default {
    name:'find',
    data () {
        return{
            timer:"",
            showEquipment:false,
            loading: true,
            showUpload:false,
            cid:"",
            showUpdate:false,
            watchList:[],
            showTable:false,
            equipmentContract:[
                {contractName:"2019年电脑采购设备合同",'partA':"张三设备销售公司"}
            ],
            updatEquipmentContract:'',
        }
    },
    methods:{
        handleCilck(data){
            this.updatEquipmentContract = data;
            this.showUpdate = true;
        },
        stopEquipment(){

        },
        buttunAdd(){
            this.showTable=true
        },
        getEquipmentContract(){
            var _this = this;
            this.$ajax.get("/api/contract/equipment/").then(res => {
                if (res.data.code = 10000) {
                    _this.equipmentContract = res.data.data   
                    _this.loading = false
                    console.log(res.data.data)
                }
            })
        },
        handleUpload(data){
            this.showUpload = true;
            this.cid = "api/contract/equipment/"+data.cid
        },
        handlePreview(data){

            window.open("/api/contract/preview/"+data.file)
        },
        handleEquipment(data){
            this.cid = data.cid
            this.showEquipment = true
            this.timer = new Date().getTime();
        },
    },
    components:{
       add,
       update,
       upload,
       equipment,
    },
    watch:{
        watchList(){
            this.showTable = false;
            this.showUpdate = false ;
            this.showEquipment =false;
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