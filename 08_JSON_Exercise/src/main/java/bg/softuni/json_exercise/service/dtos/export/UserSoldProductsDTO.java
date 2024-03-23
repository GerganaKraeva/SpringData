package bg.softuni.json_exercise.service.dtos.export;

import bg.softuni.json_exercise.service.dtos.export.ProductSoldDTO;
import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

public class UserSoldProductsDTO implements Serializable {

    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private List<ProductSoldDTO> soldProducts;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<ProductSoldDTO> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(List<ProductSoldDTO> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
