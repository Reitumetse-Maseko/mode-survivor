package com.modesurvivor.modesurvivor.Service;

import com.modesurvivor.modesurvivor.Entity.SurvivorEntity;
import com.modesurvivor.modesurvivor.Repo.SurvivorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class SurvivorServ {

    @Autowired
    private final SurvivorRepo survivorRepository;

    @Autowired
    public SurvivorServ(SurvivorRepo survivorRepository){
        this.survivorRepository = survivorRepository;
    }

    public List<SurvivorEntity> getAllSurvivors() {
        return survivorRepository.findAll();
    }

    public Optional<SurvivorEntity> getSurvivorById(Long id) {
        return survivorRepository.findById(id);
    }

    public SurvivorEntity createSurvivor(SurvivorEntity survivor) {
        return survivorRepository.save(survivor);
    }

    public SurvivorEntity updateSurvivor(Long id, SurvivorEntity updatedSurvivor) {
        Optional<SurvivorEntity> existingSurvivor = survivorRepository.findById(id);

        if (existingSurvivor.isPresent()) {
            SurvivorEntity survivor = existingSurvivor.get();
            // Update fields as needed
            survivor.setName(updatedSurvivor.getName());
            survivor.setAge(updatedSurvivor.getAge());
            // Update other fields accordingly
            return survivorRepository.save(survivor);
        }

        return null;
    }


    public double calculateInfectedPercentage() {
        List<SurvivorEntity> allSurvivors = survivorRepository.findAll();
        long totalSurvivors = allSurvivors.size();

        if (totalSurvivors == 0) {
            return 0.0; // Avoid division by zero
        }

        long infectedSurvivors = allSurvivors.stream()
                .filter(SurvivorEntity::isInfected)
                .count();

        return ((double) infectedSurvivors / totalSurvivors) * 100.0;
    }

    public double calculateNonInfectedPercentage() {
        return 100.0 - calculateInfectedPercentage();
    }

    public List<SurvivorEntity> getInfectedList() {
        return survivorRepository.findByIsInfected(true);
    }

    public List<SurvivorEntity> getNonInfectedList() {
        return survivorRepository.findByIsInfected(false);
    }

    public void markInfected(SurvivorEntity survivor) {
        if (survivor.getInfectionCounter() >= 3 && !survivor.isInfected()) {
            survivor.setInfected(true);
            survivorRepository.save(survivor); // Update the database
        }
    }

    public void deleteSurvivor(Long id) {
        survivorRepository.deleteById(id);
    }
};
