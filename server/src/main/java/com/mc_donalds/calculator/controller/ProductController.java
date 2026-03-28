package com.mc_donalds.calculator.controller;

import com.mc_donalds.calculator.service.ProductService;
import com.mc_donalds.calculator.dto.product.ProductCalculator;
import com.mc_donalds.calculator.dto.product.ProductDetails;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/calculator")
    public List<ProductCalculator> getAllProductsCalculator() {
        return service.getAllProductsCalculator();
    }

    @GetMapping("/details")
    public List<ProductDetails> getAllProductsDetails() {
        return service.getAllProductsDetails();
    }

    @GetMapping("/details/{id}")
    public ProductDetails getProductDetailsById(@PathVariable Long id) {
        return service.getProductDetailsById(id)
                      .orElseThrow(() -> new RuntimeException("Product not found with id " + id));
    }
}
