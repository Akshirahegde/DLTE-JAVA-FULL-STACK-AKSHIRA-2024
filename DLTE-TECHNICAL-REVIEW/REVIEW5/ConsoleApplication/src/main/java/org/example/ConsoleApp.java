package org.example;

import org.example.Details.Employee;
import org.example.Details.EmployeeAddress;
import org.example.Details.EmployeeBasicDetails;
import org.example.Details.InputEmployeeDetails;
import org.example.exception.EmployeeNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import static java.lang.System.exit;

public class ConsoleApp {
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
//            Employee employeeConsole;
//            Employee employeeConsoleId;
//            Employee employeeConsolePin;
//            EmployeeAddress employeePermanentAddressConsole;
//            EmployeeAddress employeeTemporaryAddressConsole;
//            EmployeeBasicDetails employeeBasicDetailsConsole;
            InputEmployeeDetails inputEmployeeDetails = new DatabaseRepositoryImplementation();
            Logger logger= LoggerFactory.getLogger(ConsoleApp.class);
            Validation validation=new Validation();
            try {
                System.out.println(resourceBundle.getString("welcome.message"));
                List<Employee> employees = new ArrayList<>();
                while (true) {
                    System.out.println(resourceBundle.getString("menu.choice"));
                    System.out.println(resourceBundle.getString("choice.input"));
                    switch (scanner.nextInt()) {
                        //enter employee details
                        case 1:do {
                            System.out.println(resourceBundle.getString("Details.input"));
                            System.out.print(resourceBundle.getString("name.input"));
                            scanner.nextLine();
                            String name = scanner.nextLine();

                            System.out.print(resourceBundle.getString("id.input"));
                            String id = scanner.nextLine();

                            System.out.print(resourceBundle.getString("emailId.input"));
                            String email = scanner.nextLine();

                            if (!validation.isValidEmail(email)) {
                                System.out.println(resourceBundle.getString("emailId.invalid"));
                                break;
                            }

                            System.out.print(resourceBundle.getString("phone.input"));
                            long phoneNumber=scanner.nextLong();
                            if (!validation.isValidPhoneNumber(phoneNumber)) {
                                System.out.println(resourceBundle.getString("Phone.invalid"));
                                break;
                            }

                            System.out.println(resourceBundle.getString("permanentAddress.input"));
                            System.out.print(resourceBundle.getString("address.input"));
                            scanner.nextLine();
                            String permanentAddress = scanner.nextLine();

                            System.out.print(resourceBundle.getString("HouseName.input"));
                            String permanentHouseNumber = scanner.nextLine();

                            System.out.print(resourceBundle.getString("city.input"));
                            String permanentCity = scanner.nextLine();

                            System.out.print(resourceBundle.getString("state.input"));
                            String permanentState = scanner.nextLine();

                            System.out.print(resourceBundle.getString("pincode.input"));
                            int permanentPinCode = Integer.parseInt(scanner.nextLine());

                            if (!validation.isValidPin(permanentPinCode)) {
                                System.out.println(resourceBundle.getString("invalid.Pin"));
                                break;
                            }

                            System.out.println(resourceBundle.getString("temporaryaddress.input"));
                            System.out.print(resourceBundle.getString("address.input"));
                            String temporaryAddress = scanner.nextLine();

                            System.out.print(resourceBundle.getString("HouseName.input"));
                            String temporaryHouseNumber = scanner.nextLine();

                            System.out.print(resourceBundle.getString("city.input"));
                            String temporaryCity = scanner.nextLine();

                            System.out.print(resourceBundle.getString("state.input"));
                            String temporaryState = scanner.nextLine();

                            System.out.print(resourceBundle.getString("pincode.input"));
                            int temporaryPinCode=scanner.nextInt();

                            if (!validation.isValidPin(temporaryPinCode)) {
                                System.out.println(resourceBundle.getString("invalid.Pin"));
                                break;
                            }

                            EmployeeBasicDetails basicDetails = new EmployeeBasicDetails(name, id, email, phoneNumber);
                            EmployeeAddress permanentDetails = new EmployeeAddress(permanentAddress, permanentHouseNumber, permanentState, permanentCity, permanentPinCode);
                            EmployeeAddress temporaryDetails = new EmployeeAddress(temporaryAddress, temporaryHouseNumber, temporaryState, temporaryCity, temporaryPinCode);

                            Employee employee = new Employee(basicDetails, permanentDetails, temporaryDetails);
                            List<Employee> employeeInfo = new ArrayList<>();
                            employeeInfo.add(employee);
                            inputEmployeeDetails.create(employeeInfo);

                            System.out.print(resourceBundle.getString("add.more"));
                        }while (scanner.next().equalsIgnoreCase(resourceBundle.getString("yes")));
                            break;
                        case 2:
                            System.out.println(resourceBundle.getString("id.input"));
                            String employeeId = scanner.next();
                            try {
                                Employee employee = inputEmployeeDetails.displayBasedOnEmployeeId(employeeId);
                                System.out.println(employee.displayEmployeeDetails());
                            } catch (EmployeeNotFoundException e) {
                                System.out.println(e.getMessage());
                                logger.warn(e.getMessage());
                            }
                            break;

                        case 3:
                            List<Employee> allEmployees = inputEmployeeDetails.read();
                            if (!allEmployees.isEmpty()) {
                                for (Employee emp : allEmployees) {
                                    System.out.println(emp.displayEmployeeDetails());
                                    System.out.println();
                                }
                            } else {
                                System.out.println(resourceBundle.getString("employee.not.found"));
                                logger.warn(resourceBundle.getString("employee.not.found"));
                            }
                            break;
                        case 4:
                            System.out.println(resourceBundle.getString("pincode.input"));
                            int pinCode = scanner.nextInt();
                            try {
                                Employee employee = inputEmployeeDetails.displayBasedOnPinCode(pinCode);
                                System.out.println(employee.displayEmployeeDetails());
                            } catch (EmployeeNotFoundException e) {
                                System.out.println(e.getMessage());
                                logger.warn(e.getMessage());
                            }
                            break;
                        case 5:
                            exit(0);
                    }
                }
            } finally {

                inputEmployeeDetails.closeConnections();
            }
        }
    }
    private static entities.Employee translateBack(Employee employee) {

        EmployeeBasicDetails employeeBasicDetailsConsole=new EmployeeBasicDetails();
        EmployeeAddress tempAddress=new EmployeeAddress();
        EmployeeAddress permAddress=new EmployeeAddress();

        employeeBasicDetailsConsole.setEmployeeName(employee.getEmployeebasicDetails().getEmployeeName());
        employeeBasicDetailsConsole.setEmployeeId(employee.getEmployeebasicDetails().getEmployeeId());
        employeeBasicDetailsConsole.setEmailId(employee.getEmployeebasicDetails().getEmailId());
        employeeBasicDetailsConsole.setPhoneNumber(employee.getEmployeebasicDetails().getPhoneNumber());

        permAddress.setAddress(employee.getEmployeePermanentAddress().getAddress());
        permAddress.setHouseName(employee.getEmployeePermanentAddress().getHouseName());
        permAddress.setCity(employee.getEmployeePermanentAddress().getCity());
        permAddress.setState(employee.getEmployeePermanentAddress().getState());
        permAddress.setPinCode(employee.getEmployeePermanentAddress().getPinCode());

        tempAddress.setAddress(employee.getEmployeeTemporaryAddress().getAddress());
        tempAddress.setHouseName(employee.getEmployeeTemporaryAddress().getHouseName());
        tempAddress.setCity(employee.getEmployeeTemporaryAddress().getCity());
        tempAddress.setState(employee.getEmployeeTemporaryAddress().getState());
        tempAddress.setPinCode(employee.getEmployeeTemporaryAddress().getPinCode());
        return new entities.Employee(employeeBasicDetailsConsole,permAddress,tempAddress);
    }


    private static Employee translateEmployee(entities.Employee employee) {
        EmployeeAddress employeeTemporaryAddress = new EmployeeAddress();
        EmployeeAddress employeePermanentAddress = new EmployeeAddress();
        EmployeeBasicDetails employeebasicDetails=new EmployeeBasicDetails();
        employeebasicDetails.setEmployeeName(employee.getEmployeebasicDetails().getEmployeeName());
        employeebasicDetails.setEmployeeId(employee.getEmployeebasicDetails().getEmployeeId());
        employeebasicDetails.setEmailId(employee.getEmployeebasicDetails().getEmailId());
        employeebasicDetails.setPhoneNumber(employee.getEmployeebasicDetails().getPhoneNumber());

        employeePermanentAddress.setAddress(employee.getEmployeePermanentAddress().getAddress());
        employeePermanentAddress.setHouseName(employee.getEmployeePermanentAddress().getHouseName());
        employeePermanentAddress.setCity(employee.getEmployeePermanentAddress().getCity());
        employeePermanentAddress.setState(employee.getEmployeePermanentAddress().getState());
        employeePermanentAddress.setPinCode(employee.getEmployeePermanentAddress().getPinCode());

        employeeTemporaryAddress.setAddress(employee.getEmployeeTemporaryAddress().getAddress());
        employeeTemporaryAddress.setHouseName(employee.getEmployeeTemporaryAddress().getHouseName());
        employeeTemporaryAddress.setCity(employee.getEmployeeTemporaryAddress().getCity());
        employeeTemporaryAddress.setState(employee.getEmployeeTemporaryAddress().getState());
        employeeTemporaryAddress.setPinCode(employee.getEmployeeTemporaryAddress().getPinCode());

        return new Employee(employeebasicDetails,employeePermanentAddress,employeeTemporaryAddress);



    }

}
