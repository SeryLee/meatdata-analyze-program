package com.selab.categoryprogram.MongoDBSchema.Body;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
public class FormatInfoVO {
    @Field("file_extension")
    private String file_extension;
    @Field("compress_method")
    private String compress_method;
    @Field("file_format")
    private String file_format;
}
