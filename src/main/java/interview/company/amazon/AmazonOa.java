package interview.company.amazon;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AmazonOa {
    public static List<Integer> optimizeCoins(int[] coins) {
        List<Integer> coinList = new ArrayList<>();
        for (int coin : coins) {
            coinList.add(coin);
        }

        while (true) {
            // 1. 计算每种面额的数量
            Map<Integer, List<Integer>> positionMap = new TreeMap<>();
            for (int i = 0; i < coinList.size(); i++) {
                positionMap.computeIfAbsent(coinList.get(i), k -> new ArrayList<>()).add(i + 1); // 1-based index
            }

            // 2. 过滤掉数量少于 2 的面额
            int minValue = -1;
            for (Map.Entry<Integer, List<Integer>> entry : positionMap.entrySet()) {
                if (entry.getValue().size() >= 2) {
                    minValue = entry.getKey();
                    break;
                }
            }

            // 如果没有符合条件的面额，停止
            if (minValue == -1) break;

            // 3. 选择最左边的两个相同面额的位置
            List<Integer> positions = positionMap.get(minValue);
            int firstIndex = positions.get(0) - 1; // 转换回 0-based 索引
            int secondIndex = positions.get(1) - 1;

            // 4. 计算新的面额 y = 2 * x
            int newValue = 2 * minValue;

            // 5. 删除这两个硬币，并在 secondIndex 位置放入新硬币
            coinList.remove(firstIndex); // 先移除前面的元素
            coinList.remove(secondIndex - 1); // 由于前一个元素删除，索引要向前偏移
            coinList.add(secondIndex - 1, newValue); // 在 secondIndex-1 位置插入新硬币
        }

        return coinList;
    }

    public static void main(String[] args) {


        int[] coins = {3, 4, 1, 2, 2, 1, 1}; // 1-based index
        List<Integer> result = optimizeCoins(coins);
        System.out.println("最终硬币序列：" + result); // 预期输出：[3, 8, 2, 1]

    }
}
