package bg.softuni.bookshopsystem.data.entities;

import bg.softuni.bookshopsystem.data.entities.base.BaseClass;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "authors")
public class Author extends BaseClass {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @OneToMany(mappedBy = "author",fetch= FetchType.EAGER)
    private Set<Book> books;

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Author() {

    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Book> getBooks() {
        return books;
    }

}

