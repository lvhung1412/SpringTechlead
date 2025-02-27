package com.example.springBootTechlead.model.dto.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class DTOLevel5Ex8 {
    List<Map<String,Integer>> list;
    int id;
    int numberOfOrder;
}
