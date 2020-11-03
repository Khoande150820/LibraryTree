package util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import util.Node;
import util.Book;

public class BSTree {

    public Node<Book> root;

    public BSTree() {
        root = null;
    }

    public void clear() {
        root = null;
    }

    public boolean isEmpty() {
        return (root == null);
    }

    public Node search(Node<Book> p, String bcode) {
        if (p == null) {
            return (null);
        }
        if (p.info.bcode.compareTo(bcode) == 0) {
            return (p);
        }
        if (bcode.compareTo(p.info.bcode) < 1) {
            return (search(p.left, bcode));
        } else {
            return (search(p.right, bcode));
        }
    }

    public void insert(Book x) {
        if (isEmpty()) {
            root = new Node<>(x);
            return;
        }
        Node<Book> f, p;
        f = null;
        p = root;
        while (p != null) {
            if (p.info.bcode.equals(x.bcode)) {
                System.out.println("The key " + x.bcode + " already exists, no insertion");
                return;
            }
            f = p;
            if (x.bcode.compareTo(p.info.bcode) < 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if (x.bcode.compareTo(f.info.bcode) < 0) {
            f.left = new Node<>(x);
        } else {
            f.right = new Node<>(x);
        }
    }

    static public void visit(Node<Book> p) {
        System.out.println(p.info + "  ");
    }

    static public void inOrder(Node<Book> p) {
        if (p == null) {
            return;
        }
        inOrder(p.left);
        visit(p);
        
        inOrder(p.right);
    }

    public void postOrder(Node<Book> p) {
        if (p == null) {
            return;
        }
        postOrder(p.left);
        postOrder(p.right);
        visit(p);
    }

    public void breadth(Node p) {
        if (p == null) {
            return;
        }
        MyQueue q = new MyQueue();
        q.enqueue(p);
        Node r;
        while (!q.isEmpty()) {
            r = q.dequeue();
            visit(r);
            if (r.left != null) {
                q.enqueue(r.left);
            }
            if (r.right != null) {
                q.enqueue(r.right);
            }
        }
    }

    public void deleByCopy(String bcode) {
        Node<Book> f, p;
        f = null;
        p = root;
        while (p != null) {
            if (p.info.bcode.equals(bcode)) {
                break;
            }
            f = p;
            if (bcode.compareTo(p.info.bcode) < 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if (p == null) {
            return; // not found
        }      // p is a leaf node
        if (p.left == null && p.right == null) {
            if (f == null) { // p==root
                root = null;
                return;
            }
            if (p == f.left) {
                f.left = null;
            } else {
                f.right = null;
            }
        }
        // p has left son only
        if (p.left != null && p.right == null) {
            if (f == null) { // p==root
                root = p.left;
                return;
            }
            if (p == f.left) {
                f.left = p.left;
            } else {
                f.right = p.left;
            }
        }
        // p has right son only
        if (p.left == null && p.right != null) {
            if (f == null) { // p==root
                root = p.right;
                return;
            }
            if (p == f.left) {
                f.left = p.right;
            } else {
                f.right = p.right;
            }
        }
        // p has both 2 sons
        if (p.left != null && p.right != null) {
            Node q = p.left;
            // find the right-most node in the left sub-tree
            Node<Book> frp, rp;
            frp = null;
            rp = q;
            while (rp.right != null) {
                frp = rp;
                rp = rp.right;
            }
            p.info = rp.info;
            if (frp == null) { // rp==q
                p.left = q.left;
            } else {
                frp.right = rp.left;
            }
        }
    }

    //balancing a BST
    //inorder a BST and save all items to an array
    public void buildArray(ArrayList a, Node<Book> p) {
        if (p == null) {
            return;
        }
        buildArray(a, p.left);
        a.add(p);
        buildArray(a, p.right);
    }

    //insert all items from an array to a new BST
    public void balance(ArrayList a, int f, int l) {
        if (f > l) {
            return;
        }
        int m = (f + l) / 2;
        Node<Book> p = (Node) a.get(m);
        insert(p.info);
        balance(a, f, m - 1);
        balance(a, m + 1, l);
    }

    public void balance(Node p) {
        ArrayList a = new ArrayList();
        buildArray(a, p);
        int l = a.size(), f = 0;
        //create a new tree and insert all items from a to the BST
        BSTree t = new BSTree();
        t.balance(a, f, l - 1);
        root = t.root;
    }
    
    public int count(Node p){
        if(p == null){
            return 0;
        }
        
        return 1 + count(p.left) + count(p.right);
    }
}
