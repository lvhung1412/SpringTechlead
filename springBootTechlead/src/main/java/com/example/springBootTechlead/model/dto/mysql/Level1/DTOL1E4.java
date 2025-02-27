package com.example.springBootTechlead.model.dto.mysql.Level1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class DTOL1E4 {
    Byte categoryId;
    String name;
    BigDecimal averageRentalDuration;
}
