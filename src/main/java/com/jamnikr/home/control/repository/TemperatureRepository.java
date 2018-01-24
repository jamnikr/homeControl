package com.jamnikr.home.control.repository;

import com.jamnikr.home.control.model.Room;
import com.jamnikr.home.control.model.Temperature;
import org.springframework.data.repository.CrudRepository;

/**
 * Copyright (c) Asseco Business Solutions S.A. All rights reserved.
 */
public interface TemperatureRepository extends CrudRepository<Temperature, Integer> {

    Temperature findDistinctFirstByRoomOrderByDateDesc(final Room room);

}
