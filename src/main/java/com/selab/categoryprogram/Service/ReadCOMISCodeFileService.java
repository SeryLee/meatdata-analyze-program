package com.selab.categoryprogram.Service;

import com.selab.categoryprogram.COMISSchema.ReadCodeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReadCOMISCodeFileService {

    public List<ReadCodeDto> readCodeDtoList() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("C:\\DATA.OBSD.csv"));
        String line;
        List<ReadCodeDto> readCodeDtoList = new ArrayList<>();
        ReadCodeDto readCodeDto = new ReadCodeDto();
        while((line = reader.readLine()) != null) {
            String[] splitCode = line.split(",");
            readCodeDto.setNewCode(splitCode[0]);
            List<String> test = Arrays.asList(splitCode[1].split("\\."));
            readCodeDto.setSaveCodeGroup(test);
            readCodeDto.setIncludeWordInFileName(splitCode[2]);
            readCodeDtoList.add(readCodeDto);
            System.out.println(line);
        }
        reader.close();
        return readCodeDtoList();
    }
}
