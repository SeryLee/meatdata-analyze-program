package com.selab.categoryprogram.MongoDBSchema.Body;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
public class AsciiElementVO {
    @Field("name")
    private String name;
    @Field("code")
    private String code;
    @Field("group1")
    private String group1;
    @Field("group2")
    private String group2;
    @Field("group3")
    private String group3;
    @Field("unit")
    private String unit;
    @Field("scale")
    private String scale;
    @Field("format")
    private String format;
    @Field("size")
    private String size;
    @Field("missing")
    private String missing;
}
