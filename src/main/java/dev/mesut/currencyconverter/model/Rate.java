package dev.mesut.currencyconverter.model;

import dev.mesut.currencyconverter.model.enums.EnumsCurrency;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Rate {

    private EnumsCurrency.Currencies target;
    private double rate;

}
