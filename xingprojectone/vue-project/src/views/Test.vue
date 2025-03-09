<template>
  <div>
    <div>
      <el-button type="primary" @click="router.back()">返回主页</el-button>
    </div>

    <div style="font-size:20px;font-weight:bold;color:red;text-decoration: underline;font-style:italic;">
      开始学习项目 upup
    </div>


    <div style="font-size: 20px;margin-bottom: 20px">
      {{ a }} {{ b }}
    </div>

    <div style="margin-bottom: 20px">
      {{ data.a }}
      {{ data.b }}
    </div>


    <div>
      <input v-model="data.a"/>
    </div>


    <div style="margin-bottom: 20px">
      <span style="color: red;font-size: 20px" v-if="data.name === '小小章'">小小章</span>
      <span style="color: blue;font-size: 20px" v-if="data.name === '小小龙'">小小龙</span>
      <span style="color: green;font-size: 20px" v-else>小小过</span>
    </div>


    <div style="display: flex">
      <div
          style="width: 300px;height: 300px;background-color: hotpink;text-align: center;font-size: 30px;margin-right: 10px">
        {{ data.smallname }}
      </div>
    </div>

    <div style="display: flex;margin-bottom: 20px">
      <div @click ="show(it)" v-for="it in data.arr" :key="it" style="width: 300px;height: 300px;background-color: aqua;text-align: center;font-size: 30px;margin-right: 10px" >
        {{ it }}
      </div>

      <div>
        <select style="width: 200px">
          <option v-for="item in data.fruits">{{ item }}</option>
        </select>
      </div>

    </div>

    <div style="margin-bottom: 20px">
      <!--      <button v-on:click="click">点我加好运</button>-->
      <button @click="click">点我加好运</button>
    </div>



    <div style="margin-bottom: 20px">
      <!--      <div :style="{'width':'100px','height':'100px','background-color':'red'}"></div>-->
      <div :style="data.box"></div>
      <div>
        <img :src="'https://www.runoob.com/wp-content/uploads/2017/01/vue.png'"alt="">
      </div>
    </div>

    <div style="margin-bottom: 20px">
      <el-button @click="clickDate" :style="{color:'red'}">点击按钮</el-button>
      <el-button type="primary" plain size="large">Primary</el-button>
      <el-button type="success" plain :bg="false" text>星星</el-button>
      <el-button type="info" plain>Info</el-button>
      <el-button type="warning" plain>Warning</el-button>
      <el-button type="danger" plain>Danger</el-button>
    </div>


    <div style="margin: 30px">
      <el-icon :size="20">
        <Edit />
      </el-icon>
      <span style="margin-left: 30px">
        <el-icon :size="20" color="black" style="top: 4px"><View />
        </el-icon>100
      </span>

      <el-button type="danger" :icon="Delete" circle/>
    </div>

    <el-input v-model="data.a"
              style="width:240px;margin-bottom: 30px"
              placeholder="Type something"
              :perfox-icon="Search"/>

  </div>
</template>


<script setup>
import {onMounted, reactive, ref} from "vue";
import {Delete, Edit, View,Search} from "@element-plus/icons-vue";
import router from'@/router/index.js'

const a = ref(1)
const b = ref("星星不烦恼")
const data = reactive({
  a: 123,
  b: "xingxing",
  name: "小小章",
  arr: [1, 2, 3],
  smallname: "小煮！",
  fruits: ["苹果", "香蕉", "橘子"],
  box: {
    width:'100px',
    height:'100px',
    backgroundColor:'red'
  }
})

const click = () => {
  alert("好运加1")
}
const show = (value) => {
  alert(value)
}
onMounted(()=>{
  console.log('页面加载完成')
})


const clickDate = () => {
  alert("我爱学习！")
}
</script>

