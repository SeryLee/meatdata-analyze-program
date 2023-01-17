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

    public List<ReadCodeDto> getReadCodeLists() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("C:\\DATA.OBSD.csv"));
        String line;
        String[] splitCode;
        ArrayList<ReadCodeDto> readCodeDtoList = new ArrayList<>();
        while((line = reader.readLine()) != null) {
            splitCode = line.split(",");
            ReadCodeDto readCodeDto = new ReadCodeDto();
            readCodeDto.setNewCode(splitCode[0]);
            List<String> test = Arrays.asList(splitCode[1].split("\\."));
            readCodeDto.setSaveCodeGroup(test);
            readCodeDto.setIncludeWordInFileName(splitCode[2]);
            readCodeDtoList.add(readCodeDto);
        }
        reader.close();
        return readCodeDtoList;
    }
}
