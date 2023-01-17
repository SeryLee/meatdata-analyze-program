package com.selab.categoryprogram.Controller;

import com.selab.categoryprogram.COMISSchema.CDMSVO;
import com.selab.categoryprogram.Service.COMISToCDMSService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller

public class TestController {
    private final COMISToCDMSService comisToCDMSService;

    public TestController(COMISToCDMSService comisToCDMSService) {
        this.comisToCDMSService = comisToCDMSService;
    }

    @RequestMapping("/")
    public String Test(Model model) throws IOException {
        List<CDMSVO> cdmsvos = comisToCDMSService.classifyMethod();
        model.addAttribute(cdmsvos);
        return "index";
    }
}
