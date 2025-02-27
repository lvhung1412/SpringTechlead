package com.example.springBootTechlead.controller.core1;

import com.example.springBootTechlead.model.dto.core.DTOLevel5Ex2;
import com.example.springBootTechlead.model.dto.core.DTOLevel5Ex5;
import com.example.springBootTechlead.model.dto.core.DTOLevel5Ex7;
import com.example.springBootTechlead.model.dto.core.DTOLevel5Ex8;
import com.example.springBootTechlead.service.core1.Level5Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/core1/level5")
public class Level5 {

    @Autowired
    private Level5Service level5Service;

    @PostMapping("/ex1")
    public Object[] ex1(@RequestBody Object[] arr){
        return level5Service.reverses(arr);
    }

    @PostMapping("/ex2")
    public Object[][] ex2(@RequestBody DTOLevel5Ex2 request){
        return level5Service.chunk(request.getArr(), request.getSize());
    }

    @PostMapping("/ex3")
    public Object[] ex3(@RequestBody Object[] arr){
        return level5Service.uniq(arr);
    }

    @PostMapping("/ex4")
    public List<Map<String, Integer>> ex4(@RequestBody List<Map<String, Integer>> list){
        return level5Service.uniqObjects(list);
    }

    @PostMapping("/ex5")
    public Map<Integer, List<Map<String, Integer>>> ex5(@RequestBody DTOLevel5Ex5 request){
        return level5Service.groupBy(request.getList(), request.getKey());
    }

    @PostMapping("/ex6")
    public String ex6(@RequestBody String s){
        return level5Service.trimAll(s);
    }

    @PostMapping("/ex7")
    public List<Map<String, Integer>> ex7(@RequestBody DTOLevel5Ex7 request){
        return level5Service.mapKey(request.getKeys(), request.getCollections());
    }

    @PostMapping("/ex8")
    public List<Map<String, Integer>> ex8 (@RequestBody DTOLevel5Ex8 request){
        return level5Service.switchOrder(request.getList(), request.getId(), request.getNumberOfOrder());
    }

    @PostMapping("/ex9")
    public Map<String, Integer> ex9(@RequestBody List<Map<String, Object>> list){
        return level5Service.sumAll(list);
    }

}
