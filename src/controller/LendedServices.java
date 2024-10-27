package controller;

import Node_model.LendedNode;
import model.Lended;
import structures.LendedLinkedList;
import view.Menu;
import view.Utils;

import java.io.IOException;

public class LendedServices {
    public static boolean run() throws IOException {
        Menu<String> menu = new Menu<>();
        String[] content = {"-----Lended managerment------", "display lended list", "add new lended", "sorting list", "Exit"};
        LendedLinkedList ld = new LendedLinkedList();
        ld.loadFromFile();
        while (true) {
            menu.display(content);
            int n = Utils.checkInt("enter your option: ");
            switch (n) {
                case 1: {
                    System.out.println("----display lended list----");
                    displayLended(ld);
                    break;
                }
                case 2: {
                    System.out.println("----add new lended----");
                    addNewLended(ld);
                    break;
                }
                case 3: {
                    System.out.println("----sorting list----");
                    sortLended(ld);
                    break;
                }
                case 4: {
                    System.out.println("exit...");
                    return true;
                }
                default: {
                    System.out.println("invalid option!");
                }
            }
        }
    }
    public static void displayLended(LendedLinkedList ld) {
        LendedNode headNode = ld.getHead();
        while (headNode != null) {
            System.out.println(headNode.lended);
            headNode = headNode.next;
        }
    }

    public static void addNewLended(LendedLinkedList ld) {
        String bcodeInput = Utils.getValue("Enter bCode: ");
        String rcodeInput = Utils.getValue("Enter rCode: ");
        int state = -1;
        while (true) {
            System.out.println("State: 0 -> , 1 -> , 2 -> ");
            state = Integer.parseInt(Utils.getValue("Enter state: "));
            if (state == 0 || state == 1 || state == 2) {
                break;
            } else {
                System.out.println("invalid state, try again!");
            }
        }
        ld.addLended(new Lended(bcodeInput, rcodeInput, state));
        System.out.println("add success!");
    }

    public static void sortLended(LendedLinkedList ld){
        System.out.println("1, sort with bcode: ");
        System.out.println("2, sort with rcode: ");
        int n = Utils.checkInt("Enter your option: ");
        switch(n)
        {
            case 1: {
                ld.sortLendedList(1);
                break;
            }
            case 2:{
                ld.sortLendedList(2);
                break;
            }
            default:{
                System.out.println("invalid option!");
            }
        }
        System.out.println("success! use display function to see the result");
    }
}
