package com.selab.categoryprogram.MongoDBSchema.Body;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
public class IndexesVO {
    @Field("index_owner")
    private String index_owner;
    @Field("index_name")
    private String index_name;
    @Field("uniqueness")
    private String uniqueness;
    @Field("columns")
    private List<IndexColumnsVO> columns;
}
