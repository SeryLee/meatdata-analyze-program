package com.selab.categoryprogram.MongoDBSchema.Header;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
public class MetaVersionInfoVO {
    @Field("meta_version")
    private String meta_version;
    @Field("mata_last_version")
    private String meta_last_version;
}
