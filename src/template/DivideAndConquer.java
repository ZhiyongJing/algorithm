package template;

import java.util.ArrayList;
// 分治法治理二叉树问题时间复杂度 O（二叉树节点个数* 每个节点的计算时间）
// 举例： 二叉树最大深度。 二叉树节点个数为 N，每个节点所用时间为 O（1）， 总时间复杂度为O（N）

public class DivideAndConquer {
    protected static class Node<E> {
        E value;
        Node left;
        Node right;
        private Node(E value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    //Traversal
    public static void preOrderTraversal(Node<Integer> root, ArrayList<Integer> result){
        if (root == null){
            return;
        }
        result.add(root.value);
        preOrderTraversal(root.left, result);
        preOrderTraversal(root.right, result);
    }
    public static void inOrderTraversal(Node<Integer> root, ArrayList<Integer> result){
        if (root == null){
            return;
        }
        inOrderTraversal(root.left, result);
        result.add(root.value);
        inOrderTraversal(root.right, result);
    }
    public static void postOrderTraversal(Node<Integer> root, ArrayList<Integer> result){
        if (root == null){
            return;
        }
        postOrderTraversal(root.left, result);
        postOrderTraversal(root.right, result);
        result.add(root.value);
    }

    //Divide and Conquer
    public static ArrayList<Integer> preOrder_Divide_Conquer(Node<Integer> root){
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null){
            return result;
        }
        ArrayList<Integer> left = preOrder_Divide_Conquer(root.left);
        ArrayList<Integer> right = preOrder_Divide_Conquer(root.right);

        result.add(root.value);
        result.addAll(left);
        result.addAll(right);
        return result;
    }
    public static ArrayList<Integer> inOrder_Divide_Conquer(Node<Integer> root){
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null){
            return result;
        }
        ArrayList<Integer> left = inOrder_Divide_Conquer(root.left);
        ArrayList<Integer> right = inOrder_Divide_Conquer(root.right);

        result.addAll(left);
        result.add(root.value);
        result.addAll(right);

        return result;
    }
    public static ArrayList<Integer> postOrder_Divide_Conquer(Node<Integer> root){
        ArrayList<Integer> result = new ArrayList<>();
        if(root == null){
            return result;
        }

        ArrayList<Integer> left = postOrder_Divide_Conquer(root.left);
        ArrayList<Integer> right = postOrder_Divide_Conquer(root.right);

        result.addAll(left);
        result.addAll(right);
        result.add(root.value);

        return result;
    }
    public static void main(String[] args) {
        Node<Integer> root = new Node<>(1);
        root.left = new Node<>(2);
        root.right = new Node<>(3);
        root.left.left = new Node<>(4);
        root.left.right = new Node<>(5);
        ArrayList<Integer> result = new ArrayList<>();
        /**
         *      1
         *    2    3
         *  4   5
         */

        // Traversal version
        preOrderTraversal(root, result);
        System.out.println(result.toString());

        System.out.println(preOrder_Divide_Conquer(root).toString());

        result = new ArrayList<>();
        inOrderTraversal(root, result);
        System.out.println(result.toString());
        System.out.println(inOrder_Divide_Conquer(root).toString());


        result = new ArrayList<>();
        postOrderTraversal(root, result);
        System.out.println(result.toString());
        System.out.println(postOrder_Divide_Conquer(root).toString());


        //Divide and Conquer


    }


}
