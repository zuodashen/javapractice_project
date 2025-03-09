<template>
  <div class="login-container">
<div class="login-box">
  <div style="padding: 30px;background-color: white;margin-left: 100px;border-radius: 30px;box-shadow: 0 0 10px rgba(0,0,0,0.2)">
    <el-form ref="formRef" :rules="data.rules" :model="data.form" style="width: 366px">
      <div style="margin-bottom: 30px; font-size: 24px;text-align: center;color: blue;font-weight: bold" >欢 迎 注 册</div>
      <el-form-item prop="username">
        <el-input size="large" v-model="data.form.username" placeholder="请输入账号" prefix-icon="User"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input show-password size="large" v-model="data.form.password" placeholder="请输入密码" prefix-icon="Lock"></el-input>
      </el-form-item>
      <el-form-item prop="confirmPassword">
        <el-input show-password size="large" v-model="data.form.confirmPassword" placeholder="请确认密码" prefix-icon="Lock"></el-input>
      </el-form-item>
      <el-form-item prop="no">
        <el-input show-password size="large" v-model="data.form.no" placeholder="工号" prefix-icon="User"></el-input>
      </el-form-item>
      <div style="margin-bottom: 20px">
        <el-button @click="register" size="large" style="width: 80%;margin-left: 40px" type="primary">注册</el-button>
      </div>
      <div style="text-align: right">已有账号，请前去登录<a style="color: #0742b1" href="/login">登录</a></div>
    </el-form>
  </div>
</div>
  </div>

</template>

<script setup>
import {reactive,ref} from "vue";
import {User,Lock} from "@element-plus/icons-vue"
import request from "@/utils/request.js";
import {ElMessage} from "element-plus";

const validatePass = (rule, value, callback) => {
  if(!value){
    callback(new Error('请再次确认密码'))
  } else if (value !== data.form.password){
    callback(new Error('两次输入密码不一致'))
  }else {
    callback()
  }
}

const data = reactive({
  form:{},
  rules:{
    username: [
      {required:true,message:'请输入账号',trigger:'blur'}
    ],
    password:[
      {required:true,message:'请输入密码',trigger:'blur'}
    ],
    confirmPassword:[
      {validator: validatePass, tirgger:'blur'}
    ],
    no:[
      {required:true,message:'请输入工号', tirgger:'blur'}
    ]
  }
})
const formRef = ref()



const register = () => {
  formRef.value.validate((valid)=>{
    if(valid) {
      request.post('/register',data.form).then(res =>{
        if(res.code === '200'){//注册成功
          ElMessage.success('注册成功')
          setTimeout(()=>{
            location.href = '/login'
          },500)
        }else {
          ElMessage.error(res.msg)
        }
      })
    }
  })
}

</script>

<style scoped>
.login-container{
  height: 100vh;
  overflow: hidden;
  background-image: url("@/assets/bg.png");
  background-size: cover;
  background-position: -200px -1px;
}

.login-box{
  width: 50%;
  height: 100%;
  display: flex;
  align-items: center;
  right: 0;
  position:absolute;
}

</style>
