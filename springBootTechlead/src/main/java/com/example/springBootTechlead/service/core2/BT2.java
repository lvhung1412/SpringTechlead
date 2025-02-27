package com.example.springBootTechlead.service.core2;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class BT2 {
    HashSet<String> nameOfNation = new HashSet<>();

    public void add(String name){
        nameOfNation.add(name);
    }

    public HashSet<String> getAll(){
        return nameOfNation;
    }

    public boolean check(String nameCheck){
        return nameOfNation.contains(nameCheck);
    }

    public boolean remove(String nameDel){
        return nameOfNation.remove(nameDel);
    }

    public int count(){
        return nameOfNation.size();
    }

}
