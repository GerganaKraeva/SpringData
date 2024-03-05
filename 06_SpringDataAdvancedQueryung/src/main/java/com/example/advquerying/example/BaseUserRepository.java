package com.example.advquerying.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseUserRepository  <T extends BaseUser> extends JpaRepository <T,Long> {
 T findByEmail(String email);
 T findByFirstNameAndLastName(String firstName, String lastName);

}
