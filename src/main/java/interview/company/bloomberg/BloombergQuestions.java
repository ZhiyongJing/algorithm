package interview.company.bloomberg;

// Interview questions

import leetcode.question.dfs.LeetCode_39_CombinationSum;
import leetcode.question.dfs.LeetCode_428_SerializeAndDeserializeNAryTree;
import leetcode.question.dfs.LeetCode_450_DeleteNodeInABst;
import leetcode.question.dp.LeetCode_322_CoinChange;
import leetcode.question.dp.LeetCode_518_CoinChangeIi;
import leetcode.question.dp.LeetCode_656_CoinPath;
import leetcode.question.dp.LeetCode_91_DecodeWays;
import leetcode.question.linked_list.LeetCode_430_FlattenAMultilevelDoublyLinkedList;
import leetcode.question.map_set.LeetCode_1396_DesignUndergroundSystem;
import leetcode.question.map_set.LeetCode_1656_DesignAnOrderedStream;
import leetcode.question.string_list.LeetCode_8_StringToIntegerAtoi;

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
                , new LeetCode_428_SerializeAndDeserializeNAryTree()


        );

    }
}
