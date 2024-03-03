package lab.inheritance;

import jakarta.persistence.*;
import lab.composition.PlateNumber;

import java.math.BigDecimal;
@Entity
//@Table(name = "cars") only for the first and the second solution
public class Car extends Vehicle {
    private static final String CAR_TYPE ="CAR";
    @Basic
    private int seats;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="plate_number_id", referencedColumnName="id")
    private PlateNumber plateNumber;

    public Car() {}


    public Car(String model, BigDecimal price, String fuelType, int seats,PlateNumber plateNumber) {
        super(CAR_TYPE, model, price, fuelType);
        this.seats = seats;
        this.plateNumber = plateNumber;

    }

}
