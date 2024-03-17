package bg.softuni.modelmapperexercise.service.dtos;

public class GamesAllDTO {
    private String title;

    private double price;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;

    }

    @Override
    public String toString() {
        return title+" "+price;
    }
}
