import { ref, computed, onMounted } from "vue"
import { fetchProductsCalculator } from "../../api/productApi"
import { MACROS_CONFIG } from "../../config/macros"

export function useCalculator() {

  const products = ref([])
  const selectedQuantities = ref({})
  const selectedOrder = ref([])
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
    selectedOrder.value.map(id =>
      products.value.find(p => p.id === id)
    )
  )

  function updateQuantity(product, change) {
    const id = product.id
    const current = selectedQuantities.value[id] || 0
    const newQuantity = current + change

    const index = selectedOrder.value.indexOf(id)

    if (newQuantity > 0) {
      selectedQuantities.value[id] = newQuantity

      if (index === -1)
        selectedOrder.value.push(id)
    } else {
      delete selectedQuantities.value[id]

      if (index !== -1)
        selectedOrder.value.splice(index, 1)
    }
  }

  function clearAllSelectedProducts() {
    const ids = [...selectedOrder.value]

    ids.forEach((id, index) => {
      setTimeout(() => {
        delete selectedQuantities.value[id]

        const orderIndex = selectedOrder.value.indexOf(id)
        if (orderIndex !== -1) {
          selectedOrder.value.splice(orderIndex, 1)
        }
      }, index * 25) // slightly smoother stagger
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
      products.value = await fetchProductsCalculator()
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
