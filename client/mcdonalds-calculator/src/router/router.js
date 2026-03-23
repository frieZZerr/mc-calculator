import { createRouter, createWebHistory } from "vue-router"

import HomeView from "../views/Home/HomeView.vue"
import ProductsView from "../views/ProductsView.vue"
import CalculatorView from "../views/CalculatorView.vue"

const routes = [
  { path: "/", component: HomeView },
  { path: "/products", component: ProductsView },
  { path: "/calculator", component: CalculatorView }
]

export default createRouter({
  history: createWebHistory(),
  routes
})
