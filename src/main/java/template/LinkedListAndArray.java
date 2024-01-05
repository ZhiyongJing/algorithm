package template;

class Node {
    int val;
    Node next;
    Node(int val) {
        this.val = val;
        next = null;
    }
}

public class LinkedListAndArray {

    public static Node reverseLinkedList(Node head) {
        Node prev = null;
        Node current = head;
        while(current != null){
            Node temp = current.next;
            current.next = prev;
            prev = current;
            current = temp;
        }
        return prev;
    }
    public static Node reverseLinkedListInKgroup(Node head, int k){
        Node dummy = new Node(-1);
        dummy.next = head;
        Node prev = dummy;
        while(prev != null){
            prev = reverseLinkedListInKgroupHelp(prev, k);

        }
        return dummy.next;
    }
    public  static Node reverseLinkedListInKgroupHelp(Node prev, int k){
        if (k <= 0 || prev == null) {
            return null;
        }
        Node nodek = prev;
        Node node1 = prev.next;
        for(int i = 0; i< k; i++){
             if (nodek == null){
                 return null;
             }
             nodek = nodek.next;
        }
        if (nodek == null){
            return null;
        }
        Node nodekNext = nodek.next;
        Node preCur = prev;
        Node cur = prev.next;
        for (int i = 0; i < k; i++){
            Node temp = cur.next;
            cur.next = preCur;
            preCur = cur;
            cur = temp;
        }
        node1.next = nodekNext;
        prev.next = nodek;
        return node1;
    }

    public static Node partitionLinkedList(Node head, int k){
        if (head == null){
            return null;
        }
        Node leftDummy = new Node(0);
        Node rightDummy = new Node(0);

        Node left = leftDummy, right = rightDummy;
        while (head != null){
            if (head.val <= k){
                left.next = head;
                left = head;
            }else{
                right.next = head;
                right = head;
            }

            head = head.next;
        }
        right.next = null;
        left.next = rightDummy.next;
        return leftDummy.next;
    }

    public static Node mergeTwoSortedLinkedList(Node l1, Node l2){
        Node dummy = new Node(-1);
        Node tail = dummy;
        while(l1 != null && l2 != null){
            if (l1.val < l2.val){
                tail.next = l1;
                l1 = l1.next;
            }else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }
        if (l1 != null){
            tail.next = l1;
        }else {
            tail.next = l2;
        }

        return dummy.next;
    }
    public static Node sortLinkedList (Node head){
        if (head == null || head.next == null){
            return head;
        }
        Node middle = findMiddleNode(head);
        Node right = sortLinkedList(middle.next);
        middle.next = null;
        Node left = sortLinkedList(head);
        return mergeTwoSortedLinkedList(left, right);
    }
    private static Node findMiddleNode(Node head){
        Node slow = head, fast = head.next;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;

    }


    public static Node LinkedListCycleStartNode(Node head){
        if (head == null || head.next == null){
            return null;
        }
        Node fast,slow;
        fast = head.next;
        slow = head;
        while (fast != slow) {
            if (fast == null || fast.next == null){
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        while(head != slow.next){
            head = head.next;
            slow = slow.next;
        }
        return head;
    }


    public static void main(String[] args) {
        int[] nums1 = {1, 200, 100};
        int[] nums2 = {6, 7, 8};
        Node head1 = new Node(0);
        Node temp = head1;
        for(int i : nums1){
            temp.next = new Node(i);
            temp = temp.next;
        }
        temp = head1;
//        while(temp != null){
//            System.out.println(temp.val);
//            temp = temp.next;
//        }
        Node head2 = new Node(5);
        temp = head2;
        for(int i : nums2){
            temp.next = new Node(i);
            temp = temp.next;
        }
        temp = head2;
//        while(temp != null){
//            System.out.println(temp.val);
//            temp = temp.next;
//        }
//////////////////////////////////////////////////////////////////
//        check method
//        temp = findMiddleNode(head1);
//        temp = mergeTwoSortedLinkedList(head1, head2);
        temp = sortLinkedList(head1);

//        temp = reverseLinkedList(head1);
//        temp = reverseLinkedListInKgroup(head1, 2);

//        temp = partitionLinkedList(head1, 2);




        while(temp != null){
            System.out.println(temp.val);
            temp = temp.next;
        }
    }
}
