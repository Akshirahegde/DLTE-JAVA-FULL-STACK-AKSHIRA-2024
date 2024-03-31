package org.console;

import org.databaseRepo.DatabaseRepositoryImplementation;
import org.databaseRepo.Employee;
import org.databaseRepo.InputEmployeeDetails;
import org.databaseRepo.exception.EmployeeNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static java.lang.System.exit;

public class ConsoleApp {
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            EmployeeConsole employeeConsole;
            EmployeeConsole employeeConsoleId;
            EmployeeConsole employeeConsolePin;
            EmployeeAddressConsole employeePermanentAddressConsole;
            EmployeeAddressConsole employeeTemporaryAddressConsole;
            InputEmployeeDetails inputEmployeeDetails = new DatabaseRepositoryImplementation();
            Logger logger = LoggerFactory.getLogger(ConsoleApp.class);
            Validation validation = new Validation();
            try {
                System.out.println(resourceBundle.getString("greet"));
                List<Employee> employees = new ArrayList<>();
                while (true) {
                    boolean validate = false;
                    System.out.println(resourceBundle.getString("menu.display"));
                    System.out.println(resourceBundle.getString("enter.choice"));
                    int choice = 0;
                    do {
                        try {
                            choice = scanner.nextInt();
                            validate = true;
                        }
                        // checking for input format
                        catch (InputMismatchException inputMismatchException) {
                            System.out.println(resourceBundle.getString("Enter.number1"));
                            scanner.nextLine();
                        }
                    } while (!validate);
                    switch (choice) {
                        case 1:
                            do {
                                System.out.println(resourceBundle.getString("enter.employeeDetails"));
                                System.out.print(resourceBundle.getString("Enter.name"));
                                scanner.nextLine();
                                String name = scanner.nextLine();

                                System.out.print(resourceBundle.getString("enter.id"));
                                String id = scanner.nextLine();

                                System.out.print(resourceBundle.getString("enter.emailId"));
                                String email = scanner.nextLine();

                                if (!validation.isValidEmail(email)) {
                                    System.out.println(resourceBundle.getString("invalid.email"));
                                    break;
                                }

                                System.out.print(resourceBundle.getString("enter.phone"));
                                long phoneNumber = Long.parseLong(scanner.nextLine());

                                if (!validation.isValidPhoneNumber(phoneNumber)) {
                                    System.out.println(resourceBundle.getString("invalid.Phone"));
                                    break;
                                }

                                System.out.println(resourceBundle.getString("enter.permanentAddress"));
                                System.out.print(resourceBundle.getString("enter.street"));
                                String permanentStreet = scanner.nextLine();

                                System.out.print(resourceBundle.getString("enter.HouseName"));
                                String permanentHouseName = scanner.nextLine();

                                System.out.print(resourceBundle.getString("enter.city"));
                                String permanentCity = scanner.nextLine();

                                System.out.print(resourceBundle.getString("enter.state"));
                                String permanentState = scanner.nextLine();

                                System.out.print(resourceBundle.getString("enter.pincode"));
                                int permanentPinCode = Integer.parseInt(scanner.nextLine());

                                if (!validation.isValidPin(permanentPinCode)) {
                                    System.out.println(resourceBundle.getString("invalid.Pin"));
                                    break;
                                }

                                System.out.println(resourceBundle.getString("enter.temporaryaddress"));
                                System.out.print(resourceBundle.getString("enter.street"));
                                String temporaryStreet = scanner.nextLine();

                                System.out.print(resourceBundle.getString("enter.HouseName"));
                                String temporaryHouseName = scanner.nextLine();

                                System.out.print(resourceBundle.getString("enter.city"));
                                String temporaryCity = scanner.nextLine();

                                System.out.print(resourceBundle.getString("enter.state"));
                                String temporaryState = scanner.nextLine();

                                System.out.print(resourceBundle.getString("enter.pincode"));
                                int temporaryPinCode = Integer.parseInt(scanner.nextLine());

                                if (!validation.isValidPin(temporaryPinCode)) {
                                    System.out.println(resourceBundle.getString("invalid.Pin"));
                                    break;
                                }
                                // EmployeeConsole employeeConsoleApp;
                                employeeConsole = new EmployeeConsole(name, id, email, phoneNumber);
                                employeePermanentAddressConsole = new EmployeeAddressConsole(permanentStreet, permanentHouseName, permanentState, permanentCity, permanentPinCode);
                                employeeTemporaryAddressConsole = new EmployeeAddressConsole(temporaryStreet, temporaryHouseName, temporaryState, temporaryCity, temporaryPinCode);
//                                employeeConsole = new EmployeeConsole(employeeConsole, employeePermanentAddressConsole, employeeTemporaryAddressConsole);
//                                employeePConsole= new EmployeeAddressConsole(permanentStreet, permanentHouseName, permanentState, permanentCity, permanentPinCode);

                                Employee employee,employee1;
                                employee = translateEmployee(employeeConsole,employeePermanentAddressConsole,employeeTemporaryAddressConsole);
//                                employee1=translateEmployeePermanentaddress();
                           //     employee1=translateEmployee(employeeConsole, employeePermanentAddressConsole, employeeTemporaryAddressConsole);


                                List<Employee> employeeInfo = new ArrayList<>();
                                employeeInfo.add(employee);
                               // employeeInfo.add(employee1);
                                inputEmployeeDetails.create(employeeInfo);

                                System.out.print(resourceBundle.getString("add.more"));
                            } while (scanner.next().equalsIgnoreCase(resourceBundle.getString("yes")));
                            break;
                        case 2:
                            System.out.println(resourceBundle.getString("enter.id"));
                            String employeeId = scanner.next();
                            try {
                                Employee employee = inputEmployeeDetails.displayBasedOnEmployeeId(employeeId);
                                employeeConsoleId = translateBack(employee);
                                System.out.println(employeeConsoleId.displayEmployeeDetails());
                            } catch (EmployeeNotFoundException e) {
                                System.out.println(e.getMessage());
                                logger.warn(e.getMessage());
                            }
                            break;
                             // dont write long code inside the switch case
                        case 3:
                            List<Employee> allEmployees = inputEmployeeDetails.read();
                            if (!allEmployees.isEmpty()) {
                                for (Employee emp : allEmployees) {
                                    employeeConsole = translateBack(emp);
                                    System.out.println(employeeConsole.displayEmployeeDetails());
                                    System.out.println();
                                }
                            } else {
                                System.out.println(resourceBundle.getString("employee.not.found"));
                                logger.warn(resourceBundle.getString("employee.not.found"));
                            }
                            break;
                        case 4:
                            System.out.println(resourceBundle.getString("enter.pincode"));
                            int pinCode = scanner.nextInt();
                            try {
                                Employee employee = inputEmployeeDetails.displayBasedOnPinCode(pinCode);
                                employeeConsolePin = translateBack(employee);
                                System.out.println(employeeConsolePin.displayEmployeeDetails());
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
                // Close connections
                inputEmployeeDetails.closeConnections();
                scanner.close();
            }
        }
    }


    private static EmployeeConsole translateBack(Employee employee) {

        EmployeeConsole employeeBasicDetailsConsole = new EmployeeConsole();
        EmployeeAddressConsole temporaryAddress = new EmployeeAddressConsole();
        EmployeeAddressConsole permanentAddress = new EmployeeAddressConsole();
        employeeBasicDetailsConsole.setEmployeeName(employee.getEmployeeName());
        employeeBasicDetailsConsole.setEmployeeId(employee.getEmployeeId());
        employeeBasicDetailsConsole.setEmailId(employee.getEmailId());
        employeeBasicDetailsConsole.setPhoneNumber(employee.getPhoneNumber());
        permanentAddress.setStreet(employee.getEmployeePermanentAddress().getStreet());
        permanentAddress.setStreet(employee.getEmployeePermanentAddress().getStreet());
        permanentAddress.setHouseName(employee.getEmployeePermanentAddress().getHouseName());
        permanentAddress.setCity(employee.getEmployeePermanentAddress().getCity());
        permanentAddress.setState(employee.getEmployeePermanentAddress().getState());
        permanentAddress.setPinCode(employee.getEmployeePermanentAddress().getPinCode());
        temporaryAddress.setStreet(employee.getEmployeeTemporaryAddress().getStreet());
        temporaryAddress.setHouseName(employee.getEmployeeTemporaryAddress().getHouseName());
        temporaryAddress.setCity(employee.getEmployeeTemporaryAddress().getCity());
        temporaryAddress.setState(employee.getEmployeeTemporaryAddress().getState());
        temporaryAddress.setPinCode(employee.getEmployeeTemporaryAddress().getPinCode());
        return new EmployeeConsole(employeeBasicDetailsConsole, permanentAddress, temporaryAddress);
    }


    private static Employee translateEmployee(EmployeeConsole employeeConsole, EmployeeAddressConsole employeePermanentAddressConsole, EmployeeAddressConsole employeeTemporaryAddressConsole) {
        EmployeeConsole employeeBasicDetailsConsole = new EmployeeConsole();
        EmployeeAddressConsole temporaryAddress = new EmployeeAddressConsole();
        EmployeeAddressConsole permanentAddress = new EmployeeAddressConsole();
        employeeBasicDetailsConsole.setEmployeeName(employeeConsole.getEmployeeName());
        employeeBasicDetailsConsole.setEmployeeId(employeeConsole.getEmployeeId());
        employeeBasicDetailsConsole.setEmailId(employeeConsole.getEmailId());
        employeeBasicDetailsConsole.setPhoneNumber(employeeConsole.getPhoneNumber());

        permanentAddress.setStreet(employeePermanentAddressConsole.getStreet());
        permanentAddress.setHouseName(employeePermanentAddressConsole.getHouseName());
        permanentAddress.setCity(employeePermanentAddressConsole.getCity());
        permanentAddress.setState(employeePermanentAddressConsole.getState());
        permanentAddress.setPinCode(employeePermanentAddressConsole.getPinCode());

        temporaryAddress.setStreet(employeeTemporaryAddressConsole.getStreet());
        temporaryAddress.setHouseName(employeeTemporaryAddressConsole.getHouseName());
        temporaryAddress.setCity(employeeTemporaryAddressConsole.getCity());
        temporaryAddress.setState(employeeTemporaryAddressConsole.getState());
        temporaryAddress.setPinCode(employeeTemporaryAddressConsole.getPinCode());

        return new Employee(employeeBasicDetailsConsole,permanentAddress,temporaryAddress);



    }



}

