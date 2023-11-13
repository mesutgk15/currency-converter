package dev.mesut.currencyconverter.service;

import dev.mesut.currencyconverter.model.CurrencyModel;
import dev.mesut.currencyconverter.model.RateRequest;
import dev.mesut.currencyconverter.model.RateResponse;

public interface ExchangeRateService {

    RateResponse getRate(RateRequest rateRequest);
    RateResponse save(RateRequest rateRequest);
    CurrencyModel updateRates();

}
