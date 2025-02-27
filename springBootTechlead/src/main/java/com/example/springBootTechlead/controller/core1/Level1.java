package com.example.springBootTechlead.controller.core1;

import com.example.springBootTechlead.service.core1.Level1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("core1/level1")
@RestController
public class Level1 {
    @Autowired
    private Level1Service level1Service;

    @GetMapping("/ex1")
    public double ex1(@RequestParam double a, @RequestParam double b){
        return a+b;
    }

    @GetMapping("/ex2")
    public double ex2(@RequestParam String s){
        return s.length();
    }

    @GetMapping("/ex3")
    public double ex3(@RequestParam double a){
        return Math.sqrt(a);
    }

    @PostMapping("/ex4")
    public double ex4(@RequestBody double[] arr){
        return level1Service.getBiggestNumber(arr);
    }

    @PostMapping("/ex5")
    public String ex5(@RequestBody String[] arr){
        return level1Service.getShortestString(arr);
    }

    @PostMapping("/ex6")
    public double[] ex6(@RequestBody double[] arr){
        return level1Service.sortArrayNum(arr);
    }

    @PostMapping("/ex7")
    public String[] ex7(@RequestBody String[] arr){
        return level1Service.sortArrayString(arr);
    }

    @PostMapping("/ex8")
    public double ex8(@RequestBody double[] arr){
        return level1Service.getMedian(arr);
    }

    @PostMapping("/ex9")
    public int ex9(@RequestBody String arr){
        return level1Service.getNumberOfCharacter(arr);
    }

    @PostMapping("/ex10")
    public int ex10(@RequestBody String[] arr){
        return level1Service.getNumberOfStringContainA(arr);
    }
}
