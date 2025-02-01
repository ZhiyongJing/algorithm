package interview.company.bloomberg;

import java.util.*;

public class TradeProcessor {

        private  Map<String, Integer> tradeVolume; // 存储公司名称 -> 总交易量
        private  PriorityQueue<Map.Entry<String, Integer>> minHeap; // 小顶堆维护 top_k
        private int k; // 记录上次 print_topk_company() 的 K 值

        public TradeProcessor() {
            this.tradeVolume = new HashMap<>();
            this.minHeap = new PriorityQueue<Map.Entry<String, Integer>>((p1,    p2)-> {
                return p1.getValue() - p2.getValue();
            }); // 小顶堆
        }

        // 交易处理：实时更新 HashMap
        public void execute_trade(String company, int volume) {
            tradeVolume.put(company, tradeVolume.getOrDefault(company, 0) + volume);
        }

        // 查询 top_k 公司（按交易量排序）
        public void print_topk_company(int top_n) {
            this.k = top_n;
            minHeap.clear(); // 清空堆，重新构造

            // 维护一个大小为 k 的小顶堆
            for (Map.Entry<String, Integer> entry : tradeVolume.entrySet()) {
                minHeap.offer(entry);
                if (minHeap.size() > k) {
                    minHeap.poll(); // 只保留 k 个最大元素
                }
            }

            // 用一个 list 存储最终结果，倒序输出（因为小顶堆顺序是最小的在前）
            List<Map.Entry<String, Integer>> result = new ArrayList<>();
            while (!minHeap.isEmpty()) {
                result.add(minHeap.poll());
            }

            // 逆序输出
            Collections.reverse(result);
            for (Map.Entry<String, Integer> entry : result) {
                System.out.print(entry.getKey() + "|" + entry.getValue() + ", ");
            }
            System.out.println();
        }

        // 测试代码
        public static void main(String[] args) {
            TradeProcessor tp = new TradeProcessor();
            tp.execute_trade("MSFT", 900);
            tp.execute_trade("APPL", 300);
            tp.execute_trade("GOOG", 1000);
            tp.execute_trade("APPL", 400);
            tp.execute_trade("META", 200);
            tp.execute_trade("BA", 500);
            tp.print_topk_company(2);
            // 预期输出：GOOG|1000, MSFT|900, APPL|700,
        }
    }


