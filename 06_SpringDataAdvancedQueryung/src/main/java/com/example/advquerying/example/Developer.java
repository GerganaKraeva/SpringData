package com.example.advquerying.example;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "developers")
public class Developer extends BaseUser{
     @Column
    private String laptop;

     public Developer(){}

    public void setLaptop(String laptop) {
        this.laptop = laptop;
    }

    public String getLaptop() {
        return laptop;
    }
}
