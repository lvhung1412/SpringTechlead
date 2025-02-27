package com.example.springBootTechlead.controller.core2;

import com.example.springBootTechlead.service.core2.BT1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/core2/bt1")
public class BT1Controller {

    @Autowired
    BT1 bt1service;

    @PostMapping("/add")
    public void add(@RequestBody Integer num){
        bt1service.add(num);
    }

    @PostMapping("/addList")
    public void addList(@RequestBody ArrayList<Integer> numbers){
        for(Integer num: numbers){
            bt1service.add(num);
        }
    }

    @GetMapping("/display")
    public ArrayList<Integer> display(){
        return bt1service.getAll();
    }

    @GetMapping("/sum")
    public int sum(){
        return bt1service.sum();
    }

    @GetMapping("/max")
    public int max(){
        return bt1service.max();
    }

    @GetMapping("/min")
    public int min(){
        return bt1service.min();
    }

    @PostMapping("/remove")
    public ArrayList<Integer> remove(@RequestBody Integer num){
        bt1service.remove(num);
        return bt1service.getAll();
    }

    @PostMapping("/check")
    public String check(@RequestBody Integer num ){
        return bt1service.check(num);
    }

    @GetMapping("/sortIncrease")
    public ArrayList<Integer> sortIncrease(){
        return bt1service.sortIncrease();
    }

    @GetMapping("/sortDecrease")
    public ArrayList<Integer> sortDecrease(){
        return bt1service.sortDecrease();
    }

}
