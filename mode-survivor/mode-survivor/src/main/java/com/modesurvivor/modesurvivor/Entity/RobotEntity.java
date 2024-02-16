package com.modesurvivor.modesurvivor.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RobotEntity {
    private Long id;
    private String model;
    private String serialNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private LocalDateTime manufacturedDate;
    private String category;

    public RobotEntity() {
    }

    private static <T> T defaultIfNull(T value, T defaultValue) {
        return value != null ? value : defaultValue;
    }

    private static LocalDateTime defaultIfNull(LocalDateTime value, LocalDateTime defaultValue) {
        return value != null ? value : defaultValue;
    }

}
