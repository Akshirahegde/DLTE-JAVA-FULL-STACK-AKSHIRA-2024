package org.example;

import java.util.List;
import java.util.Scanner;

public class EmployeeDetails {
    private static EmployeeAddress employeeAddress;

    private  static  Employee employee;
    private static EmployeeContact employeeInformation;



    public static Employee inputDetail() {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter First name :");
        String employeeFirstName = scanner.nextLine();
            System.out.println("Enter middle name :");
            String employeeMiddleName = scanner.nextLine();
            System.out.println("Enter  Last name :");
            String employeeLastName = scanner.nextLine();
        System.out.println("Enter your permanent address :");
        Scanner scanner1=new Scanner(System.in);
        System.out.println("Enter the HouseName :");
        String permanentHouseName = scanner.nextLine();
        System.out.println("Enter the city :");
        String permanentCity = scanner.nextLine();
        System.out.println("Enter the State :");
        String permanentState = scanner.nextLine();
        System.out.println("Enter the PinCode :");
        int permanentPinCode = scanner.nextInt();
        System.out.println("Enter the Temporary Address :");
        System.out.println("Enter the HouseName :");
        String temporaryHouseName = scanner.nextLine();
        System.out.println("Enter the city :");
        String temporaryCity = scanner.nextLine();
        System.out.println("Enter the State :");
        String temporaryState = scanner.nextLine();
        System.out.println("Enter the PinCode :");
        int temporaryPinCode = scanner.nextInt();
        employeeAddress = new EmployeeAddress( permanentHouseName, permanentCity, permanentState, permanentPinCode, temporaryHouseName, temporaryCity, temporaryState, temporaryPinCode);

        System.out.println("Enter the Email Id :");
        String emailId = scanner.next();
        System.out.println("Enter the Phone Number :");
        long phoneNumber = scanner.nextLong();
        employeeInformation = new EmployeeContact(emailId, phoneNumber);
        Employee employee = new Employee(employeeFirstName,employeeMiddleName,employeeLastName, employeeAddress, employeeInformation);
        System.out.println("Employee added successfuly");
        return employee;
    }


    public static void displayInput(List<Employee> employees) {
        if (employees.isEmpty()){
            System.out.println("No Data Found");
        }
        else{
            System.out.println("Employee Details:");
            for(Employee each: employees){
                System.out.println("Name= "+each.getEmployeeFirstName()+" "+each.getEmployeeMiddlename()+" "+each.getEmployeeLastName());
                System.out.println("Permanent Address :" + each.getAddress().getPermanentAddress()+","+each.getAddress().getPermanentHouseNumber()+","+each.getAddress().getPermanentCity()+","+each.getAddress().getPermanentState()+"-"+each.getAddress().getPermanentPinCode());

                System.out.println("Temporary Address :"+each.getAddress().getTemporaryAddress()+","+each.getAddress().getTemporaryHouseNumber()+","+each.getAddress().getTemporaryCity()+","+each.getAddress().getTemporaryState()+"-"+each.getAddress().getTemporaryPinCode());
                System.out.println("Email id :" + each.getEmployeeContact().getEmailId() + "\nPhone number :" + each.getEmployeeContact().getPhoneNumber());

            }
        }


    }

}