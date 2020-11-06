package Manage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import util.*;

public class Manage {

    public static ReaderList loadReader(ReaderList rlist) throws FileNotFoundException, IOException {
        FileReader f = new FileReader("reader.txt");
        BufferedReader br = new BufferedReader(f);
        String line;
        while ((line = br.readLine()) != null) {
//            System.out.println(line);
            String[] arrOfString = line.split("[|]", 0);
            System.out.println(Arrays.toString(arrOfString));
            String rcode = arrOfString[0].trim();
            String name = arrOfString[1].trim();
            int byear = Integer.parseInt(arrOfString[2].trim());
            rlist.addLast(new Reader(rcode, name, byear));
        }
        return rlist;
    }

    public static BSTree loadBook() throws FileNotFoundException, IOException {
        BSTree bookTree = new BSTree();
        FileReader f = new FileReader("book.txt");
        BufferedReader br = new BufferedReader(f);
        String line;
        while ((line = br.readLine()) != null ) {
//            System.out.println(line);
            String[] arrOfString = line.split("[|]", 0);
//            System.out.println(Arrays.toString(arrOfString));
            String bcode = arrOfString[0].trim();
            String title = arrOfString[1].trim();
            int quantity = Integer.parseInt(arrOfString[2].trim());
            int lended = Integer.parseInt(arrOfString[3].trim());
            double price = Double.parseDouble(arrOfString[4].trim());
            bookTree.insert(new Book(bcode, title, quantity, lended, price));
        }
        return bookTree;
    }

    public static void visit(PrintWriter pw, Node<Book> p) throws IOException {
        if (pw == null || p == null) {
            return;
        }
        pw.printf("%5s | %20s | %d | %.1f \r\n", p.info.bcode, p.info.title, p.info.quantity, p.info.price);
    }

    public static void saveBookInOrder(String fname, Node p) throws IOException { // Using FileReader class
        FileWriter fw = new FileWriter(fname);
        PrintWriter pw = new PrintWriter(fw);
        if (p == null) {
            return;
        }
        saveBookInOrder(fname, p.left);
        visit(pw, p);
        saveBookInOrder(fname, p.right);

    }

    public static Book getBook() {
        while (true) {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print("Enter book code: ");
                String bcode = sc.nextLine().trim();
                System.out.print("Enter book title: ");
                String title = sc.nextLine().trim();
                System.out.print("Enter quantity: ");
                int quantity = sc.nextInt();
                System.out.println("Enter price:");
                double price = sc.nextDouble();
                return new Book(bcode, title, quantity, 0, price);
            } catch (Exception e) {
                System.err.print(e);
            }
        }
    }

    public static void searchByBcode(BSTree bookTree) {
        System.out.print("Enter bcode: ");
        Scanner sc = new Scanner(System.in);
        String bcode = sc.nextLine().trim();
        Node p = bookTree.search(bookTree.root, bcode);
        if (p != null) {
            System.out.println(p.info);
        } else {
            System.out.println("Can't find book with " + bcode + " in database");
        }
    }

    public static void deleteByBcode(BSTree bookTree) {
        System.out.print("Enter bcode: ");
        Scanner sc = new Scanner(System.in);
        String bcode = sc.nextLine().trim();
        bookTree.deleByCopy(bcode);
    }

    public static Reader getReader() {
        while (true) {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print("Enter reader code: ");
                String rcode = sc.nextLine().trim();
                System.out.print("Enter reader name: ");
                String title = sc.nextLine().trim();
                System.out.print("Enter book year: ");
                int quantity = sc.nextInt();

                return new Reader(rcode, title, quantity);
            } catch (Exception e) {
                System.err.print(e);
            }
        }
    }

    public static void saveReader(ReaderList rlist) throws IOException{
       FileWriter write = new FileWriter("readerlist.txt");
        PrintWriter pw = new PrintWriter(write);
        LinkedListNode<Reader> p = rlist.head;
        while (p != null) {
            pw.println(p.info.getRcode() + " , " + p.info.getName() + " , " + p.info.getByear());
            p = p.next;
        }
        pw.close();
    }
}
