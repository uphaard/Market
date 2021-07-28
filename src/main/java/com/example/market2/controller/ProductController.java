package com.example.market2.controller;

import com.example.market2.entity.Product;
import com.example.market2.repository.ProductRepository;
import com.example.market2.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductController {
    private ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/list/product")
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }

}
