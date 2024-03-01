package lab.inheritance;

import jakarta.persistence.*;

//@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

@MappedSuperclass
public class IdType {
    //   First task @GeneratedValue(strategy = GenerationType.TABLE)
//   Second task @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    @Basic
    protected String type;
}
