package com.jamnikr.home.control.controller;

import com.jamnikr.home.control.controller.response.WeatherResponse;
import com.jamnikr.home.control.model.Room;
import com.jamnikr.home.control.repository.TemperatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

/**
 * Copyright (c) Asseco Business Solutions S.A. All rights reserved.
 */
@RestController
public class TemperatureController {

    private final TemperatureRepository temperatureRepository;

    @Autowired
    public TemperatureController(final TemperatureRepository temperatureRepository) {
        this.temperatureRepository = temperatureRepository;
    }

    @GetMapping(value = "/temperature/{room}")
    public WeatherResponse getCurrent(final @PathVariable(value = "room") String room) {
        final BigDecimal current = temperatureRepository.findDistinctFirstByRoomOrderByDateDesc(Room.valueOf(room)).getTemperature();
        final LocalDateTime dayBefore = LocalDateTime.now().minusDays(1);
        final BigDecimal average = temperatureRepository.findAverage(Room.valueOf(room), dayBefore);
        final BigDecimal max = temperatureRepository.findMax(Room.valueOf(room), dayBefore);
        final BigDecimal min = temperatureRepository.findMin(Room.valueOf(room), dayBefore);

        final WeatherResponse weatherResponse = WeatherResponse.builder()
            .avg(average.setScale(2, RoundingMode.HALF_UP))
            .current(current)
            .max(max)
            .min(min)
            .build();
        return weatherResponse;
    }

}
