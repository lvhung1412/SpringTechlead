package com.example.springBootTechlead.controller.core1;

import com.example.springBootTechlead.model.dto.DTOLevel2Ex3;
import com.example.springBootTechlead.service.core1.Level2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/core1/level2")
public class Level2 {
    @Autowired
    private Level2Service level2Service;

    @PostMapping("/ex1")
    public double ex1(@RequestBody double[] arr){
        return level2Service.getSecondBiggestNumber(arr);
    }

    @PostMapping("/ex2")
    public String ex2(@RequestBody String[] arr){
        return  level2Service.getLongestWord(arr);
    }

    @PostMapping("/ex3")
    public String ex3(@RequestBody DTOLevel2Ex3 request){
        return level2Service.getMaxCommStr(request.getS1(), request.getS2());
    }

    @PostMapping("/ex4")
    public Integer ex4(@RequestBody Integer[] arr){
        return level2Service.getSumDivideThreeAndFive(arr);
    }

    @PostMapping("/ex5")
    public Integer ex5(@RequestBody Integer[] arr){
        return level2Service.getBiggestSubArray(arr);
    }
}
