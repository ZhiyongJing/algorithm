package template;

import java.util.ArrayList;
import java.util.Arrays;

public class DFS {
    public static ArrayList<ArrayList<Integer>> permutations(int[] nums) {
        if (nums.length == 0 || nums == null) {
            return null;
        }
        Arrays.sort(nums);
        ArrayList<ArrayList<Integer>> results = new ArrayList<>();
        ArrayList<Integer> subset = new ArrayList<>();
        permutations_dfs(results, nums, subset);
        return results;
    }

    private static void permutations_dfs(
            ArrayList<ArrayList<Integer>> results, int[] nums, ArrayList<Integer> subset) {
        if(nums.length == subset.size()){
            results.add(new ArrayList<Integer>(subset));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if(!subset.contains(nums[i])){
                subset.add(nums[i]);
                permutations_dfs(results, nums, subset);
                subset.remove(subset.size() - 1);
            }
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
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> comb = new ArrayList<>();
        combination_dfs(result, comb, newNums, 0, target, 0);
        return result;

    }

    private static void combination_dfs(ArrayList<ArrayList<Integer>> result, ArrayList<Integer> comb,
                                           int[] nums, int sum, int target, int startIndex) {
        if (sum == target) {
            result.add(new ArrayList<>(comb));
        }
        for (int i = startIndex; i < nums.length; i++) {
            if (sum + nums[i] > target) {
                break;
            }
            comb.add(nums[i]);
            combination_dfs(result, comb, nums, sum + nums[i], target, i + 1);
            comb.remove(comb.size() - 1);
        }
    }
    
    public static ArrayList<ArrayList<String>> partitionToPalindrome(String s) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return result;
        }
        ArrayList<String> comb = new ArrayList<>();
        partitionToPalindrome_dfs(s, 0, result, comb);
        return result;
    }

    private static void partitionToPalindrome_dfs(String s, int startIndex, ArrayList<ArrayList<String>> result, ArrayList<String> comb) {
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
            partitionToPalindrome_dfs(s, i + 1, result, comb);
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
        System.out.println("permutation: " + permutations(nums));
        int[]  nums1 = {2, 2,3, 3,3,4, 4};
        System.out.println(combination(nums1, 7));
        String s = "aab";
        System.out.println(partitionToPalindrome(s));
    }
}
