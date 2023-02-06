package com.selab.categoryprogram.MongoDBSchema.Annex;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
public class AnnexVO {
    @Field("meta_basic_info")
    private MetaBasicInfoVO metaBasicInfoVO;
    @Field("desc_info")
    private DescInfoVO descInfoVO;
}
