package com.ud.productservice.dto;

import lombok.*;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductResponse {
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
}
