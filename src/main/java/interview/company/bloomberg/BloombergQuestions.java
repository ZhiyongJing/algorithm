package interview.company.bloomberg;

// Interview questions

import leetcode.question.dfs.LeetCode_39_CombinationSum;
import leetcode.question.dp.LeetCode_322_CoinChange;
import leetcode.question.dp.LeetCode_518_CoinChangeIi;
import leetcode.question.dp.LeetCode_656_CoinPath;
import leetcode.question.linked_list.LeetCode_430_FlattenAMultilevelDoublyLinkedList;
import leetcode.question.map_set.LeetCode_1396_DesignUndergroundSystem;

import java.util.ArrayList;
import java.util.List;
public class BloombergQuestions {
    public static void main(String[] args) {
        List<Object> questions = new ArrayList<>();
        questions.add(new LeetCode_39_CombinationSum());//优化memorization
        questions.add(new LeetCode_322_CoinChange());//返回最小硬币数量的所有组合，无限硬币
        questions.add(new LeetCode_518_CoinChangeIi());//返回所有硬币数量组合
        questions.add(new LeetCode_430_FlattenAMultilevelDoublyLinkedList());//问时间复杂度
        questions.add(new LeetCode_1396_DesignUndergroundSystem());//做完问了一些拓展的问题 比如如果有人只刷进站不刷出站怎么办 反着怎么办
        questions.add(new LeetCode_656_CoinPath());//

    }
}
