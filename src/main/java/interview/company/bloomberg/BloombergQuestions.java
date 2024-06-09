package interview.company.bloomberg;

// Interview questions

import leetcode.question.dfs.LeetCode_39_CombinationSum;
import leetcode.question.dp.LeetCode_322_CoinChange;
import leetcode.question.dp.LeetCode_518_CoinChangeIi;
import leetcode.question.linked_list.LeetCode_430_FlattenAMultilevelDoublyLinkedList;

import java.util.ArrayList;
import java.util.List;
public class BloombergQuestions {
    public static void main(String[] args) {
        List<Object> questions = new ArrayList<>();
        questions.add(new LeetCode_39_CombinationSum());
        questions.add(new LeetCode_518_CoinChangeIi());
        questions.add(new LeetCode_322_CoinChange());
        questions.add(new LeetCode_518_CoinChangeIi());
        questions.add(new LeetCode_430_FlattenAMultilevelDoublyLinkedList());

    }
}
