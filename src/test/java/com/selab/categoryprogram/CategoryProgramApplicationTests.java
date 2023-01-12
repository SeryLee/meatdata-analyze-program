package com.selab.categoryprogram;

import com.selab.MongDBRepository.TestRepo;
import com.selab.Schema.TestDoc;
import com.selab.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class CategoryProgramApplicationTests {


    @Autowired
    TestRepo testRepo;

    TestService testService;
    @Test
    void mongoDBFindTest() {
        String testId = "T.COMIS5.DCOMIS.MIS_ADM^AGR_AGRI_AWS.latest";
        List<TestDoc> findTest = testRepo.findAllById(Collections.singleton(testId));
        String aClass = findTest.get(0).get_class();
        assertThat(aClass).isEqualTo("kma.comis5.meta.mngt.vo.dbmeta.DbSchema");
    }

    @Test
    void mongoDBConnectTest() {
        List<TestDoc> findAll = testRepo.findAll();
        assertThat(findAll).isNotNull();
    }

}
