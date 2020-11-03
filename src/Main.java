
import Manage.Manage;
import java.io.IOException;
import util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BSTree bookTree = new BSTree();
        while (true) {
            int selection = Manage.mainMenu();
            switch (selection) {
                case 1:
                    bookTree = Manage.loadData();
                    System.out.println("1.1.      Load data from file");
                    break;
                case 2:
                    System.out.println("1.2.      Input & insert data");
                    Book b = Manage.getBook();
                    bookTree.insert(b);
                    break;
                case 3:
                    System.out.println("1.3.      In-order traverse");
                    bookTree.inOrder(bookTree.root);
                    break;
                case 4:
                    System.out.println("1.4.      Breadth-first traverse");
                    bookTree.breadth(bookTree.root);
                    break;
                case 5:
                    System.out.println("1.5.      In-order traverse to file");
                    Manage.saveFileInOrder("booktext.txt", bookTree.root);
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

}
