package interview.company.bloomberg;

// Interview questions

import leetcode.question.bfs.LeetCode_127_WordLadder;
import leetcode.question.bfs.*;
import leetcode.question.binary_search.LeetCode_1095_FindInMountainArray;
import leetcode.question.binary_search.LeetCode_33_SearchInRotatedSortedArray;
import leetcode.question.binary_search.LeetCode_611_ValidTriangleNumber;
import leetcode.question.dfs.*;
import leetcode.question.dp.*;
import leetcode.question.greedy.LeetCode_1029_TwoCityScheduling;
import leetcode.question.greedy.LeetCode_134_GasStation;
import leetcode.question.heap.LeetCode_253_MeetingRoomsIi;
import leetcode.question.heap.LeetCode_642_DesignSearchAutocompleteSystem;
import leetcode.question.linked_list.LeetCode_203_RemoveLinkedListElements;
import leetcode.question.linked_list.LeetCode_2_AddTwoNumbers;
import leetcode.question.linked_list.LeetCode_430_FlattenAMultilevelDoublyLinkedList;
import leetcode.question.linked_list.LeetCode_445_AddTwoNumbersIi;
import leetcode.question.map_set.*;
import leetcode.question.prefix_sum.LeetCode_1352_ProductOfTheLastKNumbers;
import leetcode.question.prefix_sum.LeetCode_53_MaximumSubarray;
import leetcode.question.prefix_sum.LeetCode_560_SubarraySumEqualsK;
import leetcode.question.stack.*;
import leetcode.question.string_list.LeetCode_1583_CountUnhappyFriends;
import leetcode.question.string_list.LeetCode_1910_RemoveAllOccurrencesOfASubstring;
import leetcode.question.string_list.LeetCode_2423_RemoveLetterToEqualizeFrequency;
import leetcode.question.string_list.LeetCode_8_StringToIntegerAtoi;
import leetcode.question.two_pointer.*;

import java.util.Arrays;
import java.util.List;
public class BloombergQuestions {
    public static void main(String[] args) {
        List<Object> questions = Arrays.asList(
            new LeetCode_64_MinimumPathSum() // Find the minimum path sum from the top-left to the bottom-right of a grid. // 从网格的左上角到右下角，找出路径和最小的路径。
                , new LeetCode_1095_FindInMountainArray()
                , new LeetCode_1387_SortIntegersByThePowerValue()
                , new LeetCode_44_WildcardMatching()
                , new LeetCode_2_AddTwoNumbers()
                , new LeetCode_189_RotateArray()
                , new LeetCode_203_RemoveLinkedListElements()
                , new LeetCode_98_ValidateBinarySearchTree()
                , new LeetCode_3160_FindTheNumberOfDistinctColorsAmongTheBalls()
                , new LeetCode_386_LexicographicalNumbers()
            , new LeetCode_797_AllPathsFromSourceToTarget() // Find all paths from source to target in a graph. // 查找图中从源节点到目标节点的所有路径。
            , new LeetCode_380_InsertDeleteGetrandomO1() // Design a data structure that supports insert, delete, and get random element in constant time. // 设计一个支持插入、删除和随机获取元素的数据结构。
            , new LeetCode_19_RemoveNthNodeFromEndOfList() // Remove the N-th node from the end of a linked list. // 删除链表中的倒数第 N 个节点。
            , new LeetCode_146_LruCache() // Design and implement a Least Recently Used (LRU) cache. // 设计并实现一个最近最少使用（LRU）的缓存机制。
            , new LeetCode_33_SearchInRotatedSortedArray() // Search for a target value in a rotated sorted array. // 在旋转排序数组中查找目标值。
                //           ?? 不是=k 而是>=2
            , new LeetCode_1209_RemoveAllAdjacentDuplicatesInStringIi() // Remove all adjacent duplicates in a string where duplicates are repeated at least K times. // 删除字符串中所有相邻的重复项，其中重复项至少连续出现 K 次。
            , new LeetCode_200_NumberOfIslands() // Count the number of islands in a 2D grid. // 计算二维网格中的岛屿数量。
            , new LeetCode_443_StringCompression() // Compress a string by replacing repeated characters with the character followed by its count. // 通过将重复字符替换为该字符及其出现次数来压缩字符串。
            , new MyLinkedList() // Implement an iterator to flatten a nested list of integers. // 实现一个迭代器，用于展平成嵌套的整数列表。
            , new LeetCode_430_FlattenAMultilevelDoublyLinkedList() // Flatten a multilevel doubly linked list. Time complexity question, follow-up with similar problem maintaining sorted order. // 展平多级双向链表。时间复杂度问题，跟进类似问题保持递增排序。
            , new LeetCode_445_AddTwoNumbersIi() // Add two numbers represented as linked lists in reverse order. // 以链表形式表示的两个数字相加，链表表示的数字是反向的。
            , new LeetCode_139_WordBreak() // Determine if a string can be segmented into words from a dictionary. // 判断字符串是否可以被分割为字典中的单词。
            , new LeetCode_155_MinStack() // Design a stack that supports push, pop, top, and retrieving the minimum element in constant time. // 设计一个支持常数时间内获取最小元素的栈。
            , new LeetCode_116_PopulatingNextRightPointersInEachNode() // Connect the next right pointers for each node in a perfect binary tree. // 在完美二叉树中连接每个节点的右侧指针。
            , new LeetCode_1249_MinimumRemoveToMakeValidParentheses() // Remove the minimum number of parentheses to make the string valid. // 移除最少数量的括号，使字符串有效。
            , new LeetCode_380_InsertDeleteGetrandomO1() // Design a data structure that supports insert, delete, and get random element in constant time. // 设计一个支持插入、删除和随机获取元素的数据结构。
            , new LeetCode_112_PathSum() // Determine if there is a root-to-leaf path with a given sum in a binary tree. // 判断二叉树中是否存在根到叶子节点的路径，其路径和等于给定值。
            , new LeetCode_113_PathSumIi() // Find all root-to-leaf paths with a given sum in a binary tree. // 找到二叉树中路径和等于给定值的所有路径。
            , new LeetCode_437_PathSumIii() // Count the number of paths that sum to a given value in a binary tree. // 计算二叉树中路径和等于给定值的路径数量。
            , new LeetCode_146_LruCache() // Design and implement a Least Recently Used (LRU) cache. // 设计并实现一个最近最少使用（LRU）的缓存机制。
            , new LeetCode_797_AllPathsFromSourceToTarget() // Find all paths from source to target in a graph. // 查找图中从源节点到目标节点的所有路径。
            , new LeetCode_387_FirstUniqueCharacterInAString() // Find the first non-repeating character in a string. // 找到字符串中第一个不重复的字符。
            , new LeetCode_103_BinaryTreeZigzagLevelOrderTraversal() // Perform a zigzag level order traversal on a binary tree. // 对二叉树执行之字形层次遍历。
            , new LeetCode_1472_DesignBrowserHistory()
                , new LeetCode_723_CandyCrush()
                , new LeetCode_1583_CountUnhappyFriends()
                , new LeetCode_560_SubarraySumEqualsK()
                , new LeetCode_62_UniquePaths()
                , new LeetCode_1352_ProductOfTheLastKNumbers()
                , new LeetCode_1910_RemoveAllOccurrencesOfASubstring()

            , new LeetCode_399_EvaluateDivision()
            , new LeetCode_314_BinaryTreeVerticalOrderTraversal() // Perform a vertical order traversal on a binary tree. // 对二叉树执行垂直顺序遍历。
            , new LeetCode_1249_MinimumRemoveToMakeValidParentheses() // Remove the minimum number of parentheses to make the string valid. // 移除最少数量的括号，使字符串有效。
            , new LeetCode_39_CombinationSum() // Candidate can be used unlimited times, optimize memorization. // 候选元素可以无限使用，优化记忆化
            , new LeetCode_40_CombinationSumIi() // Candidate can be used at most once. // 候选元素最多使用一次
            , new LeetCode_322_CoinChange() // Return the minimum number of coins needed for change, with unlimited coins. Follow-up if there are many coin types. // 返回所需的最小硬币数量，使用无限硬币。跟进如果硬币种类非常多
            , new LeetCode_518_CoinChangeIi() // Return all possible combinations of coins to make the amount. // 返回所有可能的硬币组合以达到目标金额
            , new LeetCode_1396_DesignUndergroundSystem() // Design an underground system with check-ins and check-outs, handling edge cases like checking in without checking out. // 设计一个地下系统，包括进站和出站，处理如仅进站未出站等边界情况
            , new LeetCode_656_CoinPath() // Find the minimum cost path using coins, similar to LeetCode_322. // 找到使用硬币的最小成本路径，类似于 LeetCode_322
            , new LeetCode_8_StringToIntegerAtoi() // Convert a string to an integer with edge cases. // 将字符串转换为整数，处理边界情况
            , new LeetCode_91_DecodeWays() // Decode a string with encoding rules. // 解码字符串，按编码规则解码
            , new LeetCode_2423_RemoveLetterToEqualizeFrequency()
            , new LeetCode_1656_DesignAnOrderedStream() // Design an ordered stream data structure. // 设计一个有序流数据结构
            , new LeetCode_450_DeleteNodeInABst() // Delete a node in a Binary Search Tree. // 在二叉搜索树中删除节点
            , new LeetCode_428_SerializeAndDeserializeNAryTree() // Serialize and deserialize an N-ary tree. // 序列化和反序列化 N 叉树
            , new LeetCode_1029_TwoCityScheduling() // Schedule people to two cities to minimize the total cost. // 将人安排到两个城市中，以最小化总成本
            , new LeetCode_1244_DesignALeaderboard() // Design a leaderboard for stock transactions, sort by transaction volume. // 设计一个股票交易的排行榜，按交易量排序
            , new LeetCode_125_ValidPalindrome() // Determine if a string is a valid palindrome, ignoring non-alphanumeric characters. // 判断字符串是否为有效回文，忽略非字母数字字符
            , new LeetCode_394_DecodeString() // Decode a string with encoded patterns (e.g., "3[a2[c]]"). // 解码带有编码模式的字符串（例如，"3[a2[c]]"）
            , new LeetCode_79_WordSearch() // Search for a word in a 2D board. // 在二维板中搜索单词
            , new LeetCode_134_GasStation() // Find the starting gas station to complete the circuit. // 找到可以完成环路的起始加油站
            , new LeetCode_126_WordLadderIi() // Return all shortest transformation sequences from start to end. // 返回从起始到结束的所有最短转换序列
            , new LeetCode_127_WordLadder() // Find the length of the shortest transformation sequence. // 查找最短转换序列的长度
            , new LeetCode_118_PascalsTriangle() // Generate Pascal's triangle up to the specified number of rows. // 生成指定行数的帕斯卡三角形
            , new LeetCode_119_PascalsTriangleIi() // Return the k-th row of Pascal's triangle. // 返回帕斯卡三角形的第 k 行
            , new LeetCode_1614_MaximumNestingDepthOfTheParentheses() // Find the maximum depth of nested parentheses. // 找到嵌套括号的最大深度
            , new LeetCode_381_InsertDeleteGetrandomO1DuplicatesAllowed() // Design a data structure that allows duplicates and supports insert, delete, and get random element in constant time. // 设计一个允许重复的支持插入、删除和随机获取元素的数据结构
            , new LeetCode_78_Subsets() // Generate all possible subsets of a set. // 生成一个集合的所有可能子集
            , new LeetCode_53_MaximumSubarray()
            , new LeetCode_430_FlattenAMultilevelDoublyLinkedList() // Flatten a multilevel doubly linked list. // 展平多级双向链表
            , new LeetCode_1169_InvalidTransactions() // Find invalid transactions based on a list of transactions sorted by time. // 根据按时间排序的交易列表找出无效交易
            , new LeetCode_1614_MaximumNestingDepthOfTheParentheses() // Find the maximum depth of nested parentheses using BFS, follow-up with DFS. // 使用 BFS 查找嵌套括号的最大深度，后续使用 DFS
            , new LeetCode_117_PopulatingNextRightPointersInEachNodeIi()
            , new LeetCode_642_DesignSearchAutocompleteSystem() // Design an autocomplete system that suggests completions based on input. // 设计一个自动完成系统，根据输入建议补全
            , new LeetCode_138_CopyListWithRandomPointer() // Copy a linked list with random pointers. // 复制带有随机指针的链表
            , new LeetCode_987_VerticalOrderTraversalOfABinaryTree() // Traverse a binary tree in vertical order. // 以垂直顺序遍历二叉树
            , new LeetCode_239_SlidingWindowMaximum() // Find the maximum value in a sliding window of size k. // 在大小为 k 的滑动窗口中找出最大值
            , new LeetCode_20_ValidParentheses() // Determine if a string of parentheses is valid. // 判断括号字符串是否有效
            , new LeetCode_253_MeetingRoomsIi() // Find the minimum number of meeting rooms required. // 查找所需的最小会议室数量
            , new LeetCode_1274_NumberOfShipsInARectangle() // Count the number of ships in a rectangular grid using binary search. // 使用二分查找计算矩形网格中的船只数量
            , new LeetCode_3_LongestSubstringWithoutRepeatingCharacters() // Find the length of the longest substring without repeating characters. // 查找最长无重复字符的子串长度
            , new LeetCode_139_WordBreak() // Determine if a string can be segmented into words from a dictionary. // 判断字符串是否可以被分割为字典中的单词
            , new LeetCode_140_WordBreakIi() // Return all possible sentences that can be formed by segmenting the string into words from a dictionary. // 返回所有可以将字符串分割为字典中单词的句子
            , new LeetCode_98_ValidateBinarySearchTree() // Validate if a binary tree is a valid binary search tree. // 验证二叉树是否是有效的二叉搜索树
            , new LeetCode_301_RemoveInvalidParentheses() // Remove invalid parentheses to make a valid string. // 移除无效括号以生成有效字符串
            , new LeetCode_611_ValidTriangleNumber() // Count the number of valid triangles that can be formed from an array of side lengths. // 计算可以由边长数组形成的有效三角形的数量
            , new LeetCode_1091_ShortestPathInBinaryMatrix() // Find the shortest path in a binary matrix. Follow-up with constraints like limited fuel in a desert. // 查找二进制矩阵中的最短路径。跟进如沙漠中有限燃料等约束
            , new LeetCode_33_SearchInRotatedSortedArray() // Search for a target value in a rotated sorted array. // 在旋转排序数组中查找目标值
        );

    }
}
