package com.airtribe.learntrack;

import com.airtribe.learntrack.Entity.Course;
import com.airtribe.learntrack.Entity.Enrollment;
import com.airtribe.learntrack.Entity.Student;
import com.airtribe.learntrack.Entity.Trainer;
import com.airtribe.learntrack.Exception.InvalidInputException;
import com.airtribe.learntrack.Service.CourseService;
import com.airtribe.learntrack.Service.EnrollmentService;
import com.airtribe.learntrack.Service.Impl.CourseServiceImpl;
import com.airtribe.learntrack.Service.Impl.EnrollmentServiceImpl;
import com.airtribe.learntrack.Service.Impl.StudentServiceImpl;
import com.airtribe.learntrack.Service.Impl.TrainerServiceImpl;
import com.airtribe.learntrack.Service.StudentService;
import com.airtribe.learntrack.Service.TrainerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    public static ArrayList<Student> students = new ArrayList<>();
    public static ArrayList<Course> courses = new ArrayList<>();
    public static ArrayList<Enrollment> enrollments = new ArrayList<>();
    public static ArrayList<Trainer> trainers = new ArrayList<>();
    public static void main(String[] args) {
        System.out.println("Welcome to LearnTrack!");


        boolean exit = false;

        while (!exit){
            try {
                printMainMenu();
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice){
                    case 1 : studentManagementMenu();
                    break;
                    case 2 : trainerManagementMenu();
                    break;
                    case 3 : courseManagementMenu();
                    break;
                    case 4 : enrollmentManagementMenu();
                    break;
                    case 0 : exit = true;
                        System.out.println("Exiting application. Goodbye!");
                        break;
                    default :
                        throw new InvalidInputException("Invalid main menu option!");
                }
            }
            catch (InvalidInputException e) {
                System.out.println("Error: " + e.getMessage());

            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number only.");
                sc.nextLine();
            }
        }
    }

    public static void printMainMenu() {
        System.out.println("*********************************************");
        System.out.println("*               MAIN MENU                   *");
        System.out.println("*********************************************");
        System.out.println("*  1. Student Management                    *");
        System.out.println("*  2. Trainer Management                    *");
        System.out.println("*  3. Course Management                     *");
        System.out.println("*  4. Enrollment Management                 *");
        System.out.println("*  0. Exit                                  *");
        System.out.println("*********************************************");
        System.out.print("Please select an option: ");
    }

    private static void studentManagementMenu() {

            StudentService studentService = new StudentServiceImpl();
            boolean back = false;

            while (!back) {
                try {
                System.out.println("*********************************************");
                System.out.println("*            Student Management             *");
                System.out.println("*********************************************");
                System.out.println("*  1. Add new student                       *");
                System.out.println("*  2. View all students                     *");
                System.out.println("*  3. Deactivate a student                  *");
                System.out.println("*  4. Search student by ID                  *");
                System.out.println("*  0. Back                                  *");
                System.out.println("*********************************************");

                System.out.print("Enter choice: ");

                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        String addResult = studentService.addStudent();
                        System.out.println(addResult);
                        break;

                    case 2:
                        List<Student> students = studentService.viewAllStudents();
                        students.forEach(System.out::println);
                        break;

                    case 3:
                        String deactivateResult = studentService.deactivateStudent();
                        System.out.println(deactivateResult);
                        break;

                    case 4:
                        Student student = studentService.searchStudentById();
                        System.out.println(student);
                        break;

                    case 0:
                        back = true;
                        break;

                    default:
                        throw new InvalidInputException("Invalid Student Management option!");
                }

            } catch(Exception e){
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    private static void courseManagementMenu() {

        CourseService courseService = new CourseServiceImpl();
        boolean back = false;
        while(!back) {
            try {
                System.out.println("*********************************************");
                System.out.println("*            Course Management              *");
                System.out.println("*********************************************");
                System.out.println("*  1. Add new course                        *");
                System.out.println("*  2. View all courses                      *");
                System.out.println("*  3. Remove a courses                      *");
                System.out.println("*  4. Activate/Deactivate course            *");
                System.out.println("*  0. Back                                  *");
                System.out.println("*********************************************");

                System.out.print("Enter choice: ");

                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1: String addResult = courseService.addCourse();
                        System.out.println(addResult);
                        break;
                    case 2: List<Course> courseList =  courseService.viewAllCourses();
                        courseList.forEach(System.out::println);
                        break;
                    case 3: String removedResult =  courseService.removeCourse();
                        System.out.println(removedResult);
                        break;
                    case 4: String toggleRes = courseService.toggleCourseStatus();
                        System.out.println(toggleRes);
                        break;
                    case 0:
                        back = true;
                        break;
                    default:
                        throw new InvalidInputException("Invalid Course Management option!");
                }

            } catch (InvalidInputException e) {
                System.out.println("Error " + e.getMessage());
            }
        }
    }
    private static void enrollmentManagementMenu() {

        EnrollmentService enrollmentService = new EnrollmentServiceImpl();
        boolean back = false;
        while(!back) {
            try {
                System.out.println("*********************************************");
                System.out.println("*            Enrollment Management          *");
                System.out.println("*********************************************");
                System.out.println("*  1. Enroll student in course              *");
                System.out.println("*  2. Enroll trainer in course              *");
                System.out.println("*  3. View enrollments for student          *");
                System.out.println("*  4. Update enrollment status              *");
                System.out.println("*  0. Back                                  *");
                System.out.println("*********************************************");

                System.out.print("Enter choice: ");

                int choice = sc.nextInt();

                switch (choice) {
                    case 1: String addResult = enrollmentService.enrollStudent();
                        System.out.println(addResult);
                        break;
                    case 2: String addTrainer = enrollmentService.enrollTrainerToCourse();
                        System.out.println(addTrainer);
                        break;
                    case 3: Enrollment enrollment = enrollmentService.viewEnrollmentsByStudent();
                        if(null != enrollment)
                            System.out.println(enrollment);
                        break;

                    case 4: String updateStatus =  enrollmentService.updateEnrollmentStatus();
                        System.out.println(updateStatus);
                        break;
                    case 0:
                        back = true;
                        break;
                    default:
                        throw new InvalidInputException("Invalid Enrollment option!");
                }

            } catch (InvalidInputException /*| EntityNotFoundException*/ e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    private static void trainerManagementMenu() {
        TrainerService trainerService = new TrainerServiceImpl();
        boolean back = false;
        while (!back) {
            try {

                System.out.println("*********************************************");
                System.out.println("*            Trainer Management             *");
                System.out.println("*********************************************");
                System.out.println("*  1. Add new Trainer                       *");
                System.out.println("*  2. View all Trainer                      *");
                System.out.println("*  3. Deactivate a Trainer                  *");
                System.out.println("*  0. Back                                  *");
                System.out.println("*********************************************");
                System.out.print("Enter choice: ");

                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1: String addResult = trainerService.addTrainer();
                        System.out.println(addResult);
                        break;

                    case 2: List<Trainer> trainerList =  trainerService.viewAllTrainers();
                        trainerList.forEach(System.out::println);
                        break;

                    case 3: String toggleStatus = trainerService.deactivateTrainer();
                        System.out.println(toggleStatus);
                        break;

                    case 0:
                        back = true;
                        break;
                    default:
                        throw new InvalidInputException("Invalid Trainer Management option!");
                }

            } catch (InvalidInputException e) {
                System.out.println("Error " + e.getMessage());
            }
        }
    }
}



