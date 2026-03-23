<template>
  <div id="app">
    <div v-for="(products, category) in groupedProducts" :key="category" class="category-section">
      <h2 class="category-title">{{ category }}</h2>
      <div class="product-grid">
        <ProductCard
          v-for="product in products"
          :key="product.id"
          :product="product"
          @add-product="$emit('add-product', $event)"
        />
      </div>
    </div>
  </div>
</template>

<script>
import ProductCard from "./ProductCard.vue";

export default {
  components: { ProductCard },
  props: { products: Array },
  computed: {
    groupedProducts() {
      return this.products.reduce((groups, product) => {
        const cat = product.category || "Uncategorized";
        if (!groups[cat]) groups[cat] = [];
        groups[cat].push(product);
        return groups;
      }, {});
    }
  }
};
</script>
