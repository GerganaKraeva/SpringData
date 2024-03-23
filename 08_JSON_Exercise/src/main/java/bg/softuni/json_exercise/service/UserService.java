package bg.softuni.json_exercise.service;

import bg.softuni.json_exercise.service.dtos.export.UserAndProductsDTO;
import bg.softuni.json_exercise.service.dtos.export.UserSoldProductsDTO;

import java.io.FileNotFoundException;
import java.util.List;

public interface UserService {
    void seedUsers() throws FileNotFoundException;

    List<UserSoldProductsDTO> getAllUsersAndSoldItems();

     void printAllUsersAndSoldItems();

     UserAndProductsDTO getUserAndProductsDto();
     void printGetUserAndProductsDTO();
}
