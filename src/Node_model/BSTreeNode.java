package Node_model;

import model.Book;

public class BSTreeNode {
    public Book bookData;
    public BSTreeNode left, right;

    public BSTreeNode(){
        this.bookData = null;
        this.left = this.right = null;
    }

    public BSTreeNode(Book bookData) {
        this.bookData = bookData;
        this.left = this.right = null;
    }

    public Book getBookData() {
        return bookData;
    }

    public BSTreeNode getLeft() {
        return left;
    }

    public BSTreeNode getRight() {
        return right;
    }

}
