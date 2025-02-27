package com.example.springBootTechlead.model.entity.enumData;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class FilmRatingConverter implements AttributeConverter<FilmRating, String> {

    @Override
    public String convertToDatabaseColumn(FilmRating attribute) {
        return (attribute == null) ? null : attribute.name().replace("_", "-");
    }

    @Override
    public FilmRating convertToEntityAttribute(String dbValue) {
        return (dbValue == null || dbValue.trim().isEmpty()) ? null : FilmRating.fromDbValue(dbValue);
    }
}
