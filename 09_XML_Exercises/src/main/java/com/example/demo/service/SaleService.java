package com.example.demo.service;

import jakarta.xml.bind.JAXBException;

public interface SaleService {
    void seedSales();
    void exportSales() throws JAXBException;

}
