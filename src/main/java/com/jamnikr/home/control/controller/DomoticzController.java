package com.jamnikr.home.control.controller;

import com.jamnikr.home.control.model.Temperature;
import com.jamnikr.home.control.repository.TemperaturRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    private final TemperaturRepository temperaturRepository;

    @Autowired
    public DomoticzController(final TemperaturRepository temperaturRepository) {
        this.temperaturRepository = temperaturRepository;
    }

    @GetMapping(value = "/json.htm")
    public void temperature(final @RequestParam("idx") Integer id, final @RequestParam("svalue") BigDecimal value) {

        temperaturRepository.save(Temperature.builder().idx(id).temperature(value).date(LocalDateTime.now()).build());
        log.info("Id: {} Value: {}", id, value);
    }

}
