package com.schoolproject.management.entities;

import jakarta.persistence.AttributeConverter;
import org.springframework.core.convert.converter.Converter;

public class StudentMonitorConverter implements AttributeConverter<Boolean, String> {
    @Override
    public String convertToDatabaseColumn(Boolean aBoolean) {
        if (aBoolean == null) return null;
        return (aBoolean) ? "Yes" : "No";
    }

    @Override
    public Boolean convertToEntityAttribute(String s) {
        if (s == null) return null;
        return s.equals("Yes");
    }
}
