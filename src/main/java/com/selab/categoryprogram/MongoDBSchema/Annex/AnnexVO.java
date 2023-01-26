package com.selab.categoryprogram.MongoDBSchema.Annex;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
public class AnnexVO {
    @Field("desc_info")
    private DescInfoVO descInfoVO;
}
