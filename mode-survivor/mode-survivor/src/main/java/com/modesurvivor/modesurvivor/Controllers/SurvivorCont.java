package com.modesurvivor.modesurvivor.Controllers;

import com.modesurvivor.modesurvivor.Entity.SurvivorEntity;
import com.modesurvivor.modesurvivor.Repo.SurvivorRepo;
import com.modesurvivor.modesurvivor.Service.SurvivorServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/survivors")
public class SurvivorCont {
    private SurvivorServ  survivorServ;

    @Autowired
    public SurvivorCont(SurvivorServ survivorServ){
        this.survivorServ = survivorServ;
    }

    @GetMapping()
    public List<SurvivorEntity> getAllSurvivors() {
        return survivorServ.getAllSurvivors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SurvivorEntity> getSurvivorById(@PathVariable Long id) {
        Optional<SurvivorEntity> survivor = survivorServ.getSurvivorById(id);

        if (survivor.isPresent()) {
            return new ResponseEntity<>(survivor.get(), HttpStatus.OK);
        } else {
            throw new RuntimeException("Survivor not found with id: " + id);
        }
    }


    @PostMapping
    public ResponseEntity<SurvivorEntity> createSurvivor(@RequestBody SurvivorEntity survivor) {
        SurvivorEntity savedSurvivor = survivorServ.createSurvivor(survivor);
        return new ResponseEntity<>(savedSurvivor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SurvivorEntity> updateSurvivor(@PathVariable Long id, @RequestBody SurvivorEntity updatedSurvivor) {
        SurvivorEntity updated = survivorServ.updateSurvivor(id, updatedSurvivor);

        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/infectedPercentage")
    public ResponseEntity<Double> getInfectedPercentage() {
        double infectedPercentage = survivorServ.calculateInfectedPercentage();
        return ResponseEntity.ok(infectedPercentage);
    }

    @GetMapping("/nonInfectedPercentage")
    public ResponseEntity<Double> getNonInfectedPercentage() {
        double nonInfectedPercentage = survivorServ.calculateNonInfectedPercentage();
        return ResponseEntity.ok(nonInfectedPercentage);
    }

    @GetMapping("/infectedList")
    public ResponseEntity<List<SurvivorEntity>> getInfectedList() {
        List<SurvivorEntity> infectedList = survivorServ.getInfectedList();
        return ResponseEntity.ok(infectedList);
    }

    @GetMapping("/nonInfectedList")
    public ResponseEntity<List<SurvivorEntity>> getNonInfectedList() {
        List<SurvivorEntity> nonInfectedList = survivorServ.getNonInfectedList();
        return ResponseEntity.ok(nonInfectedList);
    }

}
