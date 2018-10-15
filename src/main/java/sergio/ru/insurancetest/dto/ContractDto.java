package sergio.ru.insurancetest.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class ContractDto {

    private Integer id;

    private String serie;

    private String number;

    private String type;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate signDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate openDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expirationDate;

    private Double sumNoNds;

    private Integer ndsRate;

    private Double ndsSum;

    private Double sumWithNds;

    private Boolean minSumAccord;

    private String accord;

    private String vehicleNumber;

    private String note;

    public ContractDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Double getSumNoNds() {
        return round(sumNoNds, 2);
    }

    public void setSumNoNds() {
        this.sumNoNds = sumWithNds - ndsSum;
    }

    public Integer getNdsRate() {
        return ndsRate;
    }

    public void setNdsRate() {
        this.ndsRate = (int)((ndsSum / sumWithNds) * 100);
    }

    public Double getNdsSum() {
        return round(ndsSum, 2);
    }

    public void setNdsSum(Double ndsSum) {
        this.ndsSum = ndsSum;
    }

    public Double getSumWithNds() {
        return round(sumWithNds,2);
    }

    public void setSumWithNds(Double sumWithNds) {
        this.sumWithNds = sumWithNds;
    }

    public Boolean getMinSumAccord() {
        return minSumAccord;
    }

    public void setMinSumAccord() {
        if (sumWithNds > 1000) {
            this.minSumAccord = true;
            accord = "Да";
        } else {
            this.minSumAccord = false;
            accord = "Нет";
        }
    }

    public String getAccord() {
        return accord;
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
        return "ContractDto{" +
                "id=" + id +
                ", serie='" + serie + '\'' +
                ", number='" + number + '\'' +
                ", type='" + type + '\'' +
                ", signDate=" + signDate +
                ", openDate=" + openDate +
                ", expirationDate=" + expirationDate +
                ", sumNoNds=" + sumNoNds +
                ", ndsRate=" + ndsRate +
                ", ndsSum=" + ndsSum +
                ", sumWithNds=" + sumWithNds +
                ", minSumAccord=" + minSumAccord +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                '}';
    }

    public boolean isNew() {
        return (this.id == null);
    }

    private double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
