package interview.company.point72;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FrequencySort {
    public static List<Integer> itemSort(List<Integer> items){
        List<Integer> result = new ArrayList<>();
        if(items == null || items.size() == 0){
            return result;
        }
        //<Integer, Frequcy>
        Map<Integer, Integer> frequecyMap = new HashMap<>();
        items.stream().forEach( i -> frequecyMap.put(i, frequecyMap.getOrDefault(i, 0) + 1));
        // custom sort
        result =  items.stream()
                .sorted((a,b) -> frequecyMap.get(a) != frequecyMap.get(b) ? frequecyMap.get(a) - frequecyMap.get(b) : a -b).collect(Collectors.toList());

//        int maximumFrequency = Collections.max(frequecyMap.values());
        // Make the list of buckets and apply bucket sort.
        // bucket index is the frequency of number
//        List<List<Integer>> buckets = new ArrayList<>();
//        for (int i = 0; i <= maximumFrequency; i++) {
//            buckets.add(new ArrayList<Integer>());
//        }
//        for (Integer key : frequecyMap.keySet()) {
//            int freq = frequecyMap.get(key);
//            buckets.get(freq).add(key);
//        }
        return result;

    }

    public static void main(String[] args) {
        FrequencySort fs = new FrequencySort();
//        List<Integer> items = Arrays.asList(4, 5, 6, 5, 4, 3);
//        System.out.println(fs.itemSort(items));
        List<Integer> items = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        Integer size = sc.nextInt();
        for(int i = 0; i < size; i++){
            items.add(sc.nextInt());
        }
        sc.close();
        System.out.println(fs.itemSort(items));
    }
}
