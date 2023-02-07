package com.selab.categoryprogram.MongoDBSchema.Body;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
public class AsciiRecordInfoVO {
    @Field("ascii_elements")
    private List<AsciiElementVO> ascii_elements;
}
