import { createWebHistory, createRouter } from 'vue-router'
import HomeView from "../view/main/HomeView.vue";
import MessageView from "../view/main/MessageView.vue";
import GoodsView from "../view/main/GoodsView.vue";
import InventoryView from "../view/main/InventoryView.vue";
import InventoryGoodsView from "../view/main/InventoryGoodsView.vue";


const routes = [
    {path:'/home',component: HomeView},
    {path:'/message',component: MessageView},
    {path:'/goods',component: GoodsView},
    {path:'/inventory',component: InventoryView},
    {path:'/inventory/:id',component: InventoryGoodsView}
]

export const router = createRouter({
    history: createWebHistory(),
    routes,
})