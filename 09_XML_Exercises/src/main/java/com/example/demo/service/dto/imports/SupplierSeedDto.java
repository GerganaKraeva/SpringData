package com.example.demo.service.dto.imports;

import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.*;

import java.io.Serializable;

@XmlRootElement(name = "supplier")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierSeedDto implements  Serializable{

    @XmlAttribute
    @Size(min = 5)
    private String name;

    @XmlAttribute(name = "is-importer")
    private boolean isImporter;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isImporter() {
        return isImporter;
    }

    public void setImporter(boolean importer) {
        isImporter = importer;
    }
}
