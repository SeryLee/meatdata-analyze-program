package com.selab.categoryprogram.MongoDBSchema.Header;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
public class PatternInfoVo {
    @Field("pattern")
    private List<PatternVO> pattern;
}
