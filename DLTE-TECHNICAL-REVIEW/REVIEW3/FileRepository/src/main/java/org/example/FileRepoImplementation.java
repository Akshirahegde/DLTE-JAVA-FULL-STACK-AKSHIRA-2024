package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FileRepoImplementation implements EmployeeDetails{
    List<Employee> employeeList=new ArrayList<>();
    File file=new File("Record.txt");
    ResourceBundle resourceBundle= ResourceBundle.getBundle("application");
    Logger logger= LoggerFactory.getLogger(FileRepoImplementation.class);
    public void writeIntoFile() throws IOException, ClassNotFoundException {
        List<Employee> employees=new ArrayList<>();

        FileOutputStream fileOutputStream=new FileOutputStream(file);
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(employeeList);
        objectOutputStream.close();
        fileOutputStream.close();
    }
    public void readFromFile() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream=new FileInputStream(file);
        ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
        employeeList=(List<Employee>) objectInputStream.readObject();
        objectInputStream.close();
        fileInputStream.close();
    }
    @Override
    public void create(List<Employee> list)  {
        try {
            if(file.exists()){ readFromFile();}

            for(Employee employee:list) {
                boolean employeeExists = employeeList.stream().anyMatch(alreadyExist -> alreadyExist.getEmployeeId() == (employee.getEmployeeId()));

                if (employeeExists) System.out.println(resourceBundle.getString("employee.exists"));
                else {
                    employeeList.addAll(list);
                }
            }

            writeIntoFile();
        } catch (IOException e) {
            e.printStackTrace();
            //logger.atTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public Employee displayBasedOnEmployeeId(String id) {
//        try {
//            readFromFile();
//            return employeeList.stream().filter(employee1 -> employee1.getEmployeeId().equals(id)).findFirst().orElse(null);
//        } catch (IOException e) {
//            e.printStackTrace();
//            logger.atTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//            logger.atTrace();
//        }
//        return null;
//    }

    @Override
    public Employee displayBasedOnPinCode(int pinCode) {
        try {
            readFromFile();
            return employeeList.stream().filter(employee1 -> employee1.getAddress().getTemporaryPinCode()==pinCode).findAny().orElse(null);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Employee> read() {
        try {
            readFromFile();
            return employeeList;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
