package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeMain {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("1.Enter the Employee Data\n2.Display Employee Data\n3.Exit");
            System.out.println("Enter your choice: ");
            int choice=scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 1:do{
//                    EmployeeDetails employeeDetails = new EmployeeDetails();
//                    employeeDetails.inputDetail(scanner);
                    Employee employee=EmployeeDetails.inputDetail();
                    employees.add(employee);
                    System.out.println("Do you want to add one more employee?(yes/no): ");
                    scanner.nextLine();
                }while(scanner.nextLine().equalsIgnoreCase("yes"));
                    break;
                case 2:EmployeeDetails.displayInput(employees);
                    break;
            }
        }


    }

}
