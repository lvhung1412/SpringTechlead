package com.example.springBootTechlead.model.entity.enumData;

public enum FilmRating {
    G, PG, PG_13, R, NC_17;

    public static FilmRating fromDbValue(String dbValue) {
        try {
            return FilmRating.valueOf(dbValue.replace("-", "_"));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Giá trị không hợp lệ: " + dbValue);
        }
    }
}

