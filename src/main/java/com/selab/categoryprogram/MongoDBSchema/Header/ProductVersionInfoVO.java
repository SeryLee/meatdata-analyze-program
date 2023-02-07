package com.selab.categoryprogram.MongoDBSchema.Header;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
public class ProductVersionInfoVO {
    @Field("product_version")
    private String product_version;
    @Field("product_version_start_date")
    private String product_version_start_date;
    @Field("product_version_end_date")
    private String product_version_end_date;
}
