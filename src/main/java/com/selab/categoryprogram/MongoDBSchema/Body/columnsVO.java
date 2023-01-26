package com.selab.categoryprogram.MongoDBSchema.Body;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
public class columnsVO {
    @Field("column_id")
    private String column_id;
    @Field("column_name")
    private String column_name;
    @Field("key")
    private String key;
    @Field("date_type")
    private String date_type;
    @Field("nullable")
    private String nullable;
    @Field("data_default")
    private String data_default;
    @Field("comments")
    private String comments;
}
