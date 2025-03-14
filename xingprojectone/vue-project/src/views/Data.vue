<template>
  <div>
   <div style="display: flex; grid-gap: 10px;justify-content: flex-start; margin-bottom:10px ">
     <div class="card" style="padding: 20px; width: 50%;height: 400px" id="bar"></div>
     <div class="card" style="padding: 20px; width: 50%;height: 400px" id="line"></div>
   </div>

    <div style="display: flex; grid-gap: 10px;margin-bottom:10px ">
      <div class="card" style="padding: 20px; width: calc(50% - 45px);height: 400px" id="pie"></div>
    </div>
<!--    另一种对齐的布局方式-->
<!--    <el-row :gutter="10">-->
<!--      <el-clo :span="12" style="margin-bottom: 10px">-->
<!--        <div class="card" style="padding: 20px; height: 400px" id="bar"></div>-->
<!--      </el-clo>-->
<!--      <el-clo :span="12" style="margin-bottom: 10px">-->
<!--        <div class="card" style="padding: 20px; height: 400px" id="line"></div>-->
<!--      </el-clo>-->
<!--      <el-clo :span="12" style="margin-bottom: 10px">-->
<!--        <div class="card" style="padding: 20px; height: 400px" id="pie"></div>-->
<!--      </el-clo>-->
<!--    </el-row>-->


  </div>
</template>


<script setup>
import {reactive,onMounted} from "vue";
import * as echarts from "echarts"
import request from "@/utils/request.js";

const  data = reactive({


})
const barOption ={
  title: {
    text: '各部门员工数量'
  },
  tooltip: {},
  legend: {
    trigger:'item'
  },
  xAxis: {
    data: []
  },
  yAxis: {},
  series: [
    {
      name: '人数',
      type: 'bar',
      data: [],
      itemStyle: {
        normal:{
          color: function () {
            return '#'+Math.floor(Math.random()*(256*256-19)).toString(16);
          }
        }
      }
    },
  ]
};
const lineOption ={
  title: {
    text: '近七天发布文章数量'
  },
  tooltip: {},
  legend: {
    trigger:'item'
  },
  xAxis: {
    data: []
  },
  yAxis: {},
  series: [
    {
      name: '发布数量',
      type: 'line',
      data: [],
      smooth:true
    },
  ]
};

const pieOption ={
  title: {
    text: '各部门员工数量比例图',
    left: 'center'
  },
  tooltip: {
    trigger: 'item'
  },
  legend: {
    orient: 'vertical',
    left: 'left'
  },
  series: [
    {
      name: '员工数量',
      type: 'pie',
      radius: '50%',
      data: [],
      center:['50%','60%'],
      label:{
        formatter: '{b}: {@2012} ({d}%)'
      },
      emphasis: {
        itemStyle: {
          shadowBlur: 10,
          shadowOffsetX: 0,
          shadowColor: 'rgba(0, 0, 0, 0.5)'
        }
      }
    }
  ]
}


onMounted(()=>{
//基于准备好的dom，初始化echarts实例
  const barChart = echarts.init(document.getElementById('bar'))
  request.get('/barData').then(res=>{
    barOption.xAxis.data = res.data.department //横轴数据
    barOption.series[0].data = res.data.count     //纵轴数据
    //使用刚指定的配置项和数据显示图标
    barChart.setOption(barOption)
    console.log(res)
  })
  const lineChart = echarts.init(document.getElementById('line'))
  request.get('/lineData').then(res=>{
    lineOption.xAxis.data = res.data.date
    lineOption.series[0].data = res.data.count
    lineChart.setOption(lineOption)
    console.log(res)
  })
  const pieChart = echarts.init(document.getElementById('pie'))
  request.get('/pieData').then(res=>{
    pieOption.series[0].data = res.data
    pieChart.setOption(pieOption)
    console.log(res)
  })

})



</script>



<style scoped>

</style>