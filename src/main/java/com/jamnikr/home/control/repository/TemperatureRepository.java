package com.jamnikr.home.control.repository;

import com.jamnikr.home.control.model.Room;
import com.jamnikr.home.control.model.Temperature;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Copyright (c) Asseco Business Solutions S.A. All rights reserved.
 */
public interface TemperatureRepository extends CrudRepository<Temperature, Integer> {

    Temperature findDistinctFirstByRoomOrderByDateDesc(final Room room);

    @Query("select AVG(t.temperature) from Temperature t where t.date>?2 and t.room=?1")
    BigDecimal findAverage(final Room room, final LocalDateTime from);

    @Query("select min(t.temperature) from Temperature t where t.date>?2 and t.room=?1")
    BigDecimal findMin(final Room room, final LocalDateTime from);

    @Query("select max(t.temperature) from Temperature t where t.date>?2 and t.room=?1")
    BigDecimal findMax(final Room room, final LocalDateTime from);

}
