<template>
    <div>
        <FindAllUser></FindAllUser>
    </div>

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