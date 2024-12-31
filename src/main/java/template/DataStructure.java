package template;

import java.util.Comparator;
import java.util.PriorityQueue;

public class DataStructure {
    public static void main(String[] args) {
        /**
         * ArrayList
         */
//        List<String> l = new ArrayList<>();
//        l = Arrays.asList("1", "2");
//        l.add(1);
//        l.add(2);
//        l.addAll(new ArrayList<>(l));
//        Collections.sort(l, ( a, b )-> (b.compareTo(a)));
//        System.out.println(l);
//        String[] arr = l.toArray(new String[l.size()]);
//        List<String> l1 = Arrays.asList("hello");
//        System.out.println(l1);


        /**
         * Array
         */
//        int [] arr = {1, 2,3, 4, 5};
//        String s = "abc";
//        char[] chars = s.toCharArray();

        /**
         * Type conversion
         */
//        System.out.println(Integer.parseInt("2"));
//        System.out.println(String.valueOf(2));
//        System.out.println(String.valueOf('a'));
//        System.out.println('3' - '0');


        /**
         * create priority queue
         */
//        PriorityQueue<String> pq = new PriorityQueue<String>((a, b)->(b.compareTo(a)));
        PriorityQueue<String> pq = new PriorityQueue<String>(
                new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return 0;
                    }
                }
        );
        pq.add("4");

        pq.add("3");
        pq.add("8");
        System.out.println(pq.peek());





    }
}
