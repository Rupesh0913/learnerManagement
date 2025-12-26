package com.airtribe.learntrack.Service.Impl;

import com.airtribe.learntrack.Entity.Student;
import com.airtribe.learntrack.Exception.EntityNotFoundException;
import com.airtribe.learntrack.Service.StudentService;
import com.airtribe.learntrack.Util.IdGenerator;
import com.airtribe.learntrack.Util.InputValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.airtribe.learntrack.Main.students;

public class StudentServiceImpl implements StudentService {

    Scanner sc = new Scanner(System.in);
    @Override
    public String addStudent() {
        try {
            System.out.print("First Name: ");
            String firstName = InputValidator.validateRequiredString(
                    sc.nextLine(), "First Name");

            System.out.print("Last Name: ");
            String lastName =InputValidator.validateRequiredString(
                    sc.nextLine(),"Last Name");

            System.out.print("Batch: ");
            String batch = sc.nextLine();
            if(null ==batch || batch.isEmpty()){
                throw new EntityNotFoundException("Batch cannot be empty");
            }

            System.out.print("Email (optional, press Enter to skip): ");
            String email = InputValidator.validateOptionalEmail(
                    sc.nextLine()
            );

            long id = IdGenerator.generateStudentId();

            Student student;
            if (null == email) {
                student = new Student(id, firstName, lastName, batch);
            } else {
                student = new Student(id, firstName, lastName, email, batch);
            }
            student.setActive(true);

            students.add(student);
            return "Student added successfully! with ID: " + id;
        }catch (Exception e){
            return "Error adding student: " + e.getMessage();
        }
    }

    @Override
    public List<Student> viewAllStudents() {
        try {
            List<Student> stud;
            if (students.isEmpty()) {
                throw new EntityNotFoundException("Student list not initialized");
            } else {
                stud = new ArrayList<>(students);
            }
            return stud;
        } catch (Exception e) {
            System.out.println("Error viewing students: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public String deactivateStudent() {

        try {
            if (students.isEmpty()) {
                throw new EntityNotFoundException("No students available to deactivate.");
            }

            System.out.print("Enter Student ID: ");
            int id = sc.nextInt();

            students.stream()
                    .filter(student -> student.getId() == id)
                    .findFirst()
                    .ifPresent(student -> student.setActive(false));

            return "Student deactivated successfully!";

        } catch (Exception e) {
            return "Error deactivating student: " + e.getMessage();
        }
    }

    @Override
    public Student searchStudentById() {

            if (students.isEmpty()) {
                throw new EntityNotFoundException("No students available.");
            }

            System.out.print("Enter Student ID: ");
            int id = sc.nextInt();

            return students.stream()
                    .filter(student -> student.getId() == id)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Student with ID " + id + " not found."));

    }

}
