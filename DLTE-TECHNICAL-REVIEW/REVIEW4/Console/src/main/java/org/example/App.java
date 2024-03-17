package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Scanner scanner=new Scanner(System.in);
        EmployeeDetails employeeDetails=new DatabaseRepository();
        ResourceBundle resourceBundle= ResourceBundle.getBundle("Database");
        Logger logger= LoggerFactory.getLogger(App.class);
        EmployeeAddress employeeAddress;
        Employee employee;
        //  EmployeeInformation employeeInformation;
        Validation validation=new Validation();
        while (true){
            System.out.println(resourceBundle.getString("menu.input"));
            System.out.println(resourceBundle.getString("choice.input"));
            switch (scanner.nextInt()){
                case 1:
                    do{
                        System.out.println(resourceBundle.getString("name.input"));
                        //Scanner scanner=new Scanner(System.in);
                        String employeeName = scanner.next();
                        scanner.nextLine();
                        System.out.println(resourceBundle.getString("id.input"));
                        String employeeId=scanner.next();
                        scanner.nextLine();
                        System.out.println(resourceBundle.getString("permanentAddress.input"));
                        System.out.println(resourceBundle.getString("street.input"));
                        String permanentStreet = scanner.nextLine();
                        System.out.println(resourceBundle.getString("Housename.input"));
                        String permanentHouseName = scanner.nextLine();
                        System.out.println(resourceBundle.getString("city.input"));
                        String permanentCity = scanner.nextLine();
                        System.out.println(resourceBundle.getString("state.input"));
                        String permanentState = scanner.nextLine();
                        System.out.println(resourceBundle.getString("pincode.input"));
                        int permanentPinCode = scanner.nextInt();
                                                if (!validation.isValidPin(permanentPinCode)) {
                            System.out.println("Invalid PIN. PIN should be 6 digits long.");
                            continue;
                        }
                        System.out.println(resourceBundle.getString("temporaryaddress.input"));
                        System.out.println(resourceBundle.getString("street.input"));
                        scanner.nextLine();
                        String temporaryStreet = scanner.nextLine();
                        System.out.println(resourceBundle.getString("Housename.input"));
                        String temporaryHouseName = scanner.nextLine();
                        System.out.println(resourceBundle.getString("city.input"));
                        String temporaryCity = scanner.nextLine();
                        System.out.println(resourceBundle.getString("state.input"));
                        String temporaryState = scanner.nextLine();
                        System.out.println(resourceBundle.getString("pincode.input"));
                        int temporaryPinCode = scanner.nextInt();
                        if (!validation.isValidPin(temporaryPinCode)) {
                            System.out.println("Invalid PIN. PIN should be 6 digits long.");
                            continue;
                        }
                        System.out.println(resourceBundle.getString("emailId.input"));
                        String emailId = scanner.next();
                        if (!validation.isValidEmail(emailId)) {
                            System.out.println("Invalid email address format. Please try again.");
                            continue;
                        }
                        System.out.println(resourceBundle.getString("phone.input"));
                        long phoneNumber = scanner.nextLong();
                        if (!validation.isValidPhoneNumber(phoneNumber)) {
                            System.out.println("Invalid phone number format. Please try again.");
                            continue;
                        }
                        employeeAddress = new EmployeeAddress(permanentStreet, permanentHouseName, permanentCity, permanentState, permanentPinCode, temporaryStreet, temporaryHouseName, temporaryCity, temporaryState, temporaryPinCode);
                        employee = new Employee(employeeName,employeeId,emailId,phoneNumber,employeeAddress);
                        System.out.println(resourceBundle.getString("success.add"));
                        logger.info(resourceBundle.getString("success.add"));
                        List<Employee> employeeInfo=new ArrayList<>();
                        employeeInfo.add(employee);
                        employeeDetails.create(employeeInfo);
                        System.out.println(resourceBundle.getString("add.more"));
                    }while (scanner.next().equalsIgnoreCase(resourceBundle.getString("yes")));
                    break;
                case 2:
                    System.out.println(employeeDetails.read());
                    break;
                case 3:
                    System.out.println(resourceBundle.getString("pincode.input"));
                    System.out.println(employeeDetails.displayBasedOnPinCode(scanner.nextInt()));
                    break;
            }
        }

    }
}
