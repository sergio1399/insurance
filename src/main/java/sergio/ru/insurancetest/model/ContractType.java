package sergio.ru.insurancetest.model;

public class ContractType {

    private Integer id;

    private String name;

    public ContractType() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ContractType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
