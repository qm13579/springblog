<template>
    <main>
        <el-row v-for="item in row" :key="item" type="flex" class="row-bg" justify="center">
            <el-col v-for="value in [ list[item],list[item+1],list[item+2],list[item+3] ]" :key="value" :span="6" :offset="1">
                <div class="box">{{value}}</div>
            </el-col>
        </el-row>
    </main>
</template>

<script>
export default {
    name:"equipment",
    data(){
        return{
            row:[],
            list:[1,2,3,4,5,6,7,8,9],
            listObj:[
                {id:1, name:'zs1'},
                {id:2, name:'zs2'},
                {id:3, name:'zs3'},
                {id:4, name:'zs4'},
                {id:5, name:'zs5'},
                {id:6, name:'zs6'},
                ],
        }
    },
    created(){
        var leng = this.list.length;
        console.log(leng)
        if (leng%4 == 0) {
            var num = leng/4;
            for (let index = 0; index < num; index++) {
                this.row.push(index)
            }
        }else{
            var num = leng/4;
            console.log(num)
            for (let index = 0; index < num; index++) {
                
                if(index==0){
                    this.row.push(index)
                }else{
                    this.row.push(index*4)
                }
            }
        }
    }

}
</script>


<style scoped>
.box{
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    height:100px;
    width: 100%;
    color: rgb(44, 182, 97);
    text-align: center;
    border-radius: 30px;
}
</style>
