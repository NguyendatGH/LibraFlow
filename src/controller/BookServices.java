package controller;

import Node_model.BSTreeNode;
import structures.BookTree;
import model.Book;
import view.Menu;
import view.Utils;

import java.util.ArrayList;
import java.util.Scanner;

public class BookServices {
    private static final Scanner scanner = new Scanner(System.in);

    public static boolean run() {
        Menu<String> menu = new Menu<>();
        String[] content = {
                "-----Book management------",
                "Input & insert data",
                "In-order traverse",
                "Breadth-first traverse",
                "In-order traverse to file",
                "Search by bcode",
                "Delete by bcode",
                "Simply balance the tree",
                "Count number of books",
                "Exit"
        };

        BookTree bt = new BookTree();
        bt.loadFromFile();

        while (true) {
            menu.display(content);
            int n = Utils.checkInt("Enter your option: ");
            switch (n) {
                case 1:
                    addBookFromKeyboard(bt);
                    break;
                case 2:
                    displayTree(bt.inOrderTraverse(), "In-order traverse:");
                    break;
                case 3:
                    displayTree(bt.breadthFirstTraverse(), "Breadth-first traverse:");
                    break;
                case 4:
                    System.out.println("In-order traverse to file.");
                    inOrderTraverseToFile(bt);
                    break;
                case 5:
                    searchByBcode(bt);
                    break;
                case 6:
                    deleteByBcode(bt);
                    break;
                case 7:
                    System.out.println("Simply balance the tree:");
                    bt.balanceTree();
                    System.out.println("Tree balanced successfully.");
                    break;
                case 8:
                    System.out.println("Count number of books: " + bt.countBooks());
                    break;
                case 9:
                    System.out.println("exit...");
                    return true;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    private static void addBookFromKeyboard(BookTree bt) {
        String bcode = "";
        while (true) {
            bcode = Utils.getValue("Enter book code: ");
            if (!bt.isBcodeExists(bcode)) {
                break;
            } else {
                System.out.println("Code is exits! try again");
            }
        }

        String title = Utils.getValue("Enter book title: ");
        int quantity = Utils.checkInt("Enter quantity: ");
        int lended = Utils.checkInt("Enter lended: ");

        while (quantity < lended) {
            System.out.println("Quantity cannot be less than lended. Please enter again.");
            quantity = Utils.checkInt("Enter quantity: ");
            lended = Utils.checkInt("Enter lended: ");
        }

        double price = Utils.checkDouble("Enter price: ");

        Book newBook = new Book(bcode, title, quantity, lended, price);
        bt.insert(newBook);
        System.out.println("Book added successfully!");
    }

    private static void inOrderTraverseToFile(BookTree bt) {
        String outputFilePath = "src/resources/BooksOutput.txt";
        bt.inOrderTraverseToFile(outputFilePath);
        System.out.println("In-order traversal has been written to " + outputFilePath);
    }

    private static void searchByBcode(BookTree bt) {
        System.out.print("Enter book code to search: ");
        String bcode = scanner.nextLine();
        Book foundBook = bt.search(bcode);
        if (foundBook != null) {
            System.out.println("Found book: " + foundBook);
        } else {
            System.out.println("Book with code " + bcode + " not found.");
        }
    }

    private static void deleteByBcode(BookTree bt) {
        String bcode = Utils.getValue("Enter book code to delete: ");
        if (bt.deleteBook(bcode)) {
            System.out.println("Book deleted successfully");
        } else {
            System.out.println("not found book!");
        }
    }

    private static void displayTree(ArrayList<Book> tmp, String displayText) {
        if (tmp.isEmpty()) {
            System.out.println("list empty");
        } else {
            System.out.println(displayText);
            for (Book b : tmp) {
                System.out.println(b.toString());
            }
        }
    }

}
