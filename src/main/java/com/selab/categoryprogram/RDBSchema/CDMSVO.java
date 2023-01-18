package com.selab.categoryprogram.RDBSchema;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Entity(name = "CDMS")
public class CDMSVO {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private String new_code;
    private String category;
    private String comis_id;
    private String product_group;
    private String product_name_kr;
    private String type;
}
