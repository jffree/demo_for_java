package com.jffree.java_demo.algorithm.tree;

public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

    @Override
    public void insert(T data) {
        if (root == null) {
            root = new Node<>(data);
            ;
        } else {
            internalInsert(root, data);
        }
    }

    private void internalInsert(Node<T> node, T data) {
        int c = node.getData().compareTo(data);
        if (c == 0) {
            System.out.println("Data already exist: " + data);
        } else if (c > 0) {
            if (node.left == null) {
                node.left = new Node<>(data);
                node.left.parent = node;
                System.out.println("Insert to tree: " + data);
                size += 1;
            } else {
                internalInsert(node.left, data);
            }
        } else {
            if (node.right == null) {
                node.right = new Node<>(data);
                node.right.parent = node;
                System.out.println("Insert to tree: " + data);
                size += 1;
            } else {
                internalInsert(node.right, data);
            }
        }
    }

    @Override
    public void delete(T data) {
        Node<T> target = search(data);
        if (target == null) {
            return;
        }
        if (target.left == null && target.right == null) {
            if (target.parent.left.getData().compareTo(target.getData()) == 0) {
                target.parent.left = null;
            } else {
                target.parent.right = null;
            }
        } else if (target.left != null && target.right != null) {
            //将该节点与右子树的最小节点的值进行互换
            Node<T> minRight = target.right;
            while (minRight.left != null) {
                minRight = minRight.left;
            }
            target.setData(minRight.getData());
            minRight.parent.left = null;
        } else {
            if (target.left != null) {
                if (target.parent.left.getData().compareTo(target.getData()) == 0) {
                    target.parent.left = target.left;
                } else {
                    target.parent.right = target.left;
                }
            } else {
                if (target.parent.left.getData().compareTo(target.getData()) == 0) {
                    target.parent.left = target.right;
                } else {
                    target.parent.right = target.right;
                }
            }
        }
        System.out.println("Remove from tree: " + data);
    }

    @Override
    public Node<T> search(T data) {
        if (root == null)
            return null;
        Node<T> tmp = root;
        while (tmp != null) {
            int c = tmp.getData().compareTo(data);
            if (c == 0) {
                break;
            } else if (c > 0) {
                tmp = tmp.left;
            } else {
                tmp = tmp.right;
            }
        }
        System.out.println("Fount node: " + tmp);
        return tmp;
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 7, 3, 10, 12, 5, 1, 9, 4 };
        BinaryTree<Integer> test = new BinarySearchTree<>();
        for (int a : arr) {
            test.insert(a);
        }
        test.preOrderTraverse(); //7315410912
        System.out.println();
        test.search(10);
        test.delete(3); //741510912
        test.preOrderTraverse();
    }
}
