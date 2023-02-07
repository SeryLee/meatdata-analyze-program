package com.selab.categoryprogram.MongoDBSchema.Body;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
public class AsciiInfoVO {
    @Field("common_info")
    private CommonInfoVO commonInfoVO;
    @Field("ascii_record_info")
    private AsciiRecordInfoVO asciiRecordInfoVO;
}

