package com.selab.categoryprogram.MongoDBSchema.Header;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
public class PatternVO {
    @Field("p_num")
    private String p_num;
    @Field("name")
    private String name;
    @Field("value")
    private List<String> value;
}
