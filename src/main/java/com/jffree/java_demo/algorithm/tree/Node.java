package com.jffree.java_demo.algorithm.tree;

public class Node<T> {
    private final T data;
    public Node<T> left;
    public Node<T> right;

    public Node(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
