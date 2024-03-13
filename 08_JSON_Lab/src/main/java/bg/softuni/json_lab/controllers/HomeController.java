package bg.softuni.json_lab.controllers;

import bg.softuni.json_lab.dtos.PersonDTO;
import jakarta.validation.Valid;

public class HomeController {
//
//    public PersonDTO getPerson(int id) {
//        return ....;
////THE RETURN WILL BE IN JSON format
//    }

    public void updatePerson(int id, @Valid PersonDTO data) {
//        takes JSON format and returns data in DTO;
    }
}
