<template>
    <el-row type="flex" class="row-bg" justify="center">

        <el-form  ref="form" :model="equipment"  label-width="100px" class="demo-ruleForm">

            <el-form-item label="品牌" prop="brand">
                <el-input  disabled v-model="equipment.brand" autocomplete="off"></el-input>
            </el-form-item>

            <el-form-item label="设备类型" prop="type.id">
                <el-input  disabled v-model="equipment.type.groupName" autocomplete="off"></el-input>
            </el-form-item>
            
            <el-form-item label="登记日期" prop="registerDate">
                <el-input disabled v-model="equipment.registerDate"></el-input>
            </el-form-item>                
            <el-form-item label="生产商" prop="producers">
                <el-input disabled v-model="equipment.producers"></el-input>
            </el-form-item>
            <el-form-item label="运维电话" prop="mobile">
                <el-input disabled v-model="equipment.mobile"></el-input>
            </el-form-item>
            <el-form-item label="销售商" prop="seller">
                <el-input disabled v-model="equipment.seller"></el-input>
            </el-form-item>

            <el-form-item label="设备状态"  prop="statusString">
                <el-input disabled v-model="equipment.statusString"></el-input>
            </el-form-item>   

            <el-form-item label="分配状态">
                <el-input disabled v-model="equipment.metricsString"></el-input>
            </el-form-item> 

            <el-form-item label="分配用户">
                <el-cascader v-model="value" :options="equipemtList" :props="{ expandTrigger: 'hover' }" @change="handleChange"></el-cascader>
            </el-form-item> 

            <el-form-item>
                <!-- <el-button type="primary"  @click="submit(false)">提交</el-button> -->
                <el-button type="primary"  @click="submit(true)">分配并激活</el-button>
                <el-button  @click="back">返回</el-button>
            </el-form-item>
        </el-form>
    </el-row>
</template>

<script>
export default {
    name:"userEquipment-e",
    props:{
        watchaddData:{
            type:Array
        },
        equipment:{
            type:Object
        }
    },
    data(){
        return{
            useEquipment:{},
            value:[],
            userList:[],
            group:[],
            equipemtList:[],
        }
    },
    created(){
        var _this = this;
        //获取用户
        this.$ajax.get("/api/user/").then(res =>{
            _this.userList =  res.data.data;
                    //获取用户分组
            if(res){
                this.$ajax.get("/api/user/group/").then(res =>{
                    _this.group =  res.data.data
                    _this.equipemntHandle(_this.userList)
                });
            }
        });
    },
    methods:{
        back(){
            this.watchaddData.push(true)
        },
        submit(value){
            this.useEquipment.user = {}
            this.useEquipment.user.id = this.value[1]

            this.useEquipment.equipment = {}
            this.useEquipment.equipment.id = this.equipment.id
            if(value){
                this.$ajax.post("api/useEquipment/metrics",this.useEquipment).then(res =>{
                    console.log(res)
                    this.watchaddData.push(true)
                    }) 
                }else{
                this.$ajax.post("api/useEquipment/",this.useEquipment).then(res =>{
                    console.log(res)
                    this.watchaddData.push(true)
                })
            }
        },
        equipemntHandle(equipemt){
            console.log(equipemt)
            this.group.forEach(element =>{
                this.equipemtList.push({"value":element.id,"label":element.groupName,"children":[]})
            })
            equipemt.forEach(element => {
                this.equipemtList.forEach(type => {
                    if (element.group.id == type.value){
                        type.children.push({"value":element.id,"label":element.username})                  
                    }
                })
            });
            console.log(this.equipemtList)
        },
        handleChange(){
        },
    }
}
</script>
