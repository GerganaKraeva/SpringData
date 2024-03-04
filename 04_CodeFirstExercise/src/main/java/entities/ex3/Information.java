package entities.ex3;

import entities.BaseEntity;
import jakarta.persistence.*;

//@Entity
//@Table (name = "informations")
//@Inheritance(strategy = InheritanceType.JOINED)
@MappedSuperclass
public abstract class Information extends BaseEntity {

    @Column(name = "first_name",nullable = false)
    private String firstName;

    @Column(name = "last_name",nullable = false)
    private String lastName;

    @Column
    private String phone;
}
