<template>
        <el-form  :model="user"  label-width="100px" class="demo-ruleForm">
            <el-form-item label="用户名" >
                <el-input  v-model="user.username" disabled autocomplete="off"></el-input>
            </el-form-item>

            <el-form-item label="部门" prop="group">
                <el-input  v-model="user.group.groupName" disabled autocomplete="off"></el-input>
            </el-form-item>

            <el-form-item label="选择设备" prop="group">
                <el-cascader v-model="value" :options="equipemtList" :props="{ expandTrigger: 'hover' }" @change="handleChange"></el-cascader>
            </el-form-item>

            <el-form-item>
                <el-button type="primary"  @click="submit(true)">分配并激活</el-button>
                <el-button @click="back">返回</el-button>
            </el-form-item>
        </el-form>

</template>
<script>
export default {
    name:"userEquipment-user",
    data(){
        return{
            useEquipment:{},
            value:'',
            equipmentTypeList:[{
                "id":"5",
                "groupName":"笔记本",
                "gid":"2"
            },{
                "id":"6",
                "groupName":"服务器",
                "gid":"2"
            },{
                "id":"8",
                "groupName":"路由器",
                "gid":"2"
            }],
            equipemtList:[],
        }
    },
    props:{
        watchaddData:{
            type:Array
        },
        user:{
            type:Object
        }
    },
    methods:{
        back(){
            this.watchaddData.push(true)
        },
        submit(value){
            this.useEquipment.user = {}
            this.useEquipment.user.id = this.user.id

            this.useEquipment.equipment = {}
            this.useEquipment.equipment.id = this.value[1]
            if(value){
                this.$ajax.post("api/useEquipment/metrics",this.useEquipment).then(res =>{
                    console.log(res)
                    this.watchaddUser.push(true)
                    }) 
                }else{
                this.$ajax.post("api/useEquipment/",this.useEquipment).then(res =>{
                    console.log(res)
                    this.watchaddUser.push(true)
                })
            }
        },
        handleChange(value) {
            console.log(value);
        },
        equipemntHandle(equipemt){
            this.equipmentTypeList.forEach(element =>{
                this.equipemtList.push({"value":element.id,"label":element.groupName,"children":[]})
            })
            equipemt.forEach(element => {
                this.equipemtList.forEach(type => {
                    if (element.type.id == type.value){
                        type.children.push({"value":element.id,"label":element.brand})                  
                    }
                })
            });
        },
        
    },
    created(){
        var _this = this
        this.$ajax.get("api/equipment/").then( res => {
            _this.equipemntHandle(res.data.data)
        })
    }
    
}
</script>
