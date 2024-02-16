package com.modesurvivor.modesurvivor.Entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "my_survivors")
@Data
public class SurvivorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name,gender;

    private int age,infectionCounter;

    private double longitude,latitude;

    private boolean hasWater,hasFood,hasMedication,hasAmmunition,isInfected;
}
