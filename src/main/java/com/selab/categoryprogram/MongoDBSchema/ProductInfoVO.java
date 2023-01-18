package com.selab.categoryprogram.MongoDBSchema;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Setter
public class ProductInfoVO {
    @Field("product_id")
    private String product_id;

    @Field("product_group")
    private List<String> product_group;

    @Field("product_name_kr")
    private String product_name_kr;
}
