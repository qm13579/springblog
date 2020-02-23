  <template>
    <div>
        <el-dropdown  split-button type="primary" style="float:left">
        功能
            <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>维保</el-dropdown-item>
                <el-dropdown-item>科技</el-dropdown-item>
                <el-dropdown-item>人事</el-dropdown-item>
                <el-dropdown-item>后勤</el-dropdown-item>
                <el-dropdown-item>办公室</el-dropdown-item>
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
                <el-button @click="handleCilck(scope.row)" type="text" size="small">查看</el-button>
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
                    <!-- <el-input v-model="equipment.statusString"></el-input> -->
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
        <!--父组件通过数据绑定传递值，子组件通过prop获取-->
        <addEquipment v-show="add" 
            :equipmentList="equipmentList" 
            :equipmentTypeList="equipmentTypeList">
        </addEquipment>
        <UseEquipment v-show="equipmentShow" :watchaddData="watchaddData" :equipment="equipment" ></UseEquipment>

    </div>
  </template>

<script>
import UseEquipment from "./useEquipment-e"
import addEquipment from '@/components/equipment/addEquipment'
export default {
    name: "equipment",
    components:{
        addEquipment,
        UseEquipment,
    },
    data() {
        return{
            status:"",
            watchaddData:[],
            equipmentShow:false,
            equipmentValue:'',
            add:false,
            showTable: true,
            showFrom: false,
            equipment:{},
            title: 'this findAl;l User',
            equipmentList: [],
            equipmentTypeList:[{
                "id":"5",
                "groupName":"笔记本",
                "gid":"2"
            },{
                "id":"6",
                "groupName":"服务器",
                "gid":"2"
            },{
                "id":"8",
                "groupName":"路由器",
                "gid":"2"
            }],
            statusList:[
                {id:0,name:"已启用"},
                {id:1,name:"故障"},
                {id:2,name:"未启用"}
            ],
        }
    },
    methods:{
        back(){
            this.showTable = true;
            this.showFrom = false;
        },
        handleCilck(data){
            this.showTable = false;
            this.showFrom = true;
            this.showUser = this.user;
            this.equipment = data;
            this.equipmentValue = data.type.id
            this.status = data.status

        },
        submit(){
            this.showTable = true;
            this.showFrom = false;
            this.$ajax.put("/api/equipment/",this.equipment).then(res =>{
                console.log(data)
            })
        },
        addEquipment(){
            this.showTable = false;
            this.showFrom = false;
            this.add = true;
        },
        handleEquipment(data){
            this.equipment = data;
            this.showTable = false;
            this.showFrom = false;
            this.equipmentShow=true;
        },


    },
    //监听数据是否发生变化
    watch:{
        equipmentList(){
            this.showTable = true;
            this.add = false;
        },
        watchaddData(){
            this.showTable = true;
            this.add = false;
            this.equipmentShow = false; 
        }
    },
    created(){
        var _this = this;
        //获取用户
        this.$ajax.get("api/equipment/").then(res =>{
            _this.equipmentList =  res.data.data;
            console.log(_this.equipmentList)
        });

    },
    mounted(){
        for(var i=0;i<5;i++){
            this.equipmentList.push(this.equipment)
        }
    },
    
}

</script>

<style scoped>
 #from-user{
    width: 20%;
    margin:0 auto
 }
</style>