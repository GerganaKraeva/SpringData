package lab.inheritance;

import jakarta.persistence.*;

import javax.lang.model.element.Name;
import java.math.BigDecimal;

@Entity
//  First task @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)


//Second task @Table(name = "vehicle")
//@Inheritance(strategy = InheritanceType.JOINED)

@Table(name = "vehicle")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Vehicle extends IdType {
    @Basic
    private String model;

    @Basic
    private BigDecimal price;

    @Column(name = "fuel_type")
    private String fuelType;

    protected Vehicle() {
    }

    protected Vehicle(String type) {
        super.type = type;
    }

    protected Vehicle(String type, String model, BigDecimal price, String fuelType) {
        this.type = type;
        this.model = model;
        this.price = price;
        this.fuelType = fuelType;
    }
}
