<template>

  <div class="page">
    <div class="panel">

      <h1 class="title">Menu</h1>

      <div v-if="loading" class="state">Loading menu...</div>
      <div v-else-if="error" class="state error">{{ error }}</div>

      <div class="category-bar">
        <button v-for="cat in categories" :key="cat" class="cat-btn" @click="scrollToCategory(cat)">{{ cat }}</button>
      </div>

      <div v-if="loading" class="state">Loading menu...</div>
      <div v-else-if="error" class="state error">{{ error }}</div>
      <div v-else>
        <div class="menu-grouped">
          <div v-for="(items, category) in groupedProducts" :key="category" class="category-block" :ref="el => setSectionRef(el, category)">

            <h2 class="category-title">{{ category }}</h2>

            <div class="menu-grid">
              <div v-for="product in items" :key="product.id" class="product-card">

                <div class="image-wrapper">
                  <img :src="product.imageUrl" class="product-image" />
                </div>

                <div class="product-content">
                  <h3>{{ product.name }}</h3>
                </div>

              </div>
            </div>

          </div>
        </div>
      </div>

    </div>
  </div>

</template>

<script setup>

  import { ref, computed, onMounted } from "vue"
  import { fetchProductsBase } from "../../api/productApi"

  const products = ref([])
  const loading = ref(true)
  const error = ref(null)
  const sectionRefs = ref({})

  function setSectionRef(el, category) {
    if (!sectionRefs.value) {
      sectionRefs.value = {}
    }

    if (el) {
      sectionRefs.value[category] = el
    }
  }

  async function loadProducts() {
    try {
      loading.value = true
      error.value = null

      products.value = await fetchProductsBase()
    } catch (err) {
      console.error(err)
      error.value = "Failed to load menu"
    } finally {
      loading.value = false
    }
  }

  onMounted(loadProducts)

  const groupedProducts = computed(() => {
    const groups = {}

    products.value.forEach(product => {
      const category = product.category || "Other"

      if (!groups[category]) {
        groups[category] = []
      }

      groups[category].push(product)
    })

    return groups
  })

  const categories = computed(() => Object.keys(groupedProducts.value))

  function scrollToCategory(category) {
    const el = sectionRefs.value[category]

    if (el) {
      el.scrollIntoView({
        behavior: "smooth",
        block: "start"
      })
    }
  }

</script>

<style scoped>

  .page {
    min-height: 100dvh;
    display: flex;
    justify-content: center;
    padding: 40px 20px;
    background: #111827
  }

  .panel {
    width: 100%;
    max-width: 1200px;
    background: #1f2937;
    border-radius: 14px;
    padding: 20px;
    display: flex;
    flex-direction: column;
  }

  .title {
    text-align: center;
    margin-bottom: 10px;
    color: white;
  }

  .category-bar {
    position: sticky;
    top: 75px;
    z-index: 10;
    background: linear-gradient(270deg, #1f2937, 5%, #111827);
    border: 7px solid #111827;
    border-radius: 12px;
    box-shadow: 0 8px 20px rgba(0,0,0,0.4);
    padding: 10px;
    display: flex;
    gap: 15px;
    overflow-x: auto;
    margin: 10px;
  }

  .cat-btn {
    padding: 6px 12px;
    border-radius: 999px;
    border: none;
    background: #fbbf24;
    color: black;
    cursor: pointer;
    white-space: nowrap;
    transition: 0.2s;
    font-weight: 550;
  }

  .cat-btn:hover {
    background: #f59e0b;
    transform: scale(1.05);
  }

  .cat-btn.active {
    background: white;
  }

  .menu-grid {
    margin-top: 20px;
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(220px, 1fr));
    gap: 20px;
  }

  .product-card {
    width: 100%;
    min-width: 0;
    max-width: 100%;
    box-sizing: border-box;
    border-radius: 14px;
    padding: 5px;
    background: #111827;
    border: 2px solid #111827;
    color: #f9fafb;
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 10px;
    box-shadow: 0 8px 20px rgba(0,0,0,0.35);
    transition: transform 0.2s ease, box-shadow 0.2s ease;
    transition: all 0.2s ease;
    position: relative;
  }

  .product-card:hover {
    border-color: #fbbf24;
    cursor: pointer;
    transform: translateY(-4px) scale(1.02);
    box-shadow: 0 12px 24px rgba(0,0,0,0.45);
  }

  .product-card:hover .product-image {
    transform: scale(1.1);
  }

  .product-card:hover h3 {
    color: #fbbf24;
  }

  .product-image {
    transition: transform 0.2s ease;
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

  .menu-grouped {
    display: flex;
    flex-direction: column;
    gap: 15px;
  }

  .category-block {
    display: flex;
    flex-direction: column;
    scroll-margin-top: 140px;
  }

  .category-title {
    font-weight: 700;
    font-size: 1.4rem;
    color: #f9fafb;
    padding: 10px 14px;
    margin: 16px 10px;
    border-left: 5px solid #fbbf24;
    border-radius: 8px;
    background: linear-gradient(270deg, #1f2937 0%, #111827 50%);
    letter-spacing: 0.5px;
    transition: all 0.2s ease;
    position: relative;
  }

  .category-title::before {
    content: "";
    position: absolute;
    left: 0;
    top: 0;
    height: 100%;
    width: 5px;
    background: #fbbf24;
    box-shadow: 0 0 10px rgba(251, 191, 36, 0.6);
    border-radius: 4px;
  }

  .menu-grid {
    display: grid;
    grid-template-columns: repeat(4, minmax(0, 1fr));
    gap: 20px;
  }

</style>
