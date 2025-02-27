package com.example.springBootTechlead.service.core1;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class Level3Service {
    // 1
    public double getSmallestNum(double[] arr){
        double min = Integer.MAX_VALUE;
        double secondMin = Integer.MAX_VALUE;
        for (double num : arr) {
            if (num < min) {
                secondMin = min;
                min = num;
            } else if (num < secondMin && num > min) {
                secondMin = num;
            }
        }
        return secondMin;
    }

    // 2
    public double getBiggestDiff(double[] arr){
        double min = Integer.MAX_VALUE;
        double max = Integer.MIN_VALUE;
        for (double num : arr) {
            if (num < min) {
                min = num;
            }
            if (num > max) {
                max = num;
            }
        }
        return max-min;
    }

    // 3
    public int getLengthOfSubIncreaseString(double[] arr){
        int n = arr.length;
        int[] dp = new int[n];
        int maxLIS = 1;

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }

        for (int i = 1; i < n; i++) {
            // xét tất cả các phần tử phía trước arr[i]: 0 -> i-1
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
            }
            maxLIS = Math.max(maxLIS, dp[i]);
        }
        return maxLIS;
    }

    // 4
    public static int maxOverlap(String s, String t){
        List<Character> s1 = s.chars().mapToObj(e -> (char) e).collect(Collectors.toList());
        List<Character> t1 = t.chars().mapToObj(e -> (char) e).collect(Collectors.toList());
        int count = 0;
        for(int i =0 ; i < s1.size(); i++){
            for(int j = 0; j < t1.size(); j++){
                //System.out.println(s1.get(i) + " " + t1.get(j));
                if(s1.get(i).equals(t1.get(j)) && s1.get(i) != ' '){
                    s1.set(i, ' ');
                    t1.set(j, ' ');
                    count++;
                }
            }
        }
        return count;
    }
    public String[] getTwoBestStringOverlap(String[] arr){
        int max = -1;
        int index1 = 0, index2 = 0;
        int n = arr.length;
        for(int i = 0; i < n; i++){
            for(int j = i+1; j < n; j++){
                if(max < maxOverlap(arr[i], arr[j])){
                    max = maxOverlap(arr[i], arr[j]);
                    index1 = i;
                    index2 = j;
                }
            }
        }
        String[] result = new String[2];
        result[0] = arr[index1];
        result[1] = arr[index2];
        return result;
    }

    // 5
    public int getSmallestIntegerNotCreateByArray(Integer[] arr){
        Arrays.sort(arr);

        int smallest = 1;  // số nguyên dương nhỏ nhất
        // duyệt các phần tử trong mảng
        for(int i : arr){
            // nếu phần tử hiện tại nhỏ hơn hoặc bằng số nguyên dương nhỏ nhất
            if(i <= smallest){
                smallest += i;
            }else{
                break;
            }
        }
        return smallest;
    }

    // 7
    public double getMedianOfTwoArray(Integer[] arr1, Integer[] arr2){
        int n = arr1.length;
        int m = arr2.length;
        int[] arr3 = new int[n + m];
        for(int i = 0; i < n; i++) {
            arr3[i] = arr1[i];
        }
        for(int i =n; i < n + m; i++) {
            arr3[i] = arr2[i - n];
        }
        Arrays.sort(arr3);
        System.out.println("Trung vị của mảng sau là: ");
        n = n + m;
        if(n % 2 == 0) {
            return((double)(arr3[n/2] + arr3[n/2 - 1]) / 2);
        }
        return (arr3[n/2]);
    }

    // 10
    public static int getNumberOfDistinctCharacter(String s){
        Set<Character> set = new HashSet<>();
        for(int i =0; i< s.length(); i++){
            set.add(s.charAt(i));
        }
        return set.size();
    }

    public String getSortedString(String[] s){
        Arrays.sort(s, (str1, str2) -> {
            int distinct1 = getNumberOfDistinctCharacter(str1);
            int distinct2 = getNumberOfDistinctCharacter(str2);
            if (distinct1 != distinct2) {
                return Integer.compare(distinct1, distinct2);
            }
            if (str1.length() != str2.length()){
                return Integer.compare(str2.length(), str1.length());
            }
            return str1.compareTo(str2);
        });
        return Arrays.toString(s);
    }

}
