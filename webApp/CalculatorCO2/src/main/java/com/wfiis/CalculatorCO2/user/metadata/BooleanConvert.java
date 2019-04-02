package com.wfiis.CalculatorCO2.user.metadata;

import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;

@Component
public class BooleanConvert implements AttributeConverter<Boolean, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Boolean aBoolean) {
        return aBoolean == Boolean.FALSE ? 0 : 1;
    }

    @Override
    public Boolean convertToEntityAttribute(Integer integer) {
        return integer == 0 ? Boolean.FALSE : Boolean.TRUE;
    }
}