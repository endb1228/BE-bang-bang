package com.bangbang;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.bangbang.heritage.domain.Heritage;
import com.bangbang.util.XMLRoot;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class XmlParseTest {
    @Test
    public void test() throws JAXBException, IOException {
        FileInputStream fileInputStream = new FileInputStream("/Users/jonghyeoklee/Documents/Project/bangbang/src/main/resources/heritages/국보.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(XMLRoot.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        // When
        XMLRoot xmlRoot = (XMLRoot) unmarshaller.unmarshal(fileInputStream);
        fileInputStream.close();

        // Then
        assertNotNull(xmlRoot.heritages);
    }
}
