package com.selab.categoryprogram.Controller;

import com.selab.categoryprogram.JPARepository.H2Repository;
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
    public String Test(Model model) throws IOException {
        List<CDMSVO> cdmsvos = comisToCDMSService.classifyCOMIS();
        List<MappingResultDto> countResult = comisToCDMSService.getCategoryCountResult();
        long fileCnt = h2Repository.countByType("FILE");
        long dbCnt = h2Repository.countByType("DB");

//        h2Repository.findAll().stream();

        model.addAttribute("cdmsvos", cdmsvos);
        model.addAttribute("countResult", countResult);
        model.addAttribute("fileCnt", fileCnt);
        model.addAttribute("dbCnt", dbCnt);
        return "index";
    }
}
