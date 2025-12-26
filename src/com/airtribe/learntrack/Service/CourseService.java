package com.airtribe.learntrack.Service;

import com.airtribe.learntrack.Entity.Course;

import java.util.List;

public interface CourseService {

    String addCourse();

    List<Course> viewAllCourses();

    String removeCourse();

    String toggleCourseStatus();
}
