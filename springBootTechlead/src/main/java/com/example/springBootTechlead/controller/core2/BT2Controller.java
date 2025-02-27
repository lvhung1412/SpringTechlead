package com.example.springBootTechlead.controller.core2;

import com.example.springBootTechlead.service.core2.BT2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;

@RestController
@RequestMapping("/core2/bt2")
public class BT2Controller {

    @Autowired
    BT2 bt2Service;
    @PostMapping("/add")
    public void add(@RequestBody String num){
        bt2Service.add(num);
    }

    @PostMapping("/addList")
    public void addList(@RequestBody ArrayList<String> numbers){
        for(String num: numbers){
            bt2Service.add(num);
        }
    }

    @GetMapping("/display")
    public HashSet<String> display(){
        return bt2Service.getAll();
    }

    @PostMapping("/remove")
    public HashSet<String> remove(@RequestBody String name){
        bt2Service.remove(name);
        return bt2Service.getAll();
    }

    @PostMapping("/check")
    public boolean check(@RequestBody String name ){
        return bt2Service.check(name);
    }

    @GetMapping("/count")
    public int count(){
        return bt2Service.count();
    }


}
