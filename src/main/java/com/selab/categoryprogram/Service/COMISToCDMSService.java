package com.selab.categoryprogram.Service;

import com.selab.categoryprogram.COMISSchema.CDMSVO;
import com.selab.categoryprogram.COMISSchema.ReadCodeDto;
import com.selab.categoryprogram.JPARepository.H2Repository;
import com.selab.categoryprogram.MongDBRepository.FindRepository;
import com.selab.categoryprogram.MongoDBSchema.COMISDoc;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class COMISToCDMSService {

    @Autowired
    private FindRepository findRepository;
    @Autowired
    private H2Repository h2Repository;
    @Autowired
    private ReadCOMISCodeFileService readCOMISCodeFileService;

    public void classifyMethod() throws IOException {
        List<ReadCodeDto> readCodeDtos = readCOMISCodeFileService.readCodeDtoList();
        for (ReadCodeDto readCodeDto : readCodeDtos) {
            List<COMISDoc> allByReadCodeDto = findRepository.findAllByReadCodeDto(readCodeDto);
            CDMSVO cdmsvo = new CDMSVO();
            for (COMISDoc comisDoc : allByReadCodeDto) {
                cdmsvo.setNewCode(readCodeDto.getNewCode());
                cdmsvo.setProduct_id(comisDoc.getHeaderVO().getMetaInfoVO().getProductInfoVO().getProduct_id());
                cdmsvo.setProduct_group(comisDoc.getHeaderVO().getMetaInfoVO().getProductInfoVO().getProduct_group().toString());
                h2Repository.save(cdmsvo);
            }
        }

    }
}
