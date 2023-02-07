package com.selab.categoryprogram.MongoDBSchema.Body;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
public class BinaryRecordInfoVO {
    @Field("record_size")
    private String record_size;
    @Field("binary_elements")
    List<BinaryElementVO> binary_elements;
}
