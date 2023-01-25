package com.selab.categoryprogram.MongoDBSchema;

import com.selab.categoryprogram.MongoDBSchema.Header.HeaderVO;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("db.metadata.product.db")
@Getter
public class COMISDoc_db {
    @Id
    private String _id;
    private String _class;
    @Field("header")
    private HeaderVO headerVO;
}

