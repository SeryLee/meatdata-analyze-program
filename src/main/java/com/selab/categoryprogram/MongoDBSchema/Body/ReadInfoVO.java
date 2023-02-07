package com.selab.categoryprogram.MongoDBSchema.Body;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
public class ReadInfoVO {
    @Field("binary_info")
    private BinaryInfoVO binaryInfoVO;
    @Field("ascii_info")
    private AsciiInfoVO asciiInfoVO;

}
