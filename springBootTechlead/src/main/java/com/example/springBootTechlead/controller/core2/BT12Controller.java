package com.example.springBootTechlead.controller.core2;

import com.example.springBootTechlead.service.core2.BT12;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/core2/bt12")
public class BT12Controller {

    @Autowired
    BT12 bt12Service;

    @PostMapping("/add")
    public void add(@RequestBody BT12.Product product){
        bt12Service.add(product);
    }

    @GetMapping("/sortByName")
    public List<BT12.Product> sortByName(){
        return bt12Service.sortByName();
    }

    @GetMapping("/sortByPrice")
    public List<BT12.Product> sortByPrice(){
        return bt12Service.sortByPrice();
    }

    @GetMapping("/sortByManufacture")
    public List<BT12.Product> sortByManufacture(){
        return bt12Service.sortByDateOfManufacture();
    }

    @GetMapping("/sortByPriceAndManufacture")
    public List<BT12.Product> sortByPriceAndManufacture(){
        return bt12Service.sortByPriceAndDateOfManufacture();
    }

}
