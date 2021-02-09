package br.com.cast.avaliacao.util;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BigDecimalConvert {
    // 1.000,00 -> 1000.00
    public BigDecimal converter (String value){

        if (value == null){
            return null;
        }

        value = value.replace(".", "").replace(",", ".");
        return new BigDecimal(value);
    }
}
