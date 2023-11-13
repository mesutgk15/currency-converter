package dev.mesut.currencyconverter.controller;

import dev.mesut.currencyconverter.model.*;
import dev.mesut.currencyconverter.model.entity.ExchangeRateEntity;
import dev.mesut.currencyconverter.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rates")
public class RateController {

    private final RestTemplate restTemplate;
    private final ExchangeRateService exchangeRateService;

    @GetMapping
    public RateResponse getRate(@RequestBody RateRequest rateRequest) {
        return exchangeRateService.getRate(rateRequest);
    }

    @PostMapping
    public RateResponse saveRate(@RequestBody RateRequest rateRequest) {
        return exchangeRateService.save(rateRequest);
    }

    @PutMapping
    public CurrencyModel updateRates() {

        CurrencyModel currencyModel = exchangeRateService.updateRates();

        // Log the rates updated in the database
        for (CurrencySubModel c : currencyModel.getCurrencies()) {
            System.out.println(c.getSource());
            for (Rate r : c.getRates()) {
                System.out.println(r);
            }
        }

        return currencyModel;

    }
}
