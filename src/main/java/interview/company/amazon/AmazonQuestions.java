package interview.company.amazon;

import leetcode.question.bfs.LeetCode_207_CourseSchedule;
import leetcode.question.bfs.LeetCode_210_CourseScheduleIi;
import leetcode.question.bfs.LeetCode_269_AlienDictionary;
import leetcode.question.bfs.LeetCode_314_BinaryTreeVerticalOrderTraversal;
import leetcode.question.bfs.LeetCode_317_ShortestDistanceFromAllBuildings;
import leetcode.question.bfs.LeetCode_323_NumberOfConnectedComponentsInAnUndirectedGraph;
import leetcode.question.bfs.LeetCode_787_CheapestFlightsWithinKStops;
import leetcode.question.bfs.LeetCode_994_RottingOranges;
import leetcode.question.binary_search.LeetCode_528_RandomPickWithWeight;
import leetcode.question.dfs.LeetCode_112_PathSum;
import leetcode.question.dfs.LeetCode_199_BinaryTreeRightSideView;
import leetcode.question.dfs.LeetCode_200_NumberOfIslands;
import leetcode.question.dfs.LeetCode_235_LowestCommonAncestorOfABinarySearchTree;
import leetcode.question.dfs.LeetCode_285_InorderSuccessorInBst;
import leetcode.question.dfs.LeetCode_399_EvaluateDivision;
import leetcode.question.dfs.LeetCode_653_TwoSumIvInputIsABst;
import leetcode.question.dfs.LeetCode_863_AllNodesDistanceKInBinaryTree;
import leetcode.question.dfs.LeetCode_889_ConstructBinaryTreeFromPreorderAndPostorderTraversal;
import leetcode.question.dfs.LeetCode_987_VerticalOrderTraversalOfABinaryTree;
import leetcode.question.dp.LeetCode_1125_SmallestSufficientTeam;
import leetcode.question.dp.LeetCode_518_CoinChangeIi;
import leetcode.question.greedy.LeetCode_135_Candy;
import leetcode.question.heap.LeetCode_23_MergeKSortedLists;
import leetcode.question.heap.LeetCode_253_MeetingRoomsIi;
import leetcode.question.heap.LeetCode_295_FindMedianFromDataStream;
import leetcode.question.heap.LeetCode_347_TopKFrequentElements;
import leetcode.question.heap.LeetCode_767_ReorganizeString;
import leetcode.question.heap.LeetCode_973_KClosestPointsToOrigin;
import leetcode.question.map_set.LeetCode_1152_AnalyzeUserWebsiteVisitPattern;
import leetcode.question.map_set.LeetCode_128_LongestConsecutiveSequence;
import leetcode.question.map_set.LeetCode_12_IntegerToRoman;
import leetcode.question.map_set.LeetCode_138_CopyListWithRandomPointer;
import leetcode.question.map_set.LeetCode_146_LruCache;
import leetcode.question.map_set.LeetCode_36_ValidSudoku;
import leetcode.question.map_set.LeetCode_380_InsertDeleteGetrandomO1;
import leetcode.question.map_set.LeetCode_381_InsertDeleteGetrandomO1DuplicatesAllowed;
import leetcode.question.map_set.LeetCode_535_EncodeAndDecodeTinyurl;
import leetcode.question.map_set.LeetCode_709_ToLowerCase;
import leetcode.question.map_set.LeetCode_939_MinimumAreaRectangle;
import leetcode.question.sort.LeetCode_56_MergeIntervals;
import leetcode.question.stack.LeetCode_1249_MinimumRemoveToMakeValidParentheses;
import leetcode.question.stack.LeetCode_20_ValidParentheses;
import leetcode.question.stack.LeetCode_735_AsteroidCollision;
import leetcode.question.string_list.LeetCode_214_ShortestPalindrome;
import leetcode.question.string_list.LeetCode_273_IntegerToEnglishWords;
import leetcode.question.string_list.LeetCode_415_AddStrings;
import leetcode.question.string_list.LeetCode_50_PowxN;
import leetcode.question.string_list.LeetCode_65_ValidNumber;
import leetcode.question.string_list.LeetCode_937_ReorderDataInLogFiles;
import leetcode.question.trie.LeetCode_1268_SearchSuggestionsSystem;
import leetcode.question.two_pointer.LeetCode_15_ThreeSum;
import leetcode.question.two_pointer.LeetCode_16_ThreeSumClosest;
import leetcode.question.two_pointer.LeetCode_409_LongestPalindrome;

import java.util.Arrays;
import java.util.List;

public class AmazonQuestions {

    List<Object> questions = Arrays.asList(
            new LeetCode_12_IntegerToRoman()
            , new LeetCode_15_ThreeSum()
            , new LeetCode_16_ThreeSumClosest()
            , new LeetCode_20_ValidParentheses()
            , new LeetCode_23_MergeKSortedLists()
            , new LeetCode_36_ValidSudoku()
            , new LeetCode_50_PowxN()
            , new LeetCode_56_MergeIntervals()
            , new LeetCode_65_ValidNumber()
            , new LeetCode_112_PathSum()
            , new LeetCode_128_LongestConsecutiveSequence()
            , new LeetCode_135_Candy()
//            , new LeetCode_136_SingleNumber()
            , new LeetCode_138_CopyListWithRandomPointer()
            , new interview.company.amazon.LeetCode_140_WordBreakIi()
            , new LeetCode_146_LruCache()
            , new LeetCode_199_BinaryTreeRightSideView()
            , new LeetCode_200_NumberOfIslands()
            , new LeetCode_207_CourseSchedule()
            , new LeetCode_210_CourseScheduleIi()
            , new LeetCode_214_ShortestPalindrome()
            , new LeetCode_235_LowestCommonAncestorOfABinarySearchTree()
            , new LeetCode_253_MeetingRoomsIi()
            , new LeetCode_269_AlienDictionary()
            , new LeetCode_273_IntegerToEnglishWords()
            , new LeetCode_285_InorderSuccessorInBst()
            , new LeetCode_295_FindMedianFromDataStream()
            , new LeetCode_314_BinaryTreeVerticalOrderTraversal()
            , new LeetCode_317_ShortestDistanceFromAllBuildings()
            , new LeetCode_323_NumberOfConnectedComponentsInAnUndirectedGraph()
            , new LeetCode_347_TopKFrequentElements()
            , new LeetCode_380_InsertDeleteGetrandomO1()
            , new LeetCode_381_InsertDeleteGetrandomO1DuplicatesAllowed()
            , new LeetCode_399_EvaluateDivision()
            , new LeetCode_409_LongestPalindrome()
            , new LeetCode_415_AddStrings()
            , new LeetCode_518_CoinChangeIi()
            , new LeetCode_528_RandomPickWithWeight()
            , new LeetCode_535_EncodeAndDecodeTinyurl()
            , new LeetCode_653_TwoSumIvInputIsABst()
            , new LeetCode_709_ToLowerCase()
            , new LeetCode_735_AsteroidCollision()
            , new LeetCode_767_ReorganizeString()
            , new LeetCode_787_CheapestFlightsWithinKStops()
            , new LeetCode_863_AllNodesDistanceKInBinaryTree()
            , new LeetCode_889_ConstructBinaryTreeFromPreorderAndPostorderTraversal()
            , new LeetCode_937_ReorderDataInLogFiles()
            , new LeetCode_939_MinimumAreaRectangle()
            , new interview.company.dfs.LeetCode_949_LargestTimeForGivenDigits()
            , new LeetCode_973_KClosestPointsToOrigin()
            , new LeetCode_987_VerticalOrderTraversalOfABinaryTree()
            , new LeetCode_994_RottingOranges()
            , new LeetCode_1125_SmallestSufficientTeam()
            , new LeetCode_1152_AnalyzeUserWebsiteVisitPattern()
            , new LeetCode_1249_MinimumRemoveToMakeValidParentheses()
            , new LeetCode_1268_SearchSuggestionsSystem()
    );

}
