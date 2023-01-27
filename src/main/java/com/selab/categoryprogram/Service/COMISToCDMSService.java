package com.selab.categoryprogram.Service;

import com.selab.categoryprogram.MongoDBSchema.COMISDocDto;
import com.selab.categoryprogram.RDBSchema.CDMSVO;
import com.selab.categoryprogram.RDBSchema.MappingResultDto;
import com.selab.categoryprogram.RDBSchema.ReadCodeDto;
import com.selab.categoryprogram.JPARepository.H2Repository;
import com.selab.categoryprogram.MongoDBSchema.COMISDoc_db;
import com.selab.categoryprogram.MongoDBSchema.COMISDoc_file;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class COMISToCDMSService {

    private final H2Repository h2Repository;
    private final ReadCOMISCodeFileService readCOMISCodeFileService;
    private final MongoTemplate mongoTemplate;

    public COMISToCDMSService(H2Repository h2Repository, ReadCOMISCodeFileService readCOMISCodeFileService, MongoTemplate mongoTemplate) {
        this.h2Repository = h2Repository;
        this.readCOMISCodeFileService = readCOMISCodeFileService;
        this.mongoTemplate = mongoTemplate;
    }

    public void classifyCOMIS() throws IOException {
        List<ReadCodeDto> readCodeDtos = readCOMISCodeFileService.getReadCodeLists();
        for (ReadCodeDto readCodeDto : readCodeDtos) {
            setCOMISInfosToCDMS(readCodeDto);
        }
    }

    private void setCOMISInfosToCDMS(ReadCodeDto readCodeDto) {
        String includeWordInFileName = readCodeDto.getIncludeWordInFileName();
        List<String> saveCodeGroup = readCodeDto.getSaveCodeGroup();
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.andOperator(Criteria.where("_id").regex(includeWordInFileName), Criteria.where("header.meta_info.product_info.product_group").all(saveCodeGroup));
        query.addCriteria(criteria);

        getCOMISInfoFromFile(readCodeDto, query);
        getCOMISInfoFromDB(readCodeDto, query);
    }

    private void getCOMISInfoFromFile(ReadCodeDto readCodeDto, Query query) {
        List<COMISDoc_file> comisDocs = mongoTemplate.find(query, COMISDoc_file.class);
        ArrayList<CDMSVO> cdmsvos = new ArrayList<>();
        for (COMISDoc_file comisDoc : comisDocs) {
            CDMSVO cdmsvo = new CDMSVO();
            cdmsvo.setNew_code(readCodeDto.getNewCode());
            cdmsvo.setCategory(readCodeDto.getFileName());
            cdmsvo.setComis_id(comisDoc.get_id());
            cdmsvo.setProduct_group(comisDoc.getHeaderVO().getMetaInfoVO().getProductInfoVO().getProduct_group().toString());
            cdmsvo.setProduct_name_kr(comisDoc.getHeaderVO().getMetaInfoVO().getProductInfoVO().getProduct_name_kr());
            cdmsvo.setType("FILE");
            cdmsvos.add(cdmsvo);
        }
        h2Repository.saveAll(cdmsvos);
    }

    private void getCOMISInfoFromDB(ReadCodeDto readCodeDto, Query query) {
        List<COMISDoc_db> comisDocs = mongoTemplate.find(query, COMISDoc_db.class);
        ArrayList<CDMSVO> cdmsvos = new ArrayList<>();
        for (COMISDoc_db comisDoc : comisDocs) {
            CDMSVO cdmsvo = new CDMSVO();
            cdmsvo.setNew_code(readCodeDto.getNewCode());
            cdmsvo.setCategory(readCodeDto.getFileName());
            cdmsvo.setComis_id(comisDoc.get_id());
            cdmsvo.setProduct_group(comisDoc.getHeaderVO().getMetaInfoVO().getProductInfoVO().getProduct_group().toString());
            cdmsvo.setProduct_name_kr(comisDoc.getHeaderVO().getMetaInfoVO().getProductInfoVO().getProduct_name_kr());
            cdmsvo.setType("DB");
            cdmsvos.add(cdmsvo);
        }
        h2Repository.saveAll(cdmsvos);
    }

    public List<MappingResultDto> getCategoryCountResult() {
        List<String> categoryList = h2Repository.findCategory();
        List<MappingResultDto> countResult = new ArrayList<>();
        for (String category : categoryList) {
            MappingResultDto resultDto = new MappingResultDto();
            resultDto.setCategory(category);
            resultDto.setCount_result(h2Repository.countByCategory(category));
            countResult.add(resultDto);
        }
        return countResult;
    }

    public List<COMISDocDto> getNoMappingData(List<String> comisIdList) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").nin(comisIdList));
        List<COMISDoc_db> dataFromDB = mongoTemplate.find(query, COMISDoc_db.class);
        List<COMISDoc_file> dataFromFile = mongoTemplate.find(query, COMISDoc_file.class);
        List<COMISDocDto> comisDocDtos = new ArrayList<>();
        for (COMISDoc_file comisDocFile : dataFromFile) {
            COMISDocDto comisDocDto = new COMISDocDto();
            comisDocDto.set_id(comisDocFile.get_id());
            comisDocDto.setProduct_name_kr(comisDocFile.getHeaderVO().getMetaInfoVO().getProductInfoVO().getProduct_name_kr());
            comisDocDto.setType("FILE");
            comisDocDtos.add(comisDocDto);
        }
        for (COMISDoc_db comisDocDb : dataFromDB) {
            COMISDocDto comisDocDto = new COMISDocDto();
            comisDocDto.set_id(comisDocDb.get_id());
            comisDocDto.setProduct_name_kr(comisDocDb.getHeaderVO().getMetaInfoVO().getProductInfoVO().getProduct_name_kr());
            comisDocDto.setType("DB");
            comisDocDtos.add(comisDocDto);
        }
        return comisDocDtos;
    }
}
