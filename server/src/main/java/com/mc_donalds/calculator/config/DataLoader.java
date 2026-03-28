package com.mc_donalds.calculator.config;

import com.mc_donalds.calculator.model.Product;
import com.mc_donalds.calculator.repository.ProductRepository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.util.List;

@Configuration
public class DataLoader implements CommandLineRunner {

    private final ProductRepository repository;
    private final ObjectMapper objectMapper;

    public DataLoader(ProductRepository repo, ObjectMapper mapper) {
        this.repository = repo;
        this.objectMapper = mapper;
    }

    @Override
    public void run(String... args) throws Exception {

        if (repository.count() > 0) {
            return;
        }

        InputStream inputStream = new ClassPathResource("database/products_data.json").getInputStream();

        List<Product> products = objectMapper.readValue(
            inputStream,
            new TypeReference<List<Product>>() {}
        );

        repository.saveAll(products);
    }
}
