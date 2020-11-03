package util;

public class Node<T> {

    public T info;
    public Node left, right;

    public Node() {
    }

    public Node(T x) {
        info = x;
        left = right = null;
    }
}
