const API_URL = "http://localhost:8080/products"

export async function fetchProductsCalculator() {

    const response = await fetch(API_URL+"/calculator")

    if (!response.ok) {
        throw new Error("Failed to fetch products")
    }

    return await response.json()
}

export async function fetchProductsDetails() {

    const response = await fetch(API_URL+"/details")

    if (!response.ok) {
        throw new Error("Failed to fetch products details")
    }

    return await response.json()
}
