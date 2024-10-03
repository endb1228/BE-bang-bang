package com.bangbang.heritage.service;

import com.bangbang.heritage.domain.Heritage;
import com.bangbang.heritage.domain.HeritageType;
import com.bangbang.heritage.repository.HeritageRepository;
import com.bangbang.util.HeritageXMLParser;
import jakarta.annotation.PostConstruct;
import java.util.List;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
public class HeritageService {

    private final HeritageRepository heritageRepository;
    private final HeritageXMLParser xmlParser;

    public HeritageService(HeritageRepository heritageRepository, HeritageXMLParser xmlParser) {
        this.heritageRepository = heritageRepository;
        this.xmlParser = xmlParser;
    }

    @PostConstruct
    public void initHeritage() throws Exception {
        for (HeritageType type : HeritageType.values()) {
            heritageRepository.saveAll(xmlParser.parse(type.name() + ".xml"));
        }
    }

    public List<Heritage> getHeritageList() {
        return heritageRepository.findAll();
    }
}
