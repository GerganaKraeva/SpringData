package lab.inheritance;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
//@Table(name="bikes") only for the first and the second solution
public class Bike extends Vehicle {
    private static final String BIKE_TYPE ="BIKE";

   public Bike() {
   }

    public Bike(String model, BigDecimal price, String fuelType) {
        super(BIKE_TYPE, model, price, fuelType);
    }
}
