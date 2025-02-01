package interview.company.bloomberg;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FlattenAndSortList {
    class Node {
        int val;
        Node next;
        Node child; // 多级链表中的子链指针

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.child = null;
        }
    }
    /**
     * 将多级链表展开，并保持递增排序
     * @param head 原始多级链表的头节点
     * @return 处理后的有序链表头节点
     */
    public Node flattenAndSort(Node head) {
        if (head == null) return null;

        List<Node> nodeList = new ArrayList<>();
        flatten(head, nodeList); // 展开链表结构

        // 按值排序（O(N log N)）
        nodeList.sort(Comparator.comparingInt(n -> n.val));

        // 重新构建有序链表
        Node dummy = new Node(0);
        Node current = dummy;
        for (Node node : nodeList) {
            node.next = null; // 清除旧的 next 指针
            node.child = null; // 清除 child 指针
            current.next = node;
            current = node;
        }
        return dummy.next;
    }

    /**
     * 递归展开链表，将所有节点存入列表
     */
    private static void flatten(Node node, List<Node> nodeList) {
        while (node != null) {
            nodeList.add(node);
            if (node.child != null) {
                flatten(node.child, nodeList); // 递归展开 child 链表
            }
            node = node.next;
        }
    }

    /**
     * 测试代码
     */
    public static void main(String[] args) {
        /**
         * dummy
         * |
         * [1] → [2] → [3] → [8] → [10]
         *        |
         *       [9]
         * |
         * [4] → [5] → [6]
         * |
         * [7]
         */
        // 构造测试用例
        FlattenAndSortList flattenAndSortList = new FlattenAndSortList();
        Node head = flattenAndSortList.new Node(1);
        head.next = flattenAndSortList.new Node(2);
        head.next.next = flattenAndSortList.new Node(3);
        head.next.next.next = flattenAndSortList.new Node(8);
        head.next.next.next.next = flattenAndSortList.new Node(10);
        head.next.child = flattenAndSortList.new Node(9);
        head.next.next.next.next.next = flattenAndSortList.new Node(4);
        head.next.next.next.next.next.next = flattenAndSortList.new Node(5);
        head.next.next.next.next.next.next.next = flattenAndSortList.new Node(6);
        head.next.next.next.next.next.next.next.next = flattenAndSortList.new Node(7);

        printList(head);
        // 处理链表
        Node sortedHead = flattenAndSortList.flattenAndSort(head);

        // 打印结果
        printList(sortedHead);
    }

    private static void printList(Node head) {
        while (head != null) {
            System.out.print("[" + head.val + "] -> ");
            head = head.next;
        }
        System.out.println("NULL");
    }
}
