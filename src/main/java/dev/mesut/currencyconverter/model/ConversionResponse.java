package dev.mesut.currencyconverter.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ConversionResponse {

    private String transactionId;
    private String source;
    private String target;
    private double convertedAmount;
    private LocalDateTime timeStamp;

}
