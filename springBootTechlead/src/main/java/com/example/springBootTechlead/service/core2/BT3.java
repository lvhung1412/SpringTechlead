package com.example.springBootTechlead.service.core2;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BT3 {
    Map<String, Integer> hashMap = new HashMap<>();

    public void add(String key, Integer value){
        hashMap.put(key, value);
    }

    public Map<String, Integer> getAll(){
        return hashMap;
    }

    public int getAge(String name){
        return hashMap.getOrDefault(name, -1);
    }

    public boolean remove(String nameDel) {
        return hashMap.remove(nameDel) != null;
    }

    public boolean find(String name, Integer age){
        Integer numCheck = hashMap.getOrDefault(name, -1);
        return numCheck == age;
    }

}
