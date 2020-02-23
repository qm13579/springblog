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
        
        <br>
        <br>
        <br>
        <el-table v-show="showTable" :data="userEquipmentList" height="500" style="width: 100%">
        <el-table-column prop="id" label="ID" width="180"></el-table-column>
        <el-table-column prop="user" label="使用人" width="180"></el-table-column>
        <el-table-column prop="equipment" label="机器类型"></el-table-column>
        <el-table-column prop="date" label="使用日期"></el-table-column>
        <el-table-column prop="status" label="状态"></el-table-column>
        <el-table-column  label="操作">
            <template slot-scope="scope">
                <el-button @click="handleCilck(scope.row)" type="text" size="small">查看</el-button>
                <el-button type="text" size="small">编辑</el-button>
            </template>
        </el-table-column>   
        </el-table>
        <div id="from-user" >
            <el-form v-show="showFrom" :model="userEquipment"  label-width="100px" class="demo-ruleForm">
                <el-form-item label="ID" prop="id">
                    <el-input  v-model="userEquipment.id" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="用户" prop="user">
                    <el-input  v-model="userEquipment.user" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="设备" prop="equipment">
                    <el-input v-model="userEquipment.equipment"></el-input>
                </el-form-item>
                <el-form-item label="使用日期" prop="date">
                    <el-input v-model="userEquipment.date"></el-input>
                </el-form-item>                
                <el-form-item label="状态" prop="status">
                    <el-input v-model="userEquipment.status"></el-input>
                </el-form-item>
                 
                <el-form-item>
                    <el-button type="primary"  @click="submit">提交</el-button>
                    <el-button >重置</el-button>
                </el-form-item>
            </el-form>
        </div>
        <!--父组件通过数据绑定传递值，子组件通过prop获取-->
        <addUserEquipment v-show="add" :userEquipmentList="userEquipmentList"></addUserEquipment>
    </div>
  </template>

<script>
import addUserEquipment from '@/components/equipment/addUserEquipment'
export default {
    name: "userEquipment",
    components:{
        addUserEquipment
    },
    data() {
        return{
            add:false,
            showTable: true,
            showFrom: false,
            userEquipment:{
                    id: '1',
                    user: '冯志',
                    equipment: '服务器',
                    date:'2020-02-02',
                    status:'1',
                },
            title: 'this findAl;l User',
            userEquipmentList: []
        }
    },
    methods:{
        handleCilck(data){
            this.showTable = false;
            this.showFrom = true;
            this.showUser = this.user;
            this.userEquipment = data;
        },
        submit(){
            this.showTable = true;
            this.showFrom = false;
        },
        addUserEquipment(){
            this.showTable = false;
            this.showFrom = false;
            this.add = true;
        },

    },
    //监听数据是否发生变化
    watch:{
        userEquipmentList(){
            this.showTable = true;
            this.add = false;
        }
    },
    mounted(){
        for(var i=0;i<5;i++){
            this.userEquipmentList.push(this.userEquipment)
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