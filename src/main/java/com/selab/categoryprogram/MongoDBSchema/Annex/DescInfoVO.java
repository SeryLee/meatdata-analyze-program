package com.selab.categoryprogram.MongoDBSchema.Annex;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
public class DescInfoVO {
    @Field("product_origin")
    private String product_origin;
    @Field("product_system")
    private String product_system;
    @Field("product_lang")
    private String product_lang;
    @Field("meta_lang")
    private String meta_lang;
    @Field("meta_charset")
    private String meta_charset;
    @Field("meta_timezone")
    private String meta_timezone;
    @Field("test_start_date")
    private List<String> test_start_date;
    @Field("test_finish_date")
    private List<String> test_finish_date;
}
