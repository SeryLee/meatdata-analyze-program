package com.selab.categoryprogram.Service;

import com.selab.categoryprogram.COMISSchema.CDMSVO;
import com.selab.categoryprogram.COMISSchema.ReadCodeDto;
import com.selab.categoryprogram.JPARepository.H2Repository;
import com.selab.categoryprogram.MongDBRepository.FindRepository;
import com.selab.categoryprogram.MongoDBSchema.COMISDoc;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class COMISToCDMSService {

    private final FindRepository findRepository;
    private final H2Repository h2Repository;
    private final ReadCOMISCodeFileService readCOMISCodeFileService;
    private final MongoTemplate mongoTemplate;

    public COMISToCDMSService(FindRepository findRepository, H2Repository h2Repository, ReadCOMISCodeFileService readCOMISCodeFileService, MongoTemplate mongoTemplate) {
        this.findRepository = findRepository;
        this.h2Repository = h2Repository;
        this.readCOMISCodeFileService = readCOMISCodeFileService;
        this.mongoTemplate = mongoTemplate;
    }

    public List<CDMSVO> classifyMethod() throws IOException {
        List<ReadCodeDto> readCodeDtos = readCOMISCodeFileService.getReadCodeLists();
        for (ReadCodeDto readCodeDto : readCodeDtos) {
            String includeWordInFileName = readCodeDto.getIncludeWordInFileName();
            List<String> saveCodeGroup = readCodeDto.getSaveCodeGroup();
            List<COMISDoc> allByReadCodeDto = findRepository.findAllByReadCodeDto(includeWordInFileName, saveCodeGroup);
            CDMSVO cdmsvo = new CDMSVO();
            for (COMISDoc comisDoc : allByReadCodeDto) {
                cdmsvo.setNewCode(readCodeDto.getNewCode());
                cdmsvo.setProduct_id(comisDoc.getHeaderVO().getMetaInfoVO().getProductInfoVO().getProduct_id());
                cdmsvo.setProduct_group(comisDoc.getHeaderVO().getMetaInfoVO().getProductInfoVO().getProduct_group().toString());
                h2Repository.save(cdmsvo);
            }
        }
        return h2Repository.findAll();
    }
}
