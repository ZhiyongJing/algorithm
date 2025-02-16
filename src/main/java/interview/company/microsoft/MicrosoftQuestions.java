package interview.company.microsoft;

import leetcode.question.bfs.*;
import leetcode.question.binary_search.*;
import leetcode.question.dfs.*;
import leetcode.question.dp.*;
import leetcode.question.greedy.LeetCode_409_LongestPalindrome;
import leetcode.question.greedy.LeetCode_452_MinimumNumberOfArrowsToBurstBalloons;
import leetcode.question.heap.LeetCode_23_MergeKSortedLists;
import leetcode.question.heap.LeetCode_253_MeetingRoomsIi;
import leetcode.question.heap.LeetCode_295_FindMedianFromDataStream;
import leetcode.question.linked_list.*;
import leetcode.question.map_set.*;
import leetcode.question.prefix_sum.LeetCode_53_MaximumSubarray;
import leetcode.question.sort.LeetCode_215_KthLargestElementInAnArray;
import leetcode.question.sort.LeetCode_56_MergeIntervals;
import leetcode.question.sort.LeetCode_75_SortColors;
import leetcode.question.stack.*;
import leetcode.question.string_list.*;
import leetcode.question.trie.LeetCode_208_ImplementTriePrefixTree;
import leetcode.question.trie.LeetCode_211_DesignAddAndSearchWordsDataStructure;
import leetcode.question.two_pointer.*;
import leetcode.question.union_find.LeetCode_721_AccountsMerge;

import java.util.Arrays;
import java.util.List;

public class MicrosoftQuestions {
    public static void main(String[] args) {
        List<Object> questions = Arrays.asList(
            new LeetCode_39_CombinationSum(), // Find all combinations that sum up to the target, elements can be reused. // 查找所有组合，使其和等于目标值，元素可以重复使用
            new LeetCode_40_CombinationSumIi(), // Find all combinations that sum up to the target, elements cannot be reused. // 查找所有组合，使其和等于目标值，元素不能重复使用
            new LeetCode_43_MultiplyStrings(), // Multiply two non-negative integers represented as strings. // 乘以两个用字符串表示的非负整数
            new LeetCode_216_CombinationSumIii(), // Find all combinations that sum up to the target with exactly K elements. // 查找所有组合，使其和等于目标值，并且恰好有 K 个元素
            new LeetCode_270_ClosestBinarySearchTreeValue(), // Find the value in the BST that is closest to the target. // 在二叉搜索树中查找最接近目标值的节点
            new LeetCode_450_DeleteNodeInABst(), // Delete a node in a Binary Search Tree. // 在二叉搜索树中删除节点
            new LeetCode_695_MaxAreaOfIsland(), // Calculate the maximum area of an island. // 计算岛屿的最大面积
            new LeetCode_108_ConvertSortedArrayToBinarySearchTree(), // Convert a sorted array to a balanced BST. // 将排序数组转换为平衡二叉搜索树
            new LeetCode_21_MergeTwoSortedLists(), // Merge two sorted linked lists into one sorted list. // 合并两个排序的链表为一个排序链表
            new LeetCode_114_FlattenBinaryTreeToLinkedList(), // Flatten a binary tree to a linked list. // 将二叉树展平为链表
            new LeetCode_287_FindTheDuplicateNumber(), // Find the duplicate number in an array. // 查找数组中的重复数字
            new LeetCode_56_MergeIntervals(), // Merge overlapping intervals. // 合并重叠的区间
            new LeetCode_124_BinaryTreeMaximumPathSum(), // Find the maximum path sum in a binary tree. // 查找二叉树中的最大路径和
            new LeetCode_53_MaximumSubarray(), // Find the maximum sum of a contiguous subarray. // 查找连续子数组的最大和
            new LeetCode_96_UniqueBinarySearchTrees(), // Count the number of unique BSTs that can be constructed. // 计算可以构建的不同二叉搜索树的数量
            new LeetCode_33_SearchInRotatedSortedArray(), // Search for a target value in a rotated sorted array. // 在旋转排序数组中查找目标值
            new LeetCode_875_KokoEatingBananas(), // Find the minimum eating speed for Koko to finish bananas. // 查找 Koko 完成香蕉的最小吃香蕉速度
            new LeetCode_787_CheapestFlightsWithinKStops(), // Find the cheapest flight from source to destination with at most K stops. // 查找从起点到终点的最便宜的航班，最多 K 站
            new LeetCode_409_LongestPalindrome(), // Find the longest palindrome that can be built with the given characters. // 找出可以构建的最长回文字符串
            new LeetCode_155_MinStack(), // Design a stack that supports retrieving the minimum element in constant time. // 设计一个支持常数时间获取最小元素的栈
            new LeetCode_207_CourseSchedule(), // Determine if you can finish all courses (course schedule problem). // 判断是否可以完成所有课程（课程安排问题）
            new LeetCode_1836_RemoveDuplicatesFromAnUnsortedLinkedList(), // Remove duplicates from an unsorted linked list. // 从无序链表中移除重复元素
            new LeetCode_1381_DesignAStackWithIncrementOperation(), // Design a stack with an increment operation. // 设计一个具有增量操作的栈
            new LeetCode_206_ReverseLinkedList(), // Reverse a linked list. // 反转链表
            new LeetCode_146_LruCache(), // Design a data structure that supports LRU caching. // 设计一个支持最近最少使用缓存的数据结构
            new LeetCode_234_PalindromeLinkedList(), // Determine if a linked list is a palindrome. // 判断链表是否是回文
            new LeetCode_1382_BalanceABinarySearchTree(), // Balance a binary search tree. // 平衡二叉搜索树
            new LeetCode_211_DesignAddAndSearchWordsDataStructure(), // Design a data structure for adding and searching words. // 设计一个用于添加和搜索单词的数据结构
            new LeetCode_359_LoggerRateLimiter(), // Design a rate limiter for log messages. // 设计一个日志消息的速率限制器
            new LeetCode_242_ValidAnagram(), // Determine if two strings are anagrams of each other. // 判断两个字符串是否为字母异位词
            new LeetCode_49_GroupAnagrams(), // Group anagrams together. // 将字母异位词分组
            new LeetCode_42_TrappingRainWater(), // Calculate the amount of trapped rainwater. // 计算被困雨水的量
            new LeetCode_25_ReverseNodesInKGroup(), // Reverse nodes in k-group in a linked list. // 在链表中以 K 个节点为一组进行反转
            new LeetCode_443_StringCompression(), // Compress a string using counts of repeated characters. // 使用重复字符的计数来压缩字符串
            new LeetCode_227_BasicCalculatorIi(), // Implement a basic calculator II with addition, subtraction, multiplication, and division. // 实现一个基本的计算器 II，支持加、减、乘、除
            new LeetCode_772_BasicCalculatorIii(), // Implement a basic calculator III with parentheses and operators. // 实现一个基本的计算器 III，支持括号和运算符
            new LeetCode_213_HouseRobberIi(), // Solve the house robber problem for a circular arrangement. // 解决环形排列的房屋抢劫问题
            new LeetCode_36_ValidSudoku(), // Determine if a Sudoku board is valid. // 判断一个数独板是否有效
            new LeetCode_37_SudokuSolver(), // Solve a Sudoku puzzle. // 解数独谜题
            new LeetCode_162_FindPeakElement(), // Find a peak element in an array. // 查找数组中的峰值元素
            new LeetCode_20_ValidParentheses(), // Determine if a string of parentheses is valid. // 判断括号字符串是否有效
            new LeetCode_172_FactorialTrailingZeroes(), // Find the number of trailing zeroes in the factorial of a number. // 查找一个数字的阶乘中的尾随零的数量
            new LeetCode_721_AccountsMerge(), // Merge accounts with the same email. // 合并具有相同电子邮件的帐户
            new LeetCode_461_HammingDistance(), // Calculate the Hamming distance between two integers. // 计算两个整数之间的汉明距离
            new LeetCode_477_TotalHammingDistance(), // Calculate the total Hamming distance for all pairs in an array. // 计算数组中所有对的总汉明距离
            new LeetCode_277_FindTheCelebrity(), // Find the celebrity in a group of people. // 在一群人中找出名人
            new LeetCode_62_UniquePaths(), // Find the number of unique paths in a grid. // 计算网格中唯一路径的数量
            new LeetCode_151_ReverseWordsInAString(), // Reverse the words in a string. // 反转字符串中的单词
            new LeetCode_200_NumberOfIslands(), // Find the number of islands in a grid. // 查找网格中的岛屿数量
            new LeetCode_1062_LongestRepeatingSubstring(), // Find the longest repeating substring in a string. // 查找字符串中的最长重复子串
            new LeetCode_23_MergeKSortedLists(), // Merge k sorted linked lists. // 合并 k 个排序的链表
            new LeetCode_17_LetterCombinationsOfAPhoneNumber(), // Generate all possible letter combinations from a phone number. // 生成所有可能的字母组合
            new LeetCode_7_ReverseInteger(), // Reverse an integer. // 反转整数
            new LeetCode_465_OptimalAccountBalancing(), // Find the optimal way to balance accounts. // 找到平衡账户的最佳方法
            new LeetCode_295_FindMedianFromDataStream(), // Find the median from a data stream. // 从数据流中找到中位数
            new LeetCode_994_RottingOranges(), // Find the minimum time required for all oranges to rot. // 找到所有橙子腐烂所需的最短时间
            new LeetCode_31_NextPermutation(), // Find the next lexicographical permutation of a sequence. // 找到序列的下一个字典序排列
            new LeetCode_380_InsertDeleteGetrandomO1(), // Design a data structure that supports insert, delete, and get random element in constant time. // 设计一个支持插入、删除和随机获取元素的数据结构
            new LeetCode_253_MeetingRoomsIi(), // Find the minimum number of meeting rooms required. // 查找所需的最小会议室数量

            //based on leetcode past 30 days
            new LeetCode_88_MergeSortedArray(), // Merge two sorted arrays. // 合并两个排序的数组
            new LeetCode_1466_ReorderRoutesToMakeAllPathsLeadToTheCityZero(), // Reorder routes to make all paths lead to city zero. // 重新排序路线，使所有路径都通向城市零
            new LeetCode_1_TwoSum(), // Find two numbers that add up to the target. // 查找两个数字，使其和等于目标值
            new LeetCode_2246_LongestPathWithDifferentAdjacentCharacters(), // Find the longest path with different adjacent characters. // 找到具有不同相邻字符的最长路径
            new LeetCode_5_LongestPalindromicSubstring(), // Find the longest palindromic substring. // 查找最长回文子串
            new LeetCode_3_LongestSubstringWithoutRepeatingCharacters(), // Find the longest substring without repeating characters. // 查找最长无重复字符的子串
            new LeetCode_26_RemoveDuplicatesFromSortedArray(), // Remove duplicates from a sorted array. // 从排序数组中移除重复元素
            new LeetCode_54_SpiralMatrix(), // Traverse a matrix in spiral order. // 以螺旋顺序遍历矩阵
            new LeetCode_121_BestTimeToBuyAndSellStock(), // Find the best time to buy and sell stock for maximum profit. // 查找买卖股票的最佳时间以获取最大利润
            new LeetCode_122_BestTimeToBuyAndSellStockIi(), // Find the best time to buy and sell stock multiple times for maximum profit. // 查找买卖股票的最佳时间以获取最大利润（允许多次买卖）
            new LeetCode_189_RotateArray(), // Rotate an array to the right by k steps. // 将数组向右旋转 k 步
            new LeetCode_297_SerializeAndDeserializeBinaryTree(), // Serialize and deserialize a binary tree. // 序列化和反序列化二叉树
            new LeetCode_55_JumpGame(), // Determine if you can reach the end of the array. // 判断是否可以到达数组的最后一个位置
            new LeetCode_1306_JumpGameIii(), // Determine if you can reach the end of the array with jump game III. // 使用跳跃游戏 III 判断是否可以到达数组的最后一个位置
            new LeetCode_2_AddTwoNumbers(), // Add two numbers represented by linked lists. // 将两个链表表示的数字相加
            new LeetCode_13_RomanToInteger(), // Convert a Roman numeral to an integer. // 将罗马数字转换为整数
            new LeetCode_73_SetMatrixZeroes(), // Set matrix zeroes based on rows and columns. // 根据行和列将矩阵中的零设置为零
            new LeetCode_75_SortColors(), // Sort an array of colors in-place. // 原地排序颜色数组
            new LeetCode_79_WordSearch(), // Search for a word in a grid of characters. // 在字符网格中搜索单词
            new LeetCode_94_BinaryTreeInorderTraversal(), // Perform an inorder traversal of a binary tree. // 对二叉树进行中序遍历
            new LeetCode_103_BinaryTreeZigzagLevelOrderTraversal(), // Perform a zigzag level order traversal of a binary tree. // 对二叉树进行锯齿形层次遍历
            new LeetCode_112_PathSum(), // Determine if the path sum equals the target value. // 判断路径和是否等于目标值
            new LeetCode_116_PopulatingNextRightPointersInEachNode(), // Populate each node's next right pointer in a perfect binary tree. // 在完美二叉树中填充每个节点的下一个右侧指针
            new LeetCode_128_LongestConsecutiveSequence(), // Find the longest consecutive sequence in an array. // 查找数组中的最长连续序列
            new LeetCode_138_CopyListWithRandomPointer(), // Copy a linked list with random pointers. // 复制带有随机指针的链表
            new LeetCode_141_LinkedListCycle(), // Determine if a linked list has a cycle. // 判断链表是否有环
            new LeetCode_153_FindMinimumInRotatedSortedArray(), // Find the minimum element in a rotated sorted array. // 查找旋转排序数组中的最小元素
            new LeetCode_160_IntersectionOfTwoLinkedLists(), // Find the intersection node of two linked lists. // 查找两个链表的交点
            new LeetCode_162_FindPeakElement(), // Find a peak element in an array. // 查找数组中的峰值元素
            new LeetCode_168_ExcelSheetColumnTitle(), // Convert a column number to an Excel column title. // 将列号转换为 Excel 列标题
            new LeetCode_186_ReverseWordsInAStringIi(), // Reverse words in a string II. // 反转字符串中的单词 II
            new LeetCode_204_CountPrimes(), // Count the number of prime numbers less than a given number. // 计算小于给定数字的质数数量
            new LeetCode_208_ImplementTriePrefixTree(), // Implement a trie (prefix tree). // 实现一个前缀树（字典树）
            new LeetCode_215_KthLargestElementInAnArray(), // Find the k-th largest element in an array. // 查找数组中的第 k 大元素
            new LeetCode_235_LowestCommonAncestorOfABinarySearchTree(), // Find the lowest common ancestor in a BST. // 查找二叉搜索树中的最低公共祖先
            new LeetCode_236_LowestCommonAncestorOfABinaryTree(), // Find the lowest common ancestor in a binary tree. // 查找二叉树中的最低公共祖先
            new LeetCode_445_AddTwoNumbersIi(), // Add two numbers represented by linked lists with different lengths. // 将长度不同的两个链表表示的数字相加
            new LeetCode_259_ThreeSumSmaller(), // 259. 3Sum Smaller：找到三个数的和小于给定目标值的三元组个数。Find the number of triplets with a sum smaller than the given target.
            new LeetCode_365_WaterAndJugProblem(), // 365. Water and Jug Problem：判断是否可以用两个壶达到指定的水量。Determine if you can measure the exact target amount of water using two jugs with given capacities.
            new LeetCode_419_BattleshipsInABoard(), // 419. Battleships in a Board：计算棋盘中战舰的数量，每艘战舰由多个连续的‘X’组成。Count the number of battleships on the board where each battleship is represented by contiguous 'X's.
            new LeetCode_452_MinimumNumberOfArrowsToBurstBalloons(), // 452. Minimum Number of Arrows to Burst Balloons：计算最少的箭数量来射穿所有气球。Find the minimum number of arrows required to burst all balloons, each represented as an interval on a 2D plane.
            new LeetCode_1983_WidestPairOfIndicesWithEqualRangeSum() // 1983. Widest Pair of Indices With Equal Range Sum：找到具有相同范围和的最宽索引对。Find the widest pair of indices with equal range sums between two arrays.































        );


    }
}
