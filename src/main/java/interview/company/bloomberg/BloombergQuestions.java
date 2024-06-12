package interview.company.bloomberg;

// Interview questions

import leetcode.question.dfs.LeetCode_126_WordLadderIi;
import leetcode.question.dfs.LeetCode_394_DecodeString;
import leetcode.question.dfs.LeetCode_39_CombinationSum;
import leetcode.question.dfs.LeetCode_428_SerializeAndDeserializeNAryTree;
import leetcode.question.dfs.LeetCode_450_DeleteNodeInABst;
import leetcode.question.dfs.LeetCode_797_AllPathsFromSourceToTarget;
import leetcode.question.dfs.LeetCode_79_WordSearch;
import leetcode.question.dp.LeetCode_118_PascalsTriangle;
import leetcode.question.dp.LeetCode_119_PascalsTriangleIi;
import leetcode.question.dp.LeetCode_322_CoinChange;
import leetcode.question.dp.LeetCode_518_CoinChangeIi;
import leetcode.question.dp.LeetCode_656_CoinPath;
import leetcode.question.dp.LeetCode_91_DecodeWays;
import leetcode.question.greedy.LeetCode_1029_TwoCityScheduling;
import leetcode.question.greedy.LeetCode_134_GasStation;
import leetcode.question.linked_list.LeetCode_430_FlattenAMultilevelDoublyLinkedList;
import leetcode.question.map_set.LeetCode_1244_DesignALeaderboard;
import leetcode.question.map_set.LeetCode_1396_DesignUndergroundSystem;
import leetcode.question.map_set.LeetCode_1656_DesignAnOrderedStream;
import leetcode.question.stack.LeetCode_1614_MaximumNestingDepthOfTheParentheses;
import leetcode.question.string_list.LeetCode_8_StringToIntegerAtoi;
import leetcode.question.two_pointer.LeetCode_125_ValidPalindrome;

import java.util.Arrays;
import java.util.List;
public class BloombergQuestions {
    public static void main(String[] args) {
        List<Object> questions = Arrays.asList(
                new LeetCode_39_CombinationSum()//优化memorization
                , new LeetCode_322_CoinChange()//返回最小硬币数量的所有组合，无限硬币
                , new LeetCode_518_CoinChangeIi()//返回所有硬币数量组合
                , new LeetCode_430_FlattenAMultilevelDoublyLinkedList()//问时间复杂度
                , new LeetCode_1396_DesignUndergroundSystem()//做完问了一些拓展的问题 比如如果有人只刷进站不刷出站怎么办 反着怎么办
                , new LeetCode_656_CoinPath()//结合656
                , new LeetCode_8_StringToIntegerAtoi()//12月初：店面 刷题网芭
                , new LeetCode_91_DecodeWays()//
                , new LeetCode_1656_DesignAnOrderedStream()
                , new LeetCode_450_DeleteNodeInABst()
                , new LeetCode_428_SerializeAndDeserializeNAryTree()//花时间
                , new LeetCode_1029_TwoCityScheduling()
                , new LeetCode_1244_DesignALeaderboard()//input是股票的名字和交易数量，设计一个方程来储存这个input，会不停地被call到；然后设计另一个方程输出所有股票的名字和总交易数量，按交易量从大到小排序这题我用一个priority queu做的，但是不是最优解，估计没有过
                , new LeetCode_797_AllPathsFromSourceToTarget()
                , new LeetCode_125_ValidPalindrome()
                , new LeetCode_394_DecodeString()
                , new LeetCode_79_WordSearch()
                , new LeetCode_134_GasStation()
                , new LeetCode_126_WordLadderIi()
                , new LeetCode_118_PascalsTriangle()
                , new LeetCode_119_PascalsTriangleIi() //算pascal三角形的的第n行第k列的值。就是(n k)
                , new LeetCode_1614_MaximumNestingDepthOfTheParentheses() //打印最深层次括号里的字符串。 没见过。




        );

    }
}
