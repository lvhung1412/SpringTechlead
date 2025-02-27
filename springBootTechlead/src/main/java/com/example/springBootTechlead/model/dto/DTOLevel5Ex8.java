package com.example.springBootTechlead.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class DTOLevel5Ex8 {
    List<Map<String,Integer>> list;
    int id;
    int numberOfOrder;
}
