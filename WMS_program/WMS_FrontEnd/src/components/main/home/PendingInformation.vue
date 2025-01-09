<script setup xmlns="http://www.w3.org/1999/html">
import { tobe_resolve,tobe_resolve_num } from '../../../store/index.js'

</script>

<template>
    <el-card v-if="tobe_resolve_num.num > 0" style="max-width: 100%" class="component-outline">
      <template #header>
        <h2 class="card-header">
          您有消息待处理
          <span>（{{ tobe_resolve_num.num }}）</span>
        </h2>
      </template>
      <el-scrollbar height="550px">
        <p v-for="item in tobe_resolve" :key="item.id" class="scrollbar-item">
          <span style="font-size: 20px;letter-spacing: 5px;color: darkblue">提醒：</span>id为&ensp;<span style="font-weight: bold">{{ item.id }}</span>&ensp;的货物还未入库，请及时入库。
        </p>
      </el-scrollbar>
      <template #footer><span style="color:lightblue;">请前往 货物管理 子系统进行货物入库</span></template>
    </el-card>

  <el-card v-else style="max-width: 100%" class="component-outline">
    <template #header>
      <h2 class="card-header">
        暂无待处理消息
      </h2>
    </template>
    <div style="text-align: center;align-content: center;height: 468px;color: lightgrey;font-size: 100px;font-weight: lighter">暂无待处理消息</div>
  </el-card>
</template>

<style scoped>
.component-outline{
  height: 100%;
  width: 55%;
  box-shadow: var(--el-box-shadow-lighter);
  border: 2px solid var(--el-border-color-dark);
  border-radius: 10px;
  background-color: white;
  padding: 6px;
}

.card-header {
  padding-left: 20px;
}

.card-header > span{
  color: red;
}
</style>