package com.sms.model;

public class Student {

    private int  Id;
    private String name;
    private int age;
    private String course;
    private double marks;

    public Student(int studentId, String name, int age, String course, double marks) {
        this.Id = studentId;
        this.name = name;
        this.age = age;
        this.course = course;
        this.marks = marks;
    }

    public int getStudentId() { return Id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getCourse() { return course; }
    public double getMarks() { return marks; }
    public void setMarks(double marks) { this.marks = marks; }

}
