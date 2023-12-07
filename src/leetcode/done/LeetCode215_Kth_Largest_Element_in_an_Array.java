package leetcode.done;
//215. Kth Largest Element in an Array
//Medium
import java.util.*;
public class LeetCode215_Kth_Largest_Element_in_an_Array {
    class Solution {
        public int findKthLargest(int[] nums, int k) {
            // init heap 'the smallest element first'
            PriorityQueue<Integer> heap =
                    new PriorityQueue<Integer>((n1, n2) -> n1 - n2);

            // keep k largest elements in the heap
            for (int n: nums) {
                heap.add(n);
                if (heap.size() > k)
                    heap.poll();
            }

            // output
            return heap.poll();
        }
    }
}
