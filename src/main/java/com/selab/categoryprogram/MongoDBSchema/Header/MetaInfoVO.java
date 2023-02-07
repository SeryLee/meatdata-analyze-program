package com.selab.categoryprogram.MongoDBSchema.Header;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
public class MetaInfoVO {
    @Field("meta_version_info")
    private MetaVersionInfoVO metaVersionInfoVO;
    @Field("product_info")
    private ProductInfoVO productInfoVO;
    @Field("pattern_info")
    private PatternInfoVO patternInfoVo;
    @Field("contents_info")
    private ContentsInfoVO contentsInfoVO;
    @Field("product_level")
    private ProductLevelVO productLevelVO;
    @Field("apply_info")
    private ApplyInfoVO applyInfoVO;
}
