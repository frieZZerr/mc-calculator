<template>

  <div class="page">
    <div class="panel">

      <!-- Selected Products (Left) -->
      <div class="column selected-column">
        <div class="selected-header">
          <h2>Selected Products</h2>
          <button class="clear-btn" @click="clearAllSelectedProducts">
            Remove All
          </button>
        </div>

        <!-- Macro summary (always visible) -->
        <div class="macro-summary">
          <h3>Total Macros</h3>

          <div
            v-for="(macro, key) in macros"
            :key="key"
            class="macro-bar"
          >
            <span>
              {{ macro.label }}: {{ macro.value }} {{ macro.unit }}
            </span>

            <div class="bar">
              <div
                class="fill"
                :style="{
                  width: macro.percent + '%',
                  background: macro.color
                }"
              ></div>
            </div>
          </div>
        </div>

        <!-- Gap between list and macros -->
        <div class="macro-gap"></div>

        <!-- Selected products list -->
        <TransitionGroup name="list" tag="ul" class="selected-list" appear v-if="selectedProducts.length > 0">
          <li
            v-for="item in selectedProducts"
            :key="item.id"
            class="selected-item"
          >
            <img :src="item.imageUrl" class="selected-image" />
            <span class="name">{{ item.name }}</span>

            <div class="quantity-controls">
              <button @click="updateQuantity(item, -1)">-</button>
              <input type="number" min="1" v-model.number="selectedQuantities[item.id]" />
              <button @click="updateQuantity(item, 1)">+</button>
            </div>

            <button class="remove-btn" @click="updateQuantity(item, -selectedQuantities[item.id])">X</button>
          </li>
        </TransitionGroup>

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
            @add="(product, quantity) => updateQuantity(product, quantity)"
          />
        </div>
      </div>

    </div>
  </div>

</template>

<script setup>

  import "./CalculatorView.css"
  import ProductCard from "../../components/ProductCard/ProductCard.vue"
  import { useCalculator } from "./CalculatorView"

  const {
    selectedQuantities,
    loading,
    selectedCategory,
    categories,
    filteredProducts,
    selectedProducts,
    macros,
    updateQuantity,
    clearAllSelectedProducts
  } = useCalculator()

</script>
