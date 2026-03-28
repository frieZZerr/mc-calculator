package com.mc_donalds.calculator.repository;

import com.mc_donalds.calculator.model.Product;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    <T> List<T> findBy(Class<T> type);
    <T> Optional<T> findById(Long id, Class<T> type);
}
