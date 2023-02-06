package com.selab.categoryprogram.H2Schema;

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
    private String product_origin;
    private String product_system;
    private String product_lang;
    private String meta_lang;
    private String meta_charset;
    private String ddl_script;
    private String number_of_columns;
    @Column(length = 10000)
    private String columns;
    private String file_extension;
    private String compress_method;
    private String file_format;
    private String number_of_index;
    private String indexes;
    private String _class;
    private String service_ip;
    private String dir_path;
    private String date_path;
    private String api_list;
    private String default_api;
    private String operation_start_date;
    private String operation_finish_date;
    private String product_id;
    @Column(length = 500)
    private String product_name_en;
    private String timezone_filename;
    private String timezone_contents;
    private String db_name;
    private String table_name;
    private String user_id;
}
