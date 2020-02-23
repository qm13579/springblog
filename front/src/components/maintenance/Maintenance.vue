<template>
    <div>
        <el-table :data="maintenance" style="width: 100%" :row-class-name="tableRowClassName">
            <el-table-column prop="id" label="ID" width="180"></el-table-column>
            <el-table-column prop="name" label="维保人员" width="180"></el-table-column>
            <el-table-column prop="mobile" label="电话"></el-table-column>
            <el-table-column prop="date" label="日期"></el-table-column>
            <el-table-column prop="equipment" label="设备名称"></el-table-column>
            <el-table-column prop="statusString" label="地址"></el-table-column>
            <el-table-column prop="describe" label="描述"></el-table-column>
        </el-table>
    </div>
  </template>
<script>
export default {
    name:"maintenance",
    data(){
        return{
            maintenance:[],
        }
    },
    methods:{
        tableRowClassName({row, rowIndex}) {
            if (rowIndex === 1) {
            return 'warning-row';
            } else if (rowIndex === 3) {
            return 'success-row';
            }
            return '';
        },
    },
    created(){
        var _this = this
        this.$ajax.get("api/maintenance/").then(res => {
            if(res.data.code == 10000){
                _this.maintenance = res.data.data
            }else{
                console.log("请求数据失败")
            }
        })
        
    }
    
}
</script>