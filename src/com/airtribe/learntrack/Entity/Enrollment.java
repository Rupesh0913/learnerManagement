package com.airtribe.learntrack.Entity;

import com.airtribe.learntrack.Enums.EnrollmentStatus;

import java.time.LocalDate;

public class Enrollment {

    private Long id;
    private Long studentId;
    private Long courseId;
    private Long trainerId;
    private LocalDate enrollmentDate;
    private EnrollmentStatus status;

    public Enrollment() {
    }

    public Enrollment(Long id, Long studentId, Long courseId,
                      LocalDate enrollmentDate, EnrollmentStatus status) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
        this.status = status;
    }

    public Enrollment(Long id, Long studentId, Long courseId,Long trainerId,
                      LocalDate enrollmentDate, EnrollmentStatus status) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.trainerId = trainerId;
        this.enrollmentDate = enrollmentDate;
        this.status = status;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getTrainerId() { return trainerId; }
    public void setTrainerId(Long trainerId) { this.trainerId = trainerId; }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public EnrollmentStatus getStatus() {
        return status;
    }

    public void setStatus(EnrollmentStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                ", trainerId=" + trainerId +
                ", enrollmentDate=" + enrollmentDate +
                ", status=" + status +
                '}';
    }
}
