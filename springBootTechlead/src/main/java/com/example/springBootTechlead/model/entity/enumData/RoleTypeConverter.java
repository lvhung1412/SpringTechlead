package com.example.springBootTechlead.model.entity.enumData;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RoleTypeConverter implements AttributeConverter<RoleType, String> {

    @Override
    public String convertToDatabaseColumn(RoleType attribute) {
        return (attribute == null) ? null : attribute.name();
    }

    @Override
    public RoleType convertToEntityAttribute(String dbValue) {
        return (dbValue == null || dbValue.trim().isEmpty()) ? null : RoleType.fromDbValue(dbValue);
    }
}

