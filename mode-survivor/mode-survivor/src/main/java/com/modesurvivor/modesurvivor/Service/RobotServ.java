package com.modesurvivor.modesurvivor.Service;

import com.modesurvivor.modesurvivor.Entity.RobotEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class RobotServ {
    public RobotServ() {
    }

    @Value("${external.api.url}")
    private String robotApiUrl;

    public List<RobotEntity> getRobotsWithLocations() {
        RestTemplate restTemplate = new RestTemplate();

        // Make the HTTP GET request to the API
        RobotEntity[] rawRobotsArray = restTemplate.getForObject(robotApiUrl, RobotEntity[].class);

        // Convert the array to a list for easier processing
        List<RobotEntity> rawRobots = Arrays.asList(rawRobotsArray);

        // Process raw data and create RobotEntity objects using Lombok
        List<RobotEntity> robots = processRobots(rawRobots);

        return robots;
    }

    private List<RobotEntity> processRobots(List<RobotEntity> rawRobots) {
        List<RobotEntity> robots = new ArrayList<>();

        // Process raw data and create RobotEntity objects using Lombok
        for (RobotEntity rawRobot : rawRobots) {
            RobotEntity robot = new RobotEntity();
            robot.setId(rawRobot.getId());
            robot.setModel(defaultIfNull(rawRobot.getModel(), ""));
            robot.setSerialNumber(defaultIfNull(rawRobot.getSerialNumber(), ""));
            robot.setManufacturedDate(rawRobot.getManufacturedDate());
            robot.setCategory((defaultIfNull(rawRobot.getCategory(),"")));

            robots.add(robot);
        }

        return robots;
    }

    // Helper method to provide a default value if the input is null
    private static <T> T defaultIfNull(T value, T defaultValue) {
        return value != null ? value : defaultValue;
    }
}
