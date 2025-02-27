package com.example.springBootTechlead.service.core1;

import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class Level1Service {
    public double getBiggestNumber(double[] arr){
        int n = arr.length;
        double num = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++){
            if(arr[i] > num){
                num = arr[i];
            }
        }
        return num;
    }

    public String getShortestString(String[] arr){
        int n = arr.length;
        String minString = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i].length() < minString.length()) {
                minString = arr[i];
            }
        }
        return minString;
    }

    public double[] sortArrayNum(double[] arr){
        double[] arrCopy = arr.clone();
        Arrays.sort(arrCopy);
        return arrCopy;
    }

    public String[] sortArrayString(String[] arr){
        String[] arrCopy = Arrays.copyOf(arr, arr.length);
        Arrays.sort(arrCopy);
        return  arrCopy;
    }

    public double getMedian(double[] arr){
        double[] arrCopy = arr.clone();
        Arrays.sort(arrCopy);
        int n = arrCopy.length;
        if(n % 2 == 0){
            return ((arrCopy[n/2] + arrCopy[n/2 - 1]) / 2);
        }
        return arrCopy[n/2];
    }

    public int getNumberOfCharacter(String str){
        int word_num = 0;
        if(str.charAt(0) != ' ')
        {
            word_num += 1;
        }
        for(int i = 1; i < str.length(); i++){
            if(str.charAt(i) == ' '){
                if(str.charAt(i+1) != ' '){
                    word_num += 1;
                }
            }
        }
        return word_num;
    }

    public int getNumberOfStringContainA(String[] str){
        int n = str.length;
        int count = 0;
        for(int i = 0; i < n; i++){
            if(str[i].contains("a")){
                count++;
            }
        }
        return count;
    }

}
