package com.selab.categoryprogram.MongoDBSchema.Body;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
public class BinaryInfoVO {
    @Field("header_info")
    private HeaderInfoVO headerInfoVO;
    @Field("body_info")
    private BodyInfoVO bodyInfoVO;
}
