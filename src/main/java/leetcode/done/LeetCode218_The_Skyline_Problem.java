package leetcode.done;
import java.util.*;

//218. The Skyline Problem
//Hard
//Time complexity:O(nlogn)
public class LeetCode218_The_Skyline_Problem {
    class Solution {
        public List<List<Integer>> getSkyline(int[][] buildings) {
            // Iterate over all buildings, for each building i
            // add (position, i) to edges.
            List<List<Integer>> edges = new ArrayList<>();
            for (int i = 0; i < buildings.length; ++i){
                edges.add(Arrays.asList(buildings[i][0], i));
                edges.add(Arrays.asList(buildings[i][1], i));
            }
            Collections.sort(edges, (a, b) -> {
                return a.get(0) - b.get(0);
            });

            // Initailize an empty Priority Queue 'live' to store all the newly
            // added buildings, an empty list answer to store the skyline key points.
            Queue<List<Integer>> live = new PriorityQueue<>((a, b) -> {
                return b.get(0) - a.get(0);
            });
            List<List<Integer>> answer = new ArrayList<>();

            int idx = 0;

            // Iterate over all the sorted edges.
            while (idx < edges.size()){
                // Since we might have multiple edges at same x,
                // Let the 'currX' be the current position.
                int currX = edges.get(idx).get(0);

                // While we are handling the edges at 'currX':
                while (idx < edges.size() && edges.get(idx).get(0) == currX){
                    // The index 'b' of this building in 'buildings'
                    int b = edges.get(idx).get(1);

                    // If this is a left edge of building 'b', we
                    // add (height, right) of building 'b' to 'live'.
                    if (buildings[b][0] == currX){
                        int right = buildings[b][1];
                        int height = buildings[b][2];
                        live.offer(Arrays.asList(height, right));
                    }
                    idx += 1;
                }

                // If the tallest live building has been passed,
                // we remove it from 'live'.
                while (!live.isEmpty() && live.peek().get(1) <= currX)
                    live.poll();

                // Get the maximum height from 'live'.
                int currHeight = live.isEmpty() ? 0 : live.peek().get(0);

                // If the height changes at this currX, we add this
                // skyline key point [currX, max_height] to 'answer'.
                if (answer.isEmpty() || answer.get(answer.size() - 1).get(1) != currHeight)
                    answer.add(Arrays.asList(currX, currHeight));
            }

            // Return 'answer' as the skyline.
            return answer;
        }

    }
//    class Solution {
//        public List<List<Integer>> getSkyline(int[][] buildings) {
//            // Collect and sort the unique positions of all the edges.
//            SortedSet<Integer> edgeSet = new TreeSet<Integer>();
//            for (int[] building : buildings) {
//                int left = building[0], right = building[1];
//                edgeSet.add(left);
//                edgeSet.add(right);
//            }
//            List<Integer> positions = new ArrayList<Integer>(edgeSet);
//            Collections.sort(positions);
//
//            // 'answer' for skyline key points.
//            List<List<Integer>> answer = new ArrayList<>();
//            int maxHeight, left, right, height;
//
//            // For each position, draw an imaginary vertical line.
//            for (int position : positions) {
//                // The current max height.
//                maxHeight = 0;
//
//                // Iterate over all the buildings:
//                for (int[] building : buildings) {
//                    left = building[0];
//                    right = building[1];
//                    height = building[2];
//
//                    // If the current building intersects with the line,
//                    // update 'maxHeight'.
//                    if (left <= position && position < right) {
//                        maxHeight = Math.max(maxHeight, height);
//                    }
//                }
//
//                // If its the first key point or the height changes,
//                // we add [position, maxHeight] to 'answer'.
//                if (answer.isEmpty() || answer.get(answer.size() - 1).get(1) != maxHeight) {
//                    answer.add(Arrays.asList(position, maxHeight));
//                }
//            }
//
//            // Return 'answer' as the skyline.
//            return answer;
//        }
//    }
}
