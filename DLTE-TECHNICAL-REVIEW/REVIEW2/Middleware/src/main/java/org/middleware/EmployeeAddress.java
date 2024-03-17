package org.middleware;

import java.io.Serializable;

public class EmployeeAddress implements Serializable {
    private String permanentAddress;
    private String permanentHouseName;
    private String permanentState;
    private String permanentCity;
    private Integer permanentPinCode;
    private String temporaryAddress;
    private String temporaryHouseName;
    private String temporaryState;
    private String temporaryCity;
    private Integer temporaryPinCode;

    public EmployeeAddress(String permanentAddress, String permanentHouseNumber, String permanentState, Integer permanentPinCode, String temporaryAddress, String temporaryHouseNumber, String temporaryState, Integer temporaryPinCode) {
        this.permanentAddress = permanentAddress;
        this.permanentHouseName = permanentHouseNumber;
        this.permanentState = permanentState;
        this.permanentCity = permanentCity;
        this.permanentPinCode = permanentPinCode;
        this.temporaryAddress = temporaryAddress;
        this.temporaryHouseName = temporaryHouseNumber;
        this.temporaryState = temporaryState;
        this.temporaryCity = temporaryCity;
        this.temporaryPinCode = temporaryPinCode;
    }

    @Override
    public String toString() {
        return "EmployeeAddress{" +
                "permanentAddress='" + permanentAddress + '\'' +
                ", permanentHouseNumber='" + permanentHouseName + '\'' +
                ", permanentState='" + permanentState + '\'' +
                ", permanentCity='" + permanentCity + '\'' +
                ", permanentPinCode=" + permanentPinCode +
                ", temporaryAddress='" + temporaryAddress + '\'' +
                ", temporaryHouseNumber='" + temporaryHouseName + '\'' +
                ", temporaryState='" + temporaryState + '\'' +
                ", temporaryCity='" + temporaryCity + '\'' +
                ", temporaryPinCode=" + temporaryPinCode +
                '}';
    }

    public EmployeeAddress() {
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getPermanentHouseNumber() {
        return permanentHouseName;
    }

    public void setPermanentHouseNumber(String permanentHouseNumber) {
        this.permanentHouseName = permanentHouseNumber;
    }

    public String getPermanentState() {
        return permanentState;
    }

    public void setPermanentState(String permanentState) {
        this.permanentState = permanentState;
    }

    public String getPermanentCity() {
        return permanentCity;
    }

    public void setPermanentCity(String permanentCity) {
        this.permanentCity = permanentCity;
    }

    public Integer getPermanentPinCode() {
        return permanentPinCode;
    }

    public void setPermanentPinCode(Integer permanentPinCode) {
        this.permanentPinCode = permanentPinCode;
    }

    public String getTemporaryAddress() {
        return temporaryAddress;
    }

    public void setTemporaryAddress(String temporaryAddress) {
        this.temporaryAddress = temporaryAddress;
    }

    public String getTemporaryHouseNumber() {
        return temporaryHouseName;
    }

    public void setTemporaryHouseNumber(String temporaryHouseNumber) {
        this.temporaryHouseName = temporaryHouseNumber;
    }

    public String getTemporaryState() {
        return temporaryState;
    }

    public void setTemporaryState(String temporaryState) {
        this.temporaryState = temporaryState;
    }

    public String getTemporaryCity() {
        return temporaryCity;
    }

    public void setTemporaryCity(String temporaryCity) {
        this.temporaryCity = temporaryCity;
    }

    public Integer getTemporaryPinCode() {
        return temporaryPinCode;
    }

    public void setTemporaryPinCode(Integer temporaryPinCode) {
        this.temporaryPinCode = temporaryPinCode;
    }

}
