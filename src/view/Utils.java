package view;

import java.util.Scanner;

public class Utils {
    static Scanner sc = new Scanner(System.in);

    public static String getValue(String textToDisplay) {
        System.out.println(textToDisplay);
        String res = sc.nextLine();
        return res;
    }

    public static int checkInt(String textToDisplay) {
        int n = -1;
        while (true) {
            try {
                n = Integer.parseInt(getValue(textToDisplay));
                if (n > 0) {
                    break;
                }else{
                    System.out.println("number must > 0!");
                }
            } catch (NumberFormatException err) {
                System.out.println("input must be an integer");
            }
        }
        return n;
    }
    public static double checkDouble(String textToDisplay){
        double n = -1.0;
        while (true) {
            try {
                n = Double.parseDouble(getValue(textToDisplay));
                if (n > 0.0) {
                    break;
                }else{
                    System.out.println("number must > 0.0!");
                }
            } catch (NumberFormatException err) {
                System.out.println("input must be an double");
            }
        }
        return n;
    }
}
