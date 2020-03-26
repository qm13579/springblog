<template>
<main>
    <el-row>
        <el-button type="success" size="small" @click="submitContract">提交</el-button>
        <el-input placeholder="请输入合同名称" size="small" style="width:15%" v-model="input" clearable></el-input>
        <el-radio-group v-model="radio"  size="small">
            <el-radio-button label="模板"></el-radio-button>
            <el-radio-button label="正文"></el-radio-button>
        </el-radio-group>

    </el-row>
    <br>
    <el-row>
        <el-col :span="12">
        <quill-editor 
        ref="myTextEditor" 
        v-model="content" 
        :options="editorOption" 
        style="height:600px;"></quill-editor>
        </el-col>
        
        <el-col :span="11" :offset="1">
            <div class="preview" style="box-shadow: rgba(0, 0, 0, 0.1) 0px 2px 12px 0px;height:50px;">
                <span>合同预览</span>
            </div>
            <div class="ql-editor boxs" v-html="this.content" >
            </div>
        </el-col>
    </el-row>
    <br>

</main>
</template>

<script>
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'

import { quillEditor } from 'vue-quill-editor'

export default {
    name:"contractDraft",
    data(){
        return{
            radio:"正文",
            input:"",
            content:"",
            editorOption: {
               placeholder: '编辑文章内容'
            },
            draft:{},
        }
    },
    components: {
        quillEditor
    },
    methods:{
        onEditorChange({ editor, html, text }) {
            this.content = html;
        },
        submitContract(){
            if (this.input == "") {
                this.open4()
            }else{
                this.draft.title=this.input
                this.draft.status = this.radio=="正文"?1:0
                console.log(this.draft.status)
                this.draft.content = this.content
                this.$ajax.post("api/contract/draft",this.draft).then(res => {
                    if (res.data.code == 10000) {
                        this.open2()
                    }
                })
            }
        },
     open4() {
        this.$message.error('错了哦，要记得写合同标题哦');
      },      
      open2() {
        this.$message({
          message: '恭喜,上传合同成功',
          type: 'success'
        });
      },

    },
    watch:{
        content(){
        }
    }
    
}
</script>

<style scoped>
.preview{
    text-align: center;
    font-size: 30px;
    font-family: 华文行楷;
    color: #409eff;
}
.boxs{
    height:600px;
}
</style>
