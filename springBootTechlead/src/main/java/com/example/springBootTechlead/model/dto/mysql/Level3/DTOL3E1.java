package com.example.springBootTechlead.model.dto.mysql.Level3;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class DTOL3E1 {
    String firstName;
    String lastName;
    String categoryName;
    BigDecimal avg;
}
