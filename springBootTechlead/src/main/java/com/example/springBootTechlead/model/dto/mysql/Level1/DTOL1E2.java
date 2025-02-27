package com.example.springBootTechlead.model.dto.mysql.Level1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class DTOL1E2 {
    String title;
    BigDecimal rental_rate;
    BigDecimal replacement_cost;
}
