package com.jffree.java_demo.algorithm.tree;

public class Node<T> {
    private T data;
    public Node<T> parent;
    public Node<T> left;
    public Node<T> right;

    public Node(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Node{" +
            "data=" + data +
            '}';
    }
}
