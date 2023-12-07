package template;

import java.util.*;

public class LeetCodeTesting {
    public List<List<Integer>> combnations(int[] nums , int k){
        List<List<Integer>> total = new ArrayList<>();
        List<Integer> comb = new ArrayList<>();
        this.dfs(nums, total, comb, 0);

        return total;

    }
    private void dfs(int[] nums, List<List<Integer>> total, List<Integer> comb, int startIndex){
        total.add(new ArrayList<>(comb));
        for(int i = startIndex; i < nums.length; i++){
            comb.add(nums[i]);
            dfs(nums, total, comb, i+1);
            comb.remove(comb.size() - 1);
        }

    }


    public static void main(String[] args) {
        LeetCodeTesting t = new LeetCodeTesting();
        int[] nums = {1,2, 3, 4};

        System.out.println(
                t.combnations(nums, 1)
        );
    }
}
