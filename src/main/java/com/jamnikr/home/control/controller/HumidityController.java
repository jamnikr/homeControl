package com.jamnikr.home.control.controller;

import com.jamnikr.home.control.controller.response.WeatherResponse;
import com.jamnikr.home.control.model.Humidity;
import com.jamnikr.home.control.model.Room;
import com.jamnikr.home.control.repository.HumidityRepository;
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
@RequestMapping(value = "humidity")
@Slf4j
public class HumidityController {

    private final HumidityRepository humidityRepository;

    @Autowired
    public HumidityController(final HumidityRepository humidityRepository) {
        this.humidityRepository = humidityRepository;
    }

    @GetMapping(value = "add")
    public void addHumidity(final @RequestParam("room") String room, final @RequestParam("value") BigDecimal value) {
        log.info("Room: {} Value: {}", room, value);
        humidityRepository.save(Humidity.builder().room(Room.valueOf(room)).humidity(value).date(LocalDateTime.now()).build());
    }

    @GetMapping(value = "{room}")
    public WeatherResponse getHumidity(final @PathVariable(value = "room") String room) {
        final BigDecimal current = humidityRepository.findDistinctFirstByRoomOrderByDateDesc(Room.valueOf(room)).getHumidity();
        final LocalDateTime dayBefore = LocalDateTime.now().minusDays(1);
        final BigDecimal average = humidityRepository.findAverage(Room.valueOf(room), dayBefore);
        final BigDecimal max = humidityRepository.findMax(Room.valueOf(room), dayBefore);
        final BigDecimal min = humidityRepository.findMin(Room.valueOf(room), dayBefore);

        final WeatherResponse weatherResponse = WeatherResponse.builder()
            .avg(average.setScale(2, RoundingMode.HALF_UP))
            .current(current)
            .max(max)
            .min(min)
            .build();
        return weatherResponse;
    }

}
