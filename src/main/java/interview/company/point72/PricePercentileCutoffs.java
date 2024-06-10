package interview.company.point72;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class PricePercentileCutoffs {
    public void findPrice(List<List<Integer>> input){
        if(input == null || input.size() == 0){
            System.out.println(0.0);
        }
//        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<Pair<Integer, Integer>>(
//            (a, b) -> { return a.getKey() - b.getKey();
//            }
//        );
//        input.stream().forEach(i ->{
//            pq.i.get(0)
//        });
        TreeMap<Integer, Integer> treeMap = new TreeMap<Integer, Integer>(
                (a, b) -> { return a - b ;
                }
        );
        AtomicInteger count = new AtomicInteger();
        input.stream().forEach(i ->{
            count.addAndGet(i.get(1));
            treeMap.put(i.get(0), treeMap.getOrDefault(i.get(0), 0) + i.get(1));
        });
        int row = (int) (count.intValue() * 0.8);
        Set<Integer> keySet = treeMap.navigableKeySet();
        for(Integer i: keySet){

            if(row <= treeMap.get(i)){
                double price = i/100.0;
                System.out.println(price);
                return;
            }
            row = row - treeMap.get(i);
        }

    }

    public static void main(String[] args) {
        PricePercentileCutoffs pc = new PricePercentileCutoffs();
        Scanner sc = new Scanner(System.in);
        int size  = sc.nextInt();
        List<List<Integer>> inputList = new ArrayList<>();
        for(int i = 0; i < size; i++){
            String input = sc.next();
            List<Integer> record = Arrays.stream(input.split(",")).map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList());
            inputList.add(record);
        }
        pc.findPrice(inputList);

    }
}
