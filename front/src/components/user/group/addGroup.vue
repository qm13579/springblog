<template>
    <el-form  :model="group"  :rules="rules" ref="group" label-width="100px" class="demo-ruleForm">
        <el-form-item label="部门" prop="groupName">
            <el-input size="small" style="width:200px" v-model="group.groupName" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item>
            <el-button type="primary" @click="addSubmit('group')" >提交</el-button>
            <el-button @click="back" >返回</el-button>
        </el-form-item>
    </el-form>
</template>

<script>
export default {
    name: 'addGroup',
    data() {
        var checkGroupName=(rule,value,callback) =>{
            if (!value) {
                return callback(new Error("部门不能为空"))
            }else{
                callback()
            }
        }
        return{
            group:{},
            rules:{
                groupName:[{validator: checkGroupName, trigger: 'blur'}],
            }
        }
    },
    props:{
        watchData:{
            type:Array
        }
    },
    methods:{
        addSubmit(group){
            this.$refs[group].validate((valid) => {
                if (valid) {
                    var _this = this
                    this.$ajax.post("api/user/group",this.group).then(res => {
                        if (res.data.code == 10000) {
                            this.open2()
                            this.$router.go(0)
                        }else{
                            this.open4()
                        }
                    })
                }else{
                    this.open4()
                }
            })

        },
        back(){
            this.watchData.push(true)
        },
        open4() {
            this.$message.error('错了哦，添加失败');
        },
        open2() {
            this.$message({
            message: '部门添加成功',
            type: 'success'
            });
        },
    },
}


</script>