package com.example.springBootTechlead.controller.core2;

import com.example.springBootTechlead.model.dto.DTOCore2BT5;
import com.example.springBootTechlead.service.core2.BT5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/core2/bt5")
public class BT5Controller {
    @Autowired
    BT5 bt5Service;

    @PostMapping("")
    public int[] cal(@RequestBody DTOCore2BT5 request){
        return bt5Service.twoSum(request.getNums(), request.getTarget());
    }
}
