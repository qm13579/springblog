<template>
    <el-upload
    class="upload-demo"
    :action = "uploadUrl()"
    :on-preview="handlePreview"
    :on-remove="handleRemove"
    :file-list="fileList"
    accept="application/pdf "
    list-type="picture">
    <el-button size="small" type="primary">点击上传</el-button>
    <div slot="tip" class="el-upload__tip">只能上传PDF文件</div>
    </el-upload>
  
</template>
<script>
export default {
    name:"upload",
    data() {
      return {
        fileList: []
      };
    },
    methods: {
      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlePreview(file) {
        console.log(file);
      },
      uploadUrl(){
        return this.cid
      }
    },
    props:{
      cid:{
        type:String
      }
    }
}
</script>