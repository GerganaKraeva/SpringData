package com.demo.service;

import com.demo.data.entities.User;

public interface UserService  {
   void register(User user);

  User  findUserById(int i);

  User findByName(String name);

}
