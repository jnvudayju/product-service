package com.ud.productservice.entity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(value = "product")
@Builder
@AllArgsConstructor
@NoArgsConstructor@Getter
@Setter
public class Product {

    @Id
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
}
