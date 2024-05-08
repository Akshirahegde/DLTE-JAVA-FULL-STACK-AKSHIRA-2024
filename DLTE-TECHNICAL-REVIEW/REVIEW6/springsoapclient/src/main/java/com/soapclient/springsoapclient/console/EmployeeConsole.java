package com.soapclient.springsoapclient.console;

import com.soapclient.springsoapclient.configuration.WebServiceConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.ws.client.core.WebServiceTemplate;
import services.employee.*;
//import soap.webservice.*;
//import services.employee.*;

import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class EmployeeConsole {
    private static Logger logger = LoggerFactory.getLogger(EmployeeConsole.class);

    private static ResourceBundle resourceBundle= ResourceBundle.getBundle("employee");
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(WebServiceConfiguration.class);
        context.refresh();

        WebServiceTemplate webServiceTemplate = context.getBean(WebServiceTemplate.class);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(resourceBundle.getString("menu"));
            System.out.println(resourceBundle.getString("emp.create"));
            System.out.println(resourceBundle.getString("emp.id"));
            System.out.println(resourceBundle.getString("emp.list"));
            System.out.println(resourceBundle.getString("emp.pin"));
            System.out.println(resourceBundle.getString("emp.exit"));
            System.out.print(resourceBundle.getString("enter.choice"));
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createEmployee(webServiceTemplate, scanner);
                    break;
                case 2:
                    filterByIdRequest(webServiceTemplate, scanner);
                    break;
                case 3:
                    findAllEmployeeRequest(webServiceTemplate);
                    break;
                case 4:
                    filterByPinCodeRequest(webServiceTemplate, scanner);
                    break;
                case 5:
                    System.out.println(resourceBundle.getString("exiting"));
                    context.close();
                    System.exit(0);
                default:
                    System.out.println(resourceBundle.getString("invalid.choice"));
            }
        }
    }

    private static void createEmployee(WebServiceTemplate webServiceTemplate, Scanner scanner) {
        boolean add= true;

        while (add) {
            System.out.println(resourceBundle.getString("enter.employee"));
            System.out.print(resourceBundle.getString("enter.id"));
            String employeeId = scanner.nextLine();
            System.out.print(resourceBundle.getString("enter.name"));
            String employeeName = scanner.nextLine();
            System.out.print(resourceBundle.getString("enter.email"));
            String email = scanner.nextLine();
            System.out.print(resourceBundle.getString("enter.phoneNumber"));
            Long phoneNumber = scanner.nextLong();
            scanner.nextLine();

            System.out.println(resourceBundle.getString("enter.permanentDetails"));
            System.out.print(resourceBundle.getString("enter.street"));
            String permanentStreet = scanner.nextLine();
            System.out.print(resourceBundle.getString("enter.houseName"));
            String permanentHouseName = scanner.nextLine();
            System.out.print(resourceBundle.getString("enter.city"));
            String permanentCity = scanner.nextLine();
            System.out.print(resourceBundle.getString("enter.state"));
            String permanentState = scanner.nextLine();
            System.out.print(resourceBundle.getString("enter.pin"));
            Integer permanentPin = scanner.nextInt();
            scanner.nextLine();

            System.out.println(resourceBundle.getString("enter.temporaryAddress"));
            System.out.print(resourceBundle.getString("enter.street"));
            String temporaryStreet = scanner.nextLine();
            System.out.print(resourceBundle.getString("enter.houseName"));
            String temporaryHouseName = scanner.nextLine();
            System.out.print(resourceBundle.getString("enter.city"));
            String temporaryCity = scanner.nextLine();
            System.out.print(resourceBundle.getString("enter.state"));
            String temporaryState = scanner.nextLine();
            System.out.print(resourceBundle.getString("enter.pin"));
            Integer temporaryPin = scanner.nextInt();
            scanner.nextLine();

            EmployeeDetails details = new EmployeeDetails();
            details.setEmailId(email);
            details.setEmployeeName(employeeName);
            details.setPhoneNumber(phoneNumber);
            details.setEmployeeId(employeeId);

            EmployeeAddress permanentAddress = new EmployeeAddress();
            permanentAddress.setStreet(permanentStreet);
            permanentAddress.setHouseName(permanentHouseName);
            permanentAddress.setCity(permanentCity);
            permanentAddress.setState(permanentState);
            permanentAddress.setPin(permanentPin);

            EmployeeAddress temporaryAddress = new EmployeeAddress();
            temporaryAddress.setStreet(temporaryStreet);
            temporaryAddress.setHouseName(temporaryHouseName);
            temporaryAddress.setCity(temporaryCity);
            temporaryAddress.setState(temporaryState);
            temporaryAddress.setPin(temporaryPin);

            Employee employee = new Employee();
            employee.setEmployeeDetails(details);
            employee.setEmployeePermanentAddress(permanentAddress);
            employee.setEmployeeTemporaryAddress(temporaryAddress);

            CreateEmployeeRequest request = new CreateEmployeeRequest();
            request.setEmployee(employee);

            CreateEmployeeResponse response = (CreateEmployeeResponse) webServiceTemplate.marshalSendAndReceive(request);
            ServiceStatus status = response.getServiceStatus();
            System.out.println(resourceBundle.getString("create.status") + status.getStatus());
            logger.info(resourceBundle.getString("create.status"));
            System.out.println(resourceBundle.getString("create.message") + status.getMessage());
            logger.info(resourceBundle.getString("create.message"));

            //  if (status != null && "SUCCESS".equals(status.getStatus())) {
            Employee createdEmployee = response.getEmployee();
            displayEmployeeDetails(createdEmployee);
            //  }

            System.out.print(resourceBundle.getString("add.another.employee"));
            String choice = scanner.next();
            if (!"yes".equalsIgnoreCase(choice)) {
                add= false;
            }
            scanner.nextLine();
        }
    }

    private static void filterByIdRequest(WebServiceTemplate webServiceTemplate, Scanner scanner) {
        System.out.print(resourceBundle.getString("filter.id"));
        String employeeId = scanner.nextLine();
        FilterByIdRequest request = new FilterByIdRequest();
        request.setEmployeeId(employeeId);
        FilterByIdResponse response = (FilterByIdResponse) webServiceTemplate.marshalSendAndReceive(request);
        ServiceStatus status = response.getServiceStatus();

        //  if (status != null && "SUCCESS".equals(status.getStatus())) {
        System.out.println(resourceBundle.getString("id.response") + status.getStatus());
        System.out.println(resourceBundle.getString("id.message") + status.getMessage());

        Employee filteredEmployee = response.getEmployee();
        displayEmployeeDetails(filteredEmployee);
        //  }
    }




    private static void findAllEmployeeRequest(WebServiceTemplate webServiceTemplate) {
        FindAllEmployeeRequest request = new FindAllEmployeeRequest();
        FindAllEmployeeResponse response = (FindAllEmployeeResponse) webServiceTemplate.marshalSendAndReceive(request);
        ServiceStatus status = response.getServiceStatus();
        System.out.println(resourceBundle.getString("filter.idResponse")+ status.getStatus());
        System.out.println(resourceBundle.getString("filter.idMessage") + status.getMessage());
        //  if (status != null && "SUCCESS".equals(status.getStatus())) {
        List<Employee> employees = response.getEmployee();
        for (Employee each: employees) {
            displayEmployeeDetails(each);

            //}
        }
    }

    private static void filterByPinCodeRequest(WebServiceTemplate webServiceTemplate, Scanner scanner) {
        System.out.print(resourceBundle.getString("enter.pinCodeFilter"));
        int pin= scanner.nextInt();
        scanner.nextLine();
        FilterByPinRequest request = new FilterByPinRequest();
        request.setPin(pin);
        FilterByPinResponse response = (FilterByPinResponse) webServiceTemplate.marshalSendAndReceive(request);
        ServiceStatus status = response.getServiceStatus();
        System.out.println(resourceBundle.getString("pinCode.response") + status.getStatus());
        System.out.println(resourceBundle.getString("pinCode.message") + status.getMessage());
        // if (status != null && "SUCCESS".equals(status.getStatus())) {
        List<Employee> employees = response.getEmployee();
        for (Employee employee : employees) {
            displayEmployeeDetails(employee);
            System.out.println("---------------------------");
            //}
        }
    }

    private static void displayEmployeeDetails(Employee employee) {
        EmployeeDetails details= employee.getEmployeeDetails();
        EmployeeAddress permanentAddress = employee.getEmployeePermanentAddress();
        EmployeeAddress temporaryAddress = employee.getEmployeeTemporaryAddress();

        System.out.println("\nEmployee Details:");
        System.out.println("Employee ID: " + details.getEmployeeId());
        System.out.println("Employee Name: " + details.getEmployeeName());
        System.out.println("Email: " + details.getEmailId());
        System.out.println("Phone Number: " + details.getPhoneNumber());

        System.out.println("\nPermanent Address:");
        System.out.println("Street: " + permanentAddress.getStreet());
        System.out.println("House Name: " + permanentAddress.getHouseName());
        System.out.println("City: " + permanentAddress.getCity());
        System.out.println("State: " + permanentAddress.getState());
        System.out.println("Pin Code: " + permanentAddress.getPin());

        System.out.println("\nTemporary Address:");
        System.out.println("Street: " + temporaryAddress.getStreet());
        System.out.println("House Name: " + temporaryAddress.getHouseName());
        System.out.println("City: " + temporaryAddress.getCity());
        System.out.println("State: " + temporaryAddress.getState());
        System.out.println("Pin Code: " + temporaryAddress.getPin());
    }

}