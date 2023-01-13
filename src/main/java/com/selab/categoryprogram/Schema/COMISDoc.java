package com.selab.categoryprogram.Schema;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("db.metadata.product.db")
@Getter
@Setter
public class COMISDoc {
    @Id
    private String _id;
    private String _class;
    @Field("header")
    private HeaderVO headerVO;
    @Field("body")
    private BodyVO bodyVO;
}

