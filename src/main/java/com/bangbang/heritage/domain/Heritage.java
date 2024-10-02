package com.bangbang.heritage.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;

@Entity
@Getter
@XmlAccessorType(XmlAccessType.FIELD)
public class Heritage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @XmlElement(name = "ccbaMnm")
    private String name;
    @XmlElement(name = "ccmaName")
    private HeritageType type;
    //UTM-K 기준 좌표
    @XmlElement(name = "cnY")
    private double utmkN;
    @XmlElement(name = "cnX")
    private double utmkE;
}
