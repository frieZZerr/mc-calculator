package com.mc_donalds.calculator.dto.product;

public interface ProductDetails {
    Long getId();

    String getName();
    String getCategory();
    String getImageUrl();

    String getDescription();
    String getIngredients();
    String getAllergens();
}
