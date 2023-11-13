package dev.mesut.currencyconverter.service;

import dev.mesut.currencyconverter.model.ConversionRequest;
import dev.mesut.currencyconverter.model.ConversionResponse;

import java.time.LocalDate;
import java.util.List;

public interface ConversionService {

    ConversionResponse convert(ConversionRequest conversionRequest);
    List<ConversionResponse> getConversionList(String transactionid, LocalDate date);
}
