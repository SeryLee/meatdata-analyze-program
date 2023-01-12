package com.selab.Schema;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("db.metadata.product.db")
@Getter
@Setter
public class TestDoc {
    @Id
    private String _id;
    private String product_id;
    private String _class;
}

