package bg.softuni.bookshopsystem.data.entities.base;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class  BaseClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    protected BaseClass() {

    }

    public int getId() {
        return id;
    }
}
