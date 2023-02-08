package com.selab.categoryprogram.MongoDBSchema.Body;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
public class IndexInfoVO {
    @Field("number_of_indexes")
    private String number_of_indexes;
    @Field("indexes")
    private List<IndexesVO> indexes;
}
