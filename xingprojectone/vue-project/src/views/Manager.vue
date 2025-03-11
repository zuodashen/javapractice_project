<template>
  <div>

    <!-- 头部开始 -->
    <div style="height: 60px;background-color: #3c7fff;display: flex;align-items: center">
        <div style="width: 200px;display: flex; align-items: center;font-size: 20px;color: white" >
          <img style="width: 40px" src="@/assets/花.png" alt="">
          <span style="font-size: 20px;color: white">后台管理系统</span>
        </div>

      <div style="flex: 1"></div>
        <div style="width: fit-content;display: flex;align-items: center;padding-right: 10px">
          <img :src="data.user.avatar ||'https://i0.hdslb.com/bfs/sycp/creative_img/202503/306a1b0cb3aab8b6e2843523ced602e5.jpg' " alt="" style="width: 40px;height: 40px;border-radius: 50%">
          <span style="color: white;margin-left: 5px">{{data.user.name}}</span>
        </div>
    </div>
    <!-- 头部结束 -->

    <!-- 左侧导航菜单开始 -->
    <div style="display: flex">
      <div style="width: 200px;border-right: 1px solid #ddd;min-height:calc(100vh - 60px)">
        <el-menu router :default-active="router.currentRoute.value.path" :default-openeds="['1']" style="border: 0">
          <el-menu-item index="/manager/home">
            <el-icon><House /></el-icon>
            系统首页
          </el-menu-item>

          <el-menu-item index="/manager/data">
            <el-icon><DataAnalysis/></el-icon>
            数据统计
          </el-menu-item>

          <el-sub-menu index="1">
            <template #title>
              <el-icon><User /></el-icon>
              <span>用户管理</span>
            </template>
            <el-menu-item index="/manager/admin">管理员信息</el-menu-item>
            <el-menu-item index="/manager/employee">员工信息</el-menu-item>
          </el-sub-menu>

          <el-menu-item index="/manager/person">
            <el-icon><UserFilled /></el-icon>
            个人信息
          </el-menu-item>

          <el-menu-item index="/manager/password">
            <el-icon><Lock/></el-icon>
            修改密码
          </el-menu-item>


          <el-menu-item @click="logout">
            <el-icon><switch-button /></el-icon>
            退出登录
          </el-menu-item>

        </el-menu>
      </div>
      <!-- 左侧导航菜单结束 -->
      <!-- 右侧主体区域开始 -->
      <div style="flex: 1;width: 0;background-color: #f5f7ff;padding: 10px">
      <RouterView @updateUser="updateUser"/>
      </div>
      <!-- 右侧主体区域结束 -->
    </div>
    <!-- 下面部分结束 -->

  </div>
</template>


<script setup>
import {Back, DataAnalysis, House, InfoFilled, Switch, SwitchButton, User, UserFilled} from "@element-plus/icons-vue";
import router from "@/router/index.js";
import {reactive,onMounted} from "vue";
import {ElMessage} from "element-plus";

const data = reactive({
  user: JSON.parse(localStorage.getItem('xingprojectone-user')),
});

const logout = () => {
  localStorage.removeItem('xingprojectone-user')
  location.href ='/login'//清楚当前登录的用户缓存数据
}

const updateUser =() =>{
  data.user = JSON.parse(localStorage.getItem('xingprojectone-user'))
}
</script>


<style>
.elmenu .is-active {
  background-color: #e6ecf7 !important;
}
</style>