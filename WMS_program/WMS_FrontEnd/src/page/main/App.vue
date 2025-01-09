<script setup>
import {onMounted, reactive, ref} from 'vue'
import { useRoute,useRouter } from 'vue-router'
import { ArrowDown } from '@element-plus/icons-vue'
import {Goods,House,Box} from '@element-plus/icons-vue'
import { tobe_resolve_num } from '../../store/index.js'
import {ElMessage} from "element-plus";

const route = useRoute()
const router = useRouter()

let username = sessionStorage.getItem('username');
const title = ref("首页")

onMounted(() => {
  router.replace("/home")
})

const open = (index) => {
  switch (index){
    case 1:
      router.replace('/home');
      title.value = "首页";
      break;
    case 2:
      router.replace('/inventory');
      title.value = "仓库管理";
      break;
    case 3:
      router.replace('/goods');
      title.value = "货物管理";
      break;
    default:
      break;
  }
}

const toLogin = () =>{
  window.open('http://localhost:5173/index.html','_parent')
}
</script>

<template>
  <div class="common-layout">
    <el-container>

      <el-aside width="180px">
        <el-row class="tac">
          <el-col :span="24">
            <img src="../../assets/logo.png" width="180" height="120" alt="">
            <el-menu
                default-active="1"
                class="el-menu-vertical-demo"
            >
              <el-badge v-if="tobe_resolve_num.num > 0" :value="tobe_resolve_num.num" :max="99" class="reminder">
              </el-badge>
              <el-menu-item index="1" @click="open(1)">
                <el-icon :size="30"><House /></el-icon>
                <span>首页</span>
              </el-menu-item>
              <el-menu-item index="2" @click="open(2)">
                <el-icon :size="30"><Box /></el-icon>
                <span>仓库管理</span>
              </el-menu-item>
              <el-menu-item index="3" @click="open(3)">
                <el-icon :size="30"><Goods /></el-icon>
                <span>货物管理</span>
              </el-menu-item>
            </el-menu>
          </el-col>
        </el-row>
      </el-aside>

      <el-container>

        <el-header>
          <div class="header-title">{{ title }}</div>
          <el-dropdown>
            <span class="el-dropdown-link">
              用户： {{ username }}
              <el-icon class="el-icon--right">
              <arrow-down />
              </el-icon>
            </span>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item @click="ElMessage.info('抱歉，当前功能暂未开发')">个人信息</el-dropdown-item>
                <el-dropdown-item @click="toLogin()" divided>退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </el-header>

        <el-main>
          <RouterView></RouterView>
        </el-main>

      </el-container>
    </el-container>
  </div>
</template>

<style scoped>
.el-dropdown{
  position: relative;
  left: 60%;
  height: 100%;
}

.el-dropdown-link {
  cursor: pointer;
  color: var(--el-color-primary);
  display: flex;
  align-items: center;
}

.el-header{
  box-shadow: var(--el-box-shadow);
  border: 1px solid var(--el-border-color);
  border-radius: 10px;
  display: flex;
  background-color: white;
}

.header-title{
  width: 30%;
  height: 100%;
  padding-left: 10px;
  justify-content: center;
  align-content: center;
  font-size: 24px;
  font-family: Kaiti;
  font-weight: bold;
  letter-spacing: 3px;
  background: -webkit-linear-gradient(left, #0033ff, #00d0ff);
  background: -o-linear-gradient(right, #0033ff, #00d0ff);
  background: -moz-linear-gradient(right, #0033ff, #00d0ff);
  background: linear-gradient(to right, #0033ff, #00d0ff);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
}

.el-aside{
  border-right: 2px solid darkblue;
  background-color: rgba(0,0,0,0.85);
}

.el-menu{
  background-color: rgba(0,0,0,0);
  position: relative;
  top: 10%;
}

.el-menu-item{
  width: 180px;
  height: 80px;
  margin-top: 50px;
  color: white;
  font-size: 20px;
}

.el-menu-item:hover{
  background-color: rgba(100, 100, 100, 0.60);
}

.el-menu-item > span{
  width: 60%;
  text-align: center;
}

.el-menu-vertical-demo{
  align-self: center;
}

.reminder{
  margin-bottom: -150px;
  margin-left: 138px;
}

.el-main{
  margin: 10px;
  box-shadow: var(--el-box-shadow-dark);
  border: 1px solid var(--el-border-color);
  border-radius: 30px;
}
</style>