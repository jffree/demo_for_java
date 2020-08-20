package com.jffree.java_demo.algorithm.tree;

public class Node {
    private final String data;
    public Node left;
    public Node right;

    public Node(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}
