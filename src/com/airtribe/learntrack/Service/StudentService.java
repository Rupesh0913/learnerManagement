package com.airtribe.learntrack.Service;

import com.airtribe.learntrack.Entity.Student;

import java.util.List;

public interface StudentService {

    String addStudent();

    List<Student> viewAllStudents();

    String deactivateStudent();

    Student searchStudentById();
}
