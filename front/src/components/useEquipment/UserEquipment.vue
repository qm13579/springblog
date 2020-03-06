<template>
  <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
    <el-tab-pane label="工单查询" name="first"><findAllUserEquipment></findAllUserEquipment></el-tab-pane>
    <el-tab-pane label="历史工单" name="second"><historyUserEquipment></historyUserEquipment></el-tab-pane>
  </el-tabs>
</template>

<script>
import addUserEquipment from './addUserEquipment'
import findAllUserEquipment from "./findAllUseEquipment"
import historyUserEquipment from "./historyUseEquipment"

export default {
    name:"UseEquipemnt",
    components: {
        findAllUserEquipment,
        historyUserEquipment,
    },
    data () {
        return{
            title : "this is user--",
            activeName:"first"
        }
    },
    methods:{
        handleClick(tab,event){
            console.log(tab,event)
            console.log(this.activeName)
        }
    }
    
}
</script>