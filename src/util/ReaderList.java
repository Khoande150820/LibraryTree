package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class ReaderList {

    public LinkedListNode<Reader> head, tail, sort;

    public ReaderList() {
        head = tail = sort = null;
    }

    boolean isEmpty() {
        return (head == null);
    }

    void clear() {
        head = tail = null;
    }

    public void addLast(Reader a) {
        LinkedListNode<Reader> q = new LinkedListNode<>(a);
        if (isEmpty()) {
            head = tail = q;
            return;
        }
        tail.next = q;
        tail = q;
    }

    public void visit(LinkedListNode<Reader> p) {
        if (p != null) {
            System.out.println(p.info);
        }
    }

    public void displayData() {
        System.out.println("Code        name       Year         ");
        LinkedListNode<Reader> p = head;
        while (p != null) {
            visit(p);
            p = p.next;
        }
        System.out.println();
    }

    void saveData(ReaderList rlist) throws Exception {
        final String filename = "readerlist.txt";
        FileWriter write = new FileWriter(filename, false);
        PrintWriter pw = new PrintWriter(write);
        LinkedListNode<Reader> p = rlist.head;
        while (p != null) {
            pw.println(p.info.rcode + " , " + p.info.name + " , " + p.info.byear);
            p = p.next;
        }
        pw.close();
    }

    Reader inputToAdd() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input reader code: ");
        String rcode = sc.nextLine();
        System.out.println("Input name: ");
        String name = sc.nextLine();
        System.out.println("Input book year: ");
        int byear = Integer.parseInt(sc.nextLine().trim());
        Reader a = new Reader(rcode, name, byear);
        return a;
    }

    public LinkedListNode<Reader> search(String rcode) {
        LinkedListNode<Reader> p = head;
        while (p != null) {
            if (p.info.rcode.equals(rcode)) {
                return p;
            }
            p = p.next;
        }
        return null;
    }

    void removeFirst() {
        if (isEmpty()) {
            return;
        }
        head = head.next;
        if (head == null) {
            tail = null;
        }

    }

   public void remove(LinkedListNode<Reader> q) {
        if (q == null) {
            return;
        }
        if (q == head) {
            removeFirst();
            return;
        }
        LinkedListNode<Reader> f = head;
        while (f != null && f.next != q) {
            f = f.next;
        }
        if (f == null) {
            return;//q is not in the list
        }
        LinkedListNode<Reader> q1 = q.next;
        f.next = q1;
        if (f.next == null) {
            tail = f;
        }
    }

    void removeByName(String bcode) {
        LinkedListNode<Reader> q = search(bcode);
        remove(q);
    }
}
