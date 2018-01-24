package com.jamnikr.home.control.model;

import lombok.AllArgsConstructor;

import java.util.Arrays;

/**
 * Copyright (c) Asseco Business Solutions S.A. All rights reserved.
 */
@AllArgsConstructor
public enum Room {

    BEDROOM(3), BALCONY(4), BATHROOM(5), TEST(0);

    private final Integer idx;

    public static Room valueOf(final Integer idx) {
        return Arrays.stream(values()).filter(room -> room.idx.equals(idx)).findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
