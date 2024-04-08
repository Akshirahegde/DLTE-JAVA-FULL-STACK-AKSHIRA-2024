package mybank.dao.mybankdeposit.entity;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

public class DepositAvailed {
    @NotNull(message = "{depositavailid.notnull}")
    private long depositAvailId;
    @NotNull(message = "{amount.notnull}")
    @Digits(integer = 6,fraction =2,message = "{deposit.amount}")
    private double depositAmount;
    @NotNull(message = "{duration.notnull}")
    @Digits(integer = 2,fraction = 0,message = "{deposit.duration}")
    private int depositDuration;
    @NotNull(message = "{maturity.notnull}")
    @Digits(integer = 2,fraction = 0,message = "{deposit.maturity}")
    private int depositMaturity;
    @NotNull(message = "{depositid.notnull}")
    private long depositId;
    @NotNull(message = "{customerid.notnull}")
    private long customerId;

    public DepositAvailed(long depositAvailId, double depositAmount, int depositDuration, int depositMaturity, long depositId, long customerId) {
        this.depositAvailId = depositAvailId;
        this.depositAmount = depositAmount;
        this.depositDuration = depositDuration;
        this.depositMaturity = depositMaturity;
        this.depositId = depositId;
        this.customerId = customerId;
    }

    public long getDepositAvailId() {
        return depositAvailId;
    }

    public void setDepositAvailId(long depositAvailId) {
        this.depositAvailId = depositAvailId;
    }

    public double getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(double depositAmount) {
        this.depositAmount = depositAmount;
    }

    public int getDepositDuration() {
        return depositDuration;
    }

    public void setDepositDuration(int depositDuration) {
        this.depositDuration = depositDuration;
    }

    public int getDepositMaturity() {
        return depositMaturity;
    }

    public void setDepositMaturity(int depositMaturity) {
        this.depositMaturity = depositMaturity;
    }

    public long getDepositId() {
        return depositId;
    }

    public void setDepositId(long depositId) {
        this.depositId = depositId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public DepositAvailed() {
    }

    @Override
    public String toString() {
        return "DepositAvailed{" +
                "depositAvailId=" + depositAvailId +
                ", depositAmount=" + depositAmount +
                ", depositDuration=" + depositDuration +
                ", depositMaturity=" + depositMaturity +
                ", depositId=" + depositId +
                ", customerId=" + customerId +
                '}';
    }
}
