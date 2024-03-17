package org.console;

import org.middleware.Employee;
import org.middleware.EmployeeDetails;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;
import static jdk.nashorn.internal.runtime.ListAdapter.create;

public class App
{
    public static void main( String[] args ) throws IOException, ClassNotFoundException {
        List<Employee> employees=new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("1.Add Employee\n2.Display Employee\n3.Exit");
            System.out.println("Enter your choice: ");
            int choice=scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 1:
                    do{
                        //Employee employee=EmployeeDetails

                        EmployeeDetails employeeDetails=new EmployeeDetails();

                        employeeDetails.inputDetail();
                       // create(scanner);
                        // employees.add(employee);
                        System.out.println("Do you want to add one more employee?(yes/no): ");
                    }while(scanner.next().equalsIgnoreCase("yes"));

                    break;
                case 2:EmployeeDetails employeeDetails=new EmployeeDetails();
                    employeeDetails.read();
                    break;
                case 3:exit(0);
                default:
                    System.out.println("invalid choice");
                    break;
            }
        }
    }
}
