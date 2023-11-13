package dev.mesut.currencyconverter.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@Builder
public class RateResponse {

    private String source;
    private String target;
    private double rate;
    private LocalDateTime lastUpdated;

}
