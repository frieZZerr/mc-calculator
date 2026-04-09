package com.mc_donalds.calculator.service;

import com.mc_donalds.calculator.repository.ProductRepository;
import com.mc_donalds.calculator.dto.product.*;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<ProductBase> getAllProducts() {
        return repository.findBy(ProductBase.class);
    }

    public List<ProductCalculator> getAllProductsCalculator() {
        return repository.findBy(ProductCalculator.class);
    }

    public Optional<ProductDetails> getProductDetailsById(Long id) {
        return repository.findById(id, ProductDetails.class);
    }
}
