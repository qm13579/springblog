  <template>
    <div>
        <el-dropdown  split-button type="primary" style="float:left">
        功能
            <el-dropdown-menu slot="dropdown">
                <el-dropdown-item v-for=" item in functionList" :key="item.id" @click.native="findEquipmentByGruop(item)">{{item.groupName}}</el-dropdown-item>
            </el-dropdown-menu>
        </el-dropdown>
        <el-button type="seccess" style="float:right" @click="addEquipment">添加设备</el-button>
        <br>
        <br>
        <br>
        <el-table v-show="showTable" :data="equipmentList" height="500" style="width: 100%">

        <el-table-column prop="brand" label="品牌" width="180"></el-table-column>
        <el-table-column prop="type.groupName" label="类型"></el-table-column>
        <el-table-column prop="part" label="型号"></el-table-column>
        <el-table-column prop="registerDate" label="登记日期"></el-table-column>
        <el-table-column prop="producers" label="生产商"></el-table-column>
        <el-table-column prop="seller" label="销售商"></el-table-column>
        <el-table-column prop="mobile" label="运维电话"></el-table-column>
        <el-table-column prop="statusString" label="设备状态"></el-table-column>
        <el-table-column prop="metricsString" label="分配状态"></el-table-column>

        <el-table-column  label="操作">
            <template slot-scope="scope">
                <el-button @click="handleCilck(scope.row)" type="text" size="small">编辑</el-button>
                <el-button @click="handleEquipment(scope.row)" type="text" size="small">分配用户</el-button>
            </template>
        </el-table-column>   
        </el-table>
        <div id="from-user" >
            <el-form v-show="showFrom" :model="equipment"  label-width="100px" class="demo-ruleForm">

                <el-form-item label="品牌" prop="brand">
                    <el-input  v-model="equipment.brand" autocomplete="off"></el-input>
                </el-form-item>

                <el-form-item label="设备类型">
                    <el-select  v-model="equipmentValue" placeholder="请选择">
                        <el-option v-for="item in equipmentTypeList" :key="item.id" :label="item.groupName" :value="item.id"></el-option>
                    </el-select>
                </el-form-item>
                
                <el-form-item label="登记日期" prop="registerDate">
                    <el-input v-model="equipment.registerDate"></el-input>
                </el-form-item>                
                <el-form-item label="生产商" prop="producers">
                    <el-input v-model="equipment.producers"></el-input>
                </el-form-item>
                <el-form-item label="运维电话" prop="mobile">
                    <el-input v-model="equipment.mobile"></el-input>
                </el-form-item>
                <el-form-item label="销售商" prop="seller">
                    <el-input v-model="equipment.seller"></el-input>
                </el-form-item>

                <el-form-item label="设备状态"  >
                    <el-select v-model="status" placeholder="请选择活动区域">
                        <el-option v-for="item in statusList" :key="item.id" :label="item.name" :value="item.id"></el-option>
                    </el-select>
                </el-form-item>                
                
                <el-form-item>
                    <el-button type="primary"  @click="submit">提交</el-button>
                    <el-button @click="back" >返回</el-button>
                </el-form-item>
            </el-form>
        </div>
        <!--添加设备-->
        <addEquipment v-show="add" :equipmentList="equipmentList" :equipmentTypeList="equipmentTypeList" :watchaddData="watchaddData"></addEquipment>
        <!-- 分配设备使用 -->
        <UseEquipment v-show="equipmentShow" :watchaddData="watchaddData" :equipment="equipment" ></UseEquipment>
        <!-- 维保记录 -->
        <maintenance v-show="maintenanceShow" :watchaddData="watchaddData" :maintenance="maintenance"></maintenance>
        <!-- 新增维保和报修 -->
        <addMaintenance v-show="addMaintenanceShow" :equipment="equipment" :watchaddData="watchaddData"></addMaintenance>
    </div>
  </template>

<script>
import UseEquipment from "./useEquipment-e"
import addEquipment from '@/components/equipment/addEquipment'
import maintenance from "../maintenance/maintenanceToEeqpment"
import addMaintenance from "../maintenance/addMaintenance"

export default {
    name: "equipment",
    components:{
        addEquipment,
        UseEquipment,
        maintenance,
        addMaintenance,
    },
    data() {
        return{
            addMaintenanceShow:false,
            functionList:[],
            maintenance:[],
            maintenanceShow:false,
            status:"",
            watchaddData:[],
            equipmentShow:false,
            equipmentValue:'',
            add:false,
            showTable: true,
            showFrom: false,
            equipment:{
                type:{}
            },
            title: 'this findAl;l User',
            equipmentList: [],
            equipmentTypeList:[],
            statusList:[
                {id:0,name:"已启用"},
                {id:1,name:"故障"},
                {id:2,name:"未启用"}
            ],
        }
    },
    methods:{
        //用于切换显示数据
        back(){
            this.showTable = true;
            this.showFrom = false;
            this.findEquipmentGruop()
        },
        //用户传递数据并切换 实现更新
        handleCilck(data){
            this.showTable = false;
            this.showFrom = true;
            this.equipment = data;
            this.equipmentValue = data.type.id
            this.status = data.status
            this.functionList=[
                {id:"1",groupName:"维保记录"},
                {id:"2",groupName:"新增维保"},
                {id:"3",groupName:"故障申报"},
                {id:"4",groupName:"查看设备"},

            ]
        },
        //更新组件中提交
        submit(){
            this.showTable = true;
            this.showFrom = false;
            this.$ajax.put("/api/equipment/",this.equipment).then(res =>{
                
            })
        },
        //添加设备
        addEquipment(){
            this.showTable = false;
            this.showFrom = false;
            this.add = true;
            this.findEquipmentGruop()
        },
        handleEquipment(data){
            this.equipment = data;
            this.showTable = false;
            this.showFrom = false;
            this.equipmentShow=true;
            this.findEquipmentGruop()
        },
        //当在维保历史组件时更新功能键
        toMmaintenance(){
            if(this.showTable == false){
                var _this = this;
                this.$ajax.get("api/maintenance/"+this.equipment.id).then(res =>{
                    if (res.data.code == 10000) {
                        _this.maintenance = res.data.data
                        _this.showTable = false;
                        _this.showFrom = false;
                        _this.equipmentShow=false;
                        _this.maintenanceShow = true;

                    }
                })
            }else{
                // this.$router.push("/maintenance")
            }
        },
        //当新增维保及报修中更新功能键
        addToMmaintenance(){
            if(this.showTable == false){
                this.showTable = false;
                this.showFrom = false;
                this.equipmentShow=false;
                this.maintenanceShow = false;
                this.addMaintenanceShow=true;
            }
        },
        //通过设备类型查找设备
        findEquipmentByGruop(item){
            console.log(item)
            var _this =this
            if (this.showTable == false) {
                if (item.id == "1") {
                    this.toMmaintenance()
                }
                if(item.id == "2" || item.id == "3"){
                    this.addToMmaintenance()
                }
                else{
                    this.showTable = false;
                    this.showFrom = true;
                    this.maintenanceShow = false;
                    this.addMaintenanceShow=false;
                }
            }else{
                this.$ajax.get("api/equipment/"+item.id).then(res => {
                    if (res.data.code == 10000) {
                        _this.equipmentList = res.data.data;
                    }
                })
            }

        },
        //获取设备分类
        findEquipmentGruop(){
            var _this = this
            this.$ajax.get("api/equipment/equipment/group").then(res => {
                if (res.data.code == 10000) {
                    _this.functionList = res.data.data
                    _this.equipmentTypeList = res.data.data

                }
            })
        }

    },
    //监听数据是否发生变化
    watch:{
        equipmentList(){
            this.showTable = true;
            this.add = false;
            this.findEquipmentGruop()
        },
        watchaddData(){
            this.showTable = true;
            this.add = false;
            this.equipmentShow = false; 
            this.findEquipmentGruop()
        }
    },
    created(){
        var _this = this;
        //获取用户
        this.$ajax.get("api/equipment/").then(res =>{
            _this.equipmentList =  res.data.data;
        });
        //获取设备分类
        this.findEquipmentGruop()
    },
    
}

</script>

<style scoped>
 #from-user{
    width: 20%;
    margin:0 auto
 }
</style>