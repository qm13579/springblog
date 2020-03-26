<template>
    <main>
    <el-table v-loading="loading" :data="logInfoList" style="width: 100%" :row-class-name="tableRowClassName">
        <el-table-column prop="url" label="访问地址" width="180"></el-table-column>
        <el-table-column prop="method" label="请求方式" width="180"> </el-table-column>
        <el-table-column prop="user" label="访问用户" width="180"> </el-table-column>
        <el-table-column prop="date" label="日期" width="180"> </el-table-column>
        <el-table-column prop="time" label="请求用时" width="180"> </el-table-column>
    </el-table>
      <div class="block">
            <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="pageInfo.pageNum"
            :page-sizes="[10, 20, 30, 40]"
            :page-size="pageInfo.pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total=pageInfo.total>
            </el-pagination>
        </div>
    </main>
</template>

<script>
export default {
    name:"log",
    data(){
        return{
            logInfoList:[],
            pageInfo:{
                pageNum:1,
                pageSize:10,
                total:null,
            },
            loading: true
        }
    },
    methods:{
        getInfo(){
            var _this = this;
            this.$ajax.post("/api/info/log",this.pageInfo).then(res => {
                _this.logInfoList = res.data.data.list
                _this.pageInfo.total = res.data.data.total
                 _this.pageInfo.pageNum = res.data.data.pageNum
                 _this.loading = false
            })
        },
        tableRowClassName({row, rowIndex}) {
            if (rowIndex % 2 != 0) {
            return 'warning-row';
            } else if (rowIndex % 2 > 0) {
            return 'success-row';
            }
            return '';
        },
        handleSizeChange(val) {
            console.log(`每页 ${val} 条`);
            this.pageInfo.pageSize = val;
        },
        handleCurrentChange(val) {
            console.log(`当前页: ${val}`);
            this.pageInfo.pageNum = val;
            this.getInfo();
        },

    },
    created(){
        this.getInfo();
    }
}
</script>
<style>
  .el-table .warning-row {
    background: oldlace;
  }

  .el-table .success-row {
    background: #f0f9eb;
  }
</style>