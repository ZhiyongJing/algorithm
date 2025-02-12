package interview.company.bloomberg;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class TradeProcessor {
    // 1. 用于累计各个 ticker 的交易量
    ConcurrentHashMap<String, AtomicLong> tickerTotals = new ConcurrentHashMap<>();

    // 2. 用于维护 Top-K 的最小堆（这里假设 k 是固定的）
    final int K = 2; // 假设取 Top 10
    // 堆中元素：Pair(String ticker, Long totalAmount)
    PriorityQueue<Pair<String, Long>> topKHeap = new PriorityQueue<>(Comparator.comparingLong(Pair::getValue));

    // 方法：处理每笔交易
    public void processTrade(String ticker, long quantity) {
        // 实时处理交易
//        execute_trade(ticker, quantity);

        // 更新累计交易量
        tickerTotals.compute(ticker, (key, currentTotal) -> {
            if (currentTotal == null) {
                return new AtomicLong(quantity);
            } else {
                currentTotal.addAndGet(quantity);
                return currentTotal;
            }
        });
        long newTotal = tickerTotals.get(ticker).get();

        // 更新 Top-K 数据结构
        updateTopK(ticker, newTotal);
    }

    // 更新 Top-K 的方法（需要注意并发问题，此处简化为单线程逻辑）
    private synchronized void updateTopK(String ticker, long newTotal) {
        // 先判断该 ticker 是否已在堆中
        Pair<String, Long> existing = null;
        for (Pair<String, Long> pair : topKHeap) {
            if (pair.getKey().equals(ticker)) {
                existing = pair;
                break;
            }
        }
        if (existing != null) {
            // 移除原有的记录，并插入新的记录（因为 total 已经更新）
            topKHeap.remove(existing);
            topKHeap.offer(new Pair<>(ticker, newTotal));
        } else {
            // 不在堆中，考虑是否加入
            if (topKHeap.size() < K) {
                topKHeap.offer(new Pair<>(ticker, newTotal));
            } else {
                // 堆已满，比较新总量与堆顶（最小）的累计量
                Pair<String, Long> minPair = topKHeap.peek();
                if (minPair != null && newTotal > minPair.getValue()) {
                    topKHeap.poll();
                    topKHeap.offer(new Pair<>(ticker, newTotal));
                }
            }
        }
    }

    // 方法：获取当前 Top-K（注意，此时堆中顺序并非从大到小，可以先排序）
    public synchronized List<Pair<String, Long>> getTopK() {
        List<Pair<String, Long>> result = new ArrayList<>(topKHeap);
        result.sort((p1, p2) -> Long.compare(p2.getValue(), p1.getValue())); // 降序排列
        return result;
    }

        // 测试代码
        public static void main(String[] args) {
            TradeProcessor tp = new TradeProcessor();
            tp.processTrade("MSFT", 900);
            tp.processTrade("APPL", 300);
            tp.processTrade("GOOG", 1000);
            tp.processTrade("APPL", 400);
            tp.processTrade("META", 200);
            tp.processTrade("BA", 500);
            System.out.println( tp.getTopK());

            // 预期输出：GOOG|1000, MSFT|900, APPL|700,
        }
    }


