package com.example.demo.service;

import jakarta.xml.bind.JAXBException;

public interface CustomerService {
    void seedCustomers() throws JAXBException;

    void exportOrderedCustomers() throws JAXBException;

   void exportCustomersWithCars() throws JAXBException;
}
