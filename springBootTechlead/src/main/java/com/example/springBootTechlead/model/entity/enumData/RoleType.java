package com.example.springBootTechlead.model.entity.enumData;

public enum RoleType {
    USER, ADMIN;

    public static RoleType fromDbValue(String dbValue) {
        try {
            return RoleType.valueOf(dbValue.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Giá trị không hợp lệ: " + dbValue);
        }
    }
}
