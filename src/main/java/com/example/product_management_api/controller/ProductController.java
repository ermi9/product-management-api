package com.example.product_management_api.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import com.example.product_management_api.Entity.*;
import com.example.product_management_api.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("api/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;   

    @GetMapping
    public ResponseEntity<Page<Product>> getAllProducts(Pageable pageable){
        Page<Product> products=productRepository.findAll(pageable);
        return ResponseEntity.ok(products);
    }

    

    
}
