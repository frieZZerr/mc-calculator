package com.mc_donalds.calculator.controller;

import com.mc_donalds.calculator.service.ProductService;
import com.mc_donalds.calculator.dto.product.*;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<ProductBase> getAllProducts() {
        return service.getAllProducts();
    }

    @GetMapping("/calculator")
    public List<ProductCalculator> getAllProductsCalculator() {
        return service.getAllProductsCalculator();
    }

    @GetMapping("/{id}")
    public Optional<ProductDetails> getProductDetailsById(@PathVariable Long id) {
        return service.getProductDetailsById(id);
    }
}
