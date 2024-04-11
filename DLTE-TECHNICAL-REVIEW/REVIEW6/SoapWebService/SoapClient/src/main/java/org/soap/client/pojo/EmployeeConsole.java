//package org.soap.client.pojo;
//
//public class EmployeeConsole {
//    EmployeeDetailsConsole employeeDetails;
//    EmployeeAddressConsole employeePermanentAddress;
//    EmployeeAddressConsole employeeTemporaryAddress;
//
//    public EmployeeConsole(EmployeeDetailsConsole employeeDetails, EmployeeAddressConsole employeePermanentAddress, EmployeeAddressConsole employeeTemporaryAddress) {
//        this.employeeDetails = employeeDetails;
//        this.employeePermanentAddress = employeePermanentAddress;
//        this.employeeTemporaryAddress = employeeTemporaryAddress;
//    }
//
//    public EmployeeConsole() {
//    }
//
//    public EmployeeDetailsConsole getEmployeeDetails() {
//        return employeeDetails;
//    }
//
//    public void setEmployeeDetails(EmployeeDetailsConsole employeeDetails) {
//        this.employeeDetails = employeeDetails;
//    }
//
//    public EmployeeAddressConsole getEmployeePermanentAddress() {
//        return employeePermanentAddress;
//    }
//
//    public void setEmployeePermanentAddress(EmployeeAddressConsole employeePermanentAddress) {
//        this.employeePermanentAddress = employeePermanentAddress;
//    }
//
//    public EmployeeAddressConsole getEmployeeTemporaryAddress() {
//        return employeeTemporaryAddress;
//    }
//
//    public void setEmployeeTemporaryAddress(EmployeeAddressConsole employeeTemporaryAddress) {
//        this.employeeTemporaryAddress = employeeTemporaryAddress;
//    }
//
//    public String displayEmployeeDetails() {
//        return "Employee ID: " + employeeDetails.getEmployeeId() + "\n" +
//                "Name: " + employeeDetails.getEmployeeName()+ "\n" +
//                "Email: " + employeeDetails.getEmailId() + "\n" +
//                "Phone Number: " + employeeDetails.getPhoneNumber() + "\n" +
//                "Permanent Address: " + employeePermanentAddress.getStreet() + ", " +
//                employeePermanentAddress.getHouseName() + ", " +
//                employeePermanentAddress.getCity() + ", " +
//                employeePermanentAddress.getState() + ", " +
//                employeePermanentAddress.getPin() + "\n" +
//                "Temporary Address: " + employeeTemporaryAddress.getStreet()+ ", " +
//                employeeTemporaryAddress.getHouseName() + ", " +
//                employeeTemporaryAddress.getCity() + ", " +
//                employeeTemporaryAddress.getState() + ", " +
//                employeeTemporaryAddress.getPin();
//    }
//}
