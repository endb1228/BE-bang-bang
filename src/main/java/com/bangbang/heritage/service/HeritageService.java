package com.bangbang.heritage.service;

import com.bangbang.heritage.domain.Heritage;
import com.bangbang.heritage.repository.HeritageRepository;
import com.bangbang.util.HeritageXMLParser;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class HeritageService {

    private final HeritageRepository heritageRepository;

    public HeritageService(HeritageRepository heritageRepository) {
        this.heritageRepository = heritageRepository;
    }

    private static final String HERITAGE_FILE_PATH = "/Users/jonghyeoklee/Documents/Project/bangbang/src/main/resources/heritages/";

    public List<Heritage> getHeritageList() {
        List<Heritage> heritageList = heritageRepository.findAll();
        if (heritageList.isEmpty()) {
            try {
                heritageList = heritageRepository.saveAll(HeritageXMLParser.parse(HERITAGE_FILE_PATH + "국보.xml"));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return heritageList;
    }
}
