<template>
    <el-main>
        <find></find>
        <el-dialog title="编辑用户" :visible.sync="showTable">
        </el-dialog>

    </el-main>
</template>

<script>
import find from './find'
export default {
    name: "mantanceContract",
    data () {
        return{
            showTable : false,
            equipmentContract:[],
        }
    },
    components:{
        find
    }

}
</script>