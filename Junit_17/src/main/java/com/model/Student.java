package com.model;

public class Student {

    private String name;
    private int marks;

    public Student() {}

    public Student(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public int getMarks() {
        return marks;
    }
}