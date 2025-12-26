package com.airtribe.learntrack.Entity;

public class Student extends Person {

    private String batch;
    private boolean active;

    public Student() {
        super();
    }

    public Student(Long id, String firstName, String lastName, String email,
                   String batch) {
        super(id, firstName, lastName, email); // calls Person constructor
        this.batch = batch;
    }

    // Overloaded constructor (WITHOUT email)
    public Student(Long id, String firstName, String lastName,
                   String batch) {
        super(id, firstName, lastName);   // calls Person constructor
        this.batch = batch;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Student{" +
                ", id=" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", batch='" + batch + '\'' +
                ", active=" + active +
                '}';
    }
}
