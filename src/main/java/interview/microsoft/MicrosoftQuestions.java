package interview.microsoft;

import leetcode.question.bfs.LeetCode_207_CourseSchedule;
import leetcode.question.bfs.LeetCode_270_ClosestBinarySearchTreeValue;
import leetcode.question.bfs.LeetCode_787_CheapestFlightsWithinKStops;
import leetcode.question.binary_search.LeetCode_33_SearchInRotatedSortedArray;
import leetcode.question.binary_search.LeetCode_875_KokoEatingBananas;
import leetcode.question.dfs.LeetCode_108_ConvertSortedArrayToBinarySearchTree;
import leetcode.question.dfs.LeetCode_114_FlattenBinaryTreeToLinkedList;
import leetcode.question.dfs.LeetCode_124_BinaryTreeMaximumPathSum;
import leetcode.question.dfs.LeetCode_216_CombinationSumIii;
import leetcode.question.dfs.LeetCode_21_MergeTwoSortedLists;
import leetcode.question.dfs.LeetCode_39_CombinationSum;
import leetcode.question.dfs.LeetCode_40_CombinationSumIi;
import leetcode.question.dfs.LeetCode_450_DeleteNodeInABst;
import leetcode.question.dfs.LeetCode_695_MaxAreaOfIsland;
import leetcode.question.dp.LeetCode_122_BestTimeToBuyAndSellStockIi;
import leetcode.question.dp.LeetCode_96_UniqueBinarySearchTrees;
import leetcode.question.greedy.LeetCode_409_LongestPalindrome;
import leetcode.question.linked_list.LeetCode_1836_RemoveDuplicatesFromAnUnsortedLinkedList;
import leetcode.question.map_set.LeetCode_287_FindTheDuplicateNumber;
import leetcode.question.prefix_sum.LeetCode_53_MaximumSubarray;
import leetcode.question.sort.LeetCode_56_MergeIntervals;
import leetcode.question.stack.LeetCode_155_MinStack;

import java.util.Arrays;
import java.util.List;

public class MicrosoftQuestions {
    public static void main(String[] args) {
        List<Object> questions = Arrays.asList(
                new LeetCode_39_CombinationSum(),// 重复使用
                new LeetCode_40_CombinationSumIi(), //不可重复使用
                new LeetCode_216_CombinationSumIii(), // 有 K
                new LeetCode_270_ClosestBinarySearchTreeValue(), //找最接近值
                new LeetCode_450_DeleteNodeInABst(),
                new LeetCode_695_MaxAreaOfIsland(),
                new LeetCode_108_ConvertSortedArrayToBinarySearchTree(), //创建bst
                new LeetCode_21_MergeTwoSortedLists(),
                new LeetCode_114_FlattenBinaryTreeToLinkedList(),//出现2次，重点
                new LeetCode_287_FindTheDuplicateNumber(),
                new LeetCode_56_MergeIntervals(),
                new LeetCode_122_BestTimeToBuyAndSellStockIi(),
                new LeetCode_124_BinaryTreeMaximumPathSum(),
                new LeetCode_53_MaximumSubarray(),
                new LeetCode_96_UniqueBinarySearchTrees(),
                new LeetCode_33_SearchInRotatedSortedArray(),
                new LeetCode_875_KokoEatingBananas(),
                new LeetCode_787_CheapestFlightsWithinKStops(),
                new LeetCode_409_LongestPalindrome(),
                new LeetCode_155_MinStack(),
                new LeetCode_207_CourseSchedule(),
                new LeetCode_1836_RemoveDuplicatesFromAnUnsortedLinkedList()









        );


    }
}
