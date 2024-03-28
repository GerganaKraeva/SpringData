package com.example.demo.service;

import jakarta.xml.bind.JAXBException;
import org.springframework.stereotype.Service;


public interface CarService {
    void seedCars() throws JAXBException;

    void exportToyotaCars() throws JAXBException;
    void exportCarsAndParts() throws JAXBException;
}
