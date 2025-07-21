package com.example.crud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.model.Product;
import com.example.crud.service.ProductService;

import lombok.Data;

import java.net.http.HttpClient;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@Data
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private  final ProductService productService;

    

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProduct());
        
        
    }


    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        Product product = productService.getProductById(id)
        .orElseThrow(
            () -> new RuntimeException("not found")
        );
        return ResponseEntity.ok(product);
    }

    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String categoryName) {
        return ResponseEntity.ok(productService.getProductByCategory(categoryName));
    }
    
    @GetMapping("/price/{maxPrice}")
    public ResponseEntity<List<Product>> getProductByMaxPrice(@PathVariable Double maxPrice){
        
        return ResponseEntity.ok(productService.getProductsByPriceLessThanEqual(maxPrice));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProduct(@RequestParam String name){
        return ResponseEntity.ok(
            productService.getProductsByProductName(name)
        );
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        Product newProduct = productService.saveProduct(product);

        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product){
        Product productToUpdate = productService.getProductById(id)
        .orElseThrow(
            () -> new RuntimeException("not found")
        );

        productToUpdate.setName((product.getName()));
        productToUpdate.setDescription((product.getDescription()));
        productToUpdate.setPrice((product.getPrice()));
        productToUpdate.setCategory((product.getCategory()));

        Product updatedProduct = productService.saveProduct(productToUpdate);

        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){

         Product productToDelete = productService.getProductById(id)
        .orElseThrow(
            () -> new RuntimeException("not found")
        );
        
        productService.deleteProduct(productToDelete);

        return ResponseEntity.ok("delete");
    }
    
}
