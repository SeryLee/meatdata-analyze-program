package com.selab.categoryprogram.Service;

import com.selab.categoryprogram.RDBSchema.CDMSVO;
import com.selab.categoryprogram.RDBSchema.ReadCodeDto;
import com.selab.categoryprogram.JPARepository.H2Repository;
import com.selab.categoryprogram.MongDBRepository.COMISDBFindRepository;
import com.selab.categoryprogram.MongoDBSchema.COMISDoc_db;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class COMISToCDMSServiceTest {

    @Autowired
    private COMISDBFindRepository COMISDBFindRepository;
    @Autowired
    private H2Repository h2Repository;
    @Autowired
    private ReadCOMISCodeFileService readCOMISCodeFileService;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Test
    void classifyMethod_MongoRepository() throws IOException {
        List<ReadCodeDto> readCodeDtos = readCOMISCodeFileService.getReadCodeLists();
        for (ReadCodeDto readCodeDto : readCodeDtos) {
            String includeWordInFileName = readCodeDto.getIncludeWordInFileName();
            List<String> saveCodeGroup = readCodeDto.getSaveCodeGroup();

            List<COMISDoc_db> allByCodes = COMISDBFindRepository.findAllByReadCodeDto(includeWordInFileName, saveCodeGroup);
            CDMSVO cdmsvo = new CDMSVO();
            for (COMISDoc_db comisDoc : allByCodes) {
                cdmsvo.setNew_code(readCodeDto.getNewCode());
                cdmsvo.setComis_id(comisDoc.getHeaderVO().getMetaInfoVO().getProductInfoVO().getProduct_id());
                cdmsvo.setProduct_group(comisDoc.getHeaderVO().getMetaInfoVO().getProductInfoVO().getProduct_group().toString());
                h2Repository.save(cdmsvo);
            }
        }
    }

    @Test
    void classifyMethod_MongoTemplate() throws IOException {
        List<ReadCodeDto> readCodeDtos = readCOMISCodeFileService.getReadCodeLists();
        for (ReadCodeDto readCodeDto : readCodeDtos) {
            String includeWordInFileName = readCodeDto.getIncludeWordInFileName();
            List<String> saveCodeGroup = readCodeDto.getSaveCodeGroup();

            Query query = new Query();
            Criteria criteria = new Criteria();
            criteria.andOperator(Criteria.where("_id").regex(includeWordInFileName), Criteria.where("header.meta_info.product_info.product_group").is(saveCodeGroup));
            query.addCriteria(criteria);
            List<COMISDoc_db> comisDocs = mongoTemplate.find(query, COMISDoc_db.class);

            CDMSVO cdmsvo = new CDMSVO();
            for (COMISDoc_db comisDocDb : comisDocs) {
                cdmsvo.setNew_code(readCodeDto.getNewCode());
                cdmsvo.setComis_id(comisDocDb.getHeaderVO().getMetaInfoVO().getProductInfoVO().getProduct_id());
                cdmsvo.setProduct_group(comisDocDb.getHeaderVO().getMetaInfoVO().getProductInfoVO().getProduct_group().toString());
                h2Repository.save(cdmsvo);
            }
        }
    }

    @Test
    void readFileTest() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("C:\\OBSD.csv"));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
        List<ReadCodeDto> readCodeDtoList = readCOMISCodeFileService.getReadCodeLists();
        assertThat(readCodeDtoList.size()).isEqualTo(38);
    }
}