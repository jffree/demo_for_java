package com.jffree.java_demo.algorithm.tree;

public abstract class BinaryTree<T> {
    Node<T>       root;
    protected int size;

    BinaryTree() {
        size = 0;
        root = null;
    };

    public Node<T> getRoot() {
        return root;
    }

    public int getSize() {
        return size;
    }

    //插入
    public abstract void insert(T data);

    //删除
    public abstract void delete(T data);

    //查找
    public abstract Node<T> search(T data);

    public void preOrderTraverse() {
        internalPreOrderTraverse(root);
    }

    private void internalPreOrderTraverse(Node<T> root) {
        if (root == null)
            return;
        System.out.print(root.getData());
        internalPreOrderTraverse(root.left);
        internalPreOrderTraverse(root.right);
    }

}
