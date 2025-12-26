package com.airtribe.learntrack.Service.Impl;

import com.airtribe.learntrack.Entity.Course;
import com.airtribe.learntrack.Entity.Enrollment;
import com.airtribe.learntrack.Entity.Student;
import com.airtribe.learntrack.Entity.Trainer;
import com.airtribe.learntrack.Enums.EnrollmentStatus;
import com.airtribe.learntrack.Exception.InvalidInputException;
import com.airtribe.learntrack.Service.CourseService;
import com.airtribe.learntrack.Service.EnrollmentService;
import com.airtribe.learntrack.Service.StudentService;
import com.airtribe.learntrack.Service.TrainerService;
import com.airtribe.learntrack.Util.IdGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.airtribe.learntrack.Main.*;

public class EnrollmentServiceImpl implements EnrollmentService {
    Scanner sc = new Scanner(System.in);
    @Override
    public String enrollStudent() {
        try {
            if (students.isEmpty()) {
                return "No students available to enroll.";
            }
            if (courses.isEmpty()) {
                return "No courses available for enrollment.";
            }
            System.out.println("***** Available Students who are active *****");
            for (Student s : students) {
                if (s.isActive()) {
                    System.out.println(s);
                }
            }
            System.out.print("Select Student ID to enroll:");
            if (!sc.hasNextInt()) {
                sc.nextLine();
                throw new InvalidInputException("ID must be numeric.");
            }
            long studentId = sc.nextInt();

            students.stream()
                    .filter(student -> student.getId() == studentId  && student.isActive())
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Student with ID " + studentId + " not found."));

            System.out.println("***** Available Courses which is active *****");
            for (Course c : courses) {
                if (c.isActive()) {
                    System.out.println(c);
                }
            }
            System.out.print("Select Course ID to enroll the student into:");
            if (!sc.hasNextInt()) {
                sc.nextLine();
                throw new InvalidInputException("ID must be numeric.");
            }
            long courseId = sc.nextInt();
            courses.stream()
                    .filter(course -> course.getId() == courseId  && course.isActive())
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Course with ID " + courseId + " not found."));

            long id = IdGenerator.generateEnrollmentId();
            LocalDate enrollmentDate = LocalDate.now();
            Enrollment enrollment = new Enrollment(id,studentId,courseId,enrollmentDate, EnrollmentStatus.ACTIVE);

            enrollments.add(enrollment);
            return "Student enrolled successfully! Enrollment ID: " + id;
        }catch (Exception e){
            return "Error during enrollment: " + e.getMessage();

        }
    }

    @Override
    public String enrollTrainerToCourse() {
        try{
            if (enrollments.isEmpty()) {
                return "No enrollments available to assign trainer.";
            }
            if (trainers.isEmpty()) {
                return "No trainers available for assignment.";
            }

            System.out.println("***** Available Enrollments *****");
            for (Enrollment enrollment : enrollments) {
                if(enrollment.getStatus() == EnrollmentStatus.ACTIVE)
                    System.out.println(enrollment);
            }

            System.out.print("Enter Enrollment ID to assign trainer: ");
            if (!sc.hasNextInt()) {
                sc.nextLine();
                throw new InvalidInputException("ID must be numeric.");
            }
            long enrollmentId = sc.nextInt();

            Enrollment enrollment = enrollments.stream()
                    .filter(enr -> enr.getId() == enrollmentId && enr.getStatus() == EnrollmentStatus.ACTIVE)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Enrollment with ID " + enrollmentId + " not found."));

            System.out.println("***** Available Trainers *****");
            for (Trainer trainer : trainers) {
                System.out.println(trainer);
            }
            System.out.print("Select Trainer ID to assign: ");
            if (!sc.hasNextInt()) {
                sc.nextLine();
                throw new InvalidInputException("ID must be numeric.");
            }
            long trainerId = sc.nextInt();

            trainers.stream()
                    .filter(trainer -> trainer.getId() == trainerId && trainer.isActive())
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Trainer with ID " + trainerId + " not found."));

            enrollment.setTrainerId(trainerId);
            return "Trainer assigned successfully to Enrollment ID: " + enrollmentId;
        }
        catch (Exception e){
            return "Error during trainer assignment: " + e.getMessage();

        }
    }

    @Override
    public Enrollment viewEnrollmentsByStudent() {
        try{
            if (enrollments.isEmpty()) {
                System.out.println( "No enrollments available." );
                return null;
            }
            System.out.print("Enter Student ID to view enrollments: ");
            if (!sc.hasNextInt()) {
                sc.nextLine();
                throw new InvalidInputException("ID must be numeric.");
            }
            long studentId = sc.nextInt();
            return enrollments.stream()
                    .filter(enr -> enr.getStudentId() == studentId)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("No enrollments found for Student ID " + studentId + "."));

        }catch (Exception e){
            System.out.println("Error viewing enrollments: " + e.getMessage());
            return null;
        }
    }


    @Override
    public String updateEnrollmentStatus() {
        try{
            if (enrollments.isEmpty()) {
                return "No enrollments available to update its status.";
            }

            System.out.println("***** Available Enrollments *****");
            for (Enrollment enrollment : enrollments) {
                System.out.println(enrollment);
            }

            System.out.print("Enter Enrollment ID to update status: ");
            if (!sc.hasNextInt()) {
                sc.nextLine();
                throw new InvalidInputException("ID must be numeric.");
            }
            long enrollmentId = sc.nextInt();
            Enrollment enrollment = enrollments.stream()
                    .filter(enr -> enr.getId() == enrollmentId)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Enrollment with ID " + enrollmentId + " not found."));

            System.out.println("Select new status: 1. ACTIVE 2. COMPLETED 3. CANCELLED");
            int statusChoice = sc.nextInt();
            switch (statusChoice) {
                case 1:
                    enrollment.setStatus(EnrollmentStatus.ACTIVE);
                    return "Enrollment marked as ACTIVE successfully!";
                case 2:
                    if( null == enrollment.getTrainerId()){
                        throw new InvalidInputException("Cannot mark as COMPLETED without assigning a trainer.");
                    }
                    enrollment.setStatus(EnrollmentStatus.COMPLETED);
                    return "Enrollment marked as COMPLETED successfully!";
                case 3:
                    enrollment.setStatus(EnrollmentStatus.CANCELLED);
                    return "Enrollment marked as CANCELLED successfully!";
                default:
                    throw new InvalidInputException("Invalid status choice.");
            }

        }
        catch (Exception e){
            return "Error updating enrollment status: " + e.getMessage();
        }
    }
}
