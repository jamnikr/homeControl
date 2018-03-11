package com.jamnikr.home.control.controller.response;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * Copyright (c) Asseco Business Solutions S.A. All rights reserved.
 */
@Getter
@Builder
public class WeatherResponse {

    private final BigDecimal current;

    private final BigDecimal avg;

    private final BigDecimal min;

    private final BigDecimal max;
}
