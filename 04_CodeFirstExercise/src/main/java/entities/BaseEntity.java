package entities;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class BaseEntity {
    @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    private  int id;

    protected BaseEntity() {}

    public int getId() {
        return id;
    }
}
