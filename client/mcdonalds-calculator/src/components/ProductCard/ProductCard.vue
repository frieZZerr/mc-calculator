<template>

  <div class="product-card">
    
    <div class="image-wrapper">
      <img :src="product.imageUrl" alt="" class="product-image" />
    </div>

    <h3 class="product-name">{{ product.name }}</h3>

    <div class="quantity-controls">
      <button class="qty-btn" @click="decrease">-</button>

      <input
        type="number"
        min="1"
        v-model.number="localQuantity"
      />

      <button class="qty-btn" @click="increase">+</button>
    </div>

    <button class="add-btn" @click="addProduct">
      Add
    </button>

  </div>

</template>

<script setup>

  import "./ProductCard.css"
  import { ref, watch } from "vue"

  const props = defineProps({
    product: Object,
    quantity: {
      type: Number,
      default: 1
    }
  })

  const emit = defineEmits(["add"])

  const localQuantity = ref(props.quantity)

  watch(() => props.quantity, val => {
    localQuantity.value = val
  })

  function increase() {
    localQuantity.value++
  }

  function decrease() {
    if (localQuantity.value > 1) localQuantity.value--
  }

  function addProduct() {
    emit("add", props.product, localQuantity.value)
  }

</script>
