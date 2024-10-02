package com.bangbang.util;

import com.bangbang.heritage.domain.Heritage;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import java.io.FileInputStream;
import java.util.List;

public class HeritageXMLParser {
    public static List<Heritage> parse(String path) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(path);
        JAXBContext jaxbContext = JAXBContext.newInstance(XMLRoot.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        XMLRoot xmlRoot = (XMLRoot) unmarshaller.unmarshal(fileInputStream);
        fileInputStream.close();
        return xmlRoot.heritages;
    }
}
