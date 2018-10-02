package sergio.ru.insurancetest.model;

public class Vehicle {

    private String number;

    private String brand;

    private String model;

    private Integer yearOfCreation;

    public Vehicle() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYearOfCreation() {
        return yearOfCreation;
    }

    public void setYearOfCreation(Integer yearOfCreation) {
        this.yearOfCreation = yearOfCreation;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "number='" + number + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", yearOfCreation=" + yearOfCreation +
                '}';
    }
}
