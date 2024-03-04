package entities.ex4;

import entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "medicaments")
public class Medicament extends BaseEntity {

    @Column
    private String name;

    @ManyToMany(mappedBy = "medicaments")
    private Set<Diagnose> diagnoses;

}
