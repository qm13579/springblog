  <template>
  <!-- @click.native -->
  <!-- :comman -->
    <div>
        <el-dropdown  split-button type="primary" style="float:left" @click="findAll">
        部门
            <el-dropdown-menu slot="dropdown">
                <el-dropdown-item v-for=" item in group" :key="item.id"  @click.native="selectGropu(item)">{{item.groupName}}</el-dropdown-item>
            </el-dropdown-menu>
        </el-dropdown>

        <el-button type="seccess" style="float:right" @click="buttunAdd">添加用户</el-button>
        <br>
        <br>
        <br>
        <el-table v-show="showTable" :data="tableData" height="500" style="width: 100%">
        <el-table-column prop="username" label="用户名" width="180"></el-table-column>
        <el-table-column prop="createDate" label="创建时间"></el-table-column>
        <el-table-column prop="group.groupName" label="部门"></el-table-column>
        <el-table-column prop="statusString" label="用户状态"></el-table-column>
        <el-table-column  label="操作">
            <template slot-scope="scope">
                <el-button @click="handleCilck(scope.row)" type="text" size="small">查看</el-button>
                <el-button  @click="handleEquipment(scope.row)" type="text" size="small">分配设备</el-button>
            </template>
        </el-table-column>   
        </el-table>
        <div id="from-user" >
            <el-form v-show="showFrom" :model="user"  label-width="100px" class="demo-ruleForm">
                <el-form-item label="用户名" prop="username">
                    <el-input  v-model="user.username" autocomplete="off"></el-input>
                </el-form-item>

                <el-form-item label="部门">
                    <el-select v-model="groupValue" placeholder="请选择活动区域">
                        <el-option v-for="item in group" :key="item.id" :label="item.groupName" :value="item.id"></el-option>
                    </el-select>
                </el-form-item>

                <el-form-item label="用户状态" prop="status">
                    <!-- <el-input v-model="user.status"></el-input> -->

                    <el-select v-model="statusValues" placeholder="请选择活动区域">
                        <el-option v-for="item in statusList" :key="item.id" :label="item.name" :value="item.id"></el-option>
                    </el-select>
                </el-form-item>

                <el-form-item label="角色">
                    <el-checkbox-group v-model="rolesValue" >
                        <el-checkbox v-for="role in roles"  :label="role.id" :key="role.id">{{role.roleName}}</el-checkbox>
                    </el-checkbox-group>
                </el-form-item>

                <el-form-item>
                    <el-button type="primary"  @click="submit">提交</el-button>
                    <el-button @click="back">返回</el-button>
                </el-form-item>
            </el-form>
        </div>
        <!--父组件通过数据绑定传递值，子组件通过prop获取-->
        <addUser v-show="add" :tableData="tableData" :group="group" :watchaddUser="watchaddUser"></addUser>
        <UseEquipment v-show="equipmentShow" :watchaddUser="watchaddUser" :user="user" ></UseEquipment>
    </div>
  </template>

<script>
import addUser from '@/components/user/addUser'
import UseEquipment from "./UseEquipment"
export default {
    name: "findAllUser",
    components:{
        addUser,
        UseEquipment,
    },
    data() {
        return{
            statusValues:"",
            equipmentShow:false,
            watchaddUser:[],
            selectGroup:'',
            groupValue:'',
            rolesValue:[],
            add:false,
            showTable: true,
            showFrom: false,
            group:[],
            user:{
                group:{}
                },
            title: 'this findAl;l User',
            tableData: [],
            roles:[],
            statusList:[
                {id:0,name:"启用"},
                {id:2,name:"停用"},
            ]
        }
    },
    methods:{
        handleCilck(data){
            this.showTable = false;
            this.showFrom = true;
            this.user = data;
            this.groupValue = this.user.group.id
            // this.rolesValue = this.user.roles
            data.roles.forEach(role => {
                this.rolesValue.push(role.id)
            });
            this.statusValues = data.status;
        },
        submit(){
            this.showTable = true;
            this.showFrom = false;
            for(var i=0;i<this.tableData.length;i++){
                 var currentUser = this.tableData[i]
                 if(currentUser.id == this.user.id){
                     currentUser.group.id = this.groupValue
                     this.tableData[i] = this.user
                     this.tableData[i] = currentUser
                     currentUser.roles=[]
                     this.rolesValue.forEach(element => {
                         currentUser.roles.push({"id":element})
                     });
                     this.$ajax.put("api/user/",currentUser).then(res => {
                         console.log(res)
                     })
                     break
                 }
            }

        },
        buttunAdd(){
            this.showTable = false;
            this.showFrom = false;
            this.add = true;
        },
        selectGropu(group){
            var _this = this;
            this.$ajax.get("api/user/group/"+group.id).then(res =>{
                _this.tableData =  res.data.data;
            })
        },
        findAll(){
            var _this = this;
            this.$ajax.get("/api/user/").then(res =>{
            _this.tableData =  res.data.data;
            });
            this.showFrom = false;
        },
        back(){
            this.showTable = true;
            this.showFrom = false;
        },
        handleEquipment(data){
            this.user = data;
            this.showTable = false;
            this.showFrom = false;
            this.equipmentShow=true;
        },
    },
    created(){
        var _this = this;
        //获取用户
        this.$ajax.get("/api/user/").then(res =>{
            _this.tableData =  res.data.data;
            console.log(_this.tableData)
        });
        //获取用户分组
        this.$ajax.get("/api/user/group/").then(res =>{
            _this.group =  res.data.data
        });
        //获取角色分组
        // this.$ajax.get("api/user/role/").then(res =>{
        //     _this.group =  res.data.data
        // });
        this.$ajax.get("/api/role/").then(res =>{
            _this.roles =  res.data.data
        });
    },
    //监听数据是否发生变化
    watch:{
        tableData(){
            this.showTable = true;
            this.add = false;
        },
        watchaddUser(){
            this.showTable = true;
            this.add = false;
            this.equipmentShow=false;
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