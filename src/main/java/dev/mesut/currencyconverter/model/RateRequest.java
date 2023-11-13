package dev.mesut.currencyconverter.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;


@Data
@Builder
public class RateRequest {

    private String source;
    private String target;
    private double rate;

}
