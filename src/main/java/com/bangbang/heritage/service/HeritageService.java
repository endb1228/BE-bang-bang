package com.bangbang.heritage.service;

import com.bangbang.heritage.domain.Heritage;
import com.bangbang.heritage.domain.HeritageType;
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
                for (HeritageType type : HeritageType.values()) {
                    heritageRepository.saveAll(HeritageXMLParser.parse(HERITAGE_FILE_PATH + type.name() + ".xml"));
                    heritageList = heritageRepository.findAll();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return heritageList;
    }
}
