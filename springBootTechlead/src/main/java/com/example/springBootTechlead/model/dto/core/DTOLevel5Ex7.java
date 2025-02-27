package com.example.springBootTechlead.model.dto.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class DTOLevel5Ex7 {
    List<String> keys;
    List<Map<String, Integer>> collections;
}
