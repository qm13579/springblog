<template>
    <el-main>
        <el-row>
            <el-button type="seccess" style="float:right" @click="addGroup">添加设备类型</el-button>
        </el-row>
        <br>
        <br>
        <br>
        <el-row type="flex" class="row-bg" justify="center">

        <el-table v-show="groupShow" :data="groupList" height="500" style="width: 100%">
        <el-table-column prop="id" label="id" width="180"></el-table-column>
        <el-table-column prop="groupName" label="设备类型" width="180"></el-table-column>

        <el-table-column  label="操作">
            <template slot-scope="scope">
                <el-button @click="handleCilck(scope.row)" type="text" size="small">查看</el-button>
                <el-button type="text" size="small">编辑</el-button>
            </template>
        </el-table-column>   
        </el-table>
            <addGroup v-show="addShow" :watchData="watchData" ></addGroup>
            <updateGroup v-show="updateShow" :watchData="watchData" :group="group"></updateGroup>
        </el-row>
    </el-main>
</template>

<script>
import addGroup from './addGroup'
import updateGroup from './updateGroup'

export default {
    name:"equipmentGroup",
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
        this.$ajax.get("api/equipment/group").then(res => {
            if(res.data.code == 10000){
                _this.groupList = res.data.data
            }
        });
    },
    methods:{
        handleCilck(data){
            this.group = data
            this.addShow = false
            this.updateShow = true
            this.groupShow = false
        },
        //添加设备类型
        addGroup(){
            this.addShow = true
            this.updateShow = false
            this.groupShow = false
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