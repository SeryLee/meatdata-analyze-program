package com.selab.categoryprogram.MongoDBSchema.Body;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
public class CommonInfoVO {
    @Field("header_size")
    private String header_size;
    @Field("record_type")
    private String record_type;
    @Field("delemeter")
    private String delemeter;
    @Field("terminator")
    private String terminator;
    @Field("record_size")
    private String record_size;
}
