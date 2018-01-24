package com.jamnikr.home.control.controller;

import com.jamnikr.home.control.model.Room;
import com.jamnikr.home.control.model.Temperature;
import com.jamnikr.home.control.repository.TemperatureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Copyright (c) Asseco Business Solutions S.A. All rights reserved.
 */
@RestController
@Slf4j
public class DomoticzController {

    private final TemperatureRepository temperatureRepository;

    @Autowired
    public DomoticzController(final TemperatureRepository temperatureRepository) {
        this.temperatureRepository = temperatureRepository;
    }

    @GetMapping(value = "/json.htm")
    public void temperature(final @RequestParam("idx") Integer id, final @RequestParam("svalue") BigDecimal value) {
        log.info("Id: {} Value: {}", id, value);
        temperatureRepository.save(Temperature.builder().room(Room.valueOf(id)).temperature(value).date(LocalDateTime.now()).build());
    }

    @GetMapping(value = "/temperature/{room}")
    public BigDecimal getCurrentTemperature(final @PathVariable(value = "room") String room) {
        return temperatureRepository.findDistinctFirstByRoomOrderByDateDesc(Room.valueOf(room)).getTemperature();
    }

}
