package com.bangbang.util.data;

import com.bangbang.domain.Heritage;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
public class HeritageXMLParser {

    private final ResourceLoader resourceLoader;

    public HeritageXMLParser(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public List<Heritage> parse(String filename) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(XMLRoot.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        File file = resourceLoader.getResource("classpath:/heritages/" + filename).getFile();
        FileInputStream fileInputStream = new FileInputStream(file);
        XMLRoot xmlRoot = (XMLRoot) unmarshaller.unmarshal(fileInputStream);
        fileInputStream.close();
        return xmlRoot.heritages;
    }
}
