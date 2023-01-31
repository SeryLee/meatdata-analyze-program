package com.selab.categoryprogram.Controller;

import com.selab.categoryprogram.MongDBRepository.COMISDBFindRepository;
import com.selab.categoryprogram.MongDBRepository.COMISFILEFindRepository;
import com.selab.categoryprogram.MongoDBSchema.COMISDoc_db;
import com.selab.categoryprogram.MongoDBSchema.COMISDoc_file;
import com.selab.categoryprogram.Service.SearchAttributeService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
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

    @RequestMapping("/downloadExcel")
    public void downloadExcel(HttpServletResponse response) throws IOException {
        List<String> attributeName = searchAttributeService.getAttributeName();
        List<COMISDoc_db> comisDbList = comisdbFindRepository.findAll();
        List<COMISDoc_file> comisFileList = comisfileFindRepository.findAll();

        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("getDBInfo");
        int rowNo = 0;
        int num = 0;
        Row headerRow = sheet.createRow(rowNo++);
        for (String s : attributeName) {
            headerRow.createCell(num).setCellValue(s);
            num++;
        }

        response.setContentType("ms-vnd/excel");
        response.setHeader("Content-Disposition", "attachment;filename=test.xls");

        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
