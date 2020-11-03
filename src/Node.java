
public class Node<T> {
    T info;
    Node left, right;
    
    Node(){}
    Node(T x){
        info = x;
        left = right = null;
    }
}
