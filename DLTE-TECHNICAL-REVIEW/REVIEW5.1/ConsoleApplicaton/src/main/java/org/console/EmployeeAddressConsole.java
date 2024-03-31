package org.console;

import org.databaseRepo.EmployeeAddress;

public class EmployeeAddressConsole extends EmployeeAddress {
    private String street;
    private String houseName;
    private String state;
    private String city;
    private Integer pinCode;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getPinCode() {
        return pinCode;
    }

    public void setPinCode(Integer pinCode) {
        this.pinCode = pinCode;
    }

    public EmployeeAddressConsole(String street, String houseName, String state, String city, Integer pinCode) {
        this.street = street;
        this.houseName = houseName;
        this.state = state;
        this.city = city;
        this.pinCode = pinCode;
    }

    public EmployeeAddressConsole() {
    }

    @Override
    public String toString() {
        return "EmployeeAddress{" +
                "street='" + street + '\'' +
                ", houseName='" + houseName + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", pinCode=" + pinCode +
                '}';
    }
}
