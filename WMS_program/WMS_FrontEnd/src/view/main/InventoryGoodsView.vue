<script setup>
import {useRoute, useRouter} from "vue-router";
import {onMounted, ref} from "vue";
import axios from "axios";
import {ElMessage} from "element-plus";
import {Back} from "@element-plus/icons-vue";

const route = useRoute()
const router = useRouter()
const isLoading=ref(false)
const own_goods = ref([])
const own_goods_num = ref(0)
onMounted(()=>{
  isLoading.value = true
  axios.post(
      `http://localhost:8080/inventory/${route.params.id}`,
  ).then(res => {
    if(res.data.status !== 100){
      isLoading.value = false;
      ElMessage.error("该功能暂不可用，请稍后重新尝试");
    }else{
      isLoading.value = false;
      own_goods.value = res.data.data
      own_goods_num.value = own_goods.value.length
    }
  }).catch(err => {
    isLoading.value = false;
    console.log(err);
    ElMessage.error("该网页暂不可用，请稍后重新尝试");
  })
})

const currentPage = ref(1)
const pageSize = ref(10)
</script>

<template>
  <div class="out-view" v-loading="isLoading" element-loading-text="Loading..." :element-loading-spinner="svg" element-loading-background="rgba(122, 122, 122, 0.6)" style="height: auto">
    <el-button link @click="router.go(-1)" size="large" style="margin-top: 20px;font-size: 26px;color: #00d0ff"><el-icon size="30px"><Back /></el-icon>返回</el-button >
    <h3 style="text-align: center;width: 100%">Id为 {{ route.params.id }} 的仓库下的货物信息</h3>
    <div class="component-outline" style="margin-top: 50px">
      <el-table :data="own_goods.slice((currentPage-1)*pageSize,currentPage*pageSize)"
                height="500px"
                row-style="height:45px;"
                style="width: 100%;font-size: 16px">
        <el-table-column label="货物id" width="180">
          <template #default="scope">
            <div>
              {{ scope.row.id }}
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="name" label="货物名称" width="180">
        </el-table-column>
        <el-table-column prop="quantity" label="货物数量">
        </el-table-column>
        <el-table-column prop="price" label="货物单价">
        </el-table-column>
        <el-table-column prop="description" label="货物信息描述" width="400">
        </el-table-column>
      </el-table>
      <el-pagination
          v-model:current-page="currentPage"
          :page-size="pageSize"
          :size="'default'"
          :background="false"
          layout="prev, pager, next, jumper"
          :total="own_goods_num"
          style="align-content: end;justify-content: end;margin-top: 12px"
      />
    </div>
  </div>
</template>

<style scoped>
.component-outline{
  height: auto;
  width: auto;
  box-shadow: var(--el-box-shadow-lighter);
  border: 2px solid var(--el-border-color-dark);
  border-radius: 10px;
  background-color: white;
  padding: 6px;
}
</style>