package com.ud.productservice.controller;

import com.ud.productservice.dto.ProductRequest;
import com.ud.productservice.dto.ProductResponse;
import com.ud.productservice.entity.Product;
import com.ud.productservice.mapper.ProductMapper;
import com.ud.productservice.service.ProductServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductServiceImpl productServiceImpl;

    public ProductController(ProductServiceImpl productServiceImpl, ProductMapper productMapper){
        this.productServiceImpl = productServiceImpl;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest){
        productServiceImpl.create(productRequest);
    }

    @GetMapping("/get-product-by-id")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getProductById(@RequestParam String id){
        return productServiceImpl.getProductById(id);
    }

}
