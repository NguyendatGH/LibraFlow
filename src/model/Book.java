package model;

public class Book {
    private String bcode;
    private String title;
    private int quantity;
    private int lended;
    private double price;

    public Book(String bcode, String title, int quantity, int lended, double price) {
        this.bcode = bcode;
        this.title = title;
        this.quantity = quantity;
        this.lended = lended;
        this.price = price;
    }

    public String getBcode() {
        return this.bcode;
    }

    public String getTitle() {
        return this.title;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public Integer getLended() {
        return this.lended;
    }

    public Double getPrice() {
        return this.price;
    }


    public void setBcode(String bcode) {
        this.bcode = bcode;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setLended(int lended) {
        this.lended = lended;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bcode='" + bcode + '\'' +
                ", title='" + title + '\'' +
                ", quantity=" + quantity +
                ", lended=" + lended +
                ", price=" + price +
                '}';
    }
}
