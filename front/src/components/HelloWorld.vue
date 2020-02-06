<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <h1>{{message}}</h1>
    <button v-on:click="fun1('hello')">v-on绑定事件</button>
    <span>
        <font :color="ys1">v-bing属性绑定-用于获取改变标签值</font>
    </span>
    键盘绑定<input type="text" v-on:keydown="funKey($event)"/>
    <form @submit.prevent action="https:www.baidu.com" method="post">
        <input type="submit" value="submit-baidu">
    </form>
    按键修饰符--回车<input @keydown.enter="keyDownEnter" type="text"/>
    <form action="" method="post"> 
      用户名：<input type="text" @keydown.enter="keyDownEnterModel" name="username" v-model="user.username"/><br/>
      密  码：<input type="text" @keydown.enter="keyDownEnterModel" name="password" v-model="user.password"/><br/>
    </form>
    <h2 v-on:mouseover="funMouse">Essential Links</h2>
    <ul>
      <li>
        <a
          href="https://vuejs.org"
          target="_blank"
        >
          Core Docs
        </a>
      </li>
      <li>
        <a
          href="https://forum.vuejs.org"
          target="_blank"
        >
          Forum
        </a>
      </li>
      <li>
        <a
          href="https://chat.vuejs.org"
          target="_blank"
        >
          Community Chat
        </a>
      </li>
      <li>
        <a
          href="https://twitter.com/vuejs"
          target="_blank"
        >
          Twitter
        </a>
      </li>
      <br>
      <li>
        <a
          href="http://vuejs-templates.github.io/webpack/"
          target="_blank"
        >
          Docs for This Template
        </a>
      </li>
    </ul>
    <h2>Ecosystem</h2>
    <ul>
      <li>
        <a
          href="http://router.vuejs.org/"
          target="_blank"
        >
          vue-router
        </a>
      </li>
      <li>
        <a
          href="http://vuex.vuejs.org/"
          target="_blank"
        >
          vuex
        </a>
      </li>
      <li>
        <a
          href="http://vue-loader.vuejs.org/"
          target="_blank"
        >
          vue-loader
        </a>
      </li>
      <li>
        <a
          href="https://github.com/vuejs/awesome-vue"
          target="_blank"
        >
          awesome-vue
        </a>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App',
      message: "｛差值表达式｝---支持js表达式,不支持变量声明",
      ys1: "red",
      user:{
        username: "admin",
        password: "admin"
      }
    }
  },
  methods:{
    fun1: function(args){
      this.message = args;
    },
    funKey: function(event){
      // event.preventDefault(); //阻止键盘事件的输入
    },
    funMouse: function(){
      alert("鼠标悬停在Essential")
    },
    keyDownEnter: function(){
      alert('回车已经按下')
    },
  keyDownEnterModel: function(){
    alert(this.user.username)
  }
  },
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
