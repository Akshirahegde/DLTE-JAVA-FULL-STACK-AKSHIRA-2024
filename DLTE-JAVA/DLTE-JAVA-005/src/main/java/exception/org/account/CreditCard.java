package exception.org.account;

import java.util.Date;

public class CreditCard {
    private String creditcardHolder;
    private Integer creditcardCvv;
    private Integer creditcardPin;
    private Integer creditcardLimit;
    private Date billGeneration;
    private  Date billPayment;
    private Long creditcardNumber;

    public String getCreditcardHolder() {
        return creditcardHolder;
    }

    public void setCreditcardHolder(String creditcardHolder) {
        this.creditcardHolder = creditcardHolder;
    }

    public Integer getCreditcardCvv() {
        return creditcardCvv;
    }

    public void setCreditcardCvv(Integer creditcardCvv) {
        this.creditcardCvv = creditcardCvv;
    }

    public Integer getCreditcardPin() {
        return creditcardPin;
    }

    public void setCreditcardPin(Integer creditcardPin) {
        this.creditcardPin = creditcardPin;
    }

    public Integer getCreditcardLimit() {
        return creditcardLimit;
    }

    public void setCreditcardLimit(Integer creditcardLimit) {
        this.creditcardLimit = creditcardLimit;
    }

    public Date getBillGeneration() {
        return billGeneration;
    }

    public void setBillGeneration(Date billGeneration) {
        this.billGeneration = billGeneration;
    }

    public Date getBillPayment() {
        return billPayment;
    }

    public void setBillPayment(Date billPayment) {
        this.billPayment = billPayment;
    }

    public Long getCreditcardNumber() {
        return creditcardNumber;
    }

    public void setCreditcardNumber(Long creditcardNumber) {
        this.creditcardNumber = creditcardNumber;
    }

    public CreditCard(String creditcardHolder, Integer creditcardCvv, Integer creditcardPin, Integer creditcardLimit, Date billGeneration, Date billPayment, Long creditcardNumber) {
        this.creditcardHolder = creditcardHolder;
        this.creditcardCvv = creditcardCvv;
        this.creditcardPin = creditcardPin;
        this.creditcardLimit = creditcardLimit;
        this.billGeneration = billGeneration;
        this.billPayment = billPayment;
        this.creditcardNumber = creditcardNumber;
    }
}
