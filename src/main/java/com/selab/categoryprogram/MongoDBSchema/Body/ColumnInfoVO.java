package com.selab.categoryprogram.MongoDBSchema.Body;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
public class ColumnInfoVO {
    @Field("number_of_columns")
    private String number_of_columns;
    @Field("columns")
    private List<columnsVO> columns;
}
