package com.example.demo.service;

import jakarta.xml.bind.JAXBException;

public interface SupplierService {
void seedSupplier() throws JAXBException;

void exportLocalSuppliers() throws JAXBException;
}
