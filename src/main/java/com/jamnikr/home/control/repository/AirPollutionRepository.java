package com.jamnikr.home.control.repository;

import com.jamnikr.home.control.model.AirPollution;
import com.jamnikr.home.control.model.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Copyright (c) Asseco Business Solutions S.A. All rights reserved.
 */
public interface AirPollutionRepository extends CrudRepository<AirPollution, Integer> {

    AirPollution findDistinctFirstByRoomOrderByDateDesc(final Room room);

    @Query("select AVG(ap.pollution) from AirPollution ap where ap.date>?2 and ap.room=?1")
    BigDecimal findAverage(final Room room, final LocalDateTime from);

    @Query("select min(ap.pollution) from AirPollution ap where ap.date>?2 and ap.room=?1")
    BigDecimal findMin(final Room room, final LocalDateTime from);

    @Query("select max(ap.pollution) from AirPollution ap where ap.date>?2 and ap.room=?1")
    BigDecimal findMax(final Room room, final LocalDateTime from);

}
