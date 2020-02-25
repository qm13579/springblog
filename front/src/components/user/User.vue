<template>
        <el-row type="flex" class="row-bg" justify="center">
                <FindAllUser></FindAllUser>
        </el-row>
</template>

<script>
import FindAllUser from './findAllUser'

export default {
    name:"user",
    components: {
        FindAllUser
    },
    data () {
        return{
            title : "this is user--"
        }
    },
    
}
</script>