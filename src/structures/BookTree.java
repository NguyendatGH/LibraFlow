package structures;

import Node_model.BSTreeNode;
import model.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookTree {
    BSTreeNode root;

    public BookTree() {
        root = null;
    }

    public BSTreeNode getRoot() {
        return root;
    }

    public void loadFromFile() {
        String filePath = "src/resources/BookData.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    String id = data[0];
                    String title = data[1];
                    int quantity = Integer.parseInt(data[2]);
                    int lended = Integer.parseInt(data[3]);
                    double price = Double.parseDouble(data[4]);
                    Book bnew = new Book(id, title, quantity, lended, price);
                    insert(bnew);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    //    -------------------------------------------------------
    public void insert(Book book) {
        root = insertRecursive(root, book);
    }

    public BSTreeNode insertRecursive(BSTreeNode root, Book book) {
        if (root == null) {
            root = new BSTreeNode(book);
            return root;
        }

        if (book.getBcode().compareTo(root.bookData.getBcode()) < 0) {
            root.left = insertRecursive(root.left, book);
        } else if (book.getBcode().compareTo(root.bookData.getBcode()) > 0) {
            root.right = insertRecursive(root.right, book);
        }
        return root;
    }

    //    -------------------------------------------------------
    public ArrayList<Book> inOrderTraverse() {
        ArrayList<Book> bookList = new ArrayList<>();
        inOrderRecursive(root, bookList);
        return bookList;
    }

    private void inOrderRecursive(BSTreeNode root, List<Book> bookList) {
        if (root == null) {
            return;
        }

        inOrderRecursive(root.left, bookList);
        bookList.add(root.bookData);
        inOrderRecursive(root.right, bookList);
    }


    //-------------------------------------------------------
    public ArrayList<Book> breadthFirstTraverse() {
        ArrayList<Book> bookList = new ArrayList<>();
        if (root == null) {
            return bookList;
        }

        List<BSTreeNode> list = new ArrayList<>();
        list.add(root);

        while (!list.isEmpty()) {
            BSTreeNode currentNode = list.remove(0);
            bookList.add(currentNode.bookData);
            if (currentNode.left != null) {
                list.add(currentNode.left);
            }
            if (currentNode.right != null) {
                list.add(currentNode.right);
            }
        }

        return bookList;
    }

    //-------------------------------------------------------
    public void inOrderTraverseToFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            inOrderTraverseRecursiveToFile(root, writer);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private void inOrderTraverseRecursiveToFile(BSTreeNode node, BufferedWriter writer) throws IOException {
        if (node == null) {
            return;
        }
        inOrderTraverseRecursiveToFile(node.left, writer);
        writer.write(node.bookData.toString());
        writer.newLine();
        inOrderTraverseRecursiveToFile(node.right, writer);
    }

    //-------------------------------------------------------
    public void balanceTree() {
        ArrayList<Book> nodes = new ArrayList<>();
        storeInOrder(root, nodes);
        root = buildBalancedTree(nodes, 0, nodes.size() - 1);
    }

    private void storeInOrder(BSTreeNode node, ArrayList<Book> nodes) {
        if (node == null) return;
        storeInOrder(node.left, nodes);
        nodes.add(node.bookData);
        storeInOrder(node.right, nodes);
    }

    private BSTreeNode buildBalancedTree(ArrayList<Book> nodes, int start, int end) {
        if (start > end) return null;

        int mid = (start + end) / 2;
        BSTreeNode node = new BSTreeNode(nodes.get(mid));
        node.left = buildBalancedTree(nodes, start, mid - 1);
        node.right = buildBalancedTree(nodes, mid + 1, end);
        return node;
    }

    //    -------------------------------------------------------
    public Book search(String bcode) {
        BSTreeNode res = searchRescursive(root, bcode);
        if (res != null) return res.bookData;
        return null;
    }

    public BSTreeNode searchRescursive(BSTreeNode root, String bcode) {
        if (root == null || root.bookData.getBcode().compareTo(bcode) == 0) {
            return root;
        }
        if (bcode.compareTo(root.bookData.getBcode()) < 0) {
            return searchRescursive(root.left, bcode);
        }
        return searchRescursive(root.right, bcode);
    }

    public boolean deleteBook(String bcode) {
        if (isBcodeExists(bcode)) {
            root = deleteRecursive(root, bcode);
            return true;
        }
        return false;
    }

    // -------------------------------------------------------
    public BSTreeNode deleteRecursive(BSTreeNode root, String bcode) {
        if (root == null) return root;

        if (bcode.compareTo(root.bookData.getBcode()) < 0) {
            root.left = deleteRecursive(root.left, bcode);
        } else if (bcode.compareTo(root.bookData.getBcode()) > 0) {
            root.right = deleteRecursive(root.right, bcode);
        } else {
            // Node found
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Node with two children: Get the inorder successor (smallest in the right subtree)
            root.bookData = minValue(root.right);
            // Delete the inorder successor
            root.right = deleteRecursive(root.right, root.bookData.getBcode());
        }
        return root;
    }


    //    -------------------------------------------------------
    public Book minValue(BSTreeNode root) {
        Book minval = root.bookData;
        while (root.left != null) {
            minval = root.left.bookData;
            root = root.left;
        }
        return minval;
    }

    public int countBooks() {
        return countRecursive(root);
    }

    private int countRecursive(BSTreeNode node) {
        if (node == null) return 0;
        return 1 + countRecursive(node.left) + countRecursive(node.right);
    }

    public boolean isBcodeExists(String bcode) {
        return search(bcode) != null;
    }

}
