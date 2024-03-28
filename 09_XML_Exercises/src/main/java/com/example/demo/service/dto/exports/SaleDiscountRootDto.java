package com.example.demo.service.dto.exports;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.List;

@XmlRootElement(name="sales")
@XmlAccessorType(XmlAccessType.FIELD)
public class SaleDiscountRootDto implements Serializable {

    @XmlElement(name = "sale")
    private List<SaleDiscountDto> saleDiscountDtoList;

    public List<SaleDiscountDto> getSaleDiscountDtoList() {
        return saleDiscountDtoList;
    }

    public void setSaleDiscountDtoList(List<SaleDiscountDto> saleDiscountDtoList) {
        this.saleDiscountDtoList = saleDiscountDtoList;
    }
}
