package view;

public class Menu<T> {
    public void display(T[] content) {
        System.out.println(content[0]);
        for (int i = 1; i < content.length; i++) {
            System.out.println(i + ", " + content[i]);
        }
    }
}
