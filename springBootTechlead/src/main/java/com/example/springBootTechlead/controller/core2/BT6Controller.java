package com.example.springBootTechlead.controller.core2;

import com.example.springBootTechlead.service.core2.BT6;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/core2/bt6")
public class BT6Controller {
    @Autowired
    BT6 bt6Service;

    @PostMapping("")
    public int cal(List<Integer> prices){
        return bt6Service.maxProfit(prices);
    }
}
