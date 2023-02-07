package com.selab.categoryprogram.MongoDBSchema.Body;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
public class BodyVO {
    /*file*/
    @Field("format_info")
    private FormatInfoVO formatInfoVO;
    @Field("read_info")
    private ReadInfoVO readInfoVO;
    /*db*/
    @Field("column_info")
    private ColumnInfoVO columnInfoVO;
    @Field("index_info")
    private IndexInfoVO indexInfoVO;
    @Field("ddl_script")
    private String ddl_script;
}
