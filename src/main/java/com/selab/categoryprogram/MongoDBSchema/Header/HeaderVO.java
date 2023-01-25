package com.selab.categoryprogram.MongoDBSchema.Header;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
public class HeaderVO {
    @Field("meta_info")
    private MetaInfoVO metaInfoVO;
    @Field("access_info_internal")
    private AccessInfoInternalVO accessInfoInternalVO;
    @Field("access_external_external")
    private AccessInfoExternalVO accessInfoExternalVO;
}
