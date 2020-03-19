// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import Vue from 'vue'
import App from './App'
import router from './router'
import Axios from 'axios'
import Qs from 'qs'
import ECharts from 'vue-echarts'
import 'echarts/lib/chart/line'
import echarts from 'echarts'

Vue.prototype.$echarts = echarts
Axios.defaults.withCredentials = true
Vue.prototype.qs = Qs
Vue.prototype.$ajax = Axios
Vue.config.productionTip = false
Vue.use(ElementUI)
Vue.component('chart', ECharts)

router.beforeEach((to, from, next) => {
  const user = JSON.parse(sessionStorage.getItem("user"))
  if (user || to.name == "login") {
    next()
  }else{
    next({
      name: "login",
      query:{
        redirect: to.path
      }
    })
  }

})
/* eslint-disable no-new */
new Vue({
  el: '#app',  //把id为app的区域由vue接管
  router,
  components: { App },
  template: '<App/>'
})
