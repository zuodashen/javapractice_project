<template>

  <div style="text-align: center">
    <img src="@/assets/404.png" alt="">
  </div>

  <div style="text-align: center">
    <el-button  type="primary" style="width: 400px;
    height: 60px;font-size: 20px"@click="goHome">返回主页</el-button>
  </div>
</template>

<script setup>

const goHome = () =>{
  location.href='/manager/home'
}

import router from "@/router/index.js";
</script>