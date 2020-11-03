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

    public static int mainMenu() {
        System.out.println("BOOK LIST");
        System.out.println("1.1.      Load data from file");
        System.out.println("1.2.      Input & insert data");
        System.out.println("1.3.      In-order traverse");
        System.out.println("1.4.      Breadth-first traverse");
        System.out.println("1.5.      In-order traverse to file");
        System.out.println("1.6.      Search by bcode");
        System.out.println("1.7.      Delete by bcode by copying");
        System.out.println("1.8.      Simply balancing");
        System.out.println("1.9.      Count number of books");
        System.out.println("0.    Exit program");
        System.out.print("Enter your choice: ");
        Scanner sc = new Scanner(System.in);
        int selection = Integer.parseInt(sc.nextLine().trim());
        return selection;
    }

    public static BSTree loadData() throws FileNotFoundException, IOException {
        BSTree bookTree = new BSTree();
        FileReader f = new FileReader("booklist.txt");
        BufferedReader br = new BufferedReader(f);
        String line;
        while ((line = br.readLine()) != null) {
//            System.out.println(line);
            String[] arrOfString = line.split("[|]", 0);
            System.out.println(Arrays.toString(arrOfString));
            String bcode = arrOfString[0].trim();
            String title = arrOfString[1].trim();
            int quantity = Integer.parseInt(arrOfString[2].trim());
            double price = Double.parseDouble(arrOfString[3].trim());
            bookTree.insert(new Book(bcode, title, quantity, 0, price));
        }
        return bookTree;
    }

    public static void visit(PrintWriter pw, Node<Book> p) throws IOException {
        if (pw == null || p == null) {
            return;
        }
        pw.printf("%5s | %20s | %d | %.1f \r\n", p.info.bcode, p.info.title, p.info.quantity, p.info.price);
    }

    public static void saveFileInOrder(String fname, Node p) throws IOException { // Using FileReader class
        FileWriter fw = new FileWriter(fname);
        PrintWriter pw = new PrintWriter(fw);
        if (p == null) {
            return;
        }
        saveFileInOrder(fname, p.left);
        visit(pw, p);
        saveFileInOrder(fname, p.right);

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

}
