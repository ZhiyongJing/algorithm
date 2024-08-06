package interview.microsoft;

import leetcode.question.bfs.LeetCode_103_BinaryTreeZigzagLevelOrderTraversal;
import leetcode.question.bfs.LeetCode_200_NumberOfIslands;
import leetcode.question.bfs.LeetCode_207_CourseSchedule;
import leetcode.question.bfs.LeetCode_270_ClosestBinarySearchTreeValue;
import leetcode.question.bfs.LeetCode_297_SerializeAndDeserializeBinaryTree;
import leetcode.question.bfs.LeetCode_787_CheapestFlightsWithinKStops;
import leetcode.question.bfs.LeetCode_994_RottingOranges;
import leetcode.question.binary_search.LeetCode_1062_LongestRepeatingSubstring;
import leetcode.question.binary_search.LeetCode_153_FindMinimumInRotatedSortedArray;
import leetcode.question.binary_search.LeetCode_162_FindPeakElement;
import leetcode.question.binary_search.LeetCode_33_SearchInRotatedSortedArray;
import leetcode.question.binary_search.LeetCode_875_KokoEatingBananas;
import leetcode.question.dfs.LeetCode_108_ConvertSortedArrayToBinarySearchTree;
import leetcode.question.dfs.LeetCode_112_PathSum;
import leetcode.question.dfs.LeetCode_114_FlattenBinaryTreeToLinkedList;
import leetcode.question.dfs.LeetCode_116_PopulatingNextRightPointersInEachNode;
import leetcode.question.dfs.LeetCode_124_BinaryTreeMaximumPathSum;
import leetcode.question.dfs.LeetCode_1382_BalanceABinarySearchTree;
import leetcode.question.dfs.LeetCode_1466_ReorderRoutesToMakeAllPathsLeadToTheCityZero;
import leetcode.question.dfs.LeetCode_17_LetterCombinationsOfAPhoneNumber;
import leetcode.question.dfs.LeetCode_216_CombinationSumIii;
import leetcode.question.dfs.LeetCode_21_MergeTwoSortedLists;
import leetcode.question.dfs.LeetCode_2246_LongestPathWithDifferentAdjacentCharacters;
import leetcode.question.dfs.LeetCode_235_LowestCommonAncestorOfABinarySearchTree;
import leetcode.question.dfs.LeetCode_236_LowestCommonAncestorOfABinaryTree;
import leetcode.question.dfs.LeetCode_37_SudokuSolver;
import leetcode.question.dfs.LeetCode_39_CombinationSum;
import leetcode.question.dfs.LeetCode_40_CombinationSumIi;
import leetcode.question.dfs.LeetCode_450_DeleteNodeInABst;
import leetcode.question.dfs.LeetCode_695_MaxAreaOfIsland;
import leetcode.question.dfs.LeetCode_79_WordSearch;
import leetcode.question.dfs.LeetCode_94_BinaryTreeInorderTraversal;
import leetcode.question.dp.LeetCode_121_BestTimeToBuyAndSellStock;
import leetcode.question.dp.LeetCode_122_BestTimeToBuyAndSellStockIi;
import leetcode.question.dp.LeetCode_1306_JumpGameIii;
import leetcode.question.dp.LeetCode_213_HouseRobberIi;
import leetcode.question.dp.LeetCode_465_OptimalAccountBalancing;
import leetcode.question.dp.LeetCode_55_JumpGame;
import leetcode.question.dp.LeetCode_62_UniquePaths;
import leetcode.question.dp.LeetCode_96_UniqueBinarySearchTrees;
import leetcode.question.greedy.LeetCode_409_LongestPalindrome;
import leetcode.question.heap.LeetCode_23_MergeKSortedLists;
import leetcode.question.heap.LeetCode_253_MeetingRoomsIi;
import leetcode.question.heap.LeetCode_295_FindMedianFromDataStream;
import leetcode.question.linked_list.LeetCode_141_LinkedListCycle;
import leetcode.question.linked_list.LeetCode_160_IntersectionOfTwoLinkedLists;
import leetcode.question.linked_list.LeetCode_1836_RemoveDuplicatesFromAnUnsortedLinkedList;
import leetcode.question.linked_list.LeetCode_206_ReverseLinkedList;
import leetcode.question.linked_list.LeetCode_234_PalindromeLinkedList;
import leetcode.question.linked_list.LeetCode_25_ReverseNodesInKGroup;
import leetcode.question.linked_list.LeetCode_2_AddTwoNumbers;
import leetcode.question.map_set.LeetCode_128_LongestConsecutiveSequence;
import leetcode.question.map_set.LeetCode_138_CopyListWithRandomPointer;
import leetcode.question.map_set.LeetCode_13_RomanToInteger;
import leetcode.question.map_set.LeetCode_146_LruCache;
import leetcode.question.map_set.LeetCode_1_TwoSum;
import leetcode.question.map_set.LeetCode_287_FindTheDuplicateNumber;
import leetcode.question.map_set.LeetCode_359_LoggerRateLimiter;
import leetcode.question.map_set.LeetCode_36_ValidSudoku;
import leetcode.question.map_set.LeetCode_380_InsertDeleteGetrandomO1;
import leetcode.question.map_set.LeetCode_49_GroupAnagrams;
import leetcode.question.map_set.LeetCode_73_SetMatrixZeroes;
import leetcode.question.prefix_sum.LeetCode_53_MaximumSubarray;
import leetcode.question.queue.LeetCode_54_SpiralMatrix;
import leetcode.question.sort.LeetCode_215_KthLargestElementInAnArray;
import leetcode.question.sort.LeetCode_56_MergeIntervals;
import leetcode.question.sort.LeetCode_75_SortColors;
import leetcode.question.stack.LeetCode_1381_DesignAStackWithIncrementOperation;
import leetcode.question.stack.LeetCode_155_MinStack;
import leetcode.question.stack.LeetCode_20_ValidParentheses;
import leetcode.question.stack.LeetCode_227_BasicCalculatorIi;
import leetcode.question.stack.LeetCode_772_BasicCalculatorIii;
import leetcode.question.string_list.LeetCode_168_ExcelSheetColumnTitle;
import leetcode.question.string_list.LeetCode_172_FactorialTrailingZeroes;
import leetcode.question.string_list.LeetCode_204_CountPrimes;
import leetcode.question.string_list.LeetCode_242_ValidAnagram;
import leetcode.question.string_list.LeetCode_461_HammingDistance;
import leetcode.question.string_list.LeetCode_477_TotalHammingDistance;
import leetcode.question.string_list.LeetCode_7_ReverseInteger;
import leetcode.question.trie.LeetCode_208_ImplementTriePrefixTree;
import leetcode.question.trie.LeetCode_211_DesignAddAndSearchWordsDataStructure;
import leetcode.question.two_pointer.LeetCode_151_ReverseWordsInAString;
import leetcode.question.two_pointer.LeetCode_186_ReverseWordsInAStringIi;
import leetcode.question.two_pointer.LeetCode_189_RotateArray;
import leetcode.question.two_pointer.LeetCode_26_RemoveDuplicatesFromSortedArray;
import leetcode.question.two_pointer.LeetCode_277_FindTheCelebrity;
import leetcode.question.two_pointer.LeetCode_31_NextPermutation;
import leetcode.question.two_pointer.LeetCode_3_LongestSubstringWithoutRepeatingCharacters;
import leetcode.question.two_pointer.LeetCode_42_TrappingRainWater;
import leetcode.question.two_pointer.LeetCode_443_StringCompression;
import leetcode.question.two_pointer.LeetCode_5_LongestPalindromicSubstring;
import leetcode.question.two_pointer.LeetCode_88_MergeSortedArray;
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
                new LeetCode_146_LruCache(),//出现2次
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
                new LeetCode_477_TotalHammingDistance(),
                new LeetCode_277_FindTheCelebrity(),
                new LeetCode_62_UniquePaths(),
                new LeetCode_151_ReverseWordsInAString(),//follow up 如何处理很长的string processing the string in chunks,
                new LeetCode_200_NumberOfIslands(),
                new LeetCode_1062_LongestRepeatingSubstring(),
                new LeetCode_23_MergeKSortedLists(),
                new LeetCode_17_LetterCombinationsOfAPhoneNumber(),
                new LeetCode_7_ReverseInteger(),
                new LeetCode_465_OptimalAccountBalancing(),
                new LeetCode_295_FindMedianFromDataStream(),
                new LeetCode_994_RottingOranges(),
                new LeetCode_31_NextPermutation(),
                new LeetCode_380_InsertDeleteGetrandomO1(),
                new LeetCode_253_MeetingRoomsIi(),

                //based on leetcode past 30 days
                new LeetCode_88_MergeSortedArray(),
                new LeetCode_1466_ReorderRoutesToMakeAllPathsLeadToTheCityZero(),
                new LeetCode_1_TwoSum(),//no dup
                new LeetCode_2246_LongestPathWithDifferentAdjacentCharacters(), //too hard similar to 124 maximum path sum
                new LeetCode_5_LongestPalindromicSubstring(),
                new LeetCode_3_LongestSubstringWithoutRepeatingCharacters(),
                new LeetCode_26_RemoveDuplicatesFromSortedArray(),
                new LeetCode_54_SpiralMatrix(),
                new LeetCode_121_BestTimeToBuyAndSellStock(), //只能买卖1次， follow up 122 可以买卖多次
                new LeetCode_122_BestTimeToBuyAndSellStockIi(),
                new LeetCode_189_RotateArray(),
                new LeetCode_297_SerializeAndDeserializeBinaryTree(),
                new LeetCode_55_JumpGame(),//需要判断是否能够到达数组的最后一个位置。
                new LeetCode_1306_JumpGameIii(), //dp
                new LeetCode_2_AddTwoNumbers(),
                new LeetCode_13_RomanToInteger(),
                new LeetCode_20_ValidParentheses(),
                new LeetCode_73_SetMatrixZeroes(),
                new LeetCode_75_SortColors(),
                new LeetCode_79_WordSearch(),
                new LeetCode_94_BinaryTreeInorderTraversal(),
                new LeetCode_103_BinaryTreeZigzagLevelOrderTraversal(), //zigzag level order
                new LeetCode_112_PathSum(),
                new LeetCode_116_PopulatingNextRightPointersInEachNode(),
                new LeetCode_128_LongestConsecutiveSequence(),
                new LeetCode_138_CopyListWithRandomPointer(),
                new LeetCode_141_LinkedListCycle(),
                new LeetCode_153_FindMinimumInRotatedSortedArray(),
                new LeetCode_160_IntersectionOfTwoLinkedLists(),
                new LeetCode_162_FindPeakElement(),
                new LeetCode_168_ExcelSheetColumnTitle(),
                new LeetCode_186_ReverseWordsInAStringIi(),//反转 worlds
                new LeetCode_204_CountPrimes(),
                new LeetCode_208_ImplementTriePrefixTree(),
                new LeetCode_215_KthLargestElementInAnArray(),
                new LeetCode_235_LowestCommonAncestorOfABinarySearchTree(),//BST
                new LeetCode_236_LowestCommonAncestorOfABinaryTree(),//BT





























        );


    }
}
