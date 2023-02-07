package com.selab.categoryprogram.MongoDBSchema.Body;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
public class HeaderInfoVO {
    @Field("header_size")
    private String header_size;
    @Field("item")
    private List<String> item;
    @Field("item_size")
    private List<String> item_size;
}
