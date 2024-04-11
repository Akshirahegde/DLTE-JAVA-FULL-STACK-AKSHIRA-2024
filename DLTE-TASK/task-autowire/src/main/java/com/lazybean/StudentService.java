package com.lazybean;

public class StudentService {
   private Student student;

    public StudentService() {
        System.out.println("Student Service Object Created");
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void displayStudentDetails() {
        System.out.println("Student Details:");
        System.out.println("name: " + student.getName());
        System.out.println("subject: " + student.getSubject());
    }
}