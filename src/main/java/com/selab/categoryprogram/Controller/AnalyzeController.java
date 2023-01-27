package com.selab.categoryprogram.Controller;

import com.selab.categoryprogram.MongDBRepository.FindRepository;
import com.selab.categoryprogram.MongoDBSchema.COMISDoc_db;
import com.selab.categoryprogram.Service.SearchAttributeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Field;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AnalyzeController {
    private final SearchAttributeService searchAttributeService;
    private final FindRepository findRepository;

    @RequestMapping("/analyze")
    public String metadataChart(Model model) {
        List<String> attributeName = searchAttributeService.getAttributeName();
        model.addAttribute("attributeName", attributeName);
        return "metadataChart";
    }
}
