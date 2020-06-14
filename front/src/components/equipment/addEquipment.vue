<template>
    <el-row type="flex" class="row-bg" justify="center">
            
        <el-form  :model="equipment" label-width="200px" class="demo-ruleForm">

            <el-form-item label="品牌"   prop="brand">
                <el-input  v-model="equipment.brand" autocomplete="off"></el-input>
            </el-form-item>

            <el-form-item label="设备类型">
                <el-select v-model="equipmentValue" placeholder="请选择设备类型">
                    <el-option v-for="item in equipmentTypeList" :key="item.id" :label="item.groupName" :value="item.id"></el-option>
                </el-select>
            </el-form-item>

            <el-form-item label="型号">
                <el-input v-model="equipment.part"></el-input>
            </el-form-item> 

            <el-form-item label="登记日期" prop="registerDate">
                <el-date-picker v-model="equipment.registerDate5" type="date" placeholder="选择日期"></el-date-picker>
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
                <el-button @click="back">返回</el-button>
            </el-form-item>
        </el-form>
    
        <el-col :span="6"><div class="grid-content"></div></el-col>
    </el-row>
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
        },
        watchaddData:{
            type:Array
        }
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
                if (res.data.code == 10000) {
                    this.$router.go(0);
                    this.open2();
                }else{
                    this.open4()
                }
            })
            //清空数据
            this.equipment = this.$options.data().equipment
            //增加id、状态生成
            this.watchaddData.push(true)
        },
        open4() {
            this.$message.error('错了哦，添加失败');
        },
        open2() {
            this.$message({
            message: '设备添加成功',
            type: 'success'
            });
        },
        back(){
            this.watchaddData.push(true)
    }

    },
}


</script>