package interview.microsoft;

import leetcode.question.bfs.LeetCode_270_ClosestBinarySearchTreeValue;
import leetcode.question.dfs.LeetCode_216_CombinationSumIii;
import leetcode.question.dfs.LeetCode_39_CombinationSum;
import leetcode.question.dfs.LeetCode_40_CombinationSumIi;
import leetcode.question.dfs.LeetCode_450_DeleteNodeInABst;
import leetcode.question.dfs.LeetCode_695_MaxAreaOfIsland;

import java.util.Arrays;
import java.util.List;

public class MicrosoftQuestions {
    public static void main(String[] args) {
        List<Object> questions = Arrays.asList(
                new LeetCode_39_CombinationSum(),// 重复使用
                new LeetCode_40_CombinationSumIi(), //不可重复使用
                new LeetCode_216_CombinationSumIii() // 有 K
                new LeetCode_270_ClosestBinarySearchTreeValue() //找最接近值
                new LeetCode_450_DeleteNodeInABst(),
                new LeetCode_695_MaxAreaOfIsland()



        );




}
