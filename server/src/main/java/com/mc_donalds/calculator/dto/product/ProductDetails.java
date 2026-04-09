package com.mc_donalds.calculator.dto.product;

public interface ProductDetails extends ProductBase {
    String getDescription();
    String getIngredients();
    String getAllergens();
}
