package interview.company.oacarhealth;
import java.util.*;

public class TopKFrequence {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> wordMap = new HashMap<>();
        for(String s: words){
            int freq = wordMap.getOrDefault(s, 0);
            wordMap.put(s, freq + 1);
        }
        PriorityQueue<String> minHeap = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(wordMap.get(o1) == wordMap.get(o2)){
                    return o2.compareTo(o1);
                }
                return wordMap.get(o1) - wordMap.get(o2);
            }
        });
        for(Map.Entry<String, Integer> entry: wordMap.entrySet()){
            minHeap.add(entry.getKey());
            if(minHeap.size() > k){
                minHeap.poll();
            }
        }
        String[] arr = new String[minHeap.size()];
        int index = minHeap.size() - 1;
        while (minHeap.peek() != null) {
            arr[index] = minHeap.poll();
            index--;
        }

        return Arrays.asList(arr);
    }

}
