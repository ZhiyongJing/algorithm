package template;
import leetcode.util.ListNode;


public class LinkedListAndArray {

    public static ListNode reverseLinkedList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        while(current != null){
            ListNode temp = current.next;
            current.next = prev;
            prev = current;
            current = temp;
        }
        return prev;
    }
    public static ListNode reverseLinkedListInKgroup(ListNode head, int k){
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;
        while(prev != null){
            prev = reverseLinkedListInKgroupHelp(prev, k);

        }
        return dummy.next;
    }
    public  static ListNode reverseLinkedListInKgroupHelp(ListNode prev, int k){
        if (k <= 0 || prev == null) {
            return null;
        }
        ListNode ListNodek = prev;
        ListNode ListNode1 = prev.next;
        for(int i = 0; i< k; i++){
             if (ListNodek == null){
                 return null;
             }
             ListNodek = ListNodek.next;
        }
        if (ListNodek == null){
            return null;
        }
        ListNode ListNodekNext = ListNodek.next;
        ListNode preCur = prev;
        ListNode cur = prev.next;
        for (int i = 0; i < k; i++){
            ListNode temp = cur.next;
            cur.next = preCur;
            preCur = cur;
            cur = temp;
        }
        ListNode1.next = ListNodekNext;
        prev.next = ListNodek;
        return ListNode1;
    }

    public static ListNode partitionLinkedList(ListNode head, int k){
        if (head == null){
            return null;
        }
        ListNode leftDummy = new ListNode(0);
        ListNode rightDummy = new ListNode(0);

        ListNode left = leftDummy, right = rightDummy;
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

    public static ListNode mergeTwoSortedLinkedList(ListNode l1, ListNode l2){
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
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
    public static ListNode sortLinkedList (ListNode head){
        if (head == null || head.next == null){
            return head;
        }
        ListNode middle = findMiddleListNode(head);
        ListNode right = sortLinkedList(middle.next);
        middle.next = null;
        ListNode left = sortLinkedList(head);
        return mergeTwoSortedLinkedList(left, right);
    }
    private static ListNode findMiddleListNode(ListNode head){
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;

    }


    public static ListNode LinkedListCycleStartListNode(ListNode head){
        if (head == null || head.next == null){
            return null;
        }
        ListNode fast,slow;
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
        ListNode head1 = new ListNode(0);
        ListNode temp = head1;
        for(int i : nums1){
            temp.next = new ListNode(i);
            temp = temp.next;
        }
        temp = head1;
//        while(temp != null){
//            System.out.println(temp.val);
//            temp = temp.next;
//        }
        ListNode head2 = new ListNode(5);
        temp = head2;
        for(int i : nums2){
            temp.next = new ListNode(i);
            temp = temp.next;
        }
        temp = head2;
//        while(temp != null){
//            System.out.println(temp.val);
//            temp = temp.next;
//        }
//////////////////////////////////////////////////////////////////
//        check method
//        temp = findMiddleListNode(head1);
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
