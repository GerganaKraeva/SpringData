package entities.ex5;

import entities.BaseEntity;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "first_name",nullable = false)
    private String firstName;

    @Column(name = "last_name",nullable = false)
    private String lastName;

    @Column
    private String email;

    @Column
    private String password;

    @OneToMany(mappedBy = "user")
    private Set <BillingDetails>  billingDetails;

    @OneToMany()

    private Set<BillingDetails>  billingDetailsSet;


}
