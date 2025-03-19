package com.ud.productservice.service;

import com.ud.productservice.dto.ProductRequest;
import com.ud.productservice.dto.ProductResponse;
import com.ud.productservice.entity.Product;
import com.ud.productservice.mapper.ProductMapper;
import com.ud.productservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper){
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public void create(ProductRequest productdto) {
        Product product = productMapper.mapProductDtoToProduct(productdto);
        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
    }

    @Override
    public ProductResponse getProductById(String id) {
        Optional<Product> optionalProduct =  productRepository.findById(id);

        if(optionalProduct.isEmpty()){
            throw new RuntimeException("Product not found with given productId");
        }

        return productMapper.mapProductToProductResponse(optionalProduct.get());

    }


}
