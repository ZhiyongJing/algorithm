package interview.company.bloomberg;

public class FlatternLinkedListByOrder {
    class Node {
        int data;
        Node next, bottom;
        Node(int data)
        {
            this.data = data;
            next = null;
            bottom = null;
        }
    }

    // An utility function to merge two sorted linked lists
    Node merge(Node a, Node b)
    {
        // if first linked list is empty then second
        // is the answer
        if (a == null)
            return b;

        // if second linked list is empty then first
        // is the result
        if (b == null)
            return a;

        // compare the data members of the two linked lists
        // and put the larger one in the result
        Node result;

        if (a.data < b.data) {
            result = a;
            result.bottom = merge(a.bottom, b);
        }

        else {
            result = b;
            result.bottom = merge(a, b.bottom);
        }

        result.next = null;
        return result;
    }

    Node flatten(Node root)
    {
        // Base Cases
        if (root == null || root.next == null)
            return root;

        // recur for list on next
        root.next = flatten(root.next);

        // now merge
        root = merge(root, root.next);

        // return the root
        // it will be in turn merged with its left
        return root;
    }

    /* Utility function to insert a node at beginning of the
       linked list */
    Node push(Node head_ref, int data)
    {
        /* 1 & 2: Allocate the Node &
                  Put in the data*/
        Node new_node = new Node(data);

        /* 3. Make next of new Node as head */
        new_node.bottom = head_ref;

        /* 4. Move the head to point to new Node */
        head_ref = new_node;

        /*5. return to link it back */
        return head_ref;
    }
}
