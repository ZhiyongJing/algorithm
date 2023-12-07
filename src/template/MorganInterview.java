package template;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;
public class MorganInterview {
    public static List<String> getTopTen(List<String> words){
        List<String> result = new ArrayList<>();
        if(words == null || words.size() == 0){
            return result;
        }
        Map<String, Integer> wordCounts = new HashMap<>();
        for(String s: words){
            int freq = wordCounts.getOrDefault(s, 0);
            wordCounts.put(s, freq + 1);
        }
        PriorityQueue<String> minHeap = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(wordCounts.get(o1) == wordCounts.get(o2)){
                    // sort wwith the string value
                    return o2.compareTo(o1);
                }
                return wordCounts.get(o1) - wordCounts.get(o2);
                /**
                 * a 1
                 * b 2
                 * c 3
                 * d 4
                 */
            }
        });
        for(Map.Entry<String, Integer> entry: wordCounts.entrySet()){
            minHeap.add(entry.getKey());
            if(minHeap.size() > 10){
                minHeap.poll();
            }
        }

        String[] arr = new String[minHeap.size()];
        int index = minHeap.size() - 1;
        // minHeap.toArray()
        while(minHeap.peek() != null){
            arr[index] = minHeap.poll();
            index--;
        }
        result = Arrays.asList(arr);
        return result;


    }

    public static void main(String[] args) {
        // "hello word hello hellow word java ..."
        String[] test = {"hello", "word", "hello", "word", "scala", "Java"};
        System.out.println(getTopTen(Arrays.asList(test)));
    }
}
