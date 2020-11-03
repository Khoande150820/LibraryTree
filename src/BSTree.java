
import java.util.ArrayList;


public class BSTree {

    Node<Book> root;

    BSTree() {
        root = null;
    }

    void clear() {
        root = null;
    }

    boolean isEmpty() {
        return (root == null);
    }

    Node search(Node<Book> p, String bcode) {
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

    void insert(Book x) {
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

    void visit(Node<Book> p) {
        System.out.print(p.info + "  ");
    }

    void preOrder(Node<Book> p) {
        if (p == null) {
            return;
        }
        visit(p);
        preOrder(p.left);
        preOrder(p.right);
    }

    void inOrder(Node<Book> p) {
        if (p == null) {
            return;
        }
        inOrder(p.left);
        visit(p);
        inOrder(p.right);
    }

    void postOrder(Node<Book> p) {
        if (p == null) {
            return;
        }
        postOrder(p.left);
        postOrder(p.right);
        visit(p);
    }
    
    void deleByCopy(String bcode) {
      Node<Book> f,p;
      f=null; p=root;
      while(p!=null) {
        if(p.info.bcode.equals(bcode)) break;
        f=p;
        if(bcode.compareTo(p.info.bcode)< 0)
            p=p.left;
          else
            p=p.right;
      }
      if(p==null) return; // not found
      // p is a leaf node
      if(p.left==null && p.right==null) {
         if(f==null) { // p==root
           root=null;
           return;
         }
        if(p==f.left)
            f.left=null;
           else
            f.right=null;
      }
      // p has left son only
      if(p.left!=null && p.right==null) {
         if(f==null) { // p==root
           root=p.left;
           return;
         }
        if(p==f.left)
            f.left=p.left;
           else
            f.right=p.left;
      }
      // p has right son only
      if(p.left==null && p.right!=null) {
         if(f==null) { // p==root
           root=p.right;
           return;
         }
        if(p==f.left)
            f.left=p.right;
           else
            f.right=p.right;
      }
      // p has both 2 sons
      if(p.left!=null && p.right!=null) {
         Node q=p.left;
         // find the right-most node in the left sub-tree
         Node<Book> frp,rp;
         frp=null;rp=q;
         while(rp.right!=null) {
           frp=rp;
           rp=rp.right;
         }
         p.info=rp.info;
         if(frp==null) { // rp==q
            p.left=q.left;             
         }
         else {
           frp.right=rp.left;
         }
      }
   }
 
}

     
   