package com.example.demo.service.dto.exports;

import jakarta.xml.bind.annotation.*;

import java.io.Serializable;

@XmlRootElement(name = "supplier")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierLocalDto implements Serializable {

    @XmlAttribute
    private long id;

    @XmlAttribute
    private String name;

    @XmlAttribute(name = "parts-count")
    private int partsCount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPartsCount() {
        return partsCount;
    }

    public void setPartsCount(int partsCount) {
        this.partsCount = partsCount;
    }
}