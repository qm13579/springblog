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
        <el-table-column prop="id" label="ID" width="180"></el-table-column>
        <el-table-column prop="username" label="用户名" width="180"></el-table-column>
        <el-table-column prop="createDate" label="创建时间"></el-table-column>
        <el-table-column prop="group.groupName" label="部门"></el-table-column>
        <el-table-column prop="status" label="用户状态"></el-table-column>
        <el-table-column  label="操作">
            <template slot-scope="scope">
                <el-button @click="handleCilck(scope.row)" type="text" size="small">查看</el-button>
                <el-button type="text" size="small">编辑</el-button>
            </template>
        </el-table-column>   
        </el-table>
        <div id="from-user" >
            <el-form v-show="showFrom" :model="user"  label-width="100px" class="demo-ruleForm">
                <el-form-item label="ID" prop="id">
                    <el-input  v-model="user.id" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="用户名" prop="username">
                    <el-input  v-model="user.username" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="创建时间" prop="createDate">
                    <el-input v-model="user.createDate"></el-input>
                </el-form-item>

                <el-form-item label="部门">
                    <el-select v-model="groupValue" placeholder="请选择活动区域">
                        <el-option v-for="item in group" :key="item.id" :label="item.groupName" :value="item.id"></el-option>
                    </el-select>
                </el-form-item>

                <el-form-item label="用户状态" prop="status">
                    <el-input v-model="user.status"></el-input>
                </el-form-item>

                <el-form-item label="角色">
                    <el-checkbox-group v-model="rolesValue" >
                        <el-checkbox v-for="role in roles"  :label="role.id" :key="role.id">{{role.roleName}}</el-checkbox>
                    </el-checkbox-group>
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
            groupValue:'',
            rolesValue:[],
            add:false,
            showTable: true,
            showFrom: false,
            group:[],
            user:{},
            title: 'this findAl;l User',
            tableData: [],
            roles:[{
                id:'1',
                roleName:'ADMIN'
            },{
                id:'2',
                roleName:'USER'
            },{
                id:'3',
                roleName:'TEST'
            },]
        }
    },
    methods:{
        handleCilck(data){
            this.showTable = false;
            this.showFrom = true;
            this.user = data;
            this.groupValue = this.user.group.id
            this.rolesValue = this.user.roles
        },
        submit(){
            this.showTable = true;
            this.showFrom = false;

            for(var i=0;i<this.tableData.length;i++){
                 var currentUser = this.tableData[i]
                 if(currentUser.id == this.user.id){
                     console.log(currentUser)
                     currentUser.group.id = this.groupValue
                     this.tableData[i] = currentUser
                     break
                 }
            }

        },
        buttunAdd(){
            this.showTable = false;
            this.showFrom = false;
            this.add = true;
        },
    },
    created(){
        var _this = this;
        //获取用户
        this.$ajax.get("api/user/").then(res =>{
            _this.tableData =  res.data.data
        });
        //获取用户分组
        this.$ajax.get("api/user/group/").then(res =>{
            _this.group =  res.data.data
        });
        //获取角色分组
        // this.$ajax.get("api/user/group/").then(res =>{
        //     _this.group =  res.data.data
        // });
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