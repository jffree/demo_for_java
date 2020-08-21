package com.jffree.java_demo.algorithm.tree;

import java.util.*;

public class TreeSearchTest {
    public Node createTree(){
        //https://stackoverflow.com/questions/26470972/trying-to-read-from-the-console-in-java
        //A\n B\n D\n \n F\n E\n \n \n C\n G\n \n H\n \n \n I\n \n \n \n \n
        Scanner in = new Scanner(System.in);
        System.out.println("请输入节点数据：");
        String rawData = in.nextLine();
        if(rawData.isEmpty() || rawData.equals(" ")){
            return null;
        }
        else{
            Node node = new Node(rawData);
            node.left = createTree();
            node.right = createTree();
            return node;
        }
    }

    public Node createTree(List<String> input){
        //将数组（顺序）存储形式转化为链表（链式）存储形式
        if(input == null || input.size() == 0){
            return null;
        }
        String data = input.remove(0);
        if(data == null){
            return null;
        }
        Node node = new Node(data);
        node.left = createTree(input);
        node.right = createTree(input);
        return node;
    }

    //深度优先 -> 先序遍历
    public void preOrderTraverse(Node root){
        if(root == null) return;
        System.out.print(root.getData());
        preOrderTraverse(root.left);
        preOrderTraverse(root.right);
    }

    //深度优先 -> 中序遍历
    public void inOrderTraverse(Node root){
        if(root == null) return;
        inOrderTraverse(root.left);
        System.out.print(root.getData());
        inOrderTraverse(root.right);
    }

    //深度优先 -> 后序遍历
    public void postOrderTraverse(Node root){
        if(root == null) return;
        postOrderTraverse(root.left);
        postOrderTraverse(root.right);
        System.out.print(root.getData());
    }

    //深度优先 -> 使用栈先序遍历
    public void preOrderStackTraverse(Node root){
        Stack<Node> cache = new Stack<>();
        if (root == null)
            return;
        cache.push(root);
        while (!cache.isEmpty()){
            Node node = cache.pop();
            System.out.print(node.getData());
            if(node.right != null) cache.push(node.right);
            if(node.left != null) cache.push(node.left);
        }
    }

    //广度优先遍历
    public void breadthFirstSearch(Node root){
        Queue<Node> cache = new ArrayDeque<>();
        if (root == null) return;
        cache.add(root);
        while (!cache.isEmpty()){
            Node node = cache.poll();
            System.out.print(node.getData());
            if (node.left != null) cache.add(node.left);
            if (node.right != null) cache.add(node.right);
        }
    }

    public void test(){
        //这个数组也反映了，树以数组存储的形式
        String[] data = {"A", "B", "D", null, null, "F", "E", null, null, null, "C", "G", null, "H", null, null, "I"};
        //https://stackoverflow.com/questions/7885573/remove-on-list-created-by-arrays-aslist-throws-unsupportedoperationexception
        List<String> list = new ArrayList<>(Arrays.asList(data));
        Node root = createTree(list);
        System.out.println("Test preOrderTraverse: ");
        preOrderTraverse(root);
        System.out.println();
        System.out.println("Test inOrderTraverse: ");
        inOrderTraverse(root);
        System.out.println();
        System.out.println("Test postOrderTraverse: ");
        postOrderTraverse(root);
        System.out.println();
        System.out.println("Test preOrderStackTraverse: ");
        preOrderStackTraverse(root);
        System.out.println();
        System.out.println("Test breadthFirstSearch: ");
        breadthFirstSearch(root);
    }


    public static void main(String[] args) {
        new TreeSearchTest().test();
    }
}
