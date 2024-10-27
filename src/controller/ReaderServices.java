package controller;

import Node_model.ReaderNode;
import model.Reader;
import structures.ReaderLinkedList;
import view.Menu;
import view.Utils;

import java.io.IOException;

public class ReaderServices {
    public static boolean run() throws IOException {
        Menu<String> menu = new Menu<>();
        String[] content = {"-----Reader management------", " Input & add to the end", "Display reader list", " Save reader list to file", "Search by rcode", "Delete by rcode", "Exit"};
        ReaderLinkedList rd = new ReaderLinkedList();
        rd.loadFromFile();
        while (true) {
            menu.display(content);
            int n = Utils.checkInt("Enter your option: ");
            switch (n) {
                case 1: {
                    System.out.println("----Input & add to the end----");
                    addNewReader(rd);
                    break;
                }
                case 2: {
                    System.out.println("----Display reader list----");
                    displayReader(rd);
                    break;
                }
                case 3: {
                    System.out.println("----Save reader list to file----");
                    String outputFilePath = "src/resources/ReaderOutput.txt";
                    rd.saveToFile(outputFilePath);
                    System.out.println("Reader list saved successfully at "+outputFilePath);
                    break;
                }
                case 4: {
                    System.out.println("----Search by rcode----");
                    searchReader(rd);
                    break;
                }
                case 5: {
                    System.out.println("----Delete by rcode----");
                    deleteReader(rd);
                    break;
                }
                case 6: {
                    System.out.println("exit...");
                    return true;
                }
                default: {
                    System.out.println("Invalid option!");
                }
            }
        }
    }

    public static void displayReader(ReaderLinkedList rd) {
        ReaderNode headNode = rd.getHead();
        while (headNode != null) {
            System.out.println(headNode.readerInfor);
            headNode = headNode.next;
        }
    }

    public static void addNewReader(ReaderLinkedList rd) {
        String rcodeInput = Utils.getValue("Enter rCode: ");
        if (rd.isRCodeExists(rcodeInput)) {
            System.out.println("rCode already exists! Please enter a different rCode.");
            return;
        }

        String nameInput = Utils.getValue("Enter reader name: ");
        int bYear = 0;

        while (true) {
            System.out.println("Birth year is between 1900 & 2010");
            bYear = Integer.parseInt(Utils.getValue("Enter byear: "));
            if (bYear >= 1900 && bYear <= 2010) {
                break;
            } else {
                System.out.println("Invalid byear, try again!");
            }
        }
        rd.addReader(new Reader(rcodeInput, nameInput, bYear));
        System.out.println("Add success!");
    }

    public static void searchReader(ReaderLinkedList rd) {
        String readerCodeInput = Utils.getValue("Enter reader code to search: ");
        ReaderNode res = rd.searchByRCode(readerCodeInput);
        if (res == null) {
            System.out.println("Not found!");
        } else {
            System.out.println(res.readerInfor.toString());
        }
    }

    public static void deleteReader(ReaderLinkedList rd) {
        String readerCodeInput = Utils.getValue("Enter reader code to delete: ");
        boolean deleted = rd.deleteByRCode(readerCodeInput);
        if (deleted) {
            System.out.println("Reader with rCode " + readerCodeInput + " deleted successfully.");
        } else {
            System.out.println("Reader with rCode " + readerCodeInput + " not found.");
        }
    }
}
