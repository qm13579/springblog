<template>
    <div id="from-user" >
        <el-form  :model="userEquipment"  label-width="100px" class="demo-ruleForm">
            <el-form-item label="ID" prop="id">
                <el-input  v-model="userEquipment.id" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="用户" prop="user">
                <el-input  v-model="userEquipment.user" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="设备" prop="equipment">
                <el-input v-model="userEquipment.equipment"></el-input>
            </el-form-item>
            <el-form-item label="使用日期" prop="date">
                <el-date-picker v-model="userEquipment.date" type="date" placeholder="选择日期"></el-date-picker>
                <!-- <el-input v-model="userEquipment.date"></el-input> -->
            </el-form-item>                
            <el-form-item label="状态" prop="status">
                <el-input v-model="userEquipment.status"></el-input>
            </el-form-item>
                
            <el-form-item>
                <el-button type="primary"  @click="addSubmit">提交</el-button>
                <el-button >重置</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
export default {
    name: 'addEquipment',
    data() {
        return{
            userEquipment:{
                id: '',
                user: '',
                equipment: '',
                date:'',
                status:'',
            },
        }
    },
    props:{
        userEquipmentList:{
            type:Array
        },
    },
    created(){
        console.log("初始化")
    },
    methods:{
        addSubmit:function(){
            
            this.userEquipmentList.push(this.userEquipment)
            //清空数据
            this.userEquipment = this.$options.data().userEquipment
        }
    },
}


</script>