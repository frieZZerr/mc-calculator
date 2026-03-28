package com.mc_donalds.calculator.model;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;
    private String imageUrl;

    private int caloriesPer100g;
    private int caloriesPortion;
    private int fatPer100g;
    private int fatPortion;
    private int carbsPer100g;
    private int carbsPortion;
    private int proteinPer100g;
    private int proteinPortion;

    private String description;
    private String ingredients;
    private String allergens;

    public Product() {}

    public Product(String name, String category, String imageUrl,
                    int calories100g, int calories, int fat100g, int fat,
                    int carbs100g, int carbs, int protein100g, int protein,
                    String description, String ingredients, String allergens) {

        this.name = name;
        this.category = category;
        this.imageUrl = imageUrl;
        this.caloriesPer100g = calories100g;
        this.caloriesPortion = calories;
        this.fatPer100g = fat100g;
        this.fatPortion = fat;
        this.carbsPer100g = carbs100g;
        this.carbsPortion = carbs;
        this.proteinPer100g = protein100g;
        this.proteinPortion = protein;
        this.description = description;
        this.ingredients = ingredients;
        this.allergens = allergens;
    }

    public Long getId() { return id; }

    public String getName()     { return name;     }
    public String getCategory() { return category; }
    public String getImageUrl() { return imageUrl; }

    public int getCaloriesPer100g() { return caloriesPer100g; }
    public int getCaloriesPortion() { return caloriesPortion; }

    public int getFatPer100g() { return fatPer100g; }
    public int getFatPortion() { return fatPortion; }

    public int getCarbsPer100g() { return carbsPer100g; }
    public int getCarbsPortion() { return carbsPortion; }

    public int getProteinPer100g() { return proteinPer100g; }
    public int getProteinPortion() { return proteinPortion; }

    public String getDescription() { return description; }
    public String getIngredients() { return ingredients; }
    public String getAllergens()   { return allergens;   }
}
