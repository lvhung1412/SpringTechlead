package com.example.springBootTechlead.service.core1;

import org.springframework.stereotype.Service;

@Service
public class Level2Service {

    // 1
    public double getSecondBiggestNumber(double[] arr){
        double max = Integer.MIN_VALUE;
        double secondMax = Integer.MIN_VALUE;
        for (double num : arr) {
            if (num > max) {
                secondMax = max;
                max = num;
            } else if (num > secondMax && num < max) {
                secondMax = num;
            }
        }
        return secondMax;
    }

    // 2
    public String getLongestWord(String[] arr){
        int n = arr.length;
        String longestWord = "";
        String[] word;
        for (int i = 0; i < n; i++) {
            word = arr[i].split(" ");
            for (int j = 0; j < word.length; j++) {
                if (word[j].length() > longestWord.length()) {
                    longestWord = word[j];
                }
            }
        }
        return longestWord;
    }

    // 3
    public String getMaxCommStr(String s1, String s2){
        String longestSubstring  = "";
        int m = s1.length();
        int n = s2.length();
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                String temp = "";
                int x = i;
                int y = j;

                while (x < m && y < n && s1.charAt(x) == s2.charAt(y)) {
                    temp+=s1.charAt(x);
                    x++;
                    y++;
                }

                if (temp.length() > longestSubstring.length()) {
                    longestSubstring = temp.toString();
                }
            }
        }
        return longestSubstring;
    }

    // 4
    public Integer getSumDivideThreeAndFive(Integer[] arr){
        Integer sum = 0;
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            if (arr[i] % 3 == 0 && arr[i] % 5 == 0){
                sum += arr[i];
            }
        }
        return sum;
    }

    // 5
    public Integer getBiggestSubArray(Integer[] arr){
        int max_so_far = arr[0];
        int curr_max = arr[0];
        for (Integer integer : arr) {
            curr_max = Math.max(integer, curr_max + integer);
            max_so_far = Math.max(max_so_far, curr_max);
        }
        return max_so_far;
    }

}
