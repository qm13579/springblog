<template>
    <div>
        <el-table v-loading="loading" :data="maintenance" style="width: 100%" :row-class-name="tableRowClassName">
            <el-table-column label="ID" type="index" width="180"></el-table-column>
            <el-table-column prop="name" label="维保人员" width="180"></el-table-column>
            <el-table-column prop="mobile" label="电话"></el-table-column>
            <el-table-column prop="date" label="日期"></el-table-column>
            <el-table-column prop="equipment.brand" label="设备品牌"></el-table-column>
            <el-table-column prop="equipment.type.groupName" label="设备类型"></el-table-column>
            <el-table-column prop="equipment.part" label="设备型号"></el-table-column>
            <el-table-column prop="statusString" label="状态"></el-table-column>
            <el-table-column prop="describe" label="描述"></el-table-column>
        </el-table>
    </div>
  </template>
  <style>
    .el-table .warning-row {
        background: oldlace;
    }
    .el-table .success-row {
        background: #f0f9eb;
    }
</style>
<script>
export default {
    name:"maintenance",
    data(){
        return{
            maintenance:[],
            loading:false,
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
                _this.loading = false
                console.log(res.data.data)
            }else{
                console.log("请求数据失败")
            }
        })
        
    }
    
}
</script>