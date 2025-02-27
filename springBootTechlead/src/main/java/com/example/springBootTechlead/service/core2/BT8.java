package com.example.springBootTechlead.service.core2;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BT8 {
    Map<String, String> listProduct = new HashMap<>();

    public void add(String id, String infor){
        listProduct.put(id, infor);
    }

    public Map<String, String> getAll(){
        return listProduct;
    }

    public String find(String id) {
        return listProduct.getOrDefault(id, null);
    }

    public boolean remove(String id){
        return listProduct.remove(id) != null;
    }

    public boolean update(String id, String infor) {
        return listProduct.replace(id, infor) != null;
    }
}
