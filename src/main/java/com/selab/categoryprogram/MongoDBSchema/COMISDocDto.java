package com.selab.categoryprogram.MongoDBSchema;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
public class COMISDocDto {
    @Id
    private String _id;
    private String _class;
    private String product_id;
}

