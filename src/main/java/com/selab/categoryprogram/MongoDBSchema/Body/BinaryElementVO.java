package com.selab.categoryprogram.MongoDBSchema.Body;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
public class BinaryElementVO {
    @Field("order")
    private String order;
    @Field("name")
    private String name;
    @Field("code")
    private String code;
    @Field("unit")
    private String unit;
    @Field("scale")
    private String scale;
    @Field("size")
    private String size;
    @Field("missing")
    private String missing;
}
