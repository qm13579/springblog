<template>
    <iframe :src="src" scrolling="auto" frameborder="0" class="frame" >
    </iframe>
</template>
<script>
export default {
    name: "druid",
    data(){
        return{
            src:"api/druid/login.html"
        }
    },
    methods:{
        resetSrc: function (url) {
            this.src = this.global.baseUrl + url
        },
    },
    watch:{
        $route: {
            handler: function (val, oldVal) {
                console.log(val)
                // 如果是跳转到嵌套页面，切换iframe的url
                this.resetSrc(val.path)
            }
        }
    }
    
}
</script>
<style scoped>
  .frame {
    position: relative;
    top: 0px;
    width: 100%;
    height: 100%;
  }
</style>