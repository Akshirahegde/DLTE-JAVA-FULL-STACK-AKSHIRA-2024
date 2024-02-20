package basics.service;

import lombok.Data;
import lombok.Setter;

import java.util.Date;
@Data//annotation
@Setter

public class CreditCard {
    private Long creditCardNumber;
    private String creditCardHolder;
    private Date creditCardExpiry;
    private Integer creditCardCVV;
    private Integer creditCardLimit;
    private Date dateofBillGeneration;
    private Date dateofBillPayment;
    private Integer creditCardPin;
    //default constructor

    public CreditCard() {
        System.out.println("Initialize thae card properties manually");
    }
//parameterized constructor
    public CreditCard(Long creditCardNumber, String creditCardHolder, Date creditCardExpiry, Integer creditCardCVV, Integer creditCardLimit, Date dateofBillGeneration, Date dateofBillPayment, Integer creditCardPin) {
        this.creditCardNumber = creditCardNumber;
        this.creditCardHolder = creditCardHolder;
        this.creditCardExpiry = creditCardExpiry;
        this.creditCardCVV = creditCardCVV;
        this.creditCardLimit = creditCardLimit;
        this.dateofBillGeneration = dateofBillGeneration;
        this.dateofBillPayment = dateofBillPayment;
        this.creditCardPin = creditCardPin;
    }
//toString is used to display the values
    @Override
    public String toString() {
        return "CreditCard{" +
                "creditCardNumber=" + creditCardNumber +
                ", creditCardHolder='" + creditCardHolder + '\'' +
                ", creditCardExpiry=" + creditCardExpiry +
                ", creditCardCVV=" + creditCardCVV +
                ", creditCardLimit=" + creditCardLimit +
                ", dateofBillGeneration=" + dateofBillGeneration +
                ", dateofBillPayment=" + dateofBillPayment +
                ", creditCardPin=" + creditCardPin +
                '}';
    }










    public Long getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(Long creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getCreditCardHolder() {
        return creditCardHolder;
    }

    public void setCreditCardHolder(String creditCardHolder) {
        this.creditCardHolder = creditCardHolder;
    }

    public Date getCreditCardExpiry() {
        return creditCardExpiry;
    }

    public void setCreditCardExpiry(Date creditCardExpiry) {
        this.creditCardExpiry = creditCardExpiry;
    }

    public Integer getCreditCardCVV() {
        return creditCardCVV;
    }

    public void setCreditCardCVV(Integer creditCardCVV) {
        this.creditCardCVV = creditCardCVV;
    }

    public Integer getCreditCardLimit() {
        return creditCardLimit;
    }

    public void setCreditCardLimit(Integer creditCardLimit) {
        this.creditCardLimit = creditCardLimit;
    }

    public Date getDateofBillGeneration() {
        return dateofBillGeneration;
    }

    public void setDateofBillGeneration(Date dateofBillGeneration) {
        this.dateofBillGeneration = dateofBillGeneration;
    }

    public Date getDateofBillPayment() {
        return dateofBillPayment;
    }

    public void setDateofBillPayment(Date dateofBillPayment) {
        this.dateofBillPayment = dateofBillPayment;
    }

    public Integer getCreditCardPin() {
        return creditCardPin;
    }

    public void setCreditCardPin(Integer creditCardPin) {
        this.creditCardPin = creditCardPin;
    }
}
