package com.example.market2.service;

import com.example.market2.entity.Product;
import com.example.market2.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public List<Product> getAllProducts(){
        List<Product> allProducts= productRepository.findAll();
        return allProducts;
    }
}
