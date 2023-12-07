package template;

import java.util.ArrayList;
// find all possible solutions, 90% quertsions are related to combination, permutation
// big O is O(number of answer * time spent on each answer)
/**
 * Recursion
 * Combination
 * Permutation
 * Graph
 * Non-Recursion
 */
//template.DFS
//    use recursion
//        divide and conquer
//        traversal
//    use non resursion
//        divide and conquer
//        traversal

import java.util.*;

public class DFS {
    public static ArrayList<ArrayList<Integer>> permutations(int[] nums) {
        if (nums.length == 0 || nums == null) {
            return null;
        }
        Arrays.sort(nums);
        ArrayList<ArrayList<Integer>> results = new ArrayList<>();
        ArrayList<Integer> subset = new ArrayList<>();
        permutations_helper(results, 0, nums, subset);
        return results;
    }

    private static void permutations_helper(ArrayList<ArrayList<Integer>> results, int startIndex, int[] nums, ArrayList<Integer> subset) {
        System.out.println("====Current subset  is: " + subset);
        System.out.println("Add subset into results");
        results.add(new ArrayList<Integer>(subset));
        System.out.println("After adding subset into result, new result is: "+ results);
        System.out.println("**before the for loop, the startIndex is: "+ startIndex);
        for (int i = startIndex; i < nums.length; i++) {
            System.out.println("Going to add integer into subset: " + nums[i]);
            subset.add(nums[i]);
            System.out.println("After adding, new subset is: "+subset);
            permutations_helper(results, i + 1, nums, subset);
            System.out.println("Going to remove integer from subset: " + subset.get(subset.size() - 1));
            subset.remove(subset.size() - 1);
            System.out.println("After removing, new subset is: "+subset);
            System.out.println("****After removing, the current i is: "+ i);
        }
    }

    public static ArrayList<ArrayList<Integer>> combination(int[] nums, int target) {

        Arrays.sort(nums);
        // remove duplicate
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[index] != nums[i] && index != i) {
                index++;
                nums[index] = nums[i];
            }
        }
        int[] newNums = new int[index + 1];
        for (int j = 0; j <= index; j++) {
            newNums[j] = nums[j];
        }
//        for(int i: newNums){
//            System.out.println(i);
//        }
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> comb = new ArrayList<>();
        combination_helper(result, comb, newNums, 0, target, 0);
        return result;

    }

    private static void combination_helper(ArrayList<ArrayList<Integer>> result, ArrayList<Integer> comb,
                                           int[] nums, int sum, int target, int startIndex) {
        if (sum == target) {
            result.add(new ArrayList<>(comb));
        }
        for (int i = startIndex; i < nums.length; i++) {
            if (sum + nums[i] > target) {
                break;
            }
            comb.add(nums[i]);
            combination_helper(result, comb, nums, sum + nums[i], target, i + 1);
            comb.remove(comb.size() - 1);
        }
    }

    private static int sumOfArrayList(ArrayList<Integer> arr) {
        int sum = 0;
        for (int i = 0; i < arr.size(); i++) {
            sum += arr.get(i);
        }
        return sum;
    }

    public static ArrayList<ArrayList<String>> partitionToPalindrome(String s) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return result;
        }
        ArrayList<String> comb = new ArrayList<>();
        partitionToPalindrome_helper(s, 0, result, comb);
        return result;

    }

    private static void partitionToPalindrome_helper(String s, int startIndex, ArrayList<ArrayList<String>> result, ArrayList<String> comb) {
        if (startIndex == s.length()) {
            result.add(new ArrayList<>(comb));
            return;
        }
//        result.add(new ArrayList<>(comb));
        for (int i = startIndex; i < s.length(); i++) {
            String sub = s.substring(startIndex, i + 1);
            if(!isPalindrome(sub)){
                continue;
            }
            comb.add(sub);
            partitionToPalindrome_helper(s, i + 1, result, comb);
            comb.remove(sub);
        }
    }

    private static boolean isPalindrome(String s) {
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(permutations(nums));
        int[]  nums1 = {2, 2,3, 3,3,4, 4};
//        System.out.println(combination(nums1, 7));
        String s = "aab";
        System.out.println(partitionToPalindrome(s));
    }
}
