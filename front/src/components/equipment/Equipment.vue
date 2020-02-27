  <template>
    <el-main >
        <el-row >
           <el-col :span="6">
                <el-dropdown  split-button type="primary" >
                    功能
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item v-for=" item in functionList" :key="item.id" @click.native="findEquipmentByGruop(item)">{{item.groupName}}</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
           </el-col>
            <el-col  :span="6" :offset="12">
                <el-button type="seccess"  @click="addEquipment">添加设备</el-button>
            </el-col>

        </el-row>

        <br>
        <br>
        <el-row type="flex" class="row-bg" justify="center">
                <el-table v-show="showTable" :data="equipmentList" height="500" style="width: 100%">

                <el-table-column prop="brand" label="品牌"></el-table-column>
                <el-table-column prop="type.groupName" label="类型"  ></el-table-column>
                <el-table-column prop="part" label="型号"  ></el-table-column>
                <el-table-column prop="registerDate" label="登记日期"></el-table-column>
                <el-table-column prop="producers" label="生产商"></el-table-column>
                <el-table-column prop="seller" label="销售商"></el-table-column>
                <el-table-column prop="mobile" label="运维电话"></el-table-column>
                <el-table-column prop="statusString" label="设备状态"></el-table-column>
                <el-table-column prop="metricsString" label="分配状态"></el-table-column>

                <el-table-column  label="操作" width="180">
                    <template slot-scope="scope">
                        <el-button @click="handleCilck(scope.row)" type="text" size="small">    编辑</el-button>
                        <el-button @click="handleEquipment(scope.row)" type="text" size="small">分配用户</el-button>
                    </template>
                </el-table-column>   
                </el-table>

                <el-form v-show="showFrom" :model="equipment"  label-width="100px" class="demo-ruleForm">

                    <el-form-item label="品牌" prop="brand" >
                        <el-input  v-model="equipment.brand" autocomplete="off"></el-input>
                    </el-form-item>

                    <el-form-item label="设备类型">
                        <el-select  v-model="equipmentValue" placeholder="请选择">
                            <el-option v-for="item in equipmentTypeList" :key="item.id" :label="item.groupName" :value="item.id"></el-option>
                        </el-select>
                    </el-form-item>

                    <el-form-item label="型号" >
                        <el-input v-model="equipment.part"></el-input>
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
                
                <!--添加设备-->
                <addEquipment v-show="add" :equipmentList="equipmentList" :equipmentTypeList="equipmentTypeList" :watchaddData="watchaddData"></addEquipment>
                <!-- 分配设备使用 -->
                <UseEquipment v-show="equipmentShow" :watchaddData="watchaddData" :equipment="equipment" ></UseEquipment>
                <!-- 维保记录 -->
                <maintenance v-show="maintenanceShow" :watchaddData="watchaddData" :maintenance="maintenance"></maintenance>
                <!-- 新增维保和报修 -->
                <addMaintenance v-show="addMaintenanceShow" :equipment="equipment" :watchaddData="watchaddData" :functionList="functionList"></addMaintenance>
        </el-row>
    </el-main>
    
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
            input:"",
            functionList:[],
            maintenance:[],
            status:"",
            watchaddData:[],
            equipmentValue:'',
            addMaintenanceShow:false,
            maintenanceShow:false,
            equipmentShow:false,
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
            console.log("back --> 切换显示设备")
            this.showTable = true;
            this.showFrom = false;
            this.findEquipmentGruop()
        },
        //用户传递数据并切换 实现更新
        handleCilck(data){
            console.log("handle 设备数据，用于更新")
            this.showTable = false;
            this.showFrom = true;
            this.equipment = data;
            this.equipmentValue = data.type.id
            this.status = data.status
            this.functionList=[
                {id:"maintenance",groupName:"维保记录"},
                {id:"addMaintenance",groupName:"新增维保"},
                {id:"addMaintenance1",groupName:"故障申报"},
                {id:"equipment",groupName:"查看设备"},

            ]
        },
        //更新组件中提交数据
        submit(){
            console.log("提交数据后 显示设备数据")
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
            this.equipmentShow=false;
            this.addMaintenanceShow = false;
            this.maintenanceShow = false
            this.findEquipmentGruop()
        },
        handleEquipment(data){
            this.equipment = data;
            this.showTable = false;
            this.showFrom = false;
            this.equipmentShow=true;
            this.maintenanceShow = false
            this.addMaintenanceShow = false;
            this.findEquipmentGruop()
        },
        //当在维保历史组件时更新功能键，显示维保组件
        toMmaintenance(){
            this.showTable = false;
            this.showFrom = false;
            this.equipmentShow=false;
            this.maintenanceShow = true;
            this.addMaintenanceShow = false;

            console.log("已进入维保组件")
            var _this = this;
            this.$ajax.get("api/maintenance/"+this.equipment.id).then(res =>{
                if (res.data.code == 10000) {
                    _this.maintenance = res.data.data
                    console.log("asda")
                }
            })
        },
        //当新增维保及报修中更新功能键
        addToMmaintenance(){
                this.showTable = false;
                this.showFrom = false;
                this.equipmentShow=false;
                this.maintenanceShow = false;
                this.addMaintenanceShow=true;
        },
        //通过设备类型查找设备
        findEquipmentByGruop(item){
            console.log("触发了功能键")
            console.log(item)
            var _this =this
            if (item.id == "maintenance") {
                //显示维保组件
                console.log("进入维保组件")
                this.toMmaintenance()
            }else if(item.id == "addMaintenance" || item.id =="addMaintenance1"){
                this.addToMmaintenance()
            }else if(item.id == "equipment"){
                this.showTable = false;
                this.showFrom = true;
                this.maintenanceShow = false;
                this.addMaintenanceShow=false;
            }else{
                this.$ajax.get("api/equipment/"+item.id).then(res => {
                    if (res.data.code == 10000) {
                        console.log("向后端请求数据")
                        _this.equipmentList = res.data.data;
                        console.log(res.data.data)
                    }
                })}
                
                
            
        },
        //获取设备分类
        findEquipmentGruop(){
            var _this = this
            this.$ajax.get("api/equipment/group").then(res => {
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
            console.log("设备数据发生变化")
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
        //获取设备数据
        this.$ajax.get("api/equipment/").then(res =>{
            _this.equipmentList =  res.data.data;
        });
        //获取设备分类
        this.findEquipmentGruop()
    },
    
}

</script>

