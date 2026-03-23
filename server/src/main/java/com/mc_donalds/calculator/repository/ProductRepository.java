package com.mc_donalds.calculator.repository;

import com.mc_donalds.calculator.model.Product;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
