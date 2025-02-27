package com.example.springBootTechlead.model.dto.mysql.Level2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class DTOL2E1 {
    Short customerId;
    String firstName;
    String lastName;
    BigDecimal totalRevenue;
}
