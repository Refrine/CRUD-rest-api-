package com.example.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crud.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findProductsByCategory(String name);


    List<Product> findProductsByPriceLessThanEqual(Double maxprice );

    List<Product> findProductsByNameContainingIgnoreCase(String name);

}
