
import Manage.Manage;
import java.io.IOException;
import java.util.*;
import util.*;

public class Main {

    public static void bookMenu(BSTree bookTree) throws IOException {
        while (true) {
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

            int selection = Validate.getIntInRange(0, 9);
            switch (selection) {
                case 1:
                    bookTree = Manage.loadBook();
                    System.out.println("1.1.      Load data from file");
                    break;
                case 2:
                    System.out.println("1.2.      Input & insert data");
                    Book b = Manage.getBook();
                    bookTree.insert(b);
                    break;
                case 3:
                    System.out.println("1.3.      In-order traverse");
                    BSTree.inOrder(bookTree.root);
                    break;
                case 4:
                    System.out.println("1.4.      Breadth-first traverse");
                    bookTree.breadth(bookTree.root);
                    break;
                case 5:
                    System.out.println("1.5.      In-order traverse to file");
                    Manage.saveBookInOrder("booktext.txt", bookTree.root);
                    break;
                case 6:
                    System.out.println("1.6.      Search by bcode");
                    Manage.searchByBcode(bookTree);
                    break;
                case 7:
                    System.out.println("1.7.      Delete by bcode by copying");
                    Manage.deleteByBcode(bookTree);
                    break;
                case 8:
                    System.out.println("1.8.      Simply balancing");
                    bookTree.balance(bookTree.root);
                    break;
                case 9:
                    System.out.println("1.9.      Count number of books");
                    int count = bookTree.count(bookTree.root);
                    System.out.println("There are " + count + " number of books!");
                    break;
                case 0:
                    return;
            }

        }
    }

    static void readerMenu(ReaderList rlist) throws IOException {
        while (true) {
            System.out.println("Reader menu");
            System.out.println("2.1.      Load data from file\n"
                    + "2.2.      Input & add to the end\n"
                    + "2.3.      Display data\n"
                    + "2.4.      Save reader list to file\n"
                    + "2.5.      Search by rcode\n"
                    + "2.6.      Delete by rcode\n"
                    + "2.7.      Return");

            System.out.print("Enter your choice: ");
            int selection = Validate.getIntInRange(1, 7);
            switch(selection){
                case 1:
                    Manage.loadReader(rlist);
                    break;
                case 2:
                    Reader newReader = Manage.getReader();
                    rlist.addLast(newReader);
                    break;
                case 3:
                    rlist.displayData();
                    break;
                case 4:
                    Manage.saveReader(rlist);
                    break;
                case 5:
                    // search by rcode
                    String searchRcode = Validate.getString();
                    LinkedListNode<Reader> searchedReader = rlist.search(searchRcode);
                    rlist.visit(searchedReader);
                    break;
                case 6:
                    // delete by rcode
                    String deleteRcode = Validate.getString();
                    LinkedListNode<Reader> deleteReader = rlist.search(deleteRcode);
                    rlist.remove(deleteReader);
                    break;
                case 7:
                    return;
            }

        }
    }

    public static void main(String[] args) throws IOException {
        BSTree bookTree = new BSTree();
        ReaderList rlist = new ReaderList();
        while (true) {
            while (true) {
                System.out.println("-----Library management system-----");
                System.out.println("1. Book Tree");
                System.out.println("2. Reader list");
                System.out.println("3. Lending list");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                int selection = Validate.getIntInRange(0, 4);
                switch (selection) {
                    case 1:
                        bookMenu(bookTree);
                        break;
                    case 2:
                        readerMenu(rlist);
                        break;
                    case 3:
                        break;
                    case 4:
                        return;

                }
            }
        }

    }
}
