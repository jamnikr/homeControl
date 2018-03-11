package com.jamnikr.home.control.controller;

import com.jamnikr.home.control.controller.response.WeatherResponse;
import com.jamnikr.home.control.model.AirPollution;
import com.jamnikr.home.control.model.Room;
import com.jamnikr.home.control.repository.AirPollutionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

/**
 * Copyright (c) Asseco Business Solutions S.A. All rights reserved.
 */
@RestController
@RequestMapping(value = "air-pollution")
@Slf4j
public class AirPollutionController {

    private final AirPollutionRepository airPollutionRepository;

    @Autowired
    public AirPollutionController(final AirPollutionRepository airPollutionRepository) {
        this.airPollutionRepository = airPollutionRepository;
    }

    @GetMapping(value = "add")
    public void addPollution(final @RequestParam("room") Integer room, final @RequestParam("value") BigDecimal value) {
        log.info("Room: {} Value: {}", room, value);
        airPollutionRepository.save(AirPollution.builder().room(Room.valueOf(room)).pollution(value).date(LocalDateTime.now()).build());
    }

    @GetMapping(value = "{room}")
    public WeatherResponse getCurrent(final @PathVariable(value = "room") String room) {
        final BigDecimal current = airPollutionRepository.findDistinctFirstByRoomOrderByDateDesc(Room.valueOf(room)).getPollution();
        final LocalDateTime dayBefore = LocalDateTime.now().minusDays(1);
        final BigDecimal average = airPollutionRepository.findAverage(Room.valueOf(room), dayBefore);
        final BigDecimal max = airPollutionRepository.findMax(Room.valueOf(room), dayBefore);
        final BigDecimal min = airPollutionRepository.findMin(Room.valueOf(room), dayBefore);

        final WeatherResponse weatherResponse = WeatherResponse.builder()
            .avg(average.setScale(2, RoundingMode.HALF_UP))
            .current(current)
            .max(max)
            .min(min)
            .build();
        return weatherResponse;
    }

}
