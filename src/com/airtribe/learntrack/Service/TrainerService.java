package com.airtribe.learntrack.Service;

import com.airtribe.learntrack.Entity.Trainer;

import java.util.List;

public interface TrainerService {

    String addTrainer();
    List<Trainer> viewAllTrainers();

    String deactivateTrainer();
}
