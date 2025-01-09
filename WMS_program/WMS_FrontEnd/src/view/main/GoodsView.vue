<script setup>
import {onMounted, reactive, ref} from "vue";
import axios from "axios";
import {ElMessage, ElMessageBox} from "element-plus";
import { tobe_resolve_num } from '../../store/index.js'
import qs from "qs";

let goods = []
const search_goods = ref([])
const search_goods_num = ref(0)
const check_parmas = ref({
  id:null,
  name:null,
  price:null,
})
const check_and = () => {
  let temp = goods.filter((row) =>{
    let check_id = check_parmas.value.id?check_parmas.value.id:row.id;
    let check_name = check_parmas.value.name?check_parmas.value.name:row.name;
    let check_price = check_parmas.value.price?check_parmas.value.price:row.price;
    return check_id == row.id && check_name == row.name && check_price == row.price ;
  })
  search_goods_num.value = temp.length
  search_goods.value = temp
}

const check_or = () => {
  let temp = goods.filter((row) =>{
    return row.id == check_parmas.value.id || row.name == check_parmas.value.name || row.price == check_parmas.value.price;
  })
  search_goods_num.value = temp.length
  search_goods.value = temp
}

let newgood = reactive({
  new_name:null,
  new_quantity:null,
  new_price:null,
  new_description:null,

  init:function () {
    this.new_name="";
    this.quantity="";
    this.price="";
    this.description="";
  }
})
const addGood = () =>{
  isLoading.value = true
  axios.post(
      'http://localhost:8080/goods/api/add',
      qs.stringify({name:newgood.new_name,quantity:newgood.new_quantity,price:newgood.new_price,description:newgood.new_description}),
      { 'content-type': 'application/x-www-form-urlencoded' }
  ).then((res) => {
    if (res.status != 200){
      isLoading.value = false
      ElMessage.error("添加失败...")
      newgood.init()
    }else if (res.data.status != 100){
      isLoading.value = false
      ElMessage.error("添加失败...")
      newgood.init()
    }else if (res.data.data == false){
      isLoading.value = false
      ElMessage.error("添加失败...")
      newgood.init()
    }else {
      isLoading.value = false
      ElMessage.success("添加成功！")
      goods.push({
        inventory_id:0,
        id:goods.at(-1).id + 1,
        name:newgood.new_name,
        quantity:newgood.new_quantity,
        price:newgood.new_price,
        description:newgood.new_description
      })
      search_goods.value = goods
      search_goods_num.value ++;
      tobe_resolve_num.increase()
      newgood.init()
    }
  }).catch((err) => {
    isLoading.value = false
    ElMessage.error("添加失败...")
    console.log(err)
    newgood.init()
  })
}

let tobe_remove = 0;

const removeGood = ()=>{
  isLoading.value = true
  axios.post(
      'http://localhost:8080/goods/api/remove',
      qs.stringify({id:tobe_remove}),
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
      let index = goods.findIndex((good) => { return good.id == tobe_remove})
      if(goods[index].inventory_id == 0)
        tobe_resolve_num.decrease()
      goods.splice(index,1)
      search_goods.value = goods
      search_goods_num.value --;
    }
  }).catch((err) => {
    isLoading.value = false
    ElMessage.error("删除失败...")
    console.log(err)
  })
}

let move_good_id = 0
const goodout = (inventory_id) => {
  isLoading.value = true
  axios.post(
      'http://localhost:8080/goods/api/goodout',
      qs.stringify({id:move_good_id,inventory_id:inventory_id}),
      { 'content-type': 'application/x-www-form-urlencoded' }
  ).then((res) => {
    if (res.status != 200){
      isLoading.value = false
      ElMessage.error("入库失败...")
    }else if (res.data.status != 100){
      isLoading.value = false
      ElMessage.error("入库失败...")
    }else if (res.data.data == 0){
      isLoading.value = false
      ElMessage.error("入库失败...")
    }else {
      isLoading.value = false
      ElMessage.success("入库成功！")
      let index = goods.findIndex((good) => { return good.id == move_good_id})
      if(goods[index].inventory_id == 0 && inventory_id != 0)
        tobe_resolve_num.decrease()
      else if(goods[index].inventory_id != 0 && inventory_id == 0)
        tobe_resolve_num.increase()
      goods[index].inventory_id = inventory_id
      search_goods.value = goods
    }
  }).catch((err) => {
    isLoading.value = false
    ElMessage.error("入库失败...")
    console.log(err)
  })
}

const check_reset = () =>{
  search_goods.value = goods
  search_goods_num.value = goods.length
}

const isLoading=ref(false)
onMounted(()=>{
  isLoading.value = true
  axios.post(
      `http://localhost:8080/goods`,
  ).then(res => {
    if(res.data.status !== 100){
      isLoading.value = false;
      ElMessage.error("该功能暂不可用，请稍后重新尝试");
    }else{
      isLoading.value = false;
      goods = res.data.data;
      search_goods.value = goods
      search_goods_num.value = goods.length
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
  ElMessageBox.alert(`确认删除id:${tobe_remove}货物吗`, '删除确认', {
    confirmButtonText: '确认',
    callback: (action) => {
      if (action == "confirm")
        removeGood()
    },
  })
}

const openInput = () => {
  ElMessageBox.prompt('请输入对应的入库目的仓库的id', '货物入库', {
    confirmButtonText: '确认',
    cancelButtonText: '取消',
    inputPattern:
        /^\d+$/,
    inputErrorMessage: '入库id只能为数字',
  })
      .then(({ value }) => {
        goodout(value)
      })
}
</script>

<template>
  <div class="out-view" v-loading="isLoading" element-loading-text="Loading..." :element-loading-spinner="svg" element-loading-background="rgba(122, 122, 122, 0.6)" style="height: auto">
    <div class="component-outline" style="margin-bottom: 30px">
      <el-form :model="check_parmas" label-width="auto" style="width: 100%;">
        <div style="display:flex;padding: 20px">
          <h3 style="margin-top: 8px;margin-left: 20px">查询：</h3>
          <el-form-item label="id" style="margin-left: 10px;">
            <el-input v-model="check_parmas.id"></el-input>
          </el-form-item>
          <el-form-item label="名称" style="margin-left: 10px;">
            <el-input v-model="check_parmas.name"></el-input>
          </el-form-item>
          <el-form-item label="价格" style="margin-left: 10px;">
            <el-input v-model="check_parmas.price"></el-input>
          </el-form-item>
        </div>
        <el-form-item>
          <el-button @click="dialogFormVisible = true" size="default" type="success" style="margin-left: 4%;">新增货物</el-button>
          <el-button @click="check_and()" style="margin-left: 60%">并集查询</el-button>
          <el-button @click="check_or()">或集查询</el-button>
          <el-button @click="check_reset()">重置</el-button>
        </el-form-item>
      </el-form>
    </div>

    <div class="component-outline">
      <el-table :data="search_goods.slice((currentPage-1)*pageSize,currentPage*pageSize)"
                height="500px"
                row-style="height:45px;"
                style="width: 100%;font-size: 16px">
        <el-table-column prop="inventory_id" label="货物所属仓库" width="180">
        </el-table-column>
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
        <el-table-column fixed="right" min-width="120">
          <template #default="scope">
            <el-button @click="move_good_id = scope.row.id;openInput()" type="primary" size="small">入库</el-button>
            <el-button @click="ElMessage.info('数据修改功能，暂未开发完成')" type="primary" size="small">修改</el-button>
            <el-button @click="tobe_remove = scope.row.id;openAlert()" type="danger" size="small">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
          v-model:current-page="currentPage"
          :page-size="pageSize"
          :size="'default'"
          :background="false"
          layout="prev, pager, next, jumper"
          :total="search_goods_num"
          style="align-content: end;justify-content: end;margin-top: 12px"
      />
    </div>

    <el-dialog v-model="dialogFormVisible" title="添加货物" width="500">
      <el-form :model="newgood">
        <el-form-item label="货物名称" :label-width="100">
          <el-input v-model="newgood.new_name" placeholder="请输入货物名称（必填）" autocomplete="off" size="large"/>
        </el-form-item>
        <el-form-item label="货物数量" :label-width="100">
          <el-input v-model="newgood.new_quantity" placeholder="请输入货物数量（必填）" autocomplete="off" size="large"/>
        </el-form-item>
        <el-form-item label="货物单价" :label-width="100">
          <el-input v-model="newgood.new_price" placeholder="请输入货物单价（必填）" autocomplete="off" size="large"/>
        </el-form-item>
        <el-form-item label="货物描述" :label-width="100">
          <el-input v-model="newgood.new_description" placeholder="请输入货物描述（非必填）" autocomplete="off" size="large"/>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="dialogFormVisible = false">Cancel</el-button>
          <el-button type="primary" @click="dialogFormVisible = false;addGood()">
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