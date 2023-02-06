package com.selab.categoryprogram.MongoDBSchema.Header;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
public class ContentsInfoVO {
    @Field("contents_type")
    private String contents_type;
    @Field("contents_list")
    private List<ContentsVO> contents_list;
}
