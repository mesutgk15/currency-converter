package dev.mesut.currencyconverter.model.enums;


import dev.mesut.currencyconverter.exception.CurrencyNotSupportedException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class EnumsCurrency {

    public enum Currencies {
        TRY,
        USD,
        EUR,
        GBP
    }

    public static boolean isCurrencySupported(String currency) {
        Set<Currencies> currenciesSet = new HashSet<>(Arrays.asList(Currencies.values()));

        Set<String> currencies = currenciesSet.stream().map(c -> c.toString()).collect(Collectors.toSet());

//        if (!currencies.contains(currency.toUpperCase())) {
//            System.out.printf("Currency not supported: %s\n", currency);
//            return false;
//        } else
//            return true;
//    }

//        if (currencies.contains(currency.toUpperCase()))
//            return true;
//        else {
//            throw new CurrencyNotSupportedException(String.format("Currency (%s) not supported", currency));
//        }

        if (!currencies.contains(currency.toUpperCase())) {
            throw new CurrencyNotSupportedException(String.format("Currency (%s) not supported", currency));
        }

        return true;

    }
}
