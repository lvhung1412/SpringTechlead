package com.example.springBootTechlead.service.core2;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class BT1 {
    ArrayList<Integer> numbers = new ArrayList<>();

    public void add(Integer num){
        numbers.add(num);
    }

    public ArrayList<Integer> getAll(){
        return numbers;
    }

    public Integer sum(){
        int sum = 0;
        for(Integer num: numbers){
            sum+=num;
        }
        return sum;
    }

    public Integer max(){
        return Collections.max(numbers);
    }

    public Integer min(){
        return Collections.min(numbers);
    }

    public boolean remove(Integer numDel){
        return numbers.remove(numDel);
    }

    public String check(Integer numCheck){
        return ("Phần tử "+ numCheck+" tồn tại trong ArrayList: " +numbers.contains(numCheck));
    }

    public ArrayList<Integer> sortIncrease(){
        Collections.sort(numbers);
        return numbers;
    }

    public ArrayList<Integer> sortDecrease(){
        Collections.sort(numbers, Comparator.reverseOrder());
        return numbers;
    }
}
