package com.selab.categoryprogram.H2Schema;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReadCodeDto {
    private String newCode;
    private List<String> saveCodeGroup;
    private String includeWordInFileName;
    private String fileName;
}
