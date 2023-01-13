package com.selab.categoryprogram.Schema;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
public class ProductInfoVO {
    @Field("product_id")
    private String product_id;
}
