package dev.mesut.currencyconverter.service.impl;

import dev.mesut.currencyconverter.model.*;
import dev.mesut.currencyconverter.model.entity.ExchangeRateEntity;
import dev.mesut.currencyconverter.model.enums.EnumsCurrency;
import dev.mesut.currencyconverter.repository.ExchangeRateRepository;
import dev.mesut.currencyconverter.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
@Transactional
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final ExchangeRateRepository exchangeRateRepository;
    private final RestTemplate restTemplate;
    private static final String rateSourceUri = "https://mocki.io/v1/1e26abb9-d48e-42b9-995d-54cddecfbae2";

    @Override
    public RateResponse getRate(RateRequest rateRequest) {

        if (!EnumsCurrency.isCurrencySupported(rateRequest.getSource()))
            return null;

        if (!EnumsCurrency.isCurrencySupported(rateRequest.getTarget()))
            return null;

        ExchangeRateEntity exchangeRateEntity = exchangeRateRepository.findBySourceAndTarget(rateRequest.getSource(), rateRequest.getTarget());

        return RateResponse.builder()
                .source(exchangeRateEntity.getSource())
                .target(exchangeRateEntity.getTarget())
                .rate(exchangeRateEntity.getRate())
                .lastUpdated(exchangeRateEntity.getTimeStamp())
                .build();

    }

    @Override
    public RateResponse save(RateRequest rateRequest) {

        if (!EnumsCurrency.isCurrencySupported(rateRequest.getSource()))
            return null;

        if (!EnumsCurrency.isCurrencySupported(rateRequest.getTarget()))
            return null;

        ExchangeRateEntity exchangeRateEntity = ExchangeRateEntity.builder()
                .timeStamp(LocalDateTime.now())
                .rate(rateRequest.getRate())
                .source(rateRequest.getSource())
                .target(rateRequest.getTarget())
                .build();


        exchangeRateEntity = exchangeRateRepository.save(exchangeRateEntity);

        RateResponse rateResponse = RateResponse.builder()
                .rate(exchangeRateEntity.getRate())
                .source(exchangeRateEntity.getSource().toString())
                .target(exchangeRateEntity.getTarget().toString())
                .lastUpdated(exchangeRateEntity.getTimeStamp())
                .build();

        return rateResponse;
    }


    //Fetch and update the exchange rates from external source
    @Override
    public CurrencyModel updateRates() {

        CurrencyModel currencyModelResponse = new CurrencyModel();
        CurrencySubModel currencySubModelResponse;

        // Source url is defined as static in the class
        CurrencyModel currencyModel = restTemplate.getForObject(rateSourceUri, CurrencyModel.class);
        List<CurrencySubModel> currencies = currencyModel.getCurrencies();

        for (CurrencySubModel c : currencies) {
            String source;
            if (EnumsCurrency.isCurrencySupported(c.getSource().toString()))
                source = c.getSource().toString();
            else
                continue;

            currencySubModelResponse = new CurrencySubModel();
            List<Rate> rates = c.getRates();

            for (Rate r : rates) {
                String target;
                if (EnumsCurrency.isCurrencySupported(r.getTarget().toString()))
                    target = r.getTarget().toString();
                else
                    continue;

                ExchangeRateEntity exchangeRateEntity = ExchangeRateEntity.builder()
                        .source(source)
                        .target(target)
                        .rate(r.getRate())
                        .timeStamp(LocalDateTime.now())
                        .build();

                ExchangeRateEntity exchangeRateEntityResponse = exchangeRateRepository.save(exchangeRateEntity);

                currencySubModelResponse.setSource(EnumsCurrency.Currencies.valueOf(exchangeRateEntityResponse.getSource()));
                Rate rate = Rate.builder()
                        .target(EnumsCurrency.Currencies.valueOf(target))
                        .rate(exchangeRateEntity.getRate())
                        .build();
                currencySubModelResponse.getRates().add(rate);


            }

            currencyModelResponse.getCurrencies().add(currencySubModelResponse);

        }

        return currencyModelResponse;
    }
}
