package com.selab.categoryprogram.MongoDBSchema.Body;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
public class BodyStructureVO {
    @Field("member")
    private String member;
    @Field("number_of_members")
    private String number_of_members;
}
