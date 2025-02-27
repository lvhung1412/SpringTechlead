package com.example.springBootTechlead.model.dto.mysql.Level3;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class DTOL3E5 {
    String filmTitle;
    String firstName;
    String lastName;
    Date rentalDay;
    Long rentalCount;
}
