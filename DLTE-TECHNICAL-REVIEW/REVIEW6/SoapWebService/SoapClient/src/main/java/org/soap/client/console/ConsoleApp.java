package org.soap.client.console;//package org.soap.client.console;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import soap.service.Employee;
import soap.service.EmployeeWebImplementation;
import soap.service.EmployeeWebImplementationService;
import soap.service.GroupOfEmployee;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPFault;
import javax.xml.ws.soap.SOAPFaultException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import static java.lang.System.exit;
import static java.lang.System.setOut;

public class ConsoleApp {
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    private static Logger logger = LoggerFactory.getLogger(ConsoleApp.class);
    private static EmployeeWebImplementationService employeeWebImplementationService = new EmployeeWebImplementationService();
    private static EmployeeWebImplementation port = employeeWebImplementationService.getEmployeeWebImplementationPort();//-----------

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Validation validation = new Validation();
            try {
                System.out.println(resourceBundle.getString("greet"));
                while (true) {
                    System.out.println(resourceBundle.getString("menu.display"));
                    System.out.println(resourceBundle.getString("enter.choice"));
                    int choice;
                    try {
                        choice = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println(resourceBundle.getString("Enter.number1"));
                        scanner.nextLine();
                        continue;
                    }
                    switch (choice) {
                        case 1:
                            addEmployeeDetails(scanner, validation);
                            break;
                        case 2:
                            displayEmployeeById(scanner);
                            break;
                        case 3:
                            displayAllEmployees();
                            break;
                        case 4:
                            displayEmployeeByPinCode(scanner);
                            break;
                        case 5:
                            exit(0);
                        default:
                            System.out.println(resourceBundle.getString("invalid.choice"));
                    }
                }
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private static void addEmployeeDetails(Scanner scanner, Validation validation) throws SOAPException {
        do {
            Employee employee = new Employee();
           soap.service.EmployeeAddress permanentAddress;

            soap.service.EmployeeAddress temporaryAddress;

            try {
                soap.service.EmployeeDetails employeeDetails = new soap.service.EmployeeDetails();

                System.out.println(resourceBundle.getString("enter.employeeDetails"));

                System.out.print(resourceBundle.getString("Enter.name"));
                scanner.nextLine();
                employeeDetails.setEmployeeName(scanner.nextLine());

                System.out.print(resourceBundle.getString("enter.id"));
                employeeDetails.setEmployeeId(scanner.nextLine());

                String email;
                boolean validEmail;
                do {
                    System.out.print(resourceBundle.getString("enter.emailId"));
                    email = scanner.nextLine();

                    validEmail = validation.isValidEmail(email);
                    if (!validEmail) {
                        System.out.println(resourceBundle.getString("invalid.email"));
                    } else {
                        employeeDetails.setEmailId(email);
                    }
                } while (!validEmail);

                long phoneNumber;
                boolean validPhoneNumber;
                do {
                    System.out.print(resourceBundle.getString("enter.phone"));
                    phoneNumber = Long.parseLong(scanner.nextLine());

                    validPhoneNumber = validation.isValidPhoneNumber(phoneNumber);
                    if (!validPhoneNumber) {
                        System.out.println(resourceBundle.getString("invalid.Phone"));
                    } else {
                        employeeDetails.setPhoneNumber(phoneNumber);
                    }
                } while (!validPhoneNumber);

                System.out.println(resourceBundle.getString("enter.permanentAddress"));
                permanentAddress = getEmployeeAddressFromUser(scanner, validation);


                System.out.println(resourceBundle.getString("enter.temporaryaddress"));
                temporaryAddress = getEmployeeAddressFromUser(scanner, validation);

                employee.setEmployeeDetails(employeeDetails);
                employee.setEmployeePermanentAddress(permanentAddress);
                employee.setEmployeeTemporaryAddress(temporaryAddress);

                port.create(employee);

                System.out.print(resourceBundle.getString("add.more"));
            }
//            catch (SOAPFaultException e){
//                if (e.getFault().getFaultCode().equalsIgnoreCase("EmployeeExists")) {
//                    logger.warn(e.getFault().getFaultString());
//                    System.out.println(resourceBundle.getString("id.available"));
//                }
//                else{
//                    logger.warn(e.getFault().getFaultString());
//                    System.out.println(resourceBundle.getString("system.failure"));
//                    break;
//                }
//            }
            catch (SOAPFaultException e) {
                SOAPFault fault = e.getFault();
                String faultCode = fault.getFaultCode();

                if ("EmployeeExists".equalsIgnoreCase(faultCode)) {
                    logger.warn(fault.getFaultString());
                    System.out.println(resourceBundle.getString("id.available"));

                    // Generate SOAP fault with custom fault code
                    SOAPFactory soapFactory = SOAPFactory.newInstance();
                    SOAPFault customFault = soapFactory.createFault("Employee already exists", new QName("http://example.com/faults", "EmployeeExists"));
                    throw new SOAPFaultException(customFault);
                } else {
                    logger.warn(fault.getFaultString());
                    System.out.println(resourceBundle.getString("system.failure"));
                    break;
                }
            }

        } while (scanner.next().equalsIgnoreCase(resourceBundle.getString("yes")));
    }

    private static soap.service.EmployeeAddress getEmployeeAddressFromUser(Scanner scanner, Validation validation) {
        soap.service.EmployeeAddress address = new soap.service.EmployeeAddress();

        System.out.print(resourceBundle.getString("enter.address"));
        address.setStreet(scanner.nextLine());

        System.out.print(resourceBundle.getString("enter.HouseNumber"));
        address.setHouseName(scanner.nextLine());

        System.out.print(resourceBundle.getString("enter.city"));
        address.setCity(scanner.nextLine());

        System.out.print(resourceBundle.getString("enter.state"));
        address.setState(scanner.nextLine());

        boolean validPinCode;
        do {
            System.out.print(resourceBundle.getString("enter.pincode"));
            int pinCode = Integer.parseInt(scanner.nextLine());
            address.setPin(pinCode);

            validPinCode = validation.isValidPin(pinCode);
            if (!validPinCode) {
                System.out.println(resourceBundle.getString("invalid.Pin"));
            }
        } while (!validPinCode);

        return address;
    }

    private static void displayEmployeeById(Scanner scanner) throws SOAPException {
        System.out.println(resourceBundle.getString("enter.id"));
        String employeeId = scanner.next();

        try {
            Employee employee = port.displayBasedOnID(employeeId);
            if (employee != null) {
                System.out.println(formatEmployee(employee));
                System.out.println();
            } else {
                System.out.println(resourceBundle.getString("no.employee") + employeeId);
            }
        } catch (SOAPFaultException e){
                if (e.getFault().getFaultCode().equalsIgnoreCase("EmployeeExistException")) {
                   logger.warn(e.getFault().getFaultString());
                   System.out.println(resourceBundle.getString("employee.exists"));
               }
               else{
                   logger.warn(e.getFault().getFaultString());
                   System.out.println(resourceBundle.getString("system.failure"));
               }
           }
// catch (Exception e) {
//            // Handle other exceptions
//            String errorMessage = resourceBundle.getString("no.employee") + employeeId;
//            logger.warn(errorMessage);
//
//            // Create SOAP fault with custom status code
//            SOAPFactory soapFactory = SOAPFactory.newInstance();
//            SOAPFault customFault = soapFactory.createFault(errorMessage, new QName("http://example.com/faults", "ServiceError"));
//
//            // Set the fault code using a QName object
//            QName faultCode = new QName("http://example.com/faults", "Server");
//            customFault.setFaultCode(faultCode);
//            System.out.println("Status Code: " + faultCode.getLocalPart()); // Print the fault code to SOAP UI
//
//            // Print the fault message to SOAP UI
//            System.out.println("Fault Message: " + customFault.getFaultString());
//
//            // Log the error
//            logger.warn("An error occurred while displaying employee with ID: " + employeeId + ". " + e.getMessage());
//        }
    }


    private static void displayAllEmployees() {
        try {
            GroupOfEmployee employees = port.read();
            List<Employee> employeeList = employees.getEmployeeList();
            if (!employeeList.isEmpty()) {
                for (soap.service.Employee emp : employeeList) {
                    System.out.println(formatEmployee(emp));
                    System.out.println();
                }
            } else {
                System.out.println(resourceBundle.getString("employee.not.found"));
                logger.warn(resourceBundle.getString("employee.not.found"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            logger.warn(e.getMessage());
        }
    }

    private static void displayEmployeeByPinCode(Scanner scanner) {
        System.out.println(resourceBundle.getString("enter.pincode"));
        int pinCode = scanner.nextInt();
        try {
            GroupOfEmployee result = port.displayBasedOnPincode(pinCode);
            List<Employee> employeeList = result.getEmployeeList();
            if (!employeeList.isEmpty()) {
                System.out.println(resourceBundle.getString("employee.with") + pinCode + ":");
                for (Employee emp : employeeList) {
                    System.out.println(formatEmployee(emp));
                    System.out.println();
                }
            } else {
                System.out.println(resourceBundle.getString("no.pin") + pinCode);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            logger.warn(e.getMessage());
        }
    }

    private static String formatEmployee(Employee employee) {
        // Format employee details
        return "Employee Details: " +
                "\nEmployee ID: " + employee.getEmployeeDetails().getEmployeeName() +
                "\nName:" + employee.getEmployeeDetails().getEmployeeId() +
                "\nEmail: " + employee.getEmployeeDetails().getEmailId() +
                "\nPhone Number: " + employee.getEmployeeDetails().getPhoneNumber() +
                "\nPermanent Address: " + employee.getEmployeePermanentAddress().getStreet() +
                "\nPermanent House Number: " + employee.getEmployeePermanentAddress().getHouseName() +
                "\nPermanent City: " + employee.getEmployeePermanentAddress().getCity() +
                "\nPermanent State: " + employee.getEmployeePermanentAddress().getState() +
                "\nPermanent Pin Code: " + employee.getEmployeePermanentAddress().getPin() +
                "\nTemporary Address: " + employee.getEmployeeTemporaryAddress().getStreet() +
                "\nTemporary House Number: " + employee.getEmployeeTemporaryAddress().getHouseName() +
                "\nTemporary City: " + employee.getEmployeeTemporaryAddress().getCity() +
                "\nTemporary State: " + employee.getEmployeeTemporaryAddress().getState() +
                "\nTemporary Pin Code: " + employee.getEmployeeTemporaryAddress().getPin();
    }
}

