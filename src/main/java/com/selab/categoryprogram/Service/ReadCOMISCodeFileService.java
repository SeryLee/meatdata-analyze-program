package com.selab.categoryprogram.Service;

import com.selab.categoryprogram.RDBSchema.ReadCodeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReadCOMISCodeFileService {

    public List<ReadCodeDto> getReadCodeLists() throws IOException {
        File[] fileList = getFiles();
        ArrayList<ReadCodeDto> readCodeDtoList = new ArrayList<>();
        for (File file : fileList) {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while((line = reader.readLine()) != null) {
                ReadCodeDto readCodeDto = getReadCodeDto(file, line);
                if (readCodeDto == null) continue;
                readCodeDtoList.add(readCodeDto);
            }
            reader.close();
        }
        return readCodeDtoList;
    }

    private static ReadCodeDto getReadCodeDto(File file, String line) {
        String[] splitCode;
        splitCode = line.split(",");
        if(splitCode[1].equals("X")) return null;
        ReadCodeDto readCodeDto = new ReadCodeDto();
        readCodeDto.setNewCode(splitCode[0]);
        List<String> codeGroup = Arrays.asList(splitCode[1].split("\\."));
        readCodeDto.setSaveCodeGroup(codeGroup);
        readCodeDto.setIncludeWordInFileName(splitCode[2]);
        String fileName = file.getName();
        readCodeDto.setFileName(fileName.substring(0, fileName.length()-4));
        return readCodeDto;
    }

    private static File[] getFiles() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("CSVFolder");
        File folder = new File(classPathResource.getURI().getPath());
        return folder.listFiles();
    }
}
