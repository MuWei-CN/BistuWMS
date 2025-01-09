import {createApp} from "vue";
import App from './App.vue'
import { router } from '../../router/main.js'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import '../../css/main.css'

createApp(App).use(router).use(ElementPlus).mount('#app')