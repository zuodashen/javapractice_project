<template>
  <div class="login-container">
    <div class="login-box">
      <div
          style="padding: 30px;background-color: white;margin-left: 100px;border-radius: 30px;box-shadow: 0 0 10px rgba(0,0,0,0.2)">
        <el-form ref="formRef" :rules="data.rules" :model="data.form" style="width: 366px">
          <div style="margin-bottom: 30px; font-size: 24px;text-align: center;color: blue;font-weight: bold">
            欢迎登录后台管理系统
          </div>
          <el-form-item prop="username">
            <el-input size="large" v-model="data.form.username" placeholder="请输入账号" prefix-icon="User"></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input show-password size="large" v-model="data.form.password" placeholder="请输入密码"
                      prefix-icon="Lock"></el-input>
          </el-form-item>
                <el-form-item>
                  <el-select v-model="data.form.role" style="width: 100%" size="large">
                    <el-option  value="ADMIN" label="管理员"></el-option>
                    <el-option  value="EMP" label="员工"></el-option>
                  </el-select>
                </el-form-item>
          <div style="margin-bottom: 20px">
            <el-button @click="login" size="large" style="width: 80%;margin-left: 40px" type="primary">登录</el-button>
          </div>
          <div style="text-align: right">还没有账号？<a style="color: #0742b1" href="/register">注册</a></div>
        </el-form>
      </div>
    </div>
  </div>

</template>

<script setup>
import {reactive, ref} from "vue";
import {User, Lock} from "@element-plus/icons-vue"
import request from "@/utils/request.js";
import {ElMessage} from "element-plus";

const data = reactive({
  form: {role: 'ADMIN'},
  rules: {
    username: [
      {required: true, message: '请输入账号', trigger: 'blur'}
    ],
    password: [
      {required: true, message: '请输入密码', trigger: 'blur'}
    ]
  }
})
const formRef = ref()
const login = () => {
  formRef.value.validate((valid) => {
    if (valid) {
      request.post('/login', data.form).then(res => {
        console.log("登录返回后的数据：", res);
        if (res.code === '200') { // 登录成功
          // 存储后台返回的用户的信息
          localStorage.setItem('xingprojectone-user', JSON.stringify(res.data)); // 把json对象转成字符串
          console.log("打印", res);
          ElMessage.success('登录成功');
          setTimeout(() => {
            location.href = '/manager/home';
          }, 100);
        } else {
          ElMessage.error(res.msg);
        }
      }).catch(error => {
        console.error('请求出错:', error); // 打印错误信息
        ElMessage.error('登录请求出错，请稍后重试');
      });
    }
  });
};

</script>

<style scoped>
.login-container {
  height: 100vh;
  overflow: hidden;
  background-image: url("@/assets/bg.png");
  background-size: cover;
  background-position: -200px -1px;
}

.login-box {
  width: 50%;
  height: 100%;
  display: flex;
  align-items: center;
  right: 0;
  position: absolute;
}

</style>
