package dev.mesut.currencyconverter.service.impl;

import dev.mesut.currencyconverter.model.*;
import dev.mesut.currencyconverter.model.entity.ConversionEntity;
import dev.mesut.currencyconverter.repository.ConversionRepository;
import dev.mesut.currencyconverter.service.ConversionService;
import dev.mesut.currencyconverter.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ConversionServiceImpl implements ConversionService {

    private final ExchangeRateService exchangeRateService;
    private final ConversionRepository conversionRepository;

    @Override
    public ConversionResponse convert(ConversionRequest conversionRequest) {

        double rate = exchangeRateService.getRate(RateRequest.builder()
                .source(conversionRequest.getSource())
                .target(conversionRequest.getTarget())
                .build()).getRate();

        ConversionEntity conversionEntity = ConversionEntity.builder()
                .source(conversionRequest.getSource())
                .target(conversionRequest.getTarget())
                .rate(rate)
                .sourceAmount(conversionRequest.getAmount())
                .targetAmount(conversionRequest.getAmount() * rate)
                .transactionId(UUID.randomUUID().toString())
                .timeStamp(LocalDateTime.now())
                .build();


        ConversionEntity convertedEntity = conversionRepository.save(conversionEntity);

        ConversionResponse conversionResponse = ConversionResponse.builder()
                .source(convertedEntity.getSource())
                .target(conversionRequest.getTarget())
                .convertedAmount(convertedEntity.getTargetAmount())
                .transactionId(convertedEntity.getTransactionId())
                .timeStamp(convertedEntity.getTimeStamp())
                .build();

        return conversionResponse;

    }

    @Override
    public List<ConversionResponse> getConversionList(String transactionid, LocalDate date) {

        List<ConversionResponse> conversionResponseList = new ArrayList<>();

        if (transactionid != null) {
            ConversionEntity conversionEntity = conversionRepository.findByTransactionId(transactionid);

            conversionResponseList.add(ConversionResponse.builder()
                    .source(conversionEntity.getSource())
                    .target(conversionEntity.getTarget())
                    .convertedAmount(conversionEntity.getTargetAmount())
                    .timeStamp(conversionEntity.getTimeStamp())
                    .transactionId(conversionEntity.getTransactionId())
                    .build());
        } else {

            List<ConversionEntity> conversionEntityList = conversionRepository.findAllByTimeStampBetween(date.atStartOfDay(), date.plusDays(1).atStartOfDay().minusSeconds(1));

            conversionEntityList.stream().forEach(c -> conversionResponseList.add(ConversionResponse.builder()
                    .source(c.getSource())
                    .target(c.getTarget())
                    .convertedAmount(c.getTargetAmount())
                    .timeStamp(c.getTimeStamp())
                    .transactionId(c.getTransactionId())
                    .build()));
        }

        return conversionResponseList;
    }
}
