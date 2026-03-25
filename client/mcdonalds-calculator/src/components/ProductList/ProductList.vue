<template>

  <div class="product-list">

    <div
      v-for="(products, category) in groupedProducts"
      :key="category"
      class="category-section"
    >
      <!-- Category Header -->
      <div class="category-header">
        <h2>{{ category }}</h2>
        <span class="count">{{ products.length }} items</span>
      </div>

      <!-- Grid -->
      <div class="product-grid">
        <ProductCard
          v-for="product in products"
          :key="product.id"
          :product="product"
          @add="$emit('add-product', $event)"
        />
      </div>
    </div>

  </div>

</template>

<script>

  import "./ProductList.css"
  import ProductCard from "../ProductCard/ProductCard.vue"

  export default {
    components: { ProductCard },
    props: { products: Array },

    computed: {
      groupedProducts() {
        return this.products.reduce((groups, product) => {
          const cat = product.category || "Uncategorized"
          if (!groups[cat]) groups[cat] = []
          groups[cat].push(product)
          return groups
        }, {})
      }
    }
  }

</script>
