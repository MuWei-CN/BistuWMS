import { createMemoryHistory, createRouter } from 'vue-router'

import LoginView from "../view/login/LoginView.vue";
import RegisterView from "../view/login/RegisterView.vue";

const routes = [
    { path: '/login', component: LoginView },
    { path: '/register', component: RegisterView },
]

export const router = createRouter({
    history: createMemoryHistory(),
    routes,
})