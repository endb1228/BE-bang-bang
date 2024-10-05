package com.bangbang.service;

import com.bangbang.domain.Heritage;
import com.bangbang.domain.HeritageType;
import com.bangbang.repository.HeritageRepository;
import com.bangbang.util.data.HeritageXMLParser;
import jakarta.annotation.PostConstruct;
import java.util.List;
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
