package template; /**
 * 什么时候用BFS
 * 1. 图的遍历 Travel in Graph
 * 1.1 层级遍历 Level Order Traversal
 * 1.2 由点及面 Connected Component
 * 1.3 拓扑排序 Topological Sorting
 * 2. 最短路径 Shortest Path in Simple Graph
 * 2.1 仅限简单图求最短路径
 * 2.2 图中每条边长度都是1， 且没有方向
 */

import java.util.*;

public class BFS {
    // word ladder
    public static int ladderlength(String start, String end, Set<String> dict) {
        if (dict == null) {
            return 0;
        }
        if (start.equals(end)) {
            return 1;
        }
        dict.add(end);

        Set<String> hash = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        hash.add(start);

        int length = 1;
        while (!queue.isEmpty()) {
            length++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                for (String nextWord : getNextWords(word, dict)) {
                    if (hash.contains(nextWord)) {
                        continue;
                    }
                    if (nextWord.equals(end)) {
                        return length;
                    }
                    hash.add(nextWord);
                    queue.offer(nextWord);

                }
            }
        }
        return 0;
    }

    private static String replace(String s, int index, char c) {
        char[] chars = s.toCharArray();
        chars[index] = c;
        return new String(chars);
    }

    private static ArrayList<String> getNextWords(String word, Set<String> dict) {
        ArrayList<String> nextWords = new ArrayList<>(); //O(L)
        for (int i = 0; i < word.length(); i++) { //O(25)
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == word.charAt(i)) {
                    continue;
                }
                String nextWord = replace(word, i, c); //O(L)
                if (dict.contains(nextWord)) { //O(L)
                    nextWords.add(nextWord);
                }
            }
        }
        return nextWords;
    }

    public static void main(String[] args) {
        String start = "hit";
        String end = "hot";
        Set<String> dict = new HashSet<String>();

        // Adding all elements to List
        dict.addAll(Arrays.asList(new String[]{"hot", "dot", "dog", "lat", "log"}));
        System.out.println(getNextWords("hot", dict).toString());
        System.out.println(ladderlength(start, end, dict));
    }

}
