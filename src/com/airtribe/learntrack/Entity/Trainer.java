package com.airtribe.learntrack.Entity;

public class Trainer extends Person{

    private String specialization;
    private int experienceInYears;
    private boolean active;

    public boolean isActive() {
        return active;
    }

    public void setStatus(boolean active) {
        this.active = active;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getExperienceInYears() {
        return experienceInYears;
    }

    public void setExperienceInYears(int experienceInYears) {
        this.experienceInYears = experienceInYears;
    }

    public Trainer(Long id, String firstName, String lastName,
                   String specialization, int experienceInYears, boolean active) {
        super(id, firstName, lastName);
        this.specialization = specialization;
        this.experienceInYears = experienceInYears;
        this.active = active;
    }

    public Trainer(Long id, String firstName, String lastName, String email,
                   String specialization, int experienceInYears, boolean active) {
        super(id, firstName, lastName,email);
        this.specialization = specialization;
        this.experienceInYears = experienceInYears;
        this.active = active;
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "specialization='" + specialization + '\'' +
                ", experienceInYears=" + experienceInYears +
                ", active=" + active +
                ", id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
