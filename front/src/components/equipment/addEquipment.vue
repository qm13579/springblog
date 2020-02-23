<template>
    <div id="from-user" >
        <el-form  :model="equipment"  label-width="100px" class="demo-ruleForm">

            <el-form-item label="品牌" prop="brand">
                <el-input  v-model="equipment.brand" autocomplete="off"></el-input>
            </el-form-item>

            <el-form-item label="设备类型">
                <el-select v-model="equipmentValue" placeholder="请选择活动区域">
                    <el-option v-for="item in equipmentTypeList" :key="item.id" :label="item.groupName" :value="item.id"></el-option>
                </el-select>
            </el-form-item>

            <el-form-item label="型号">
                <el-input v-model="equipment.part"></el-input>
            </el-form-item> 

            <el-form-item label="登记日期" prop="registerDate">
                <el-date-picker v-model="equipment.registerDate5" type="date" placeholder="选择日期"></el-date-picker>
                <!-- <el-input v-model="equipment.registerDate"></el-input> -->
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
            equipmentValue:'',
            equipment:{
            },
        }
    },
    props:{
        equipmentList:{
            type:Array
        },
        equipmentTypeList:{
            type:Array
        }
    },
    created(){
        console.log("初始化")
    },
    methods:{
        addSubmit(){
            this.equipment.type = {}
            this.equipment.type.id = this.equipmentValue

            this.equipmentList.push(this.equipment)
            console.log(this.equipment)
            //发送请求
            var _this = this
            this.$ajax.post("/api/equipment/",this.equipment).then(res =>{
                console.log(res)
            })
            //清空数据
            this.equipment = this.$options.data().equipment
            //增加id、状态生成
        }
    },
}


</script>