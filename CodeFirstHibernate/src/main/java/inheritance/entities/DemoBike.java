package inheritance.entities;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "bikes")
@DiscriminatorValue("OurBike")
public class DemoBike extends DemoVehicle {

    private static final String BIKE_TYPE="BIKE";

    public DemoBike(){
        super("BIKE_TYPE");
    }
}

