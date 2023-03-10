package com.selab.categoryprogram.Service;

import com.selab.categoryprogram.MongDBRepository.COMISDBFindRepository;
import com.selab.categoryprogram.MongDBRepository.COMISFILEFindRepository;
import com.selab.categoryprogram.MongoDBSchema.COMISDocDto;
import com.selab.categoryprogram.H2Schema.CDMSVO;
import com.selab.categoryprogram.H2Schema.MappingResultDto;
import com.selab.categoryprogram.H2Schema.ReadCodeDto;
import com.selab.categoryprogram.H2Repository.H2Repository;
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
    private final COMISDBFindRepository comisdbFindRepository;
    private final COMISFILEFindRepository comisfileFindRepository;

    public COMISToCDMSService(H2Repository h2Repository, ReadCOMISCodeFileService readCOMISCodeFileService, MongoTemplate mongoTemplate, COMISDBFindRepository comisdbFindRepository, COMISFILEFindRepository comisfileFindRepository) {
        this.h2Repository = h2Repository;
        this.readCOMISCodeFileService = readCOMISCodeFileService;
        this.mongoTemplate = mongoTemplate;
        this.comisdbFindRepository = comisdbFindRepository;
        this.comisfileFindRepository = comisfileFindRepository;
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
            cdmsvo.set_class(comisDoc.get_class());
            /*Header*/
            cdmsvo.setMeta_version(comisDoc.getHeaderVO().getMetaInfoVO().getMetaVersionInfoVO().getMeta_version());
            cdmsvo.setMeta_last_version(comisDoc.getHeaderVO().getMetaInfoVO().getMetaVersionInfoVO().getMeta_last_version());
            cdmsvo.setProduct_version(comisDoc.getHeaderVO().getMetaInfoVO().getProductInfoVO().getProductVersionInfoVO().getProduct_version());
            cdmsvo.setProduct_version_start_date(comisDoc.getHeaderVO().getMetaInfoVO().getProductInfoVO().getProductVersionInfoVO().getProduct_version_start_date());
            cdmsvo.setProduct_version_end_date(comisDoc.getHeaderVO().getMetaInfoVO().getProductInfoVO().getProductVersionInfoVO().getProduct_version_end_date());
            cdmsvo.setProduct_id(comisDoc.getHeaderVO().getMetaInfoVO().getProductInfoVO().getProduct_id());
            cdmsvo.setProduct_name_kr(comisDoc.getHeaderVO().getMetaInfoVO().getProductInfoVO().getProduct_name_kr());
            cdmsvo.setProduct_name_en(comisDoc.getHeaderVO().getMetaInfoVO().getProductInfoVO().getProduct_name_en());
            cdmsvo.setProduct_group(comisDoc.getHeaderVO().getMetaInfoVO().getProductInfoVO().getProduct_group().toString());
            cdmsvo.setProduct_desc(comisDoc.getHeaderVO().getMetaInfoVO().getProductInfoVO().getProduct_desc());
            cdmsvo.setQc_info(comisDoc.getHeaderVO().getMetaInfoVO().getProductInfoVO().getQc_info());
            cdmsvo.setTimezone_filename(comisDoc.getHeaderVO().getMetaInfoVO().getProductInfoVO().getTimezone_filename());
            cdmsvo.setTimezone_contents(comisDoc.getHeaderVO().getMetaInfoVO().getProductInfoVO().getTimezone_contents());
            cdmsvo.setPattern(comisDoc.getHeaderVO().getMetaInfoVO().getPatternInfoVo().getPattern().toString());
            cdmsvo.setContents_type(comisDoc.getHeaderVO().getMetaInfoVO().getContentsInfoVO().getContents_type());
            cdmsvo.setContents_list(comisDoc.getHeaderVO().getMetaInfoVO().getContentsInfoVO().getContents_list().toString());
            cdmsvo.setProduct_level_main(comisDoc.getHeaderVO().getMetaInfoVO().getProductLevelVO().getMain());
            cdmsvo.setProduct_level_sub(comisDoc.getHeaderVO().getMetaInfoVO().getProductLevelVO().getSub());
            cdmsvo.setOperation_start_date(comisDoc.getHeaderVO().getMetaInfoVO().getApplyInfoVO().getOperation_start_date());
            cdmsvo.setOperation_finish_date(comisDoc.getHeaderVO().getMetaInfoVO().getApplyInfoVO().getOperation_finish_date());
            cdmsvo.setService_ip(comisDoc.getHeaderVO().getAccessInfoInternalVO().getService_ip());
            cdmsvo.setDir_path(comisDoc.getHeaderVO().getAccessInfoInternalVO().getDir_path());
            cdmsvo.setDate_path(comisDoc.getHeaderVO().getAccessInfoInternalVO().getDate_path());
            cdmsvo.setApi_list(comisDoc.getHeaderVO().getAccessInfoInternalVO().getApi_list().toString());
            cdmsvo.setDefault_api((comisDoc.getHeaderVO().getAccessInfoInternalVO() == null) ? "N/A" : comisDoc.getHeaderVO().getAccessInfoInternalVO().getDefault_api());
            /*Body*/
            cdmsvo.setFile_extension(comisDoc.getBodyVO().getFormatInfoVO().getFile_extension());
            cdmsvo.setCompress_method(comisDoc.getBodyVO().getFormatInfoVO().getCompress_method());
            cdmsvo.setFile_format(comisDoc.getBodyVO().getFormatInfoVO().getFile_format());
            cdmsvo.setBinary_info(comisDoc.getBodyVO().getReadInfoVO().getBinaryInfoVO().toString());
            cdmsvo.setBinary_header_size(comisDoc.getBodyVO().getReadInfoVO().getBinaryInfoVO().getHeaderInfoVO().getHeader_size());
            cdmsvo.setAscii_info(comisDoc.getBodyVO().getReadInfoVO().getAsciiInfoVO().toString());
            cdmsvo.setAscii_header_size(comisDoc.getBodyVO().getReadInfoVO().getAsciiInfoVO().getCommonInfoVO().getHeader_size());
            /*Annex*/
            cdmsvo.setMeta_manager(comisDoc.getAnnexVO().getMetaBasicInfoVO().getMeta_manager());
            cdmsvo.setProduct_origin(comisDoc.getAnnexVO().getDescInfoVO().getProduct_origin());
            cdmsvo.setProduct_system(comisDoc.getAnnexVO().getDescInfoVO().getProduct_system());
            cdmsvo.setProduct_lang(comisDoc.getAnnexVO().getDescInfoVO().getProduct_lang());
            cdmsvo.setMeta_lang(comisDoc.getAnnexVO().getDescInfoVO().getMeta_lang());
            cdmsvo.setMeta_charset(comisDoc.getAnnexVO().getDescInfoVO().getMeta_charset());
            cdmsvo.setMeta_timezone(comisDoc.getAnnexVO().getDescInfoVO().getMeta_timezone());
            cdmsvo.setTest_start_date(comisDoc.getAnnexVO().getDescInfoVO().getTest_start_date().toString());
            cdmsvo.setTest_finish_date(comisDoc.getAnnexVO().getDescInfoVO().getTest_finish_date().toString());
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
            cdmsvo.set_class(comisDoc.get_class());
            /*Header*/
            cdmsvo.setMeta_version(comisDoc.getHeaderVO().getMetaInfoVO().getMetaVersionInfoVO().getMeta_version());
            cdmsvo.setMeta_last_version(comisDoc.getHeaderVO().getMetaInfoVO().getMetaVersionInfoVO().getMeta_last_version());
            cdmsvo.setProduct_version(comisDoc.getHeaderVO().getMetaInfoVO().getProductInfoVO().getProductVersionInfoVO().getProduct_version());
            cdmsvo.setProduct_version_start_date(comisDoc.getHeaderVO().getMetaInfoVO().getProductInfoVO().getProductVersionInfoVO().getProduct_version_start_date());
            cdmsvo.setProduct_version_end_date(comisDoc.getHeaderVO().getMetaInfoVO().getProductInfoVO().getProductVersionInfoVO().getProduct_version_end_date());
            cdmsvo.setProduct_id(comisDoc.getHeaderVO().getMetaInfoVO().getProductInfoVO().getProduct_id());
            cdmsvo.setProduct_name_kr(comisDoc.getHeaderVO().getMetaInfoVO().getProductInfoVO().getProduct_name_kr());
            cdmsvo.setProduct_name_en(comisDoc.getHeaderVO().getMetaInfoVO().getProductInfoVO().getProduct_name_en());
            cdmsvo.setProduct_group(comisDoc.getHeaderVO().getMetaInfoVO().getProductInfoVO().getProduct_group().toString());
            cdmsvo.setProduct_desc(comisDoc.getHeaderVO().getMetaInfoVO().getProductInfoVO().getProduct_desc());
            cdmsvo.setQc_info(comisDoc.getHeaderVO().getMetaInfoVO().getProductInfoVO().getQc_info());
            cdmsvo.setTimezone_filename(comisDoc.getHeaderVO().getMetaInfoVO().getProductInfoVO().getTimezone_filename());
            cdmsvo.setTimezone_contents(comisDoc.getHeaderVO().getMetaInfoVO().getProductInfoVO().getTimezone_contents());
            cdmsvo.setContents_type(comisDoc.getHeaderVO().getMetaInfoVO().getContentsInfoVO().getContents_type());
            cdmsvo.setContents_list(comisDoc.getHeaderVO().getMetaInfoVO().getContentsInfoVO().getContents_list().toString());
            cdmsvo.setProduct_level_main(comisDoc.getHeaderVO().getMetaInfoVO().getProductLevelVO().getMain());
            cdmsvo.setProduct_level_sub(comisDoc.getHeaderVO().getMetaInfoVO().getProductLevelVO().getSub());
            cdmsvo.setOperation_start_date(comisDoc.getHeaderVO().getMetaInfoVO().getApplyInfoVO().getOperation_start_date());
            cdmsvo.setOperation_finish_date(comisDoc.getHeaderVO().getMetaInfoVO().getApplyInfoVO().getOperation_finish_date());
            cdmsvo.setService_ip(comisDoc.getHeaderVO().getAccessInfoInternalVO().getService_ip());
            cdmsvo.setDir_path(comisDoc.getHeaderVO().getAccessInfoInternalVO().getDir_path());
            cdmsvo.setDate_path(comisDoc.getHeaderVO().getAccessInfoInternalVO().getDate_path());
            cdmsvo.setApi_list(comisDoc.getHeaderVO().getAccessInfoInternalVO().getApi_list().toString());
            cdmsvo.setDefault_api((comisDoc.getHeaderVO().getAccessInfoInternalVO() == null) ? "N/A" : comisDoc.getHeaderVO().getAccessInfoInternalVO().getDefault_api());
            cdmsvo.setDb_name(comisDoc.getHeaderVO().getAccessInfoInternalVO().getTableInfoVO().getDb_name());
            cdmsvo.setUser_id(comisDoc.getHeaderVO().getAccessInfoInternalVO().getTableInfoVO().getUser_id());
            cdmsvo.setTable_name(comisDoc.getHeaderVO().getAccessInfoInternalVO().getTableInfoVO().getTable_name());
            /*Body*/
            cdmsvo.setDdl_script(comisDoc.getBodyVO().getDdl_script());
            cdmsvo.setNumber_of_columns(comisDoc.getBodyVO().getColumnInfoVO().getNumber_of_columns());
            cdmsvo.setColumns(comisDoc.getBodyVO().getColumnInfoVO().getColumns().toString());
            cdmsvo.setNumber_of_indexes(comisDoc.getBodyVO().getIndexInfoVO().getNumber_of_indexes());
            cdmsvo.setIndexes(comisDoc.getBodyVO().getIndexInfoVO().getIndexes().toString());
            /*Annex*/
            cdmsvo.setMeta_manager(comisDoc.getAnnexVO().getMetaBasicInfoVO().getMeta_manager());
            cdmsvo.setProduct_origin(comisDoc.getAnnexVO().getDescInfoVO().getProduct_origin());
            cdmsvo.setProduct_system(comisDoc.getAnnexVO().getDescInfoVO().getProduct_system());
            cdmsvo.setProduct_lang(comisDoc.getAnnexVO().getDescInfoVO().getProduct_lang());
            cdmsvo.setMeta_lang(comisDoc.getAnnexVO().getDescInfoVO().getMeta_lang());
            cdmsvo.setMeta_charset(comisDoc.getAnnexVO().getDescInfoVO().getMeta_charset());
            cdmsvo.setMeta_timezone(comisDoc.getAnnexVO().getDescInfoVO().getMeta_timezone());
            cdmsvo.setTest_start_date(comisDoc.getAnnexVO().getDescInfoVO().getTest_start_date().toString());
            cdmsvo.setTest_finish_date(comisDoc.getAnnexVO().getDescInfoVO().getTest_finish_date().toString());
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
