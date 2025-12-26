package com.airtribe.learntrack.Service;

import com.airtribe.learntrack.Entity.Enrollment;

import java.util.List;

public interface EnrollmentService {

    String enrollStudent();

    String enrollTrainerToCourse();

    Enrollment viewEnrollmentsByStudent();

    String updateEnrollmentStatus();
}
