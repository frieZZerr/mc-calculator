import { ref, computed, onMounted } from "vue"
import { fetchProducts } from "../../api/productApi"
import { MACROS_CONFIG } from "../../config/macros"

export function useCalculator() {

  const products = ref([])
  const selectedQuantities = ref({})
  const loading = ref(false)

  const selectedCategory = ref("all")

  const categories = computed(() => {
    const cats = new Set()
    products.value.forEach(p => {
      if (p.category) cats.add(p.category)
    })
    return Array.from(cats)
  })

  const filteredProducts = computed(() => {
    if (selectedCategory.value === "all")
      return products.value

    return products.value.filter(p => p.category === selectedCategory.value)
  })

  const selectedProducts = computed(() =>
    products.value.filter(p => selectedQuantities.value[p.id] > 0)
  )

  function updateQuantity(product, change) {
    const id = product.id
    const current = selectedQuantities.value[id] || 0

    const newQuantity = current + change

    if (newQuantity > 0)
      selectedQuantities.value[id] = newQuantity
    else
      delete selectedQuantities.value[id]
  }

  function clearAllSelectedProducts() {
    const items = Object.keys(selectedQuantities.value)

    items.forEach((id, index) => {
      setTimeout(() => {
        delete selectedQuantities.value[id]
      }, index * 25) // stagger animation
    })
  }

  const macros = computed(() => {
    const result = {}

    for (const key in MACROS_CONFIG) {
      const config = MACROS_CONFIG[key]

      const value = selectedProducts.value.reduce( (sum, p) => sum + config.getValue(p) * selectedQuantities.value[p.id], 0 )

      result[key] = {
        value,
        percent: Math.min((value / config.max) * 100, 100),
        ...config
      }
    }

    return result
  })

  async function loadProducts() {
    loading.value = true
    try {
      products.value = await fetchProducts()
    } finally {
      loading.value = false
    }
  }

  onMounted(loadProducts)

  return {
    products,
    selectedQuantities,
    loading,
    selectedCategory,
    categories,
    filteredProducts,
    selectedProducts,
    macros,
    updateQuantity,
    clearAllSelectedProducts
  }
}
