package interview.microsoft;

import leetcode.question.bfs.LeetCode_207_CourseSchedule;
import leetcode.question.bfs.LeetCode_270_ClosestBinarySearchTreeValue;
import leetcode.question.bfs.LeetCode_787_CheapestFlightsWithinKStops;
import leetcode.question.binary_search.LeetCode_162_FindPeakElement;
import leetcode.question.binary_search.LeetCode_33_SearchInRotatedSortedArray;
import leetcode.question.binary_search.LeetCode_875_KokoEatingBananas;
import leetcode.question.dfs.LeetCode_108_ConvertSortedArrayToBinarySearchTree;
import leetcode.question.dfs.LeetCode_114_FlattenBinaryTreeToLinkedList;
import leetcode.question.dfs.LeetCode_124_BinaryTreeMaximumPathSum;
import leetcode.question.dfs.LeetCode_1382_BalanceABinarySearchTree;
import leetcode.question.dfs.LeetCode_216_CombinationSumIii;
import leetcode.question.dfs.LeetCode_21_MergeTwoSortedLists;
import leetcode.question.dfs.LeetCode_37_SudokuSolver;
import leetcode.question.dfs.LeetCode_39_CombinationSum;
import leetcode.question.dfs.LeetCode_40_CombinationSumIi;
import leetcode.question.dfs.LeetCode_450_DeleteNodeInABst;
import leetcode.question.dfs.LeetCode_695_MaxAreaOfIsland;
import leetcode.question.dp.LeetCode_122_BestTimeToBuyAndSellStockIi;
import leetcode.question.dp.LeetCode_213_HouseRobberIi;
import leetcode.question.dp.LeetCode_96_UniqueBinarySearchTrees;
import leetcode.question.greedy.LeetCode_409_LongestPalindrome;
import leetcode.question.linked_list.LeetCode_1836_RemoveDuplicatesFromAnUnsortedLinkedList;
import leetcode.question.linked_list.LeetCode_206_ReverseLinkedList;
import leetcode.question.linked_list.LeetCode_234_PalindromeLinkedList;
import leetcode.question.linked_list.LeetCode_25_ReverseNodesInKGroup;
import leetcode.question.map_set.LeetCode_146_LruCache;
import leetcode.question.map_set.LeetCode_287_FindTheDuplicateNumber;
import leetcode.question.map_set.LeetCode_359_LoggerRateLimiter;
import leetcode.question.map_set.LeetCode_36_ValidSudoku;
import leetcode.question.map_set.LeetCode_49_GroupAnagrams;
import leetcode.question.prefix_sum.LeetCode_53_MaximumSubarray;
import leetcode.question.sort.LeetCode_56_MergeIntervals;
import leetcode.question.stack.LeetCode_1381_DesignAStackWithIncrementOperation;
import leetcode.question.stack.LeetCode_155_MinStack;
import leetcode.question.stack.LeetCode_20_ValidParentheses;
import leetcode.question.stack.LeetCode_227_BasicCalculatorIi;
import leetcode.question.stack.LeetCode_772_BasicCalculatorIii;
import leetcode.question.string_list.LeetCode_172_FactorialTrailingZeroes;
import leetcode.question.string_list.LeetCode_242_ValidAnagram;
import leetcode.question.string_list.LeetCode_461_HammingDistance;
import leetcode.question.trie.LeetCode_211_DesignAddAndSearchWordsDataStructure;
import leetcode.question.two_pointer.LeetCode_42_TrappingRainWater;
import leetcode.question.two_pointer.LeetCode_443_StringCompression;
import leetcode.question.union_find.LeetCode_721_AccountsMerge;

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
                new LeetCode_33_SearchInRotatedSortedArray(),//出现2次
                new LeetCode_875_KokoEatingBananas(),//出现2次
                new LeetCode_787_CheapestFlightsWithinKStops(),
                new LeetCode_409_LongestPalindrome(),
                new LeetCode_155_MinStack(),
                new LeetCode_207_CourseSchedule(),
                new LeetCode_1836_RemoveDuplicatesFromAnUnsortedLinkedList(),
                new LeetCode_1381_DesignAStackWithIncrementOperation(),
                new LeetCode_206_ReverseLinkedList(),
                new LeetCode_146_LruCache(),
                new LeetCode_234_PalindromeLinkedList(),
                new LeetCode_1382_BalanceABinarySearchTree(),
                new LeetCode_207_CourseSchedule(),
                new LeetCode_211_DesignAddAndSearchWordsDataStructure(),
                new LeetCode_359_LoggerRateLimiter(),
                new LeetCode_242_ValidAnagram(),//出现2次,重点 follow up 49
                new LeetCode_49_GroupAnagrams(),
                new LeetCode_42_TrappingRainWater(), // 两个线最大雨水
                new LeetCode_25_ReverseNodesInKGroup(),
                new LeetCode_443_StringCompression(),
                new LeetCode_227_BasicCalculatorIi(),//follow up on 772
                new LeetCode_772_BasicCalculatorIii(),//带括号
                new LeetCode_213_HouseRobberIi(),
                new LeetCode_36_ValidSudoku(),
                new LeetCode_37_SudokuSolver(),
                new LeetCode_162_FindPeakElement(),
                new LeetCode_20_ValidParentheses(),
                new LeetCode_172_FactorialTrailingZeroes(),
                new LeetCode_721_AccountsMerge(),
                new LeetCode_461_HammingDistance(),
                new LeetCode_477














        );


    }
}
