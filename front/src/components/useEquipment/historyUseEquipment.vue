  <template>
    <el-row type="flex" class="row-bg" justify="center">
        <br>
        <el-table v-show="showTable" :data="userEquipmentList" height="500" style="width: 100%">
        <el-table-column label="序号" type="index" ></el-table-column>
        <el-table-column prop="user.name" label="使用人" ></el-table-column>
        <el-table-column prop="equipment.type.groupName" label="设备类型"></el-table-column>
        <el-table-column prop="equipment.brand" label="设备品牌"></el-table-column>
        <el-table-column prop="equipment.part" label="设备型号"></el-table-column>
        <el-table-column prop="date" label="使用日期"></el-table-column>
        <el-table-column prop="statusString" label="状态"></el-table-column>
        <el-table-column  label="操作">
            <template slot-scope="scope">
                <el-button @click="handleCilck(scope.row)" type="text" size="small">查看</el-button>
                <el-button type="text" size="small">编辑</el-button>
            </template>
        </el-table-column>   
        </el-table>
        <el-dialog title="查看工单" :visible.sync="showFrom">
            <el-row type="flex" class="row-bg" justify="center">
            
            <el-form :model="userEquipment"  label-width="100px" class="demo-ruleForm">
                <el-form-item label="用户" prop="user">
                    <el-input  v-model="userEquipment.user.name" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="设备" prop="equipment">
                    <el-input v-model="userEquipment.equipment.brand"></el-input>
                </el-form-item>
                <el-form-item label="使用日期" prop="date">
                    <el-input v-model="userEquipment.date" autocomplete="off"></el-input>
                </el-form-item>                
                <el-form-item label="状态" prop="status">
                    <el-input v-model="userEquipment.statusString"></el-input>
                </el-form-item>
                 
                <el-form-item>
                    <el-button type="primary"  @click="submit">提交</el-button>
                    <el-button >重置</el-button>
                </el-form-item>
            </el-form>
            
            </el-row>
        </el-dialog>
        <!--父组件通过数据绑定传递值，子组件通过prop获取-->
        <!-- <addUserEquipment v-show="add" :userEquipmentList="userEquipmentList"></addUserEquipment> -->
    </el-row>
  </template>

<script>
export default {
    name: "historyAllUserEquipment",
    data() {
        return{
            add:false,
            showTable: true,
            showFrom: false,
            userEquipment:{
                user:{},
                equipment:{}
            },
            title: 'this findAl;l User',
            userEquipmentList: [],
            functionList:[
                {id:"1",name:"历史工单"},
            ],
        }
    },
    methods:{
        handleCilck(data){
            // this.showTable = false;
            this.showFrom = true;
            this.showUser = this.user;
            this.userEquipment = data;
        },
        //功能键跳转
        func(){
            var _this = this
            this.$ajax.get("api/useEquipment/history").then(res => {
                if(res.data.code == 10000){
                    _this.userEquipmentList = res.data.data
                }
            });
        }

    },
    //监听数据是否发生变化
    watch:{
        userEquipmentList(){
            this.showTable = true;
            this.add = false;
        }
    },
    created(){
        this.func()
    }
    
}

</script>

<style scoped>

</style>