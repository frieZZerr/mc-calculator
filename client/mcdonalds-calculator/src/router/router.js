import { createRouter, createWebHistory } from "vue-router"

import HomeView from "../views/Home/HomeView.vue"
import MenuView from "../views/Menu/MenuView.vue"
import CalculatorView from "../views/Calculator/CalculatorView.vue"

const routes = [
  { path: "/", component: HomeView },
  { path: "/menu", component: MenuView },
  { path: "/calculator", component: CalculatorView }
]

export default createRouter({
  history: createWebHistory(),
  routes,
  scrollBehavior(to, from, savedPosition) {
    return { left: 0, top: 0 };
  },
})
