package com.selab.categoryprogram.MongoDBSchema.Annex;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;

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
}
