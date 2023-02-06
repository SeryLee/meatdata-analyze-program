package com.selab.categoryprogram.MongoDBSchema.Header;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
public class ProductLevelVO {
    @Field("main")
    private String main;
    @Field("sub")
    private String sub;
}
