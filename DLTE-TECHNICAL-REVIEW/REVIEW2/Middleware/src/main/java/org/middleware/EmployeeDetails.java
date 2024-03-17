package org.middleware;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeDetails implements EmployeeInformation {
    private static EmployeeAddress employeeAddress;
    private  static Employee employee;
    private static EmployeeContact employeeContact;
    static List<Employee> employees1=new ArrayList<>();

@Override
    public  void inputDetail() throws IOException,ClassNotFoundException {
        EmployeeDetails employeeDetails=new EmployeeDetails();
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
        //String emailExpression="^[a-z0-9]";
        System.out.println("Enter the Email Id :");
        String emailId = scanner.next();
        System.out.println("Enter the Phone Number :");
        long phoneNumber = scanner.nextLong();
        employeeContact = new EmployeeContact(emailId, phoneNumber);
        Employee employee = new Employee(employeeFirstName,employeeMiddleName,employeeLastName, employeeAddress, employeeContact);
        System.out.println("Employee added successfuly");
      //  List<Employee> employees1=new ArrayList<>();
        employees1.add(employee);
        employeeDetails.create(employees1);


        //return employee;
    }

@Override
    public void display(List<Employee> employees) {
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
    @Override
    public void create(List<Employee> employee) throws IOException, ClassNotFoundException {
        File file=new File("Record.txt");
        if(file.exists()) {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            employees1= (List<Employee>) objectInputStream.readObject();
            employees1.addAll(employee);
        }else{
            employees1=employee;
        }
        FileOutputStream fileOutputStream=new FileOutputStream(file);
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(employees1);
        objectOutputStream.close();
        fileOutputStream.close();

    }
    @Override
    public void read()  throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream=new FileInputStream("Record.txt");
        ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
        List<Employee> information= (List<Employee>) objectInputStream.readObject();
        information.forEach(System.out::println);
        objectInputStream.close();
        fileInputStream.close();
    }

}
