package com.selab.categoryprogram.MongoDBSchema.Header;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
public class AccessInfoExternalVO {
    @Field("service_ip")
    private String service_ip;
    @Field("dir_path")
    private String dir_path;
    @Field("date_path")
    private String date_path;
    @Field("api_list")
    private List<String> api_list;
    @Field("default_api")
    private String default_api;
}
