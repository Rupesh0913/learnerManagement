package com.airtribe.learntrack.Service.Impl;

import com.airtribe.learntrack.Entity.Student;
import com.airtribe.learntrack.Entity.Trainer;
import com.airtribe.learntrack.Exception.EntityNotFoundException;
import com.airtribe.learntrack.Exception.InvalidInputException;
import com.airtribe.learntrack.Service.TrainerService;
import com.airtribe.learntrack.Util.IdGenerator;
import com.airtribe.learntrack.Util.InputValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.airtribe.learntrack.Main.trainers;

public class TrainerServiceImpl implements TrainerService {

    Scanner sc = new Scanner(System.in);
    @Override
    public String addTrainer() {
        try {
            System.out.print("First Name: ");
            String firstName = InputValidator.validateRequiredString(
                    sc.nextLine(), "First Name");

            System.out.print("Last Name: ");
            String lastName =InputValidator.validateRequiredString(
                    sc.nextLine(),"Last Name");

            System.out.print("Email (optional, press Enter to skip): ");
            String email = InputValidator.validateOptionalEmail(
                    sc.nextLine()
            );

            System.out.print("specialization: ");
            String specialization = InputValidator.validateRequiredString(
                    sc.nextLine(), "specialization");

            System.out.print("experienceInYears: ");
            if (!sc.hasNextInt()) {
                sc.nextLine();
                throw new InvalidInputException("Experience must be numeric.");
            }
            int experienceInYears = sc.nextInt();

            long id = IdGenerator.generateTrainerId();

            Trainer trainer;
            if (email.isEmpty()) {
                trainer = new Trainer(id, firstName, lastName, specialization, experienceInYears, true);
            } else {
                trainer = new Trainer(id, firstName, lastName, email, specialization, experienceInYears, true);
            }

            trainers.add(trainer);
            return "Trainer added successfully! with ID: " + id;
        }catch (Exception e){
            return "Error adding Trainer: " + e.getMessage();
        }
    }

    @Override
    public List<Trainer> viewAllTrainers() {
        try{
            List<Trainer> trn;
            if (trainers.isEmpty()) {
                throw new InvalidInputException("Trainer list not initialized");
            } else {
                trn = new ArrayList<>(trainers);
            }
            return trn;
        } catch (Exception e) {
            System.out.println("Error viewing trainers: " + e.getMessage());
        }
        return null;
    }

    @Override
    public String deactivateTrainer() {
        try{
            if (trainers.isEmpty()) {
                throw new EntityNotFoundException("Trainer list not initialized");
            }

            System.out.print("Enter Trainer ID: ");
            if (!sc.hasNextInt()) {
                sc.nextLine();
                throw new InvalidInputException("Trainer Id must be numeric.");
            }
            int id = sc.nextInt();

            for (Trainer trainer : trainers) {
                if (trainer.getId() == id) {
                    trainer.setStatus(false);
                    return "Trainer deactivated successfully!";
                }
            }
            throw new EntityNotFoundException ("Trainer with ID " + id + " not found.");
        }
        catch (EntityNotFoundException | InvalidInputException e) {
            return "Error deactivating trainer: " + e.getMessage();
        }
    }
}
