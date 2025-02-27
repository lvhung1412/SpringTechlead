package com.example.springBootTechlead.service.core1;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class Level4Service {
    // 1
    public int getNumberOfStepsToSort(double[] arr){
        int n = arr.length;
        int count = 0;
        for(int i = 0; i< n-1; i++){
            for(int j = 0; j< n-i-1; j++){
                if(arr[j] > arr[j+1]){
                    double temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    count++;
                }
            }
        }
        return count;
    }

    // 2
    public static int countSubsetsWithSum(int[] arr, int target) {
        List<List<Integer>> validSubsets = new ArrayList<>();
        findSubsets(arr, target, 0, new ArrayList<>(), validSubsets);
        return validSubsets.size();
    }
    private static void findSubsets(int[] arr, int target, int index, List<Integer> currentSubset, List<List<Integer>> validSubsets) {
        // Tính tổng của tập con hiện tại
        int sum = currentSubset.stream().mapToInt(Integer::intValue).sum();

        // Nếu tổng bằng target và có ít nhất 2 phần tử thì lưu lại
        if (sum == target && currentSubset.size() > 1) {
            validSubsets.add(new ArrayList<>(currentSubset));
        }

        // Nếu tổng đã vượt target thì không xét tiếp
        if (sum >= target) return;

        // Duyệt qua từng phần tử tiếp theo
        for (int i = index; i < arr.length; i++) {
            currentSubset.add(arr[i]);
            findSubsets(arr, target, i + 1, currentSubset, validSubsets);
            currentSubset.remove(currentSubset.size() - 1); // Loại bỏ phần tử cuối để thử tổ hợp khác
        }
    }
    public int getSubsetWithSum(int[] arr, int target){
        return countSubsetsWithSum(arr, target);
    }

    // 3
    public static String maxCommStrB3(String s1, String s2){
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
    public int getMaxConmmStrInArray(String[] arr){
        int n = arr.length;
        String s = arr[0];
        String result = "";
        for(int i = 0; i< n ;i++){
            result = maxCommStrB3(s, arr[i]);
            s = result;
        }
        return result.length();
    }

    // 6
    public int getMultiplyOfThree(int[] arr){
        int n = arr.length;
        Arrays.sort(arr);
        return (Math.max(arr[n - 1] * arr[n - 2] * arr[n - 3],
                arr[0] * arr[1] * arr[n - 1]));
    }

    // 7
    public static int getNumberCharacter(String s){
        HashSet<Character> setCharacters = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (c != ' ') {
                setCharacters.add(c);
            }
        }
        return setCharacters.size();
    }
    public String[] getSortedArrayByNumWords(String[] arrStrings){
        Arrays.sort(arrStrings, (s1, s2) -> {
            int count1 = getNumberCharacter(s1);
            int count2 = getNumberCharacter(s2);

            if (count1 != count2) {
                return Integer.compare(count2, count1);
            }
            return s1.compareTo(s2);
        });
        return arrStrings;
    }

    // 9
    public int getLongestIncreasingSubOfNumbers(int[] arr){
        int count = 1;
        int maxCount = 1;
        for(int  i =1; i< arr.length; i++){
            if(arr[i] - arr[i-1] == 1){
                count++;
            } else{
                maxCount = Math.max(maxCount, count);
                count = 1;
            }
        }
        return Math.max(maxCount, count);
    }

    // 10
    public static int getOverlap(String s1, String s2, int k) {
        int maxOverlap = 0;
        int len1 = s1.length();
        int len2 = s2.length();

        for (int i = 0; i <= len1 - k; i++) {
            for (int j = 0; j <= len2 - k; j++) {
                int count = 0;
                while (i + count < len1 && j + count < len2 && s1.charAt(i + count) == s2.charAt(j + count)) {
                    count++;
                    if (count >= k) {
                        maxOverlap = Math.max(maxOverlap, count);
                    }
                }
            }
        }
        return maxOverlap;
    }
    public static String[] findLargestOverlapPair(List<String> strings, int k) {
        int maxOverlap = 0;
        String str1 = "", str2 = "";

        // duyệt từng cặp chuỗi trong danh sách
        for (int i = 0; i < strings.size(); i++) {
            for (int j = i + 1; j < strings.size(); j++) {
                int overlap = getOverlap(strings.get(i), strings.get(j), k);
                if (overlap > maxOverlap) {
                    maxOverlap = overlap;
                    str1 = strings.get(i);
                    str2 = strings.get(j);
                }
            }
        }
        return new String[]{str1, str2};
    }
    public String findLargestOverlapWithK(List<String> strings, int k){
        String[] result = findLargestOverlapPair(strings, k);

        if (!result[0].isEmpty() && !result[1].isEmpty()) {
            return ("Hai chuỗi có phần giao nhau dài nhất: " + result[0] + " và " + result[1]);
        } else {
            return ("Không có cặp chuỗi nào có phần giao nhau dài ít nhất " + k + " ký tự.");
        }
    }
}
