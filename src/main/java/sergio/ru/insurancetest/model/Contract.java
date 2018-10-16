package sergio.ru.insurancetest.model;

import java.time.LocalDate;
import java.util.Objects;

public class Contract {

    private Integer id;

    private String type;

    private String serie;

    private String number;

    private LocalDate signDate;

    private LocalDate openDate;

    private LocalDate expirationDate;

    private Double ndsSum;

    private Double sumWithNds;

    private String vehicleNumber;

    private String note;

    public Contract() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getSignDate() {
        return signDate;
    }

    public void setSignDate(LocalDate signDate) {
        this.signDate = signDate;
    }

    public LocalDate getOpenDate() {
        return openDate;
    }

    public void setOpenDate(LocalDate openDate) {
        this.openDate = openDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Double getNdsSum() {
        return ndsSum;
    }

    public void setNdsSum(Double ndsSum) {
        this.ndsSum = ndsSum;
    }

    public Double getSumWithNds() {
        return sumWithNds;
    }

    public void setSumWithNds(Double sumWithNds) {
        this.sumWithNds = sumWithNds;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", serie='" + serie + '\'' +
                ", number='" + number + '\'' +
                ", signDate=" + signDate +
                ", openDate=" + openDate +
                ", expirationDate=" + expirationDate +
                ", ndsSum=" + ndsSum +
                ", sumWithNds=" + sumWithNds +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", note='" + note + '\'' +
                '}';
    }

    public boolean isNew() {
        return (this.id == null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contract contract = (Contract) o;
        return Objects.equals(id, contract.id) &&
                Objects.equals(type, contract.type) &&
                Objects.equals(serie, contract.serie) &&
                Objects.equals(number, contract.number) &&
                Objects.equals(signDate, contract.signDate) &&
                Objects.equals(openDate, contract.openDate) &&
                Objects.equals(expirationDate, contract.expirationDate) &&
                Objects.equals(ndsSum, contract.ndsSum) &&
                Objects.equals(sumWithNds, contract.sumWithNds) &&
                Objects.equals(vehicleNumber, contract.vehicleNumber) &&
                Objects.equals(note, contract.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, serie, number, signDate, openDate, expirationDate, ndsSum, sumWithNds, vehicleNumber, note);
    }
}
