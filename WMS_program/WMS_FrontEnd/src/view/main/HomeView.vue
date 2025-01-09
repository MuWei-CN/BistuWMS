<script setup>
import PendingInformation from "../../components/main/home/PendingInformation.vue";
import { tobe_resolve,tobe_resolve_num,set_tobe_resolve } from '../../store/index.js'
import {onMounted, ref} from "vue";
import axios from "axios";
import {ElMessage} from "element-plus";
import WMSmessage1 from "../../components/main/home/WMSmessage1.vue";
import WMSmessage2 from "../../components/main/home/WMSmessage2.vue";

const isLoading=ref(false)
onMounted(()=>{
  isLoading.value = true
  axios.post(
      `http://localhost:8080/inventory/0`,
  ).then(res => {
    if(res.data.status !== 100){
      isLoading.value = false;
      ElMessage.error("该功能暂不可用，请稍后重新尝试");
    }else if(res.data.data === []){
      isLoading.value = false;
      tobe_resolve_num.setNum(0);
    }else{
      isLoading.value = false;
      set_tobe_resolve(res.data.data);
      tobe_resolve_num.setNum(tobe_resolve.length);
    }
  }).catch(err => {
    isLoading.value = false;
    console.log(err);
    ElMessage.error("该网页暂不可用，请稍后重新尝试");
  })
})
</script>

<template>
  <div class="out-view" v-loading="isLoading" element-loading-text="Loading..." :element-loading-spinner="svg" element-loading-background="rgba(122, 122, 122, 0.6)" style="height: auto;display: flex">
    <PendingInformation/>
    <div class="message-out-line">
      <WMSmessage1/>
      <WMSmessage2/>
    </div>
  </div>
</template>

<style scoped>
.message-out-line{
  width: 45%;
  height: auto;
  display: grid;
}
</style>