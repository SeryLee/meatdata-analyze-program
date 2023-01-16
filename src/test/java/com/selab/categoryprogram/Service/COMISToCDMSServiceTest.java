package com.selab.categoryprogram.Service;

import com.selab.categoryprogram.COMISSchema.CDMSVO;
import com.selab.categoryprogram.COMISSchema.ReadCodeDto;
import com.selab.categoryprogram.JPARepository.H2Repository;
import com.selab.categoryprogram.MongDBRepository.FindRepository;
import com.selab.categoryprogram.MongoDBSchema.COMISDoc;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@SpringBootTest
class COMISToCDMSServiceTest {

    @Autowired
    private FindRepository findRepository;
    @Autowired
    private H2Repository h2Repository;
    @Autowired
    private ReadCOMISCodeFileService readCOMISCodeFileService;
    @Test
    void classifyMethod() throws IOException {
        List<ReadCodeDto> readCodeDtos = readCOMISCodeFileService.readCodeDtoList();
        for (ReadCodeDto readCodeDto : readCodeDtos) {
            List<COMISDoc> allByReadCodeDto = findRepository.findAllByReadCodeDto(readCodeDto);
            CDMSVO cdmsvo = new CDMSVO();
            for (COMISDoc comisDoc : allByReadCodeDto) {
                cdmsvo.setNewCode(readCodeDto.getNewCode());
                cdmsvo.setProduct_id(comisDoc.getHeaderVO().getMetaInfoVO().getProductInfoVO().getProduct_id());
                cdmsvo.setProduct_group(comisDoc.getHeaderVO().getMetaInfoVO().getProductInfoVO().getProduct_group().toString());
                h2Repository.save(cdmsvo);
            }
        }
    }

    @Test
    void readFileTest() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("C:\\DATA.OBSD.csv"));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
    }
}