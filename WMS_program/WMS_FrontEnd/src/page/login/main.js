import { createApp } from "vue";
import App from './App.vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import { router } from "../../router/login.js"
import '../../css/loginPage.css'

createApp(App).use(router).use(ElementPlus).mount('#app')