package com.selab.categoryprogram.MongoDBSchema.Body;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
public class IndexInfoVO {
    @Field("number_of_index")
    private String number_of_index;
    @Field("indexes")
    private List<IndexesVO> indexes;
}
