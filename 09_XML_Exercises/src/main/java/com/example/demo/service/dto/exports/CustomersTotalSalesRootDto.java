package com.example.demo.service.dto.exports;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomersTotalSalesRootDto implements Serializable {

    @XmlElement(name="customer")
    private List<CustomerTotalSaleDto> customerTotalSaleDtoList;

    public List<CustomerTotalSaleDto> getCustomerTotalSaleDtoList() {
        return customerTotalSaleDtoList;
    }

    public void setCustomerTotalSaleDtoList(List<CustomerTotalSaleDto> customerTotalSaleDtoList) {
        this.customerTotalSaleDtoList = customerTotalSaleDtoList;
    }
}
