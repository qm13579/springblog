<template>
    <div id="from-user" >
        <el-form  :model="equipment"  label-width="100px" class="demo-ruleForm">
            <el-form-item label="ID" prop="id">
                <el-input  v-model="equipment.id" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="品牌" prop="brand">
                <el-input  v-model="equipment.brand" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="设备类型" prop="type">
                <el-input v-model="equipment.type"></el-input>
            </el-form-item>
            <el-form-item label="登记日期" prop="registerDate">
                <el-input v-model="equipment.registerDate"></el-input>
            </el-form-item>                
            <el-form-item label="生产商" prop="producers">
                <el-input v-model="equipment.producers"></el-input>
            </el-form-item>
            <el-form-item label="运维电话" prop="mobile">
                <el-input v-model="equipment.mobile"></el-input>
            </el-form-item>
            <el-form-item label="销售商" prop="seller">
                <el-input v-model="equipment.seller"></el-input>
            </el-form-item>                
            <el-form-item label="设备状态" prop="status">
                <el-input v-model="equipment.status"></el-input>
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
            equipment:{
                id: '',
                brand: '',
                type: '',
                part:'',
                registerDate:'',
                producers:'',
                seller:'',
                mobile:'',
                status:''
            },
        }
    },
    props:{
        equipmentList:{
            type:Array
        },
    },
    created(){
        console.log("初始化")
    },
    methods:{
        addSubmit:function(){
            
            this.equipmentList.push(this.equipment)
            //清空数据
            this.equipment = this.$options.data().equipment
        }
    },
}


</script>