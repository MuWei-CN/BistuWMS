<script setup>
import {reactive, ref} from "vue";
import qs from 'qs'
import axios from "axios";
import {ElMessage} from "element-plus";
import { useRouter } from "vue-router";

const router = useRouter();
const isNameError=ref(false)
const isPswdError=ref(false)
const isLoading=ref(false)
const form = reactive({
  name:"",
  password:"",
  level:2,
})

// 登录函数逻辑处理
const login = (lname,lpswd,llevel) => {
  isLoading.value = true
  axios.post(
      "http://localhost:8080/login",
      qs.stringify({name:lname,password:lpswd,level:llevel}),
      { 'content-type': 'application/x-www-form-urlencoded' }
  ).then(res => {
    if(res.data.status !== 100){
      isLoading.value = false
      ElMessage.error(res.data.message);
    }else if(res.data.data !== true){
      isLoading.value = false
      ElMessage.error("用户名或密码错误");
    }else{
      isLoading.value = false
      if(llevel == 1){
        ElMessage.info("非常抱歉，管理员页面暂未开发完成")
      }else{
        ElMessage.success("登录成功，即将跳转页面");
        setTimeout("window.open('http://localhost:5173/main.html','_parent')",3000);
        sessionStorage.setItem('username',form.name)
      }
    }
  }).catch(error => {
    isLoading.value = false
    console.log(error);
    ElMessage.error("登录功能出现了错误，请联系管理人员");
  })
}

const toRegister = () =>{
  router.replace("/register")
}
const nameBlur = ()=>{
  if(form.name === ""){
    isNameError.value = true;
  }else {
    isNameError.value = false;
  }
}
const pswdBlur = ()=>{
  if(form.password === ""){
    isPswdError.value = true;
  }else {
    isPswdError.value = false;
  }
}

</script>

<template>
  <!--  测试用 -->
  <!-- 符号:是v-bind的简写 符号@是v-model的简写 -->
  <div class="login_face" v-loading="isLoading" element-loading-text="Loading..." :element-loading-spinner="svg" element-loading-background="rgba(122, 122, 122, 0.6)" style="height: auto">
    <div class="img_box"></div>
    <div class="login_box">
        <el-form :model="form" ref="formRef" label-width="80px">
          <el-form-item style="display: flex;flex-direction: row;" label="用户名：" v-bind:class="{'StateOk':!isNameError,'StateError':isNameError}">
            <el-input placeholder="请输入用户名" maxlength="10" v-model="form.name" @blur="nameBlur()"></el-input>
          </el-form-item>
          <p v-bind:class="{'StateOk':!isNameError,'StateError':isNameError}" style="margin-top: -10px;font-size: 12px;width: 100%;height: 12px;">{{ isNameError?"用户名不能为空":"" }}</p>
          <el-form-item label="密码 ：" v-bind:class="{'StateOk':!isPswdError,'StateError':isPswdError}">
            <el-input placeholder="请输入密码" maxlength="20" v-model="form.password" type="password" @blur="pswdBlur()">

            </el-input>
          </el-form-item>
          <p v-bind:class="{'StateOk':!isPswdError,'StateError':isPswdError}" style="margin-top: -10px;font-size: 12px;width: 100%;height: 12px;">{{ isPswdError?"密码不能为空":"" }}</p>
          <el-form-item label="权限等级">
            <el-radio-group  v-model="form.level" style="margin-left: 24%">
              <el-radio label=2 >普通用户</el-radio>
              <el-radio label=1 >管理员</el-radio>
            </el-radio-group>
          </el-form-item>
          <div id="enter">
            <el-button class="btn" :disabled="form.name === '' || form.password === ''"  @click="login(form.name,form.password,form.level)">登录</el-button>
            <div id="enter_message" style="display: flex;flex-flow: row;justify-content: center;align-items: center;">
              <el-button style="width: auto;height: 20px;" type="primary" link @click="toRegister()">还没有注册？</el-button>
            </div>
          </div>
        </el-form>
    </div>
  </div>
</template>

<style scoped>


.login_face {
  align-items: center;
  //background-image: url(assets/img8.jpg);
  background-size: cover;
  height: 360px;
}

.login_box {
  border-radius: 20px;
  background: rgba(255, 255, 255, 0.38);
  height: 360px;
  display: flex;
  justify-content: center; /* 水平居中 */
  align-items: end;
}

.el-form {
  width: 80%;
}

:deep(.el-form) .el-form-item{
  height: auto;
}

:deep(.el-form) .el-form-item__label{
  font-size: 16px;
  color: black;
}

:deep(.el-form) .el-radio{
  --el-radio-text-color: black;
}

:deep(.el-form) .el-input{
  height: 40px;
  border-width: 8px;
  --el-input-border-radius: 30px;
  --el-input-focus-border-color: rgb(4, 159, 255);
  --el-input-hover-border-color: rgb(0, 237, 255);
}

:deep(.el-form) .el-input__wrapper.is-focus{
  font-size: 24px;
}

.StateOk{
  color: black;
}

.StateError{
  color:red;
}

:deep(.StateOk) .el-input{
  --el-input-border-color: black;
}

:deep(.StateOk) .el-form-item__label{
  color: black;
}

:deep(.StateError) .el-input{
  --el-input-border-color: red;
}

:deep(.StateError) .el-form-item__label{
  color: red;
}

.img_box {
  width: 130px;
  height: 130px;
  background-image: url("../../assets/vue.svg");
  background-size: cover;
  border-radius: 50%;
  border: white solid 6px;
  opacity: 0.95;
  margin: auto;
  margin-bottom: -58px;
}


.btn{
  width: 160px;
  height: 30px;
  color: white;
  background-color: #315fff;
  border-radius: 40px;
  &.is-disabled{
    background-color: grey;
  }
}

</style>