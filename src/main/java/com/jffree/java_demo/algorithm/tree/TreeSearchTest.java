package com.jffree.java_demo.algorithm.tree;

import java.util.Scanner;

public class TreeSearchTest {
    public Node createTree(){
        //https://stackoverflow.com/questions/26470972/trying-to-read-from-the-console-in-java
        //A\n B\n D\n \n F\n E\n \n \n C\n G\n \n H\n \n \n I\n \n \n \n \n
        Scanner in = new Scanner(System.in);
        System.out.println("请输入节点数据：");
        String rawData = in.nextLine();
        if(rawData.isEmpty()){
            return null;
        }
        else{
            Node node = new Node(rawData);
            node.left = createTree();
            node.right = createTree();
            return node;
        }
    }

    public void preOrderTraverse(Node root){
        if(root == null) return;
        System.out.println(root.getData());
        preOrderTraverse(root.left);
        preOrderTraverse(root.right);
    }

    public void inOrderTraverse(Node root){
        if(root == null) return;
        inOrderTraverse(root.left);
        System.out.println(root.getData());
        inOrderTraverse(root.right);
    }

    public void postOrderTraverse(Node root){
        if(root == null) return;
        postOrderTraverse(root.left);
        postOrderTraverse(root.right);
        System.out.println(root.getData());
    }

    public void test(){
        Node root = createTree();
        System.out.println("Test preOrderTraverse: ");
        preOrderTraverse(root);
        System.out.println("Test inOrderTraverse: ");
        inOrderTraverse(root);
        System.out.println("Test postOrderTraverse: ");
        postOrderTraverse(root);
    }


    public static void main(String[] args) {
        new TreeSearchTest().test();
    }
}
