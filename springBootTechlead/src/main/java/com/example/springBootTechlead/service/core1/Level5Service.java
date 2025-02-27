package com.example.springBootTechlead.service.core1;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

@Service
public class Level5Service {
    //5.1
    public Object[] reverses (Object[] arr){
        int n = arr.length;
        Object[] reversesArr = new Object[n];
        for(int i = (n - 1); i >= 0; i--){
            reversesArr[n - 1 -i] = arr[i];
        }
        return reversesArr;
    }

    //5.2
    public Object[][] chunk(Object[] arr, int size) {
        int numChunks = (int) Math.ceil((double) arr.length / size);
        Object[][] result = new String[numChunks][];

        for (int i = 0; i < numChunks; i++) {
            int start = i * size;
            int end = Math.min(start + size, arr.length);
            result[i] = Arrays.copyOfRange(arr, start, end);
        }

        return result;
    }

    //5.3
    public Object[] uniq(Object[] arr){
        int n = arr.length;
        Object[] uniqObjects = new Object[n];
        int size = 0;
        for(int i = 0; i< n ;i++){
            boolean isDuplicate = false;
            for(int j = 0; j< size ; j++){
                if (arr[i].equals(uniqObjects[j])) {
                    isDuplicate = true;
                    break;
                }
            }
            if (!isDuplicate) {
                uniqObjects[size++] = arr[i];
            }
        }
        return Arrays.copyOf(uniqObjects, size);
    }

    // uniq: Loại bỏ các phần tử trùng trong mảng
    // public static <T> List<T> uniq(List<T> list) {
    //     return new ArrayList<>(new LinkedHashSet<>(list));
    // }

    //5.4
    // uniq ArrayObject: Loại bỏ các phần tử trùng trong danh sách đối tượng
    public List<Map<String, Integer>> uniqObjects(List<Map<String, Integer>> list) {
        Set<Map<String, Integer>> seen = new LinkedHashSet<>(list);
        return new ArrayList<>(seen);
    }

    //5.5
    // groupBy: Nhóm các đối tượng trong danh sách theo một trường chỉ định
    public Map<Integer, List<Map<String, Integer>>> groupBy(List<Map<String, Integer>> list, String key) {
        return list.stream()
                .filter(map -> map.containsKey(key))
                .collect(Collectors.groupingBy(map -> map.get(key)));
    }

    //5.6
    public String trimAll(String s){
        return s.trim().replaceAll("\\s+", " ");
    }

    //5.7
    // mapKey: Sắp xếp danh sách đối tượng theo thứ tự khóa cho trước
    public List<Map<String, Integer>> mapKey(List<String> keys, List<Map<String, Integer>> collections) {
        return collections.stream().map(map -> {
            Map<String, Integer> sortedMap = new LinkedHashMap<>();
            for (String key : keys) {
                if (map.containsKey(key)) {
                    sortedMap.put(key, map.get(key));
                }
            }
            return sortedMap;
        }).collect(Collectors.toList());
    }

    //5.8
    public List<Map<String, Integer>> switchOrder(List<Map<String,Integer>> list, int id, int numberOfOrder){
        // sắp xếp theo order
        list.sort(Comparator.comparingInt(map -> map.get("order")));

        // tìm kiếm
        Map<String, Integer> target = list.stream()
                .filter(map -> map.get("id") == id)
                .findFirst()
                .orElse(null);

        // không có thì trả về list ban đầu
        if(target == null) return list;

        // xóa cái cũ và thêm cái mới
        list.remove(target);
        list.add(numberOfOrder, target);

        // cập nhật lại order
        for(int i = 0; i< list.size(); i++){
            list.get(i).put("order",i);
        }
        return list;
    }

    //5.9
    public Map<String, Integer> sumAll(List<Map<String, Object>> list){
        Map<String, Integer> map = new HashMap<>();
        for(Map<String, Object> item : list){
            for(Map.Entry<String, Object> entry: item.entrySet()){
                String key = entry.getKey();
                Object value = entry.getValue();

                int valueInteger = (value instanceof Number) ? ((Number) value).intValue():
                        (value instanceof String) ? Integer.parseInt(value.toString()) : 0;

                map.put(key, map.getOrDefault(key, 0) + valueInteger);
            }
        }
        return map;
    }

    // 5.10
    public void processTemplate(String fileInput, String fileOutput, Map<String,String> params) throws IOException{
        // đọc nội dung file template
        String content = new String (Files.readAllBytes(Paths.get(fileInput)));

        // thay thế
        for(Map.Entry<String, String> entry : params.entrySet()){
            String regex = "\\{\\{" + entry.getKey() +"\\}\\}";
            content = content.replaceAll(regex, Matcher.quoteReplacement(entry.getValue()));
        }

        //ghi file
        Files.write(Paths.get(fileOutput), content.getBytes());

    }

    public void readFile() {
        try {
            String inputFile = "src/resources/static/input1.txt";
            String outputFile = "src/resources/static/output1.txt";

            Map<String, String> params = new HashMap<>();
            params.put("name", "Jonny");

            processTemplate(inputFile, outputFile, params);
            System.out.println("File processed successfully: " + outputFile);


            String inputFileHtml = "src/resources/static/input2.html";
            String outputFileHtml = "src/resources/static/output2.html";

            Map<String, String> paramsHtml = new HashMap<>();
            paramsHtml.put("title", "Search of skill");
            paramsHtml.put("pageTitle", "Home page");
            paramsHtml.put("content", "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab, consectetur");

            processTemplate(inputFileHtml, outputFileHtml, paramsHtml);
            System.out.println("File processed successfully: " + outputFileHtml);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
