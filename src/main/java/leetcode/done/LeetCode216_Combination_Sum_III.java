package leetcode.done;
//216. Combination Sum III
//Medium

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
public class LeetCode216_Combination_Sum_III {
    class Solution {
        void backtrack(int remain, int k,
                       LinkedList<Integer> comb, int next_start,
                       List<List<Integer>> results) {

            if (remain == 0 && comb.size() == k) {
                // Note: it's important to make a deep copy here,
                // Otherwise the combination would be reverted in other branch of backtracking.
                results.add(new ArrayList<Integer>(comb));
                return;
            } else if (remain < 0 || comb.size() == k) {
                // Exceed the scope, no need to explore further.
                return;
            }

            // Iterate through the reduced list of candidates.
            for (int i = next_start; i < 9; ++i) {
                comb.add(i + 1);
                this.backtrack(remain - i - 1, k, comb, i + 1, results);
                comb.removeLast();
            }
        }

        public List<List<Integer>> combinationSum3(int k, int n) {
            List<List<Integer>> results = new ArrayList<List<Integer>>();
            LinkedList<Integer> comb = new LinkedList<Integer>();

            this.backtrack(n, k, comb, 0, results);
            return results;
        }
    }

    public static void main(String[] args) {
        for(int i = 0; i < -1; i++){
            System.out.println(i);
        }
    }

}
