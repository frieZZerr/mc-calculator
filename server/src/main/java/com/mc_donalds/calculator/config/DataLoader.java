package com.mc_donalds.calculator.config;

import com.mc_donalds.calculator.model.Product;
import com.mc_donalds.calculator.repository.ProductRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader implements CommandLineRunner {

    private final ProductRepository repo;

    public DataLoader(ProductRepository repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) {

        repo.save(new Product("Cheeseburger", "Burger", "/images/cheeseburger.png", 254, 306, 11, 13, 25, 30, 13, 16));
        repo.save(new Product("BigMac", "Burger", "/images/big-mac.png", 231, 544, 12, 29, 18, 42, 12, 27));
        repo.save(new Product("McRoyal", "Burger", "/images/mcroyal.png", 256, 547, 13, 29, 18, 38, 15, 33));
        repo.save(new Product("WieśMac", "Burger", "/images/wies-mac.png", 261, 607, 16, 38, 15, 35, 13, 31));
        repo.save(new Product("McChicken", "Burger", "/images/mcchicken.png", 229, 433, 9, 17, 25, 48, 11, 21));
        repo.save(new Product("Chikker", "Burger", "/images/chikker.png", 272, 407, 12, 18, 30, 45, 10, 14));
        repo.save(new Product("Red Chikker", "Burger", "/images/red-chikker.png", 225, 342, 6, 9, 32, 49, 9, 14));
        repo.save(new Product("McDouble", "Burger", "/images/mcdouble.png", 252, 408, 13, 21, 19, 30, 15, 24));
        repo.save(new Product("4 McNuggets", "Chicken", "/images/4_mcnuggets.png", 241, 175, 11, 8, 19, 14, 15, 11));
        repo.save(new Product("6 McNuggets", "Chicken", "/images/6_mcnuggets.png", 241, 262, 11, 12, 19, 21, 15, 16));
        repo.save(new Product("9 McNuggets", "Chicken", "/images/9_mcnuggets.png", 241, 393, 11, 19, 19, 31, 15, 25));
        repo.save(new Product("20 McNuggets", "Chicken", "/images/20_mcnuggets.png", 241, 873, 11, 42, 19, 68, 15, 55));
        repo.save(new Product("Fries small", "Fries", "/images/frytki-male.png", 287, 229, 14, 11, 36, 29, 4, 4));
        repo.save(new Product("Fries medium", "Fries", "/images/frytki-male.png", 287, 327, 14, 15, 36, 41, 4, 4));
        repo.save(new Product("Fries large", "Fries", "/images/frytki-male.png", 287, 430, 14, 20, 36, 54, 4, 4));
        repo.save(new Product("Coca-Cola 0,25l", "Drinks", "/images/coca-cola-0,25.png", 42, 109, 0, 0, 11, 26, 0, 0));
        repo.save(new Product("Coca-Cola 0,4l", "Drinks", "/images/coca-cola-0,4.png", 42, 174, 0, 0, 11, 44, 0, 0));
        repo.save(new Product("Coca-Cola 0,5l", "Drinks", "/images/coca-cola-0,5.png", 42, 218, 0, 0, 11, 55, 0, 0));
    }
}
