package com.example.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.crud.model.Product;
import com.example.crud.repository.ProductRepository;

import lombok.Data;


@Data
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProduct(){

        return productRepository.findAll();
    }


    public Optional<Product> getProductById(Long id){
        return productRepository.findById(id);
    }

    public List<Product> getProductByCategory(String categoryName){
        return productRepository.findProductsByCategory(categoryName);
    }

    public List<Product>getProductsByPriceLessThanEqual(Double price){
        return productRepository.findProductsByPriceLessThanEqual(price);
    }

    public List<Product> getProductsByProductName(String name){
        return productRepository.findProductsByNameContainingIgnoreCase(name);
    }

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public void deleteProduct(Product product){
        productRepository.delete(product);
    }
}
