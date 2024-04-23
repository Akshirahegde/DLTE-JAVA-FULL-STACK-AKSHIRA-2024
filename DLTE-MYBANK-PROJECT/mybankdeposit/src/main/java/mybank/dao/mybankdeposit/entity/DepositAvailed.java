package mybank.dao.mybankdeposit.entity;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

public class DepositAvailed {

    private long depositAvailId;

    private double depositAmount;

    private int depositDuration;

    private int depositMaturity;

    private long depositId;

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
