package com.selab.categoryprogram.MongoDBSchema.Header;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
public class MetaInfoVO {
    @Field("product_info")
    private ProductInfoVO productInfoVO;
    @Field("apply_info")
    private ApplyInfoVO applyInfoVO;
}
