package softuni.exam.models.entity;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.util.Set;

@Entity
@Table(name = "stars")
public class Star extends BaseEntity {

    @Column(nullable = false,columnDefinition = "Text")
    private String description;

    @Column(name="light_years",nullable = false)
    @Positive
    private double lightYears;

    @Column(nullable = false,unique = true)
    private String name;

    @Column(name="star_type",nullable = false)
    @Enumerated(EnumType.STRING)
    private StarType starType;

    @OneToMany(mappedBy = "observingStar")
    private Set<Astronomer> observers;

    @ManyToOne
//    @JoinColumn(name = "constellation_id", referencedColumnName = "id")
    private Constellation constellation;


    public Constellation getConstellation() {
        return constellation;
    }

    public void setConstellation(Constellation constellation) {
        this.constellation = constellation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLightYears() {
        return lightYears;
    }

    public void setLightYears(double lightYears) {
        this.lightYears = lightYears;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StarType getStarType() {
        return starType;
    }

    public void setStarType(StarType starType) {
        this.starType = starType;
    }

    public Set<Astronomer> getObservers() {
        return observers;
    }

    public void setObservers(Set<Astronomer> observers) {
        this.observers = observers;
    }
}
