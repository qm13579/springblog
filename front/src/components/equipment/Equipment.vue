  <template>
    <el-main >
        <el-row >
            <el-dropdown  size="small" split-button type="primary" >
                筛选
                <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item v-for=" item in functionList" :key="item.id" @click.native="findEquipmentByGruop(item)">{{item.groupName}}</el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
            <el-upload
                class="upload-demo butten"
                action="api/equipment/file"
                :on-preview="handlePreview"
                :on-remove="handleRemove"
                :before-remove="beforeRemove"
                multiple
                :limit="1"
                :on-exceed="handleExceed"
                :file-list="fileList">
                <el-button size="small"  class="el-icon-upload2 butten"  type="primary">导入数据</el-button>
                </el-upload>
            <el-button type="primary" plain size="small" class="el-icon-download" @click="importFile">导出数据</el-button>


            <el-button type="seccess" size="small" icon="el-icon-edit"  @click="addEquipment">新增设备</el-button>

        </el-row>

        <br>
        <br>
        <el-row type="flex" class="row-bg" justify="center">
                <el-table v-loading="loading" v-show="showTable" :data="equipmentList" height="500" style="width: 100%">
                
                <el-table-column label="序号" type="index"></el-table-column>
                <el-table-column prop="brand" label="品牌"></el-table-column>
                <el-table-column prop="type.groupName" label="类型"  ></el-table-column>
                <el-table-column prop="part" label="型号"  ></el-table-column>
                <el-table-column prop="registerDate" label="登记日期"></el-table-column>
                <el-table-column prop="producers" label="生产商"></el-table-column>
                <el-table-column prop="seller" label="销售商"></el-table-column>
                <el-table-column prop="mobile" label="运维电话"></el-table-column>
                <el-table-column prop="statusString" label="设备状态"></el-table-column>
                <el-table-column prop="metricsString" label="分配状态"></el-table-column>

                <el-table-column  label="操作" width="120">
                    <template slot-scope="scope">
                        <el-button @click="handleCilck(scope.row)" type="text" size="small">    编辑</el-button>
                        <el-button @click="handleEquipment(scope.row)" type="text" size="small">分配用户</el-button>
                    </template>
                </el-table-column>   
                <el-table-column  label="查看轨迹" >
                    <template slot-scope="scope">
                        <el-button @click="timeLine(scope.row)" type="text" size="small">查看轨迹</el-button>
                    </template>
                </el-table-column> 
                </el-table>

            <el-dialog title="编辑" :visible.sync="showFrom">
                <el-row type="flex" class="row-bg" >
                    <el-button type="info" size="small"  @click="toMmaintenance" round plain>维保信息</el-button>
                    <el-button type="success" size="small"  @click="addToMmaintenance" round plain>新增维保</el-button>          
                </el-row>
                <br>
                <el-row type="flex" class="row-bg" justify="center">
                <el-form  :model="equipment"  label-width="100px" class="demo-ruleForm">

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
                        <el-button type="primary"   size="samll" @click="submit">提交</el-button>
                        <el-button @click="back"  size="samll" >返回</el-button>
                    </el-form-item>
                </el-form>
                </el-row>
            </el-dialog>

            <el-dialog title="新增设备" :visible.sync="add">
                <!--添加设备-->
                <addEquipment  :equipmentList="equipmentList" :equipmentTypeList="equipmentTypeList" :watchaddData="watchaddData"></addEquipment>
            </el-dialog>
                <!-- 分配设备使用 -->
            <el-dialog title="分配设备使用" :visible.sync="equipmentShow">
                <UseEquipment  :watchaddData="watchaddData" :equipment="equipment" ></UseEquipment>
            </el-dialog>
            <el-dialog title="维保记录" :visible.sync="maintenanceShow">
                <!-- 维保记录 -->
                <maintenance  :watchaddData="watchaddData" :maintenance="maintenance"></maintenance>
            </el-dialog>
            <el-dialog title="新增维保和报修" :visible.sync="addMaintenanceShow">
                <!-- 新增维保和报修 -->
                <addMaintenance v-show="addMaintenanceShow" :equipment="equipment" :watchaddData="watchaddData" :functionList="functionList"></addMaintenance>
            </el-dialog>
            <el-drawer title="我是标题" width="100px" :visible.sync="drawer" :with-header="false">
                <el-header>
                    <div class="position"><span class="title">设备使用轨迹</span></div>
                </el-header>
                <br>
                <div class="block">
                <el-timeline>
                    <el-timeline-item
                    v-for="(TimeLine, index) in equipmentTimeLine"
                    :key="index"
                    :icon="TimeLine.icon='el-icon-more' "
                    :type="TimeLine.type='primary' "
                    :color="TimeLine.color='#0bbd87' "
                    :size="TimeLine.size='large'"
                    :timestamp="TimeLine.date">
                    <span v-if="TimeLine.name == 'user'">管理员</span>
                    <span v-if="TimeLine.operation == 'add'">新增了设备</span>
                    <span v-if="TimeLine.operation == 'update'">{{TimeLine.name}}使用了设备</span>
                    <span v-if="TimeLine.operation == '2'">调整了设备状态为故障</span>
                    <span v-if="TimeLine.operation == '1'">调整了设备状态为启用</span>
                    <span v-if="TimeLine.operation == '0'">调整了设备状态为未启用</span>
                    </el-timeline-item>
                </el-timeline>
                </div>
            </el-drawer>
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
            loading: true,
            equipmentTimeLine:[],
            drawer:false,
            fileList:[],
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
            // this.showTable = false;
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
            this.equipment.status = this.status
            this.$ajax.put("/api/equipment/",this.equipment).then(res =>{
                if(res.data.code == 10000){
                    this.open2()
                }else{
                    this.open4()
                }
            })
        },
        //添加设备
        addEquipment(){
            // this.showTable = false;
            // this.showFrom = false;
            this.add = true;
            // this.equipmentShow=false;
            // this.addMaintenanceShow = false;
            // this.maintenanceShow = false
            this.findEquipmentGruop()
        },
        handleEquipment(data){
            this.equipment = data;
            // this.showTable = false;
            // this.showFrom = false;
            this.equipmentShow=true;
            // this.maintenanceShow = false
            // this.addMaintenanceShow = false;
            this.findEquipmentGruop()
        },
        //当在维保历史组件时更新功能键，显示维保组件
        toMmaintenance(){
            // this.showTable = false;
            // this.showFrom = false;
            // this.equipmentShow=false;
            this.maintenanceShow = true;
            // this.addMaintenanceShow = false;

            console.log("已进入维保组件")
            var _this = this;
            this.$ajax.get("api/maintenance/"+this.equipment.id).then(res =>{
                if (res.data.code == 10000) {
                    _this.maintenance = res.data.data
                }
            })
        },
        //当新增维保及报修中更新功能键
        addToMmaintenance(){
                // this.showTable = false;
                // this.showFrom = false;
                // this.equipmentShow=false;
                // this.maintenanceShow = false;
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
                // this.showTable = false;
                this.showFrom = true;
                // this.maintenanceShow = false;
                // this.addMaintenanceShow=false;
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
        },
        handleRemove(file, fileList) {
            console.log(file, fileList);
        },
        handlePreview(file) {
            console.log(file);
        },
        handleExceed(files, fileList) {
            this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
        },
        beforeRemove(file, fileList) {
            return this.$confirm(`确定移除 ${ file.name }？`);
        },
        importFile(){
            window.location.href="/api/equipment/file"
        },
        timeLine(data){
            this.drawer = true;
            this.$ajax.get("api/info/equipment/"+data.id).then(res => {
                this.equipmentTimeLine = res.data.data
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
    //监听数据是否发生变化
    watch:{
        equipmentList(){
            console.log("设备数据发生变化")
            this.showTable = true;
            // this.add = false;
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
            _this.loading = false
        });
        //获取设备分类
        this.findEquipmentGruop()
    },
    
}

</script>

<style scoped>
    .butten{
        float:right;
        margin: 1px;
    }
    .title {
        font-size: 30px;
        font-family: 华文行楷;
        color: #f3f1f1fa;
    }
    .position{
        margin: 0 auto;
        text-align: center;
    }
    .el-header, .el-footer {
        background-color: #409EFF;
        color: #333;
        text-align: center;
        line-height: 60px;
    }
</style>