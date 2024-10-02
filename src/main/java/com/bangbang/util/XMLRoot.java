package com.bangbang.util;

import com.bangbang.heritage.domain.Heritage;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "root")
public class XMLRoot {
    @XmlElement(name = "spca")
    public
    List<Heritage> heritages;
}
