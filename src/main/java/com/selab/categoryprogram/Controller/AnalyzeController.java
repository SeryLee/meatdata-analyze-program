package com.selab.categoryprogram.Controller;

import com.selab.categoryprogram.H2Repository.H2Repository;
import com.selab.categoryprogram.MongDBRepository.COMISDBFindRepository;
import com.selab.categoryprogram.MongDBRepository.COMISFILEFindRepository;
import com.selab.categoryprogram.MongoDBSchema.COMISDoc_db;
import com.selab.categoryprogram.MongoDBSchema.COMISDoc_file;
import com.selab.categoryprogram.H2Schema.CDMSVO;
import com.selab.categoryprogram.Service.SearchAttributeService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AnalyzeController {
    private final SearchAttributeService searchAttributeService;
    private final COMISDBFindRepository comisdbFindRepository;
    private final COMISFILEFindRepository comisfileFindRepository;
    private final H2Repository h2Repository;

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

    @RequestMapping("/download")
    public void exportCDMSInfo(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv; charset=MS949");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDate = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=CDMS_" + currentDate + ".csv";
        response.setHeader(headerKey, headerValue);

        List<CDMSVO> cdmsvoList = h2Repository.findAll();

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"id", "new_code", "category", "comis_id", "product_group", "product_name_kr", "type", "product_origin", "product_system", "product_lang", "meta_lang", "meta_charset", "ddl_script", "number_of_columns", "columns", "file_extension", "compress_method", "file_format", "number_of_index", "indexes", "_class", "service_ip", "dir_path", "date_path", "api_list", "default_api", "operation_start_date", "operation_finish_date", "product_id", "product_name_en", "timezone_filename", "timezone_contents", "db_name", "table_name", "user_id"};

        csvWriter.writeHeader(csvHeader);
        for (CDMSVO cdmsvo : cdmsvoList) {
            csvWriter.write(cdmsvo, csvHeader);
        }
        csvWriter.close();
    }
}
