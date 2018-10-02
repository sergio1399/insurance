package sergio.ru.insurancetest.dto;

import java.time.LocalDate;

public class ContractDto {

    private String serieNumber;

    private String contractType;

    private LocalDate signDate;

    private LocalDate openDate;

    private LocalDate expirationDate;

    private Double sumNoNds;

    private Double ndsRate;

    private Double ndsSum;

    private Double sumWithNds;

    private Boolean minSumAccord;

    private String vehicleNumber;

    public ContractDto() {
    }

    public String getSerieNumber() {
        return serieNumber;
    }

    public void setSerieNumber(String serieNumber) {
        this.serieNumber = serieNumber;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
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
        return sumNoNds;
    }

    public void setSumNoNds(Double sumNoNds) {
        this.sumNoNds = sumNoNds;
    }

    public Double getNdsRate() {
        return ndsRate;
    }

    public void setNdsRate(Double ndsRate) {
        this.ndsRate = ndsRate;
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

    public Boolean getMinSumAccord() {
        return minSumAccord;
    }

    public void setMinSumAccord(Boolean minSumAccord) {
        this.minSumAccord = minSumAccord;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    @Override
    public String toString() {
        return "ContractDto{" +
                "serieNumber='" + serieNumber + '\'' +
                ", contractType='" + contractType + '\'' +
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
}
