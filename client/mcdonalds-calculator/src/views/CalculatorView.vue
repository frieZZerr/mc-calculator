<template>

  <div class="page">
    <div class="panel">

      <!-- Selected Products (Left) -->
      <div class="column selected-column">
        <h2>Selected Products</h2>

        <!-- Macro summary (always visible) -->
        <div class="macro-summary">
          <h3>Total Macros</h3>

          <div class="macro-bar">
            <span>Calories: {{ totalCalories }} kcal</span>
            <div class="bar">
              <div class="fill calories" :style="{ width: caloriesPercent + '%' }"></div>
            </div>
          </div>

          <div class="macro-bar">
            <span>Fat: {{ totalFat }} g</span>
            <div class="bar">
              <div class="fill fat" :style="{ width: fatPercent + '%' }"></div>
            </div>
          </div>

          <div class="macro-bar">
            <span>Carbs: {{ totalCarbs }} g</span>
            <div class="bar">
              <div class="fill carbs" :style="{ width: carbsPercent + '%' }"></div>
            </div>
          </div>

          <div class="macro-bar">
            <span>Protein: {{ totalProtein }} g</span>
            <div class="bar">
              <div class="fill protein" :style="{ width: proteinPercent + '%' }"></div>
            </div>
          </div>
        </div>

        <!-- Gap between list and macros -->
        <div class="macro-gap"></div>

        <!-- Selected products list -->
        <ul class="selected-list" v-if="selectedProducts.length > 0">
          <li
            v-for="item in selectedProducts"
            :key="item.id"
            class="selected-item"
          >
            <img :src="item.imageUrl" class="selected-image" />
            <span class="name">{{ item.name }}</span>

            <div class="quantity-controls">
              <button @click="decrease(item)">-</button>
              <input type="number" min="1" v-model.number="selectedQuantities[item.id]" />
              <button @click="increase(item)">+</button>
            </div>

            <button class="remove-btn" @click="removeProduct(item)">X</button>
          </li>
        </ul>

      </div>

      <!-- All Products (Right) -->
      <div class="column products-column">
        <h2>All Products</h2>

        <div class="products-header">
          <label for="category-select">Filter by category:</label>
          <select id="category-select" v-model="selectedCategory">
            <option value="all">All</option>
            <option v-for="cat in categories" :key="cat" :value="cat">
              {{ cat }}
            </option>
          </select>
        </div>

        <div v-if="loading" class="loading">Loading...</div>
        <div class="product-grid">
          <ProductCard
            v-for="product in filteredProducts"
            :key="product.id"
            :product="product"
            :quantity="1"
            @add="addProduct"
          />
        </div>
      </div>

    </div>
  </div>

</template>

<script setup>

  import { ref, computed, onMounted } from "vue"
  import ProductCard from "../components/ProductCard.vue"
  import { fetchProducts } from "../api/productApi"

  const products = ref([])
  const selectedQuantities = ref({})
  const loading = ref(false)

  // Sorting/filtering
  const selectedCategory = ref("all")

  // Get all unique categories
  const categories = computed(() => {
    const cats = new Set()
    products.value.forEach(p => {
      if (p.category) cats.add(p.category)
    })
    return Array.from(cats)
  })

  // Filtered products based on category
  const filteredProducts = computed(() => {
    if (selectedCategory.value === "all") return products.value
    return products.value.filter(p => p.category === selectedCategory.value)
  })

  const selectedProducts = computed(() =>
    products.value.filter(p => selectedQuantities.value[p.id] > 0)
  )

  // Add product with a given quantity
  function addProduct(product, quantity) {
    if (selectedQuantities.value[product.id]) {
      selectedQuantities.value[product.id] += quantity
    } else {
      selectedQuantities.value[product.id] = quantity
    }
  }

  // Remove product completely
  function removeProduct(product) {
    delete selectedQuantities.value[product.id]
  }

  // Quantity controls for selected list
  function increase(product) {
    selectedQuantities.value[product.id]++
  }

  function decrease(product) {
    if (selectedQuantities.value[product.id] > 1) {
      selectedQuantities.value[product.id]--
    } else {
      delete selectedQuantities.value[product.id]
    }
  }

  const totalCalories = computed(() =>
    selectedProducts.value.reduce(
      (sum, p) => sum + p.caloriesPortion * selectedQuantities.value[p.id],
      0
    )
  )

  const totalFat = computed(() =>
    selectedProducts.value.reduce(
      (sum, p) => sum + p.fatPortion * selectedQuantities.value[p.id],
      0
    )
  )

  const totalCarbs = computed(() =>
    selectedProducts.value.reduce(
      (sum, p) => sum + p.carbsPortion * selectedQuantities.value[p.id],
      0
    )
  )

  const totalProtein = computed(() =>
    selectedProducts.value.reduce(
      (sum, p) => sum + p.proteinPortion * selectedQuantities.value[p.id],
      0
    )
  )

  const maxCalories = 2500
  const maxFat = maxCalories * 0.3 / 9
  const maxCarbs = maxCalories * 0.55 / 4
  const maxProtein = maxCalories * 0.15 / 4

  const caloriesPercent = computed(() =>
    Math.min((totalCalories.value / maxCalories) * 100, 100)
  )
  const fatPercent = computed(() =>
    Math.min((totalFat.value / maxFat) * 100, 100)
  )
  const carbsPercent = computed(() =>
    Math.min((totalCarbs.value / maxCarbs) * 100, 100)
  )
  const proteinPercent = computed(() =>
    Math.min((totalProtein.value / maxProtein) * 100, 100)
  )

  async function loadProducts() {
    loading.value = true
    try {
      products.value = await fetchProducts()
    } finally {
      loading.value = false
    }
  }

  onMounted(loadProducts)

</script>

<style scoped>

.page {
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: flex-start;
  background: #111827;
  padding: 30px;
}

.panel {
  width: 1200px;
  display: flex;
  gap: 20px;
}

.column {
  flex: 1;
  border-radius: 12px;
  padding: 20px;
  overflow-y: auto;
}

.selected-column {
  background: #1f2937;
  color: white;
}

.products-column {
  background: #1f2937;
  color: white;
}

h2 {
  margin-bottom: 20px;
  font-weight: 600;
  font-size: 22px;
}

.product-grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 16px;
}

.products-header {
  display: flex;
  align-items: center;
  gap: 10px;
  margin-bottom: 16px;
}

.products-header label {
  font-weight: 500;
  color: #f3f4f6;
}

.products-header select {
  padding: 4px 8px;
  border-radius: 6px;
  border: 1px solid #9ca3af;
  background: #111827;
  color: #f3f4f6;
}

.product-card {
  background: #111827; /* dark card */
  color: #f3f4f6;
  border: 1px solid #374151; /* subtle border */
  box-shadow: 0 4px 8px rgba(0,0,0,0.2);
}

.product-card .add-btn {
  background: #10b981;
  color: white;
}

.product-card .add-btn:hover {
  background: #059669;
}

.product-card .quantity-controls button {
  background: #2563eb;
  color: white;
}

.product-card .quantity-controls button:hover {
  background: #1d4ed8;
}

.product-card .quantity-controls input {
  background: #1f2937;
  color: white;
  border: 1px solid #4b5563;
}

/* Selected list */
.selected-list {
  list-style: none;
  padding: 0;
  margin: 0;
}

.selected-item {
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 8px 12px;
  border-radius: 6px;
  background: #111827;
  margin-bottom: 8px;
}

.selected-image {
  width: 50px;
  height: 50px;
  object-fit: contain;
  border-radius: 6px;
}

.name {
  flex: 1;
  font-weight: 500;
  color: white;
}

.quantity-controls {
  display: flex;
  align-items: center;
  gap: 6px;
}

.quantity-controls input {
  width: 50px;
  text-align: center;
  border-radius: 6px;
  border: 1px solid #9ca3af;
  padding: 4px;
}

.quantity-controls button {
  width: 28px;
  height: 28px;
  border: none;
  border-radius: 6px;
  font-weight: bold;
  font-size: 16px;
  cursor: pointer;
  background: #2563eb;
  color: white;
}

.quantity-controls button:hover {
  background: #1d4ed8;
}

.remove-btn {
  background: #ef4444;
  border: none;
  color: white;
  font-weight: bold;
  padding: 4px 8px;
  border-radius: 4px;
  cursor: pointer;
}

.remove-btn:hover {
  background: #dc2626;
}

.empty {
  opacity: 0.7;
}

.macro-summary {
  padding: 16px;
  border-radius: 12px;
  background: #111827;
  color: #f3f4f6;
  box-shadow: 0 4px 12px rgba(0,0,0,0.3);
}

.macro-summary h3 {
  font-size: 20px;
  font-weight: 600;
  color: #f3f4f6;
}

.macro-bar {
  margin-bottom: 10px;
}

.macro-bar span {
  display: block;
  margin-bottom: 4px;
  font-weight: 500;
}

.macro-gap {
  height: 20px;
}

.bar {
  width: 100%;
  height: 10px;
  background: #374151;
  border-radius: 6px;
  overflow: hidden;
}

.fill {
  height: 100%;
  border-radius: 6px;
  transition: width 0.35s ease-in-out;
}

.fill.calories { background: #f59e0b; }  /* amber */
.fill.fat { background: #ef4444; }       /* red */
.fill.carbs { background: #3b82f6; }     /* blue */
.fill.protein { background: #10b981; }   /* green */

</style>