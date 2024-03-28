package com.example.demo.service.dto.exports;

import com.example.demo.data.entities.Supplier;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierLocalRootDto implements Serializable {

    @XmlElement(name = "supplier")
    private List<SupplierLocalDto> supplierLocalDtoList;

    public List<SupplierLocalDto> getSupplierLocalDtoList() {
        return supplierLocalDtoList;
    }

    public void setSupplierLocalDtoList(List<SupplierLocalDto> supplierLocalDtoList) {
        this.supplierLocalDtoList = supplierLocalDtoList;
    }
}
