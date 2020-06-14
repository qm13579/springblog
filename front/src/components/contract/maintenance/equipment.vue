<template>
    <main>
        <el-checkbox-group v-model="checkList">
            <el-checkbox :label="equipment.id" v-for="equipment in equipments" :key="equipment.id">{{equipment.brand}}/{{equipment.part}}</el-checkbox>
        </el-checkbox-group>
       <br>
        <el-row >
            <el-col :span="6" :offset="8">
                <el-button type="success"  v-show="updateButter" size="samll" @click="update">更新</el-button>
                <el-button type="primary"  v-show="submitButter" size="samll" @click="submit">提交</el-button>
                <el-button @click="back"   v-show="backButter" size="samll" >返回</el-button>
                <el-button type="danger" @click="delete1" v-show="deleteButter" size="samll" >删除</el-button>
            </el-col>
        </el-row>
    </main>
</template>

<script>
export default {
    name:"equipment",
    data(){
        return{
            checkList:[],
            equipments:[],
            assignEquipment:[],
            updateButter:false,
            submitButter:true,
            deleteButter:false,
            backButter:true,
            
        }
    },
    props:{
      cid:{
          type:String
      },
      watchList:{
          type:Array
      }
    },
    created(){
        this.getEquipment();
        var _this = this;
        this.$ajax.get("/api/equipment/").then(res => {
            if (res.data.code == 10000) {
                _this.equipments = res.data.data;
            }
        })
    },
    methods:{
        //根据设备数量切换按钮
        butterTrans(list){
            if (list.length >0 ) {
                this.submitButter=false;
                this.updateButter=true;
                this.deleteButter = true;
                this.backButter = false;
            }
        },
        //获取当前合同已绑定的设备
        getEquipment(){
            var _this = this;
            this.$ajax.get("/api/contract/assign/equipment/"+this.cid).then(res => {
                if (res.data.code == 10000) {
                    res.data.data.forEach(element => {
                        _this.checkList.push(element.id);
                    });
                    _this.butterTrans(_this.checkList);
                }

            })
        },
        submit(){
            ///contract/assign/equipment/{cid}
            this.$ajax.post("/api/contract/assign/equipment/"+this.cid,this.checkList).then(res => {
                if (res.data.code == 10000) {
                    this.successMes();
                }else{
                    this.errorMes();
                }
            })
        },
        update(){
            this.$ajax.put("/api/contract/assign/equipment/"+this.cid,this.checkList).then(res=>{
                if (res.data.code ==10000) {
                    this.successMes();
                }else{
                    this.errorMes();
                }
            })
        },
        back(){
            this.watchList.push(true)
        },
        delete1(){

        },
        errorMes(){
            this.$message.error("错了哦，更新失败")
        },
        successMes(){
            this.$message({
                message:"设备更新成功",
                type:"success"
            })
        }

    },


}
</script>