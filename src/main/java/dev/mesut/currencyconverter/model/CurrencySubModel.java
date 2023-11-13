package dev.mesut.currencyconverter.model;

import dev.mesut.currencyconverter.model.enums.EnumsCurrency;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CurrencySubModel {

    private EnumsCurrency.Currencies source;
    private List<Rate> rates = new ArrayList<>();
}
