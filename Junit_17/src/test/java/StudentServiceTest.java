package com.service;

import com.exception.InvalidMarkException;
import com.model.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StudentServiceTest {

    private StudentService studentService;

    @BeforeEach
    void setUp() {
        studentService = new StudentService();
    }

    @Test
    void testPassStudent() {
        Student student = new Student("Naveen", 75);
        String result = studentService.checkResult(student);

        Assertions.assertEquals("PASS", result);
    }

    @Test
    void testFailStudent() {
        Student student = new Student("Naveen", 30);
        String result = studentService.checkResult(student);

        Assertions.assertEquals("FAIL", result);
    }

    @Test
    void testInvalidMarks() {
        Student student = new Student("Naveen", 150);

        Assertions.assertThrows(InvalidMarkException.class, () -> {
            studentService.checkResult(student);
        });
    }
}