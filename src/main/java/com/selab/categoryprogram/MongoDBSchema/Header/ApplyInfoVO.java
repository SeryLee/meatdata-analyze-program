package com.selab.categoryprogram.MongoDBSchema.Header;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
public class ApplyInfoVO {
    @Field("operation_start_date")
    private String operation_start_date;
    @Field("operation_finish_date")
    private String operation_finish_date;
}
