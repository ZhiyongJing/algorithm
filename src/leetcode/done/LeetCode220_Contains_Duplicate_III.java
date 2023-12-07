package leetcode.done;

//Hard
//Time complexity:O(nlog(min(n,k)))
import java.util.TreeSet;

public class LeetCode220_Contains_Duplicate_III {
    class Solution {
        public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
            TreeSet<Integer> set = new TreeSet<>();
            for (int i = 0; i < nums.length; ++i) {
                // Find the successor of current element
                Integer s = set.ceiling(nums[i]);
                if (s != null && (long) s <= nums[i] + t) return true;

                // Find the predecessor of current element
                Integer g = set.floor(nums[i]);
                if (g != null && nums[i] <= (long) g + t) return true;

                set.add(nums[i]);
                if (set.size() > k) {
                    set.remove(nums[i - k]);
                }
            }
            return false;
        }
    }
//    class Solution {
//     public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
//         for (int i = 0; i < nums.length; ++i) {
//             for (int j = Math.max(i - k, 0); j < i; ++j) {
//                 if (Math.abs((long) nums[i] - nums[j]) <= t) return true;
//             }
//         }
//         return false;
//     }
// }
}
