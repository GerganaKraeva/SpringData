package bg.softuni.json_exercise.service.dtos.export;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

public class ProductSoldByUserDTO implements Serializable {
    @Expose
    private int count;
    @Expose
    private List<ProductInfoDTO> products;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ProductInfoDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductInfoDTO> products) {
        this.products = products;
    }
}
