package leetcode.done;


//167. Two Sum II - Input Array Is Sorted
//Medium
public class LeetCode167_Two_Sum_II_Input_Array_Is_Sorted {
    class Solution {
        public int[] twoSum(int[] numbers, int target) {
            int l = 0, r = numbers.length - 1;
            while (numbers[l] + numbers[r] != target) {
                if (numbers[l] + numbers[r] > target) r--;
                else l++;
            }
            return new int[]{l + 1, r + 1};
        }
    }
}
