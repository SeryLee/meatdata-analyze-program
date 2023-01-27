package com.selab.categoryprogram;

import com.selab.categoryprogram.MongDBRepository.COMISDBFindRepository;
import com.selab.categoryprogram.MongoDBSchema.COMISDoc_db;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
@RequiredArgsConstructor
class CategoryProgramApplicationTests {

    @Autowired
    COMISDBFindRepository comisRepository;

    @Test
    void mongoDBFindAllTest() {
        List<COMISDoc_db> findAll = comisRepository.findAll();
        assertThat(findAll).isNotNull();
    }

    @Test
    void mongoDBFindTest() {
        String testId = "T.COMIS5.DCOMIS.MIS_ADM^AGR_AGRI_AWS.latest";
        List<COMISDoc_db> findTest = comisRepository.findAllById(Collections.singleton(testId));
        String aClass = findTest.get(0).get_class();
        assertThat(aClass).isEqualTo("kma.comis5.meta.mngt.vo.dbmeta.DbSchema");
    }

    @Test
    void getProductIdTest() {
        String testId = "T.COMIS5.DCOMIS.MIS_ADM^AGR_AGRI_AWS.latest";

        COMISDoc_db productIdById = comisRepository.getProductIdById(testId);
        log.info(productIdById.getHeaderVO().getMetaInfoVO().getProductInfoVO().getProduct_id());
        assertThat(productIdById.getHeaderVO().getMetaInfoVO().getProductInfoVO().getProduct_id()).isEqualTo("T.COMIS5.DCOMIS.MIS_ADM^AGR_AGRI_AWS");
    }
}
