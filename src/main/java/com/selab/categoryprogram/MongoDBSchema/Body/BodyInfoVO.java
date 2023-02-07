package com.selab.categoryprogram.MongoDBSchema.Body;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
public class BodyInfoVO {
    @Field("body_structure")
    private List<BodyStructureVO> body_structure;
    @Field("binary_record_info")
    private BinaryRecordInfoVO binaryRecordInfoVO;
}
