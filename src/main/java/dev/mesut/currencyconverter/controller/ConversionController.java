package dev.mesut.currencyconverter.controller;

import dev.mesut.currencyconverter.model.ConversionRequest;
import dev.mesut.currencyconverter.model.ConversionResponse;
import dev.mesut.currencyconverter.service.ConversionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/conversions")
public class ConversionController {

    private final ConversionService conversionService;

    @PostMapping
    public ConversionResponse convert(@RequestBody ConversionRequest conversionRequest) {
        return conversionService.convert(conversionRequest);
    }

    @GetMapping()
    public List<ConversionResponse> getConversionList (@RequestParam(required = false) String transactionid, @RequestParam(required = false) LocalDate date) {
        return conversionService.getConversionList(transactionid, date);
    }

}
