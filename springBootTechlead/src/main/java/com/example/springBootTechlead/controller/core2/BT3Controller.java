package com.example.springBootTechlead.controller.core2;

import com.example.springBootTechlead.model.dto.core.DTOCore2BT3;
import com.example.springBootTechlead.service.core2.BT3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/core2/bt3")
public class BT3Controller {
    @Autowired
    BT3 bt3Service;

    @PostMapping("/add")
    public Map<String, Integer> add(@RequestBody Map<String, Integer> newEntry) {
        for(Map.Entry<String, Integer> entry: newEntry.entrySet()){
            bt3Service.add(entry.getKey(), entry.getValue());
        }
        return bt3Service.getAll();
    }

    @GetMapping("/display")
    public Map<String, Integer> display(){
        return bt3Service.getAll();
    }

    @PostMapping("/get")
    public int get(@RequestBody String name){
        return bt3Service.getAge(name);
    }

    @PostMapping("/remove")
    public boolean remove(@RequestBody String name){
        return bt3Service.remove(name);
    }

    @PostMapping("/find")
    public boolean find(@RequestBody DTOCore2BT3 request){
        return bt3Service.find(request.getName(), request.getAge());
    }

}
