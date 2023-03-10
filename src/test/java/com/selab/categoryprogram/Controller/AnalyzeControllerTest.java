package com.selab.categoryprogram.Controller;

import com.selab.categoryprogram.H2Repository.H2Repository;
import com.selab.categoryprogram.H2Schema.CDMSVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@RequiredArgsConstructor
@Slf4j
class AnalyzeControllerTest {

    @Autowired
    private H2Repository h2Repository;

    @Test
    public void objectToStringTest() {
        List<CDMSVO> cdmsvoList = h2Repository.findAll();
        for (CDMSVO cdmsvo : cdmsvoList) {
            String test = cdmsvo.toString();
            log.info(test);
        }
    }

}