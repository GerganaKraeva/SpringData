package bg.softuni.modelmapperexercise.service;

import bg.softuni.modelmapperexercise.data.entities.User;
import bg.softuni.modelmapperexercise.service.dtos.UserLoginDTO;
import bg.softuni.modelmapperexercise.service.dtos.UserRegisterDTO;

public interface UserService {
    String registerUser(UserRegisterDTO userRegisterDTO);

    String loginUser(UserLoginDTO userLoginDTO);

    String logoutUser();

    User getLoggedIn();
}
