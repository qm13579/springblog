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
        <el-button type="seccess" style="float:right" @click="buttunAdd">添加用户</el-button>
        <br>
        <br>
        <br>
        <el-table v-show="showTable" :data="tableData" height="500" style="width: 100%">
        <el-table-column prop="date" label="日期" width="180"></el-table-column>
        <el-table-column prop="name" label="姓名" width="180"></el-table-column>
        <el-table-column prop="address" label="地址"></el-table-column>
        <el-table-column  label="操作">
            <template slot-scope="scope">
                <el-button @click="handleCilck(scope.row)" type="text" size="small">查看</el-button>
                <el-button type="text" size="small">编辑</el-button>
            </template>
        </el-table-column>   
        </el-table>
        <div id="from-user" >
            <el-form v-show="showFrom" :model="user"  label-width="100px" class="demo-ruleForm">
                <el-form-item label="日期" prop="date">
                    <el-input  v-model="user.date" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="姓名" prop="name">
                    <el-input  v-model="user.name" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="地址" prop="address">
                    <el-input v-model="user.address"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary"  @click="submit">提交</el-button>
                    <el-button >重置</el-button>
                </el-form-item>
            </el-form>
        </div>
        <!--父组件通过数据绑定传递值，子组件通过prop获取-->
        <addUser v-show="add" :tableData="tableData" :showTable="showTable"></addUser>
    </div>
  </template>

<script>
import addUser from '@/components/user/addUser'
export default {
    name: "findAllUser",
    components:{
        addUser
    },
    data() {
        return{
            add:false,
            showTable: true,
            showFrom: false,
            user:{
                    date: '',
                    username: '',
                    address: '',
                    id:'',
                    status:''
                },
            showUser:[],
            title: 'this findAl;l User',
            tableData: [{
            date: '2016-05-02',
            name: '王da虎',
            address: '上海市普陀区金沙江路 1518 弄'
          }, {
            date: '2016-05-04',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1517 弄'
          }, {
            date: '2016-05-01',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1519 弄'
          }, {
            date: '2016-05-03',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1516 弄'
          }]
        }
    },
    methods:{
        handleCilck(data){
            this.showTable = false;
            this.showFrom = true;
            this.showUser = this.user;
            this.user = data;
        },
        submit(){
            this.user = this.showUser;
            this.showTable = true;
            this.showFrom = false;

        },
        buttunAdd(){
            this.showTable = false;
            this.add = true;
        }
    },
    //监听数据是否发生变化
    watch:{
        tableData(){
            this.showTable = true;
            this.add = false;
        }
    }
}

</script>

<style scoped>
 #from-user{
    width: 20%;
    margin:0 auto
 }
</style>