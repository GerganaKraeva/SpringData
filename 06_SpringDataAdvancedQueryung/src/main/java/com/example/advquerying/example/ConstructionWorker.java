package com.example.advquerying.example;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "construction_workers")
public class ConstructionWorker extends BaseUser{
   @Column
    private String hummer;


   public ConstructionWorker() {

   }
    public ConstructionWorker(String hummer) {
        this.hummer = hummer;
    }

    public String getHummer() {
        return hummer;
    }

    public void setHummer(String hummer) {
        this.hummer = hummer;
    }
}
