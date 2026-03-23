package com.mc_donalds.calculator.controller;

import com.mc_donalds.calculator.model.Product;
import com.mc_donalds.calculator.service.ProductService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return service.getProductById(id)
                      .orElseThrow(() -> new RuntimeException("Product not found with id " + id));
    }
}
