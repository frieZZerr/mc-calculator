package com.mc_donalds.calculator.dto.product;

public interface ProductCalculator {
    Long getId();

    String getName();
    String getCategory();
    String getImageUrl();

    int getCaloriesPer100g();
    int getCaloriesPortion();

    int getFatPer100g();
    int getFatPortion();

    int getCarbsPer100g();
    int getCarbsPortion();

    int getProteinPer100g();
    int getProteinPortion();
}
