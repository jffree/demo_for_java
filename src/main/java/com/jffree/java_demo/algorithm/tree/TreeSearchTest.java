package com.jffree.java_demo.algorithm.tree;

import java.util.*;

public class TreeSearchTest {
    public Node<String> createTree() {
        //https://stackoverflow.com/questions/26470972/trying-to-read-from-the-console-in-java
        //A\n B\n D\n \n F\n E\n \n \n C\n G\n \n H\n \n \n I\n \n \n \n \n
        Scanner in = new Scanner(System.in);
        System.out.println("请输入节点数据：");
        String rawData = in.nextLine();
        if (rawData.isEmpty() || rawData.equals(" ")) {
            return null;
        } else {
            Node<String> node = new Node<>(rawData);
            node.left = createTree();
            node.right = createTree();
            return node;
        }
    }

    public Node<String> createTree(List<String> input) {
        //将数组（顺序）存储形式转化为链表（链式）存储形式
        if (input == null || input.size() == 0) {
            return null;
        }
        String data = input.remove(0);
        if (data == null) {
            return null;
        }
        Node<String> node = new Node<>(data);
        node.left = createTree(input);
        node.right = createTree(input);
        return node;
    }

    //深度优先 -> 先序遍历
    public void preOrderTraverse(Node<String> root) {
        if (root == null)
            return;
        System.out.print(root.getData());
        preOrderTraverse(root.left);
        preOrderTraverse(root.right);
    }

    //深度优先 -> 中序遍历
    public void inOrderTraverse(Node<String> root) {
        if (root == null)
            return;
        inOrderTraverse(root.left);
        System.out.print(root.getData());
        inOrderTraverse(root.right);
    }

    //深度优先 -> 后序遍历
    public void postOrderTraverse(Node<String> root) {
        if (root == null)
            return;
        postOrderTraverse(root.left);
        postOrderTraverse(root.right);
        System.out.print(root.getData());
    }

    //深度优先 -> 使用栈先序遍历
    public void preOrderStackTraverse(Node<String> root) {
        Stack<Node> cache = new Stack<>();
        if (root == null)
            return;
        cache.push(root);
        while (!cache.isEmpty()) {
            Node node = cache.pop();
            System.out.print(node.getData());
            if (node.right != null)
                cache.push(node.right);
            if (node.left != null)
                cache.push(node.left);
        }
    }

    //深度优先 -> 使用栈中序遍历
    public void inOrderStackTraverse(Node<String> root){
        if (root == null) return;
        Stack<Node<String>> cache = new Stack<>();
        Node tmp = root;
        while(tmp !=null || !cache.isEmpty()){
            while (tmp != null){
                cache.push(tmp);
                tmp = tmp.left;
            }
            tmp = cache.pop();
            System.out.print(tmp.getData());
            tmp = tmp.right;
        }
    }

    //深度优先 -> 使用栈后序遍历
    public void postOrderStackTraverse(Node<String> root){
        if (root == null) return;
        Stack<Node<String>> cache = new Stack<>();
        Node tmp = root;
        Node last = null;
        while(tmp != null || !cache.isEmpty()){
            while (tmp != null){
                cache.push(tmp);
                tmp = tmp.left;
            }
            tmp = cache.peek();
            if(last == tmp.right || tmp.right == null){
                last = cache.pop();
                System.out.print(tmp.getData());
                tmp = null;
            }else{
                tmp = tmp.right;
            }
        }
    }


    //广度优先遍历
    public void breadthFirstSearch(Node<String> root) {
        Queue<Node> cache = new ArrayDeque<>();
        if (root == null)
            return;
        cache.add(root);
        while (!cache.isEmpty()) {
            Node node = cache.poll();
            System.out.print(node.getData());
            if (node.left != null)
                cache.add(node.left);
            if (node.right != null)
                cache.add(node.right);
        }
    }

    //根据先序遍历和后序遍历，恢复二叉树
    //https://www.cnblogs.com/deltadeblog/p/9296469.html
    public Node<String> buildFromPreAndInOrder(String[] preOrder, String[] inOrder){
        return buildFromPreAndInOrder(preOrder, 0, preOrder.length-1, inOrder, 0, inOrder.length-1);
    }

    private Node<String> buildFromPreAndInOrder(String[] preOrder, int ps, int pe, String[] inOrder, int is, int ie){
        if(ps > pe) return null;
        Node<String> root = new Node(preOrder[ps]); //获取根节点（先序遍历的第一个节点是根节点）
        //获取根节点在中序遍历中的位置，可优化，使用 map 记录值与位置的对应关系。k 意味着子树的大小（包含 root）
        int k = 0;
        while (!root.getData().equals(inOrder[k+is])) k++;
        root.left = buildFromPreAndInOrder(preOrder, ps+1, ps+k, inOrder, is, k+is-1);
        root.right = buildFromPreAndInOrder(preOrder, ps+k+1, pe, inOrder, k+is+1, ie);

        return root;
    }

    //根据中序遍历和后序遍历，恢复二叉树
    public Node<String> buildFromInAndPostOrder(String[] postOrder, String[] inOrder){
        return buildFromInAndPostOrder(postOrder, 0, postOrder.length-1, inOrder, 0, inOrder.length-1);
    }

    private Node<String> buildFromInAndPostOrder(String[] postOrder, int ps, int pe, String[] inOrder, int is, int ie){
        if(ps > pe) return null;
        Node<String> root = new Node<>(postOrder[pe]); //获取根节点（后序遍历的最后一个节点是根节点）
        int k = 0;
        //获取根节点在中序遍历中的位置，可优化，使用 map 记录值与位置的对应关系。k 意味着子树的大小（包含 root）
        while(!inOrder[k+is].equals(root.getData())) k++;
        root.left = buildFromInAndPostOrder(postOrder, ps, ps+k-1, inOrder, is, k+is-1);
        root.right = buildFromInAndPostOrder(postOrder, ps+k, pe-1, inOrder, k+is+1, ie);
        return root;
    }

    public Node<String> buildFromPreAndPostOrder(String[] preOrder, String[] postOrder){
        return buildFromPreAndPostOrder(preOrder, 0, preOrder.length-1, postOrder, 0, postOrder.length-1);
    }

    private Node<String> buildFromPreAndPostOrder(String[] preOrder, int preStart, int preEnd, String[] postOrder, int postStart, int postEnd){
        if(preStart > preEnd || postStart > postEnd) return null;
        Node<String> root = new Node<>(preOrder[preStart]); //获取根节点（先序遍历的第一个节点是根节点）
        if(preStart == preEnd) return root;     //一定要在这里跳出，因为下面的判断是 preStart + 1，会跳出当前树的范围
        //获取根节点在中序遍历中的位置，可优化，使用 map 记录值与位置的对应关系。k 意味着子树的大小（包含 root）
        int k=0;
        while(!preOrder[preStart + 1].equals(postOrder[postStart+k])) k++;
        root.left = buildFromPreAndPostOrder(preOrder, preStart+1, preStart+k+1, postOrder, postStart, postStart+k);
        root.right = buildFromPreAndPostOrder(preOrder, preStart+k+1+1, preEnd, postOrder, postStart+k+1, postEnd-1);
        return root;
    }


    public void test(){
        //这个数组也反映了，树以数组存储的形式
        String[] data = {"A", "B", "D", null, null, "F", "E", null, null, null, "C", "G", null, "H", null, null, "I"};
        //https://img2020.cnblogs.com/blog/1542838/202008/1542838-20200809101414671-1310336891.png
        //https://stackoverflow.com/questions/7885573/remove-on-list-created-by-arrays-aslist-throws-unsupportedoperationexception
        List<String> list = new ArrayList<>(Arrays.asList(data));
        Node<String> root = createTree(list);
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
        System.out.println("Test inOrderStackTraverse: ");
        inOrderStackTraverse(root);
        System.out.println();
        System.out.println("Test postOrderStackTraverse: ");
        postOrderStackTraverse(root);
        System.out.println();
        System.out.println("Test breadthFirstSearch: ");
        breadthFirstSearch(root);
        System.out.println();

        System.out.println("Test buildFromPreAndInOrder: ");
        postOrderTraverse(buildFromPreAndInOrder(
            new String[]{"A", "B", "D", "F", "E", "C", "G", "H", "I"},
            new String[]{"D", "B", "E", "F", "A", "G", "H", "C", "I"}));
        System.out.println();
        System.out.println("Test buildFromInAndPostOrder: ");
        preOrderTraverse(buildFromInAndPostOrder(
            new String[]{"D", "E", "F", "B", "H", "G", "I", "C", "A"},
            new String[]{"D", "B", "E", "F", "A", "G", "H", "C", "I"}));
        System.out.println();

        System.out.println("Test buildFromPreAndPostOrder: ");
        inOrderTraverse(buildFromPreAndPostOrder(
            new String[]{"A", "B", "D", "F", "E", "C", "G", "H", "I"},
            new String[]{"D", "E", "F", "B", "H", "G", "I", "C", "A"}));
        System.out.println();
    }
    public static void main(String[] args) {
        new TreeSearchTest().test();
    }
}
