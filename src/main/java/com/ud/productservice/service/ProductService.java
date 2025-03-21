package com.ud.productservice.service;

import com.ud.productservice.dto.ProductRequest;
import com.ud.productservice.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    public void create(ProductRequest productRequest);

    public ProductResponse getProductById(String id);

    public List<ProductResponse> getAllProduct();
}
