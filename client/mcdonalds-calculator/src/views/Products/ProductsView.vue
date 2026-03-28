<template>

  <div>
    <h1>Products</h1>
    <ProductList
      :products="products"
      @add-product="addProduct"
    />
  </div>

</template>

<script>

import ProductList from "../../components/ProductList/ProductList.vue"
import { fetchProductsDetails } from "../../api/productApi"

export default {

  components: { ProductList },

  data() {
    return {
      products: []
    }
  },

  async mounted() {
    this.products = await fetchProductsDetails()
  },

  methods: {
    addProduct(product) {
      console.log("Add to calculator:", product)
    }
  }

}

</script>
