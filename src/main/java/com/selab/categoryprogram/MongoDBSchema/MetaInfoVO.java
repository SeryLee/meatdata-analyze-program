package com.selab.categoryprogram.MongoDBSchema;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
public class MetaInfoVO {
    @Field("product_info")
    private ProductInfoVO productInfoVO;
}
