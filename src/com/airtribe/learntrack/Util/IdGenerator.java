package com.airtribe.learntrack.Util;

public class IdGenerator {

    private static long studentIdCounter =0;
    private static long trainerIdCounter=0;
    private static long courseIdCounter=0;
    private static long enrollmentIdCounter=0;

    public static long generateStudentId() {
        return ++studentIdCounter;
    }

    public static long generateTrainerId() {
        return ++trainerIdCounter;
    }

    public static long generateCourseId() {
        return ++courseIdCounter;
    }

    public static long generateEnrollmentId() {
        return ++enrollmentIdCounter;
    }

}
