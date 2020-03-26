<template>
  <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
    <el-tab-pane label="设备合同" name="first"><EquipmentContract></EquipmentContract></el-tab-pane>
    <el-tab-pane label="维保合同" name="second"><MaintenanceContract></MaintenanceContract></el-tab-pane>
  </el-tabs>
</template>

<script>
import EquipmentContract from './equipment/EquipmentContract'
import MaintenanceContract from './maintenance/MaintenanceContract'

export default {
    name:"contract",
    components: {
        EquipmentContract,
        MaintenanceContract,

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