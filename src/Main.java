import controller.BookServices;
import controller.LendedServices;
import controller.ReaderServices;
import view.Utils;

import java.io.IOException;


public class Main {
    public static void DisplayMenu() {
        System.out.println("-----LibrarySystem-----");
        System.out.println("1. Book list");
        System.out.println("2. Reader list");
        System.out.println("3. Lended list");
        System.out.println("4. Exit");
    }

    public static void main(String[] args) throws IOException {
        BookServices bookServices = new BookServices();
        ReaderServices readerServices = new ReaderServices();
        LendedServices lendedServices = new LendedServices();
        while (true) {
            DisplayMenu();
            int n = Utils.checkInt("enter your option: ");
            switch (n) {
                case 1: {
                    if (bookServices.run()) {
                        continue;
                    }
                    break;
                }
                case 2: {
                    if (readerServices.run()) {
                        continue;
                    }
                    break;
                }
                case 3: {
                    if (lendedServices.run()) {
                        continue;
                    }
                    break;
                }
                case 4: {
                    System.out.println("End program!");
                    System.exit(0);
                }
                default: {
                    System.out.println("invalid, try again!");
                }
            }
        }
    }

}