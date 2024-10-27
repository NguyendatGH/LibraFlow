package structures;

import Node_model.BSTreeNode;
import Node_model.ReaderNode;
import model.Reader;

import java.io.*;

public class ReaderLinkedList {

    ReaderNode head;

    public ReaderLinkedList() {
        head = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void clearReaderList() {
        head = null;
    }

    public void loadFromFile() throws IOException {
        String filePath = "src/resources/ReaderData.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    String rCode = data[0];
                    String name = data[1];
                    int year = Integer.parseInt(data[2]);
                    addReader(new Reader(rCode, name, year));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveToFile(String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            ReaderNode current = head;
            while (current != null) {
                Reader reader = current.readerInfor;
                bw.write(reader.getRcode() + "," + reader.getName() + "," + reader.getByear());
                bw.newLine();
                current = current.next;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public ReaderNode getHead() {
        return head;
    }

    public void setHead(ReaderNode head) {
        this.head = head;
    }

    public boolean isRCodeExists(String rCode) {
        ReaderNode current = head;
        while (current != null) {
            if (current.readerInfor.getRcode().equals(rCode)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }


    public void addReader(Reader reader_infor) {
        ReaderNode newNode = new ReaderNode(reader_infor, null);
        if (isEmpty()) {
            head = newNode;
        } else {
            ReaderNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    public ReaderNode searchByRCode(String rCode) {
        ReaderNode current = head;
        while (current != null) {
            if (current.readerInfor.getRcode().equals(rCode)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public boolean deleteByRCode(String rCode) {
        if (isEmpty()) {
            return false;
        }

        ReaderNode current = head;

        if (current.readerInfor.getRcode().equals(rCode)) {
            head = head.next;
            return true;
        }

        while (current.next != null) {
            if (current.next.readerInfor.getRcode().equals(rCode)) {
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }

        return false;
    }

}
