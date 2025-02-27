package com.example.springBootTechlead.controller.core2;

import com.example.springBootTechlead.service.core2.BT9;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/core2/bt9")
public class BT9Controller {

    @Autowired
    BT9 bt9Service;

    @PostMapping("")
    public List<Map.Entry<String, Integer>> count(String text){
        return bt9Service.count(text);
    }
}
