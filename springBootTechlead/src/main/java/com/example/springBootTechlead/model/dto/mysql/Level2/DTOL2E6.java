package com.example.springBootTechlead.model.dto.mysql.Level2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class DTOL2E6 {
    Short actorId;
    String firstName;
    String lastName;
    BigDecimal totalRevenue;
}
