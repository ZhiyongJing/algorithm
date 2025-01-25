package interview.company.bloomberg;

class MyLinkedList {
    static class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
            this.next = null;
        }
    }

    private Node head;  // 链表头部
    private Node iterator; // 迭代指针

    public MyLinkedList() {
        this.head = null;
        this.iterator = null;
    }

    // 添加新节点到链表末尾
    public void add(int val) {
        Node newNode = new Node(val);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    // 获取下一个元素，迭代指针前进
    public Integer next() {
        if (iterator == null) {
            return null; // 如果迭代器未初始化，则返回 null
        }
        int value = iterator.val;
        iterator = iterator.next; // 迭代器移动到下一个节点
        return value;
    }

    // 判断是否还有下一个元素
    public boolean hasNext() {
        return iterator != null;
    }

    // 重置迭代指针到链表头部
    public void reset() {
        iterator = head;
    }

    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
        list.add(1);
        list.add(2);
        list.add(3);

        list.reset(); // 初始化迭代器

        while (list.hasNext()) {
            System.out.println(list.next()); // 依次输出 1, 2, 3
        }

        list.reset(); // 重置迭代器
        System.out.println("After reset: " + list.next()); // 输出 1
        System.out.println("After reset: " + list.next()); // 输出 2
    }
}

