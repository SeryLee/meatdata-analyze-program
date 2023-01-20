package com.selab.categoryprogram.Controller;

import com.selab.categoryprogram.JPARepository.H2Repository;
import com.selab.categoryprogram.MongoDBSchema.COMISDocDto;
import com.selab.categoryprogram.RDBSchema.CDMSVO;
import com.selab.categoryprogram.RDBSchema.MappingResultDto;
import com.selab.categoryprogram.Service.COMISToCDMSService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
public class IndexController {
    private final COMISToCDMSService comisToCDMSService;
    private final H2Repository h2Repository;

    public IndexController(COMISToCDMSService comisToCDMSService, H2Repository h2Repository) {
        this.comisToCDMSService = comisToCDMSService;
        this.h2Repository = h2Repository;
    }
    @RequestMapping("/")
    public String index(Model model) {

        List<MappingResultDto> countResult = comisToCDMSService.getCategoryCountResult();
        model.addAttribute("countResult", countResult);

        long fileCnt = h2Repository.countByType("FILE");
        long dbCnt = h2Repository.countByType("DB");
        model.addAttribute("fileCnt", fileCnt);
        model.addAttribute("dbCnt", dbCnt);
        return "index";
    }

    @RequestMapping("/mappingData")
    public String mappingData(Model model) {
        List<CDMSVO> cdmsvos = h2Repository.findAll();
        model.addAttribute("cdmsvos", cdmsvos);

        long fileCnt = h2Repository.countByType("FILE");
        long dbCnt = h2Repository.countByType("DB");
        model.addAttribute("fileCnt", fileCnt);
        model.addAttribute("dbCnt", dbCnt);
        return "mappingData";
    }

    @RequestMapping("/noMappingData")
    public String noMappingData(Model model) {
        List<String> comisIdList = h2Repository.findCOMISId();
        List<COMISDocDto> noMappingData = comisToCDMSService.getNoMappingData(comisIdList);
        int size = noMappingData.size();
        model.addAttribute("data", noMappingData);
        model.addAttribute("size", size);
        return "noMappingData";
    }
}
