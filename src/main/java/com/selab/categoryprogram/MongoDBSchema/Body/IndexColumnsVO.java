package com.selab.categoryprogram.MongoDBSchema.Body;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
public class IndexColumnsVO {
    @Field("name")
    private String name;
    @Field("position")
    private String position;
    @Field("order")
    private String order;
}
