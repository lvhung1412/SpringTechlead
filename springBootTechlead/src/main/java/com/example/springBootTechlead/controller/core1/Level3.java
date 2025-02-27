package com.example.springBootTechlead.controller.core1;

import com.example.springBootTechlead.model.dto.DTOLevel3Ex7;
import com.example.springBootTechlead.service.core1.Level3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/core1/level3")
public class Level3 {
    @Autowired
    private Level3Service level3Service;

    @PostMapping("/ex1")
    public double ex1(@RequestBody double[] arr){
        return level3Service.getSmallestNum(arr);
    }

    @PostMapping("/ex2")
    public double ex2(@RequestBody double[] arr){
        return level3Service.getBiggestDiff(arr);
    }

    @PostMapping("/ex3")
    public int ex3(@RequestBody double[] arr){
        return level3Service.getLengthOfSubIncreaseString(arr);
    }

    @PostMapping("/ex4")
    public String[] ex4(@RequestBody String[] arr){
        return level3Service.getTwoBestStringOverlap(arr);
    }

    @PostMapping("/ex5")
    public int ex5(@RequestBody Integer[] arr){
        return level3Service.getSmallestIntegerNotCreateByArray(arr);
    }

    @PostMapping("/ex7")
    public double ex7(@RequestBody DTOLevel3Ex7 request){
        return level3Service.getMedianOfTwoArray(request.getArr1(), request.getArr2());
    }

    @PostMapping("/ex10")
    public String ex10(@RequestBody String[] s){
        return level3Service.getSortedString(s);
    }
}
