package leetcode.done;

public class LeetCode230_Kth_Smallest_Element_In_BST {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    //    class Solution {
//        public ArrayList<Integer> inorder(TreeNode root, ArrayList<Integer> arr) {
//            if (root == null) return arr;
//            inorder(root.left, arr);
//            arr.add(root.val);
//            inorder(root.right, arr);
//            return arr;
//        }
//
//        public int kthSmallest(TreeNode root, int k) {
//            ArrayList<Integer> nums = inorder(root, new ArrayList<Integer>());
//            return nums.get(k - 1);
//        }
//    }
//class Solution {
//    public int kthSmallest(TreeNode root, int k) {
//        LinkedList<TreeNode> stack = new LinkedList<>();
//
//        while (true) {
//            while (root != null) {
//                stack.push(root);
//                root = root.left;
//            }
//            root = stack.pop();
//            if (--k == 0) return root.val;
//            root = root.right;
//        }
//    }
//}
    class Solution {
        int answer;
        int cur;

        public int kthSmallest(TreeNode root, int k) {
            inorder(root, k);
            return answer;

        }

        private void inorder(TreeNode root, int k) {
            if (root == null) return;
            inorder(root.left, k);
            cur++;
            if (cur == k) {
                answer = root.val;
                return;
            }
            inorder(root.right, k);
        }


    }
}
