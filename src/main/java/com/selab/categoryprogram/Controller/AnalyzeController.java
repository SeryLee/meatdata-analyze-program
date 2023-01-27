package com.selab.categoryprogram.Controller;

import com.selab.categoryprogram.MongDBRepository.COMISDBFindRepository;
import com.selab.categoryprogram.MongDBRepository.COMISFILEFindRepository;
import com.selab.categoryprogram.MongoDBSchema.COMISDoc_db;
import com.selab.categoryprogram.MongoDBSchema.COMISDoc_file;
import com.selab.categoryprogram.Service.SearchAttributeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AnalyzeController {
    private final SearchAttributeService searchAttributeService;
    private final COMISDBFindRepository comisdbFindRepository;
    private final COMISFILEFindRepository comisfileFindRepository;

    @RequestMapping("/analyze")
    public String metadataChart(Model model) {
        List<String> attributeName = searchAttributeService.getAttributeName();
        List<COMISDoc_db> comisDbList = comisdbFindRepository.findAll();
        List<COMISDoc_file> comisFileList = comisfileFindRepository.findAll();
        model.addAttribute("attributeName", attributeName);
        model.addAttribute("comisDbList", comisDbList);
        model.addAttribute("comisFileList", comisFileList);
        return "metadataChart";
    }
}
