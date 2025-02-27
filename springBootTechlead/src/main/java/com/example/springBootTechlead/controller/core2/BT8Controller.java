package com.example.springBootTechlead.controller.core2;

import com.example.springBootTechlead.model.dto.DTOCore2BT8;
import com.example.springBootTechlead.service.core2.BT8;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/core2/bt8")
public class BT8Controller {

    @Autowired
    BT8 bt8Service;

    @PostMapping("/add")
    public Map<String, String> add(@RequestBody Map<String, String> newEntry) {
        for(Map.Entry<String, String> entry: newEntry.entrySet()){
            bt8Service.add(entry.getKey(), entry.getValue());
        }
        return bt8Service.getAll();
    }

    @GetMapping("/display")
    public Map<String, String> display(){
        return bt8Service.getAll();
    }

    @PostMapping("/find")
    public String find(@RequestBody String id){
        return bt8Service.find(id);
    }

    @PostMapping("/remove")
    public boolean remove(@RequestBody String id){
        return bt8Service.remove(id);
    }

    @PostMapping("/update")
    public boolean update(@RequestBody DTOCore2BT8 request){
        return bt8Service.update(request.getId(), request.getInfor());
    }

}
