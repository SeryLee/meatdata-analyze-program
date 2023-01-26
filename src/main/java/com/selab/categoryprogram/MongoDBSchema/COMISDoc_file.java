package com.selab.categoryprogram.MongoDBSchema;

import com.selab.categoryprogram.MongoDBSchema.Annex.AnnexVO;
import com.selab.categoryprogram.MongoDBSchema.Body.BodyVO;
import com.selab.categoryprogram.MongoDBSchema.Header.HeaderVO;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("db.metadata.product.file")
@Getter
public class COMISDoc_file {
    @Id
    private String _id;
    private String _class;
    @Field("header")
    private HeaderVO headerVO;
    @Field("body")
    private BodyVO bodyVO;
    @Field("annex")
    private AnnexVO annexVO;
}

