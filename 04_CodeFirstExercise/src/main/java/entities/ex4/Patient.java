package entities.ex4;

import entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "patients")
public class Patient extends BaseEntity {
   @Column(name = "first_name",nullable = false)
    private String firstName;

   @Column(name="last_name", nullable = false)
    private String lastName;

   @Column
    private String address;

   @Column
    private String email;

   @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

   @Column
    private String picture;

   @Column(name = "has_medical_insurance")
    private boolean hasMedicalInsurance;

   @OneToMany(mappedBy = "patient")
   private Set<Visitation> visitations;



}
