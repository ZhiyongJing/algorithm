package leetcode.done;

import java.util.HashSet;
import java.util.Set;
public class LeetCode217_Contains_Duplicate {
    class Solution {
        public boolean containsDuplicate(int[] nums) {
            Set<Integer> set = new HashSet<>(nums.length);
            for (int x: nums) {
                if (set.contains(x)) return true;
                set.add(x);
            }
            return false;
        }
    }
}
