package com.selab.categoryprogram.MongoDBSchema.Annex;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
public class MetaBasicInfoVO {
    @Field("meta_manager")
    private String meta_manager;
}
