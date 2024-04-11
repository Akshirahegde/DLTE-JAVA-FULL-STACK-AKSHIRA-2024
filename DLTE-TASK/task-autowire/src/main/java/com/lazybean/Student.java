package com.lazybean;

 public class Student {
    private String name;
    private String subject;

    public Student() {
        System.out.println("Student object created");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Student(String name, String subject) {
        this.name = name;
        this.subject = subject;
    }
}