<template>
    <div id="myChart" :style="{width: '100%', height: '450px'}"></div>
</template>

<script>
export default {
    methods:{
        drawLine(){
        // 基于准备好的dom，初始化echarts实例
        let myChart = this.$echarts.init(document.getElementById('myChart'))
        // 绘制图表
        var _list = this.list
        myChart.setOption({
            title: {
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross',
                    label: {
                        backgroundColor: '#6a7985'
                    }
                }
            },
            legend: {
                data: ['pv', 'ip']
            },
            toolbox: {
                feature: {
                    saveAsImage: {}
                }
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: [
                {
                    type: 'category',
                    boundaryGap: false,
                    data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
                }
            ],
            yAxis: [
                {
                    type: 'value'
                }
            ],
            series: [
                {
                    name: 'pv',
                    type: 'line',
                    stack: '周流量',
                    areaStyle: {},
                    data: [120, 132, 101, 134, 90, 230, 210]
                },
                {
                    name: 'ip',
                    type: 'line',
                    stack: '周IP量',
                    areaStyle: {},
                    data: [120, 132, 101, 134, 90, 230, 210]
                },
            ]
        });
    }
  },
    mounted(){
    this.drawLine()
  },
  props:{
      list:{
        type:Array
      }

  }
}
</script>