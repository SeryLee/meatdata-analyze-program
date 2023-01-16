package com.selab.categoryprogram.Controller;

import com.selab.categoryprogram.Service.COMISToCDMSService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequiredArgsConstructor

public class TestController {

    @Autowired
    private COMISToCDMSService comisToCDMSService;
    @RequestMapping("/")
    public String Test() throws IOException {
        comisToCDMSService.classifyMethod();
        return "index";
    }
}
