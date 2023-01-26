package com.selab.categoryprogram.MongoDBSchema.Header;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
public class TableInfoVO {
    @Field("db_name")
    private String db_name;
    @Field("user_id")
    private String user_id;
    @Field("table_name")
    private String table_name;
}
