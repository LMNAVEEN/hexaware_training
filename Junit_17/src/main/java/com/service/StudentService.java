package com.service;

import com.exception.InvalidMarkException;
import com.model.Student;

public class StudentService {

    public String checkResult(Student student) {

        if (student.getMarks() < 0 || student.getMarks() > 100) {
            throw new InvalidMarkException("Marks should be between 0 and 100");
        }

        if (student.getMarks() >= 40) {
            return "PASS";
        } else {
            return "FAIL";
        }
    }
}