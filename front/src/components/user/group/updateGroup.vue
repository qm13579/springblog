<template>
        <el-form  :model="group"  label-width="100px" class="demo-ruleForm">
            
            <el-form-item label="部门" prop="groupName">
                <el-input  v-model="group.groupName" style="width:200px;" autocomplete="off"></el-input>
            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="addSubmit">提交</el-button>
                <el-button @click="back">返回</el-button>
            </el-form-item>
        </el-form>
</template>

<script>
export default {
    name: 'updateGroup',
    data() {
        return{

        }
    },
    props:{
        group:{
            type:Object
        },
        watchData:{
            type:Array
        }
    },
    methods:{
        addSubmit(){
            var _this = this
            this.$ajax.put("api/user/group",this.group).then(res => {
                console.log(res)
                if (res.data.code == 10000) {
                    this.open2()
                    _this.$router.go(0)
                }else{
                    this.open4()
                }
            })
        },
        back(){
            this.watchData.push(true)
        },
        open4() {
            this.$message.error('错了哦，更新失败');
        },
        open2() {
            this.$message({
            message: '部门更新成功',
            type: 'success'
            });
        },
    },
}


</script>