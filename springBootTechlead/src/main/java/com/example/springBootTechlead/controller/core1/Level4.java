package com.example.springBootTechlead.controller.core1;

import com.example.springBootTechlead.model.dto.DTOLevel4Ex10;
import com.example.springBootTechlead.model.dto.DTOLevel4Ex2;
import com.example.springBootTechlead.service.core1.Level4Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/core1/level4")
public class Level4 {
    @Autowired
    private Level4Service level4Service;

    @PostMapping("/ex1")
    public int ex1(@RequestBody double[] arr){
        return level4Service.getNumberOfStepsToSort(arr);
    }

    @PostMapping("/ex2")
    public int ex2(@RequestBody DTOLevel4Ex2 request){
        return level4Service.getSubsetWithSum(request.getArr(), request.getTarget());
    }

    @PostMapping("/ex3")
    public int ex3(@RequestBody String[] arr){
        return level4Service.getMaxConmmStrInArray(arr);
    }

    @PostMapping("/ex6")
    public int ex6(@RequestBody int[] arr){
        return level4Service.getMultiplyOfThree(arr);
    }

    @PostMapping("/ex7")
    public String[] ex7(@RequestBody String[] arr){
        return level4Service.getSortedArrayByNumWords(arr);
    }

    @PostMapping("/ex9")
    public int ex9(@RequestBody int[] arr){
        return level4Service.getLongestIncreasingSubOfNumbers(arr);
    }

    @PostMapping("/ex10")
    public String ex10(@RequestBody DTOLevel4Ex10 request){
        return level4Service.findLargestOverlapWithK(request.getStrings(), request.getK());
    }

}
