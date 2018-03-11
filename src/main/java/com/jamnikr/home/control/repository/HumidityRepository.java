package com.jamnikr.home.control.repository;

import com.jamnikr.home.control.model.Humidity;
import com.jamnikr.home.control.model.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Copyright (c) Asseco Business Solutions S.A. All rights reserved.
 */
public interface HumidityRepository extends CrudRepository<Humidity, Integer> {

    Humidity findDistinctFirstByRoomOrderByDateDesc(final Room room);

    @Query("select AVG(h.humidity) from Humidity h where h.date>?2 and h.room=?1")
    BigDecimal findAverage(final Room room, final LocalDateTime from);

    @Query("select min(h.humidity) from Humidity h where h.date>?2 and h.room=?1")
    BigDecimal findMin(final Room room, final LocalDateTime from);

    @Query("select max(h.humidity) from Humidity h where h.date>?2 and h.room=?1")
    BigDecimal findMax(final Room room, final LocalDateTime from);

}
