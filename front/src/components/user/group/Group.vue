<template>
    <el-main>
        <el-row>
            <el-button type="info" plain size="small" class="el-icon-plus butten" style="float:right" @click="addGroup">添加部门</el-button>
        </el-row>
        <el-row type="flex" class="row-bg" justify="center">

        <el-table v-show="groupShow" :data="groupList" height="500">
        <el-table-column label="序号" type="index" width="180"></el-table-column>
        <el-table-column prop="groupName" label="设备类型" width="180"></el-table-column>

        <el-table-column  label="操作">
            <template slot-scope="scope">
                <el-button @click="handleCilck(scope.row)" type="text" size="small">编辑</el-button>
            </template>
        </el-table-column>   
        </el-table>
        </el-row>
            <el-dialog title="新增部门" :visible.sync="addShow">
                <addGroup  :watchData="watchData" ></addGroup>            
            </el-dialog>

            <el-dialog title="更新部门" :visible.sync="updateShow">
                <updateGroup  :watchData="watchData" :group="group"></updateGroup>
            </el-dialog>
    </el-main>
</template>

<script>
import addGroup from './addGroup'
import updateGroup from './updateGroup'

export default {
    name:"userGroup",
    components:{
        addGroup,
        updateGroup,
    },
    data(){
        return{
            addShow:false,
            updateShow:false,
            groupShow:true,
            groupList:[],
            watchData:[],
            group:{},
        }
    },
    created(){
        var _this = this;
        this.$ajax.get("api/user/group/").then(res => {
            console.log(res)
            if(res.data.code == 10000){
                _this.groupList = res.data.data
            }
        });
    },
    methods:{
        handleCilck(data){
            this.group = data
            // this.addShow = false
            this.updateShow = true
            // this.groupShow = false
        },
        //添加设备类型
        addGroup(){
            this.addShow = true
            // this.updateShow = false
            // this.groupShow = false
        },
    },
    watch:{
        watchData(){
            this.addShow = false
            this.updateShow = false
            this.groupShow = true
        }
    }

}
</script>