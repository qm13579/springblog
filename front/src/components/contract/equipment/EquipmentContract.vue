<template>
    <el-main>
        <find></find>
        <el-dialog title="编辑用户" :visible.sync="showTable">
            <add></add>
        </el-dialog>

    </el-main>
</template>

<script>
// import add from './add'
import find from './find'
export default {
    name: "eContract",
    data () {
        return{
            showTable : false,
            equipmentContract:[],
        }
    },
    components:{
        find,
    }

}
</script>