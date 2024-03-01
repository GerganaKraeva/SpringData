package lab.composition;

import jakarta.persistence.*;
import lab.inheritance.Plane;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToMany(mappedBy = "owner")
    private List<Plane> planes;

    public Company() {
        this.planes = new ArrayList<>();
    }

    public Company(String name) {
        this();
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Plane> getPlanes() {
        return planes;
    }


    public void setId(final long id) {
        this.id = id;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setPlanes(final List<Plane> planes) {
        this.planes = planes;
    }
}
