package org.consoleapp.console;

import org.consoleapp.pojo.EmployeeAddressConsole;
import org.consoleapp.pojo.EmployeeConsole;
import org.consoleapp.pojo.EmployeeDetailsConsole;
import org.databaserepo.database.DatabaseRepositoryImplementation;
import org.databaserepo.entity.Employee;
import org.databaserepo.entity.EmployeeAddress;
import org.databaserepo.entity.EmployeeDetails;
import org.databaserepo.exception.EmployeeException;
import org.databaserepo.interfaces.EmployeeInputDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.*;

import static java.lang.System.exit;

public class ConsoleApp {
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(ConsoleApp.class);

        EmployeeInputDetails employeeInputDetails= new DatabaseRepositoryImplementation();
        try (Scanner scanner = new Scanner(System.in)) {
            try {
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
                        case 1://reading employee details
                           callReadAll(scanner);
                            break;
                        case 2://displaying employee based on employee id
                                callReadAllById(scanner);
                                break;

                        case 3://displaying all employees
                                 callDisplayAll(scanner);
                                 break;

                        case 4://displaying based on pincode

                                callDisplayBasedOnPin(scanner);
                           break;
                        case 5:
                            exit(0);
                    }
                }
            } finally {
                // Close connections
                employeeInputDetails.closeConnections();
                scanner.close();
            }
        }
    }

    private static EmployeeConsole translate(Employee employee) {
//from database to console
        EmployeeDetailsConsole employeeDetailsConsole = new EmployeeDetailsConsole();
        EmployeeAddressConsole temporaryAddress = new EmployeeAddressConsole();
        EmployeeAddressConsole permanentAddress = new EmployeeAddressConsole();

        employeeDetailsConsole.setEmployeeName(employee.getEmployeeDetails().getEmployeeName());
        employeeDetailsConsole.setEmployeeId(employee.getEmployeeDetails().getEmployeeId());
        employeeDetailsConsole.setEmailId(employee.getEmployeeDetails().getEmailId());
        employeeDetailsConsole.setPhoneNumber(employee.getEmployeeDetails().getPhoneNumber());

        permanentAddress.setStreet(employee.getEmployeePermanentAddress().getStreet());
        permanentAddress.setHouseName(employee.getEmployeePermanentAddress().getHouseName());
        permanentAddress.setCity(employee.getEmployeePermanentAddress().getCity());
        permanentAddress.setState(employee.getEmployeePermanentAddress().getState());
        permanentAddress.setPin(employee.getEmployeePermanentAddress().getPin());

        temporaryAddress.setStreet(employee.getEmployeeTemporaryAddress().getStreet());
        temporaryAddress.setHouseName(employee.getEmployeeTemporaryAddress().getHouseName());
        temporaryAddress.setCity(employee.getEmployeeTemporaryAddress().getCity());
        temporaryAddress.setState(employee.getEmployeeTemporaryAddress().getState());
        temporaryAddress.setPin(employee.getEmployeeTemporaryAddress().getPin());
        return new EmployeeConsole(employeeDetailsConsole, permanentAddress, temporaryAddress);
    }


    private static Employee translateEmployee(EmployeeConsole employeeConsole) {
        //from console to database
        EmployeeAddress employeeTemporaryAddress = new EmployeeAddress();
        EmployeeAddress employeePermanentAddress = new EmployeeAddress();
        EmployeeDetails employeeDetails = new EmployeeDetails();
        employeeDetails.setEmployeeName(employeeConsole.getEmployeeDetails().getEmployeeName());
        employeeDetails.setEmployeeId(employeeConsole.getEmployeeDetails().getEmployeeId());
        employeeDetails.setEmailId(employeeConsole.getEmployeeDetails().getEmailId());
        employeeDetails.setPhoneNumber(employeeConsole.getEmployeeDetails().getPhoneNumber());

        employeePermanentAddress.setStreet(employeeConsole.getEmployeePermanentAddress().getStreet());
        employeePermanentAddress.setHouseName(employeeConsole.getEmployeePermanentAddress().getHouseName());
        employeePermanentAddress.setCity(employeeConsole.getEmployeePermanentAddress().getCity());
        employeePermanentAddress.setState(employeeConsole.getEmployeePermanentAddress().getState());
        employeePermanentAddress.setPin(employeeConsole.getEmployeePermanentAddress().getPin());

        employeeTemporaryAddress.setStreet(employeeConsole.getEmployeeTemporaryAddress().getStreet());
        employeeTemporaryAddress.setHouseName(employeeConsole.getEmployeeTemporaryAddress().getHouseName());
        employeeTemporaryAddress.setCity(employeeConsole.getEmployeeTemporaryAddress().getCity());
        employeeTemporaryAddress.setState(employeeConsole.getEmployeeTemporaryAddress().getState());
        employeeTemporaryAddress.setPin(employeeConsole.getEmployeeTemporaryAddress().getPin());

        return new Employee(employeeDetails, employeePermanentAddress, employeeTemporaryAddress);

    }
public static void callReadAll(Scanner scanner){
    do {
        EmployeeConsole employeeConsole;
        EmployeeAddressConsole employeePermanentAddressConsole;
        EmployeeAddressConsole employeeTemporaryAddressConsole;
        EmployeeDetailsConsole employeeDetailsConsole;
        EmployeeInputDetails employeeInputDetails= new DatabaseRepositoryImplementation();
        Logger logger = LoggerFactory.getLogger(ConsoleApp.class);
        Validation validation = new Validation();
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
        int permanentPinCode = scanner.nextInt();

        if (!validation.isValidPin(permanentPinCode)) {
            System.out.println(resourceBundle.getString("invalid.Pin"));
            break;
        }

        System.out.println(resourceBundle.getString("enter.temporaryaddress"));
        System.out.print(resourceBundle.getString("enter.street"));
        Scanner scanner1=new Scanner(System.in);
        String temporaryStreet = scanner1.nextLine();

        System.out.print(resourceBundle.getString("enter.HouseName"));
        String temporaryHouseName = scanner1.nextLine();

        System.out.print(resourceBundle.getString("enter.city"));
        String temporaryCity = scanner1.nextLine();

        System.out.print(resourceBundle.getString("enter.state"));
        String temporaryState = scanner1.nextLine();

        System.out.print(resourceBundle.getString("enter.pincode"));
        int temporaryPinCode = scanner1.nextInt();

        if (!validation.isValidPin(temporaryPinCode)) {
            System.out.println(resourceBundle.getString("invalid.Pin"));
            break;
        }

        employeeDetailsConsole = new EmployeeDetailsConsole(name, id, email, phoneNumber);

        employeePermanentAddressConsole = new EmployeeAddressConsole(permanentStreet, permanentHouseName, permanentState, permanentCity, permanentPinCode);

        employeeTemporaryAddressConsole = new EmployeeAddressConsole(temporaryStreet, temporaryHouseName, temporaryState, temporaryCity, temporaryPinCode);
        employeeConsole = new EmployeeConsole(employeeDetailsConsole, employeePermanentAddressConsole, employeeTemporaryAddressConsole);

        Employee employee;
        employee = translateEmployee(employeeConsole);

        List<Employee> employeeInfo = new ArrayList<>();
        employeeInfo.add(employee);
        employeeInputDetails.create(employeeInfo);

        System.out.print(resourceBundle.getString("add.more"));
    } while (scanner.next().equalsIgnoreCase(resourceBundle.getString("yes")));
}
    public static void callReadAllById(Scanner scanner){
        EmployeeConsole employeeConsoleId;
        EmployeeInputDetails employeeInputDetails= new DatabaseRepositoryImplementation();
        Logger logger = LoggerFactory.getLogger(ConsoleApp.class);
        System.out.println(resourceBundle.getString("enter.id"));
        String employeeId = scanner.next();
        try {
            Employee employee = employeeInputDetails.displayBasedOnEmployeeId(employeeId);
            employeeConsoleId = translate(employee);
            System.out.println(employeeConsoleId.displayEmployeeDetails());
        } catch (EmployeeException | NullPointerException e) {
            System.out.println(e.getMessage());
            logger.warn(e.getMessage());
        }
    }
    public static void callDisplayAll(Scanner scanner){
        EmployeeConsole employeeConsole;
        EmployeeInputDetails employeeInputDetails= new DatabaseRepositoryImplementation();
        Logger logger = LoggerFactory.getLogger(ConsoleApp.class);
        List<Employee> allEmployees = employeeInputDetails.read();
        if (!allEmployees.isEmpty()) {
            for (Employee emp : allEmployees) {
                employeeConsole = translate(emp);
                System.out.println(employeeConsole.displayEmployeeDetails()+"\n");

            }
        } else {
            System.out.println(resourceBundle.getString("employee.not.found"));
            logger.warn(resourceBundle.getString("employee.not.found"));
        }
    }
    public static void callDisplayBasedOnPin(Scanner scanner){
        EmployeeConsole employeeConsolePin;
        EmployeeInputDetails employeeInputDetails= new DatabaseRepositoryImplementation();
        Logger logger = LoggerFactory.getLogger(ConsoleApp.class);
        System.out.println(resourceBundle.getString("enter.pincode"));
        int pin = scanner.nextInt();
        try {
          List  <Employee> employee = employeeInputDetails.displayBasedOnPinCode(pin);
          for(Employee emp:employee) {
              employeeConsolePin = translate(emp);
              System.out.println(employeeConsolePin.displayEmployeeDetails());
          }
        } catch (EmployeeException e) {
            System.out.println(e.getMessage());
            logger.warn(e.getMessage());
        }

    }











    }


























































































//            EmployeeConsole employeeConsole;
//            EmployeeConsole employeeConsoleId;
//            EmployeeConsole employeeConsolePin;
//            EmployeeAddressConsole employeePermanentAddressConsole;
//            EmployeeAddressConsole employeeTemporaryAddressConsole;
//            EmployeeDetailsConsole employeeDetailsConsole;
//
//          EmployeeInputDetails employeeInputDetails= new DatabaseRepositoryImplementation();
//            Logger logger = LoggerFactory.getLogger(ConsoleApp.class);
//            Validation validation = new Validation();


