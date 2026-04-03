<template>

  <div class="product-card">

    <div class="image-wrapper">
      <img :src="product.imageUrl" alt="" class="product-image" />
    </div>

    <h3 class="product-name">{{ product.name }}</h3>

    <div class="quantity-controls">
      <button class="qty-btn" @click="decrease">-</button>

      <input type="number" min="1" v-model.number="localQuantity" />

      <button class="qty-btn" @click="increase">+</button>
    </div>

    <button class="add-btn" @click="addProduct">Add</button>

  </div>

</template>

<script setup>

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

<style scoped>

  .product-card {
    width: 100%;
    min-width: 0;
    max-width: 100%;
    box-sizing: border-box;
    border-radius: 14px;
    padding: 7px;
    background: #111827;
    color: #f9fafb;
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 10px;
    box-shadow: 0 8px 20px rgba(0,0,0,0.35);
    transition: transform 0.2s ease, box-shadow 0.2s ease;
  }

  .product-card:hover {
    transform: translateY(-4px);
    box-shadow: 0 12px 24px rgba(0,0,0,0.45);
  }

  .image-wrapper {
    width: 100%;
    height: 160px;
    background: linear-gradient(180deg, #1f2937, #111827);
    border-radius: 10px;
    display: flex;
    align-items: center;
    justify-content: center;
  }

  .product-image {
    max-height: 100%;
    max-width: 100%;
    object-fit: contain;
  }

  .product-name {
    font-size: 16px;
    font-weight: 600;
    text-align: center;
  }

  .quantity-controls {
    display: flex;
    align-items: center;
    gap: 10px;
  }

  .quantity-controls input {
    width: 55px;
    text-align: center;
    border-radius: 8px;
    border: 1px solid #374151;
    background: #1f2937;
    color: #f9fafb;
    padding: 6px;
  }

  /* Buttons */
  .qty-btn {
    width: 34px;
    height: 34px;
    border: none;
    border-radius: 8px;
    background: #2563eb;
    color: white;
    font-size: 18px;
    cursor: pointer;
    transition: all 0.15s ease;
  }

  .qty-btn:hover {
    background: #1d4ed8;
    transform: scale(1.05);
  }

  .add-btn {
    width: 100%;
    background: linear-gradient(135deg, #fbbf24, #f59e0b);
    color: #111827;
    border: none;
    padding: 10px 0;
    border-radius: 10px;
    font-weight: 700;
    cursor: pointer;
    transition: all 0.2s ease;
  }

  .add-btn:hover {
    transform: scale(1.03);
    background: linear-gradient(135deg, #f59e0b, #d97706);
  }

</style>
