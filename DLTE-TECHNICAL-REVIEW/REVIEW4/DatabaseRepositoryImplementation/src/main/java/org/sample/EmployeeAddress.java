package org.sample;

public class EmployeeAddress {
    private String employeeHouseName;
    private String employeeStreet;
    private String employeeCity;
    private String employeeState;
    private String employeePinCode;

    public EmployeeAddress(String employeeHouseName, String employeeStreet, String employeeCity, String employeeState, String employeePinCode) {
        this.employeeHouseName = employeeHouseName;
        this.employeeStreet = employeeStreet;
        this.employeeCity = employeeCity;
        this.employeeState = employeeState;
        this.employeePinCode = employeePinCode;
    }

    public String getEmployeeHouseName() {
        return employeeHouseName;
    }

    public void setEmployeeHouseName(String employeeHouseName) {
        this.employeeHouseName = employeeHouseName;
    }

    public String getEmployeeStreet() {
        return employeeStreet;
    }

    public void setEmployeeStreet(String employeeStreet) {
        this.employeeStreet = employeeStreet;
    }

    public String getEmployeeCity() {
        return employeeCity;
    }

    public void setEmployeeCity(String employeeCity) {
        this.employeeCity = employeeCity;
    }

    public String getEmployeeState() {
        return employeeState;
    }

    public void setEmployeeState(String employeeState) {
        this.employeeState = employeeState;
    }

    public String getEmployeePinCode() {
        return employeePinCode;
    }

    public void setEmployeePinCode(String employeePinCode) {
        this.employeePinCode = employeePinCode;
    }

    @Override
    public String toString() {
        return "EmployeeAddress{" +
                "employeeHouseName='" + employeeHouseName + '\'' +
                ", employeeStreet='" + employeeStreet + '\'' +
                ", employeeCity='" + employeeCity + '\'' +
                ", employeeState='" + employeeState + '\'' +
                ", employeePinCode='" + employeePinCode + '\'' +
                '}';
    }
}
