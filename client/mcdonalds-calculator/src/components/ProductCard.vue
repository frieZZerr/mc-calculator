<template>
  <div class="product-card">
    <img :src="product.imageUrl" alt="" class="product-image" />
    <h3>{{ product.name }}</h3>

    <div class="quantity-controls">
      <button @click="decrease">-</button>
      <input
        type="number"
        min="1"
        v-model.number="localQuantity"
      />
      <button @click="increase">+</button>
    </div>

    <button class="add-btn" @click="addProduct">
      Add
    </button>
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

const emit = defineEmits(["add"]) // emits only when Add clicked

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
  border-radius: 10px;
  padding: 16px;
  text-align: center;
  background: white;
  color: #1f2937;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 12px;
  box-shadow: 0 4px 8px rgba(0,0,0,0.1);
}

.product-image {
  width: 100%;
  max-height: 180px;
  object-fit: contain;
  border-radius: 8px;
  border: 1px solid #e5e7eb;
}

.quantity-controls {
  display: flex;
  gap: 8px;
  align-items: center;
}

.quantity-controls input {
  width: 50px;
  text-align: center;
  border: 1px solid #9ca3af;
  border-radius: 6px;
  padding: 4px;
}

.quantity-controls button {
  width: 32px;
  height: 32px;
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

.add-btn {
  width: 100%;
  background: #10b981;
  border: none;
  padding: 8px 0;
  border-radius: 6px;
  color: white;
  font-weight: bold;
  cursor: pointer;
}

.add-btn:hover {
  background: #059669;
}
</style>