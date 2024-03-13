package bg.softuni.json_lab.services;

import org.springframework.stereotype.Service;

//@Service
public class PersonService {

    private AddressService addressService;

    public PersonService(AddressService addressService) {
        this.addressService = addressService;
    }
}
