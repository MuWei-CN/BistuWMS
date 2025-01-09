<script setup>
import {useRouter} from "vue-router";
import {onMounted, reactive, ref} from "vue";
import axios from "axios";
import {ElMessage, ElMessageBox} from "element-plus";
import qs from "qs";
import {tobe_resolve_num} from "../../store/index.js";

let username = sessionStorage.getItem('username');
const router = useRouter()

let inventories = []
const search_inventories = ref([])
const search_inventories_num = ref(0)
const check_parmas = ref({
  creator_name:null,
  id:null
})
const check_and = () => {
  let temp = inventories.filter((row) =>{
    let check_id = check_parmas.value.id?check_parmas.value.id:row.id;
    let check_creator = check_parmas.value.creator_name?check_parmas.value.creator_name:row.creator_name;
    return check_id == row.id && check_creator == row.creator_name;
  })

  search_inventories_num.value = temp.length
  search_inventories.value = temp
}

const check_or = () => {
  let temp = inventories.filter((row) =>{
    return row.creator_name == check_parmas.value.creator_name || row.id == check_parmas.value.id;
  })
  search_inventories_num.value = temp.length
  search_inventories.value = temp
}

const check_reset = () =>{
  search_inventories_num.value = inventories.length
  search_inventories.value = inventories
}

const lookover = (id) => {
  router.push(`/inventory/${id}`)
}

let newinventory = reactive({
  new_creator:username,
  new_id:null,
  new_message:null,

  init:function () {
    this.new_id="";
    this.new_message="";
  }
})

const addInventory = () =>{
  isLoading.value = true
  axios.post(
      'http://localhost:8080/inventory/api/add',
      qs.stringify({creator_name:newinventory.new_creator,id:newinventory.new_id,message:newinventory.new_message}),
      { 'content-type': 'application/x-www-form-urlencoded' }
  ).then((res) => {
    if (res.status != 200){
      isLoading.value = false
      ElMessage.error("添加失败...")
      newinventory.init()
    }else if (res.data.status != 100){
      isLoading.value = false
      ElMessage.error("添加失败...")
      newinventory.init()
    }else if (res.data.data == false){
      isLoading.value = false
      ElMessage.error("添加失败...")
      newinventory.init()
    }else {
      isLoading.value = false
      ElMessage.success("添加成功！")
      inventories.push({
        creator_name:newinventory.new_creator,
        id:newinventory.new_id,
        message:newinventory.new_message,
      })
      search_inventories.value = inventories
      search_inventories_num.value ++;
      newinventory.init()
    }
  }).catch((err) => {
    isLoading.value = false
    ElMessage.error("添加失败...")
    console.log(err)
    newinventory.init()
  })
}

let tobe_romove = 0

const removeInventory = ()=>{
  isLoading.value = true
  axios.post(
      'http://localhost:8080/inventory/api/remove',
      qs.stringify({id:tobe_romove}),
      { 'content-type': 'application/x-www-form-urlencoded' }
  ).then((res) => {
    if (res.status != 200){
      isLoading.value = false
      ElMessage.error("删除失败...")
    }else if (res.data.status != 100){
      isLoading.value = false
      ElMessage.error("删除失败...")
    }else if (res.data.data == 0){
      isLoading.value = false
      ElMessage.error("删除失败...")
    }else {
      isLoading.value = false
      ElMessage.success("删除成功！")
      let index = inventories.findIndex((inventory) => { return inventory.id == tobe_romove})
      inventories.splice(index,1)
      search_inventories.value = inventories
      search_inventories_num.value --;
    }
  }).catch((err) => {
    isLoading.value = false
    ElMessage.error("删除失败...")
    console.log(err)
  })
}

const isLoading=ref(false)
onMounted(()=>{
  isLoading.value = true
  axios.post(
      `http://localhost:8080/inventory`,
  ).then(res => {
    if(res.data.status !== 100){
      isLoading.value = false;
      ElMessage.error("该功能暂不可用，请稍后重新尝试");
    } else{
      isLoading.value = false;
      inventories = res.data.data
      search_inventories_num.value = inventories.length
      search_inventories.value = inventories
    }
  }).catch(err => {
    isLoading.value = false;
    console.log(err);
    ElMessage.error("该网页暂不可用，请稍后重新尝试");
  })
})

const currentPage = ref(1)
const pageSize = ref(10)

const dialogFormVisible = ref(false)
const openAlert = () => {
  ElMessageBox.alert(`确认删除id:${tobe_romove}仓库吗`, '删除确认', {

    confirmButtonText: '确认',
    callback: (action) => {
      if(action == "confirm")
        removeInventory()
    },
  })
}

</script>

<template>
  <div class="out-view" v-loading="isLoading" element-loading-text="Loading..." :element-loading-spinner="svg" element-loading-background="rgba(122, 122, 122, 0.6)" style="height: auto">
    <div class="component-outline" style="margin-bottom: 30px">
      <el-form :model="check_parmas" label-width="auto" style="width: 100%;">
        <div style="display:flex;padding: 20px">
          <h3 style="margin-top: 8px;margin-left: 20px">查询：</h3>
          <el-form-item label="创建者名称" style="margin-left: 10px;">
            <el-input v-model="check_parmas.creator_name"></el-input>
          </el-form-item>
          <el-form-item label="仓库id" style="margin-left: 10px;">
            <el-input v-model="check_parmas.id"></el-input>
          </el-form-item>
        </div>
        <el-form-item>
          <el-button @click="dialogFormVisible = true" size="default" type="success" style="margin-left: 4%;">新增仓库</el-button>
          <el-button @click="check_and()" style="margin-left: 60%">并集查询</el-button>
          <el-button @click="check_or()">或集查询</el-button>
          <el-button @click="check_reset()">重置</el-button>
        </el-form-item>
      </el-form>
    </div>

    <div class="component-outline">
      <el-table :data="search_inventories.slice((currentPage-1)*pageSize,currentPage*pageSize)"
                height="500px"
                row-style="height:45px;"
                style="width: 100%;font-size: 16px">
        <el-table-column prop="creator_name" label="仓库创建者名称" width="180">
        </el-table-column>
        <el-table-column label="仓库id" width="180">
          <template #default="scope">
            <div>
              {{ scope.row.id }}
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="message" label="仓库信息描述" width="800">
        </el-table-column>
        <el-table-column>
          <template #default="scope">
            <el-button type="success" size="small" @click="lookover(scope.row.id)">查看</el-button>
            <el-button @click="ElMessage.info('数据修改功能，暂未开发完成')" type="primary" size="small">修改</el-button>
            <el-button @click="tobe_romove = scope.row.id;openAlert()" type="danger" size="small">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
          v-model:current-page="currentPage"
          :page-size="pageSize"
          :size="'default'"
          :background="false"
          layout="prev, pager, next, jumper"
          :total="search_inventories_num"
          style="align-content: end;justify-content: end;margin-top: 12px"
      />
    </div>
    <el-dialog v-model="dialogFormVisible" title="添加仓库" width="500">
      <el-form :model="newinventory">
        <el-form-item label="仓库id" :label-width="100">
          <el-input v-model="newinventory.new_id" placeholder="请输入仓库id（必填）" autocomplete="off" size="large"/>
        </el-form-item>
        <el-form-item label="仓库描述" :label-width="100">
          <el-input v-model="newinventory.new_message" placeholder="请输入仓库描述信息（非必填）" autocomplete="off" size="large"/>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="dialogFormVisible = false">Cancel</el-button>
          <el-button type="primary" @click="dialogFormVisible = false;addInventory()">
            Confirm
          </el-button>
        </div>
      </template>
    </el-dialog>
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

:deep(.el-form) .el-form-item__label{
  font-size: 18px;
  padding-top: 6px;
  height: 100%;
}
</style>