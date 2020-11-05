package util;

public class LinkedListNode<T> {

    public T info;
    public LinkedListNode next;

    public LinkedListNode() {
    }

    public LinkedListNode(T x, LinkedListNode q) {
        info = x;
        next = q;
    }

    public LinkedListNode(T x) {
        this(x, null);
    }
}
