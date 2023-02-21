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
    private String type;

    /*COMIS Attribute start*/
    private String comis_id;
    private String _class;
    /*Header*/
    private String meta_version;
    private String meta_last_version;
    private String product_version;
    private String product_version_start_date;
    private String product_version_end_date;
    private String product_id;
    private String product_name_kr;
    @Column(length = 500)
    private String product_name_en;
    private String product_group;
    private String product_desc;
    private String qc_info;
    private String timezone_filename;
    private String timezone_contents;
    @Column(length = 1000)
    private String pattern;
    private String contents_type;
    private String contents_list;
    private String product_level_main;
    private String product_level_sub;
    private String operation_start_date;
    private String operation_finish_date;
    private String service_ip;
    private String dir_path;
    private String date_path;
    private String api_list;
    private String default_api;
    /*Body*/
    private String file_extension;
    private String compress_method;
    private String file_format;
    private String binary_info;
    private String binary_header_size;
    private String ascii_info;
    private String ascii_header_size;
    private String number_of_columns;
    @Column(length = 10000)
    private String columns;
    private String number_of_indexes;
    @Column(length = 500)
    private String indexes;
    private String ddl_script;
    /*Annex*/
    private String db_name;
    private String table_name;
    private String user_id;
    private String meta_manager;
    private String product_origin;
    private String product_system;
    private String product_lang;
    private String meta_lang;
    private String meta_charset;
    private String meta_timezone;
    private String test_start_date;
    private String test_finish_date;
    /*COMIS Attribute end*/
}
