package com.demo.data.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")
public class User extends BaseEntity {

    @Column(unique = true)
    private String userName;

    @Column
    private int age;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private Set<Account> accounts;

    public User () {
        this.accounts= new HashSet<>();
    }

    public User(String userName, int age) {
        this.userName = userName;
        this.age = age;
        this.accounts = new HashSet<>();
    }

    public String getUserName() {
        return userName;
    }

    public int getAge() {
        return age;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }
}
