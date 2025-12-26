package com.airtribe.learntrack.Service.Impl;

import com.airtribe.learntrack.Entity.Course;
import com.airtribe.learntrack.Exception.EntityNotFoundException;
import com.airtribe.learntrack.Exception.InvalidInputException;
import com.airtribe.learntrack.Service.CourseService;
import com.airtribe.learntrack.Util.IdGenerator;
import com.airtribe.learntrack.Util.InputValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.airtribe.learntrack.Main.courses;

public class CourseServiceImpl implements CourseService {

    Scanner sc = new Scanner(System.in);
    @Override
    public String addCourse() {
        try {
            System.out.print("Course Name: ");
            String courseName =  InputValidator.validateRequiredString(
                    sc.nextLine(), "Course Name");

            System.out.print("Course Description: ");
            String courseDescription = InputValidator.validateRequiredString(
                    sc.nextLine(), "Course Description");

            System.out.print("Course Duration(in months): ");

            if (!sc.hasNextInt()) {
                sc.nextLine();
                throw new InvalidInputException("Course duration must be numeric.");
            }
            int courseDuration = sc.nextInt();

            long id = IdGenerator.generateCourseId();

            Course course = new Course(id, courseName, courseDescription, courseDuration, true);
            courses.add(course);
            return "Course added successfully! Course Name: " + courseName;

        }
        catch (Exception e) {
            return "Error adding course: " + e.getMessage();
        }
    }

    @Override
    public List<Course> viewAllCourses() {
        try{
            List<Course> crs;
            if (courses.isEmpty()) {
                throw new EntityNotFoundException("Course list not initialized");
            } else {
                crs = new ArrayList<>(courses);
            }
            return crs;
        } catch (Exception e) {
            System.out.println("Error viewing courses: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public String removeCourse() {
        try{
            if (courses.isEmpty()) {
                throw new EntityNotFoundException("No Course available to remove.");
            }

            System.out.print("Enter Course ID to remove: ");
            if (!sc.hasNextInt()) {
                sc.nextLine();
                throw new InvalidInputException("ID must be numeric.");
            }
            long courseId = sc.nextLong();

            Course courseToRemove = null;
            for (Course course : courses) {
                if (course.getId() == courseId) {
                    courseToRemove = course;
                    break;
                }
            }

            if (courseToRemove != null) {
                courses.remove(courseToRemove);
                return "Course removed successfully!";
            } else {
                return "Course with ID " + courseId + " not found.";
            }
        } catch (Exception e) {
            return "Error removing course: " + e.getMessage();
        }
    }

    @Override
    public String toggleCourseStatus() {
        try{
            System.out.print("Enter Course ID to toggle status: ");
            if (!sc.hasNextInt()) {
                sc.nextLine();
                throw new InvalidInputException("ID must be numeric.");
            }
            long courseId = sc.nextLong();

            for (Course course : courses) {
                if (course.getId() == courseId) {
                    course.setActive(!course.isActive());
                    return "Course status toggled successfully! New status: " + (course.isActive() ? "Active" : "Inactive");
                }
            }
            return "Course with ID " + courseId + " not found.";
        } catch (Exception e) {
            return "Error toggling course status: " + e.getMessage();
        }
    }
}
