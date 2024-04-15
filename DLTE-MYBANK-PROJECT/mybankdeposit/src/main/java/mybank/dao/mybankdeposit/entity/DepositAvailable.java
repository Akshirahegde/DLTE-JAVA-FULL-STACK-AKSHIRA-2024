package mybank.dao.mybankdeposit.entity;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

public class DepositAvailable {

    @NotNull
    @Digits(integer=7,fraction = 0,message = "{deposit.id}")
    private Long depositId;
    @NotNull
    private String depositName;
    @NotNull
    @Digits(integer=2,fraction = 2,message = "{deposit.roi}")
    private Double depositRoi;
    @NotNull
    private String depositType;
    @NotNull
    private  String depositDescription;

    public DepositAvailable() {
    }

    @Override
    public String toString() {
        return "DepositAvailable{" +
                "depositId=" + depositId +
                ", depositName='" + depositName + '\'' +
                ", depositRoi=" + depositRoi +
                ", depositType='" + depositType + '\'' +
                ", depositDescription='" + depositDescription + '\'' +
                '}';
    }

    public Long getDepositId() {
        return depositId;
    }

    public void setDepositId(Long depositId) {
        this.depositId = depositId;
    }

    public String getDepositName() {
        return depositName;
    }

    public void setDepositName(String depositName) {
        this.depositName = depositName;
    }

    public Double getDepositRoi() {
        return depositRoi;
    }

    public void setDepositRoi(Double depositRoi) {
        this.depositRoi = depositRoi;
    }

    public String getDepositType() {
        return depositType;
    }

    public void setDepositType(String depositType) {
        this.depositType = depositType;
    }

    public String getDepositDescription() {
        return depositDescription;
    }

    public void setDepositDescription(String depositDescription) {
        this.depositDescription = depositDescription;
    }

    public DepositAvailable(@NotNull @Digits(integer = 7, fraction = 0, message = "{deposit.id}") Long depositId, @NotNull String depositName, @NotNull @Digits(integer = 2, fraction = 2, message = "{deposit.roi}") Double depositRoi, @NotNull String depositType, @NotNull String depositDescription) {
        this.depositId = depositId;
        this.depositName = depositName;
        this.depositRoi = depositRoi;
        this.depositType = depositType;
        this.depositDescription = depositDescription;
    }

    //    public DepositAvailable(@NotNull @Digits(integer = 7, fraction = 0, message = "{deposit.id}") Long depositId, @NotNull String depositName, @NotNull @Digits(integer = 2, fraction = 2, message = "{deposit.roi}") Double depositRoi, @NotNull String depositType, @NotNull String depositDescription) {
//        this.depositId = depositId;
//        this.depositName = depositName;
//        this.depositRoi = depositRoi;
//        this.depositType = depositType;
//        this.depositDescription = depositDescription;
//    }
//
//    public Long getDepositId() {
//        return depositId;
//    }
//
//    public void setDepositId(Long depositId) {
//        this.depositId = depositId;
//    }
//
//    public String getDepositName() {
//        return depositName;
//    }
//
//    public void setDepositName(String depositName) {
//        this.depositName = depositName;
//    }
//
//    public Double getDepositRoi() {
//        return depositRoi;
//    }
//
//    public void setDepositRoi(Double depositRoi) {
//        this.depositRoi = depositRoi;
//    }
//
//    public String getDepositType() {
//        return depositType;
//    }
//
//    public void setDepositType(String depositType) {
//        this.depositType = depositType;
//    }
//
//    public String getDepositDescription() {
//        return depositDescription;
//    }
//
//    public void setDepositDescription(String depositDescription) {
//        this.depositDescription = depositDescription;
//    }
//
//    public DepositAvailable() {
//    }
//
//    @Override
//    public String toString() {
//        return "DepositAvailable{" +
//                "depositId=" + depositId +
//                ", depositName='" + depositName + '\'' +
//                ", depositRoi=" + depositRoi +
//                ", depositType='" + depositType + '\'' +
//                ", depositDescription='" + depositDescription + '\'' +
//                '}';
//    }
}
