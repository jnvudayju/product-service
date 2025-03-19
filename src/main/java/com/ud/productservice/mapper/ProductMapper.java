package com.ud.productservice.mapper;

import com.ud.productservice.dto.ProductRequest;
import com.ud.productservice.dto.ProductResponse;
import com.ud.productservice.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public Product mapProductDtoToProduct(ProductRequest productRequest){
        return Product
                .builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
    }

    public ProductResponse mapProductToProductResponse(Product product){
        return ProductResponse
                .builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    public List<ProductResponse> mapProductsToProductResponses(List<Product> products){
        return products.stream()
                .map(this::mapProductToProductResponse)
                .collect(Collectors.toList());
    }
}
