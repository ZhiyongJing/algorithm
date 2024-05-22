package interview.company.amazon;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class LRUCacheWithExpiredTime {

    class DLinkedNode {
        int key;
        int value;
        long expirationTime;
        DLinkedNode prev;
        DLinkedNode next;

        DLinkedNode(int key, int value, long expirationTime) {
            this.key = key;
            this.value = value;
            this.expirationTime = expirationTime;
        }
    }

    private void addNode(DLinkedNode node) {
        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DLinkedNode node) {
        DLinkedNode prev = node.prev;
        DLinkedNode next = node.next;

        prev.next = next;
        next.prev = prev;
    }

    private void moveToHead(DLinkedNode node) {
        removeNode(node);
        addNode(node);
    }

    private DLinkedNode popTail() {
        DLinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }

    private Map<Integer, DLinkedNode> cache = new HashMap<>();
    private PriorityQueue<DLinkedNode> expirationQueue;
    private int size;
    private int capacity;
    private long defaultTTL;
    private DLinkedNode head, tail;

    public LRUCacheWithExpiredTime(int capacity, long defaultTTL) {
        this.size = 0;
        this.capacity = capacity;
        this.defaultTTL = defaultTTL;

        head = new DLinkedNode(-1, -1, Long.MAX_VALUE);
        tail = new DLinkedNode(-1, -1, Long.MAX_VALUE);

        head.next = tail;
        tail.prev = head;

        expirationQueue = new PriorityQueue<>((a, b) -> Long.compare(a.expirationTime, b.expirationTime));
    }

    private boolean isExpired(DLinkedNode node) {
        return System.currentTimeMillis() > node.expirationTime;
    }

    private void removeExpiredNodes() {
        while (!expirationQueue.isEmpty() && isExpired(expirationQueue.peek())) {
            DLinkedNode expiredNode = expirationQueue.poll();
            if (cache.containsKey(expiredNode.key)) {
                removeNode(expiredNode);
                cache.remove(expiredNode.key);
                size--;
            }
        }
    }

    public int get(int key) {
        removeExpiredNodes();

        DLinkedNode node = cache.get(key);
        if (node == null) return -1;

        moveToHead(node);

        return node.value;
    }

    public void put(int key, int value) {
        removeExpiredNodes();

        DLinkedNode node = cache.get(key);

        if (node == null) {
            long expirationTime = System.currentTimeMillis() + defaultTTL;
            DLinkedNode newNode = new DLinkedNode(key, value, expirationTime);

            cache.put(key, newNode);
            addNode(newNode);
            expirationQueue.offer(newNode);

            size++;

            if (size > capacity) {
                DLinkedNode tail = popTail();
                cache.remove(tail.key);
                size--;
            }
        } else {
            node.value = value;
            node.expirationTime = System.currentTimeMillis() + defaultTTL;
            moveToHead(node);

            expirationQueue.remove(node);
            expirationQueue.offer(node);
        }
    }
}

