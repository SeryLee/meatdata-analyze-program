package com.selab.categoryprogram.MongoDBSchema.Header;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
public class ContentsVO {
    @Field("name")
    private String name;
    @Field("code")
    private String code;
    @Field("level_no")
    private String level_no;
    @Field("grid_no")
    private String grid_no;
}
