  <template>
    <div>
        <el-dropdown  split-button type="primary" style="float:left">
        部门
            <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>会计</el-dropdown-item>
                <el-dropdown-item>科技</el-dropdown-item>
                <el-dropdown-item>人事</el-dropdown-item>
                <el-dropdown-item>后勤</el-dropdown-item>
                <el-dropdown-item>办公室</el-dropdown-item>
            </el-dropdown-menu>
        </el-dropdown>
        <el-button type="seccess" style="float:right" @click="addEquipment">添加用户</el-button>
        <br>
        <br>
        <br>
        <el-table v-show="showTable" :data="equipmentList" height="500" style="width: 100%">
        <el-table-column prop="id" label="ID" width="180"></el-table-column>
        <el-table-column prop="brand" label="品牌" width="180"></el-table-column>
        <el-table-column prop="type" label="类型"></el-table-column>
        <el-table-column prop="part" label="型号"></el-table-column>
        <el-table-column prop="registerDate" label="登记日期"></el-table-column>
        <el-table-column prop="producers" label="生产商"></el-table-column>
        <el-table-column prop="seller" label="销售商"></el-table-column>
        <el-table-column prop="mobile" label="运维电话"></el-table-column>
        <el-table-column prop="status" label="设备状态"></el-table-column>

        <el-table-column  label="操作">
            <template slot-scope="scope">
                <el-button @click="handleCilck(scope.row)" type="text" size="small">查看</el-button>
                <el-button type="text" size="small">编辑</el-button>
            </template>
        </el-table-column>   
        </el-table>
        <div id="from-user" >
            <el-form v-show="showFrom" :model="equipment"  label-width="100px" class="demo-ruleForm">
                <el-form-item label="ID" prop="id">
                    <el-input  v-model="equipment.id" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="品牌" prop="brand">
                    <el-input  v-model="equipment.brand" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="设备类型" prop="type">
                    <el-input v-model="equipment.type"></el-input>
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
                <el-form-item label="设备状态" prop="status">
                    <el-input v-model="equipment.status"></el-input>
                </el-form-item>                
                <el-form-item>
                    <el-button type="primary"  @click="submit">提交</el-button>
                    <el-button >重置</el-button>
                </el-form-item>
            </el-form>
        </div>
        <!--父组件通过数据绑定传递值，子组件通过prop获取-->
        <addEquipment v-show="add" :equipmentList="equipmentList"></addEquipment>
    </div>
  </template>

<script>
import addEquipment from '@/components/equipment/addEquipment'
export default {
    name: "equipment",
    components:{
        addEquipment
    },
    data() {
        return{
            add:false,
            showTable: true,
            showFrom: false,
            equipment:{
                    id: '1',
                    brand: '华为',
                    type: '服务器',
                    part:'华为中国',
                    registerDate:'2019-12-12',
                    producers:'华为设备制造',
                    seller:'华为成都销售中心',
                    mobile:'028-6685125',
                    status:'1'
                },
            title: 'this findAl;l User',
            equipmentList: []
        }
    },
    methods:{
        handleCilck(data){
            this.showTable = false;
            this.showFrom = true;
            this.showUser = this.user;
            this.equipment = data;
        },
        submit(){
            this.showTable = true;
            this.showFrom = false;
        },
        addEquipment(){
            this.showTable = false;
            this.showFrom = false;
            this.add = true;
        },

    },
    //监听数据是否发生变化
    watch:{
        equipmentList(){
            this.showTable = true;
            this.add = false;
        }
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