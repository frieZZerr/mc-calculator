const API_URL = "http://localhost:8080/products"

export async function fetchProductsBase() {

    const response = await fetch(API_URL)

    if (!response.ok) {
        throw new Error("Failed to fetch products details")
    }

    return await response.json()
}

export async function fetchProductsCalculator() {

    const response = await fetch(API_URL+"/calculator")

    if (!response.ok) {
        throw new Error("Failed to fetch products")
    }

    return await response.json()
}

export async function fetchProductDetailsById(id) {

    const response = await fetch(API_URL+"/{id}")

    if (!response.ok) {
        throw new Error("Failed to fetch products details")
    }

    return await response.json()
}
