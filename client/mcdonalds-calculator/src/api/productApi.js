const API_URL = "http://localhost:8080/products"

export async function fetchProducts() {

    const response = await fetch(API_URL)

    if (!response.ok) {
        throw new Error("Failed to fetch products")
    }

    return await response.json()
}
