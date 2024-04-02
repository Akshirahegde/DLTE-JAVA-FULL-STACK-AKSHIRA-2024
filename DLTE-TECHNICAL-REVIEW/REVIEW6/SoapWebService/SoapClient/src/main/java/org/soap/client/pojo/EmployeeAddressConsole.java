package org.consoleapp.pojo;

public class EmployeeAddressConsole {
    private String street;
    private String houseName;
    private String city;
    private String state;
    private int pin;

    public EmployeeAddressConsole(String street, String houseName, String city, String state, int pin) {
        this.street = street;
        this.houseName = houseName;
        this.city = city;
        this.state = state;
        this.pin = pin;
    }


    public EmployeeAddressConsole() {
    }

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }
}
