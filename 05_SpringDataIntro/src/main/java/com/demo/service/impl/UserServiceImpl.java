package com.demo.service.impl;

import com.demo.data.entities.User;
import com.demo.data.repositories.UserRepository;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private  final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void register(User user) {
     this.userRepository.saveAndFlush(user);
    }

    @Override
    public User findUserById(int i) {
        Optional<User> optional = this.userRepository.findById(i);
        if(optional.isPresent()){
            return optional.get();
        }
        System.out.println("No such user with given id "+ i);
        return null;
    }

    @Override
    public User findByName(String name) {
        return this.userRepository.findByUserName(name);
    }
}
