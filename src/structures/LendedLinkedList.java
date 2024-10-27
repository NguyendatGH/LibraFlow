package structures;

import Node_model.LendedNode;
import Node_model.ReaderNode;
import model.Lended;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LendedLinkedList {

    LendedNode head;

    public LendedLinkedList() {
        head = null;
    }

    public LendedNode getHead() {
        return head;
    }

    public void loadFromFile() throws IOException {
        String filePath = "src/resources/LendedData.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    String bCode = data[0];
                    String rCode = data[1];
                    int state = Integer.parseInt(data[2]);
                    addLended(new Lended(bCode, rCode, state));
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int getSize() {
        int sizeOfList = 0;
        LendedNode tmp = head;
        while (tmp != null) {
            tmp = tmp.next;
            sizeOfList++;
        }
        return sizeOfList;
    }
    public boolean isCodeExists(String bCode, String rCode) {
        LendedNode current = head;
        while (current != null) {
            if (current.lended.getBcode().equals(bCode) && current.lended.getRcode().equals(rCode)) {
                return true;
            }
            current = current.next;
        }
        return false; // bCode và rCode không tồn tại
    }


    public void addLended(Lended lendedInfo) {
        if (isCodeExists(lendedInfo.getBcode(), lendedInfo.getRcode())) {
            System.out.println("Lended entry with this bCode and rCode already exists!");
            return;
        }

        if (isEmpty()) {
            head = new LendedNode(lendedInfo, null);
        } else {
            LendedNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new LendedNode(lendedInfo, null);
        }
    }

    public void sortLendedList(int option) {
        if (head == null || head.next == null) return;

        LendedNode sortedList = null;
        LendedNode current = head;

        while (current != null) {
            LendedNode next = current.next;
            if (option == 1) {
                sortedList = sortedInsertByBcode(sortedList, current);
            } else if (option == 2) {
                sortedList = sortedInsertByRcode(sortedList, current);
            }
            current = next;
        }
        head = sortedList;
    }

    private LendedNode sortedInsertByBcode(LendedNode sortedList, LendedNode newNode) {
        if (sortedList == null || sortedList.lended.getBcode().compareTo(newNode.lended.getBcode()) > 0) {
            newNode.next = sortedList;
            return newNode;
        } else {
            LendedNode current = sortedList;
            while (current.next != null && current.next.lended.getBcode().compareTo(newNode.lended.getBcode()) <= 0) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
        return sortedList;
    }

    private LendedNode sortedInsertByRcode(LendedNode sortedList, LendedNode newNode) {
        if (sortedList == null || sortedList.lended.getRcode().compareTo(newNode.lended.getRcode()) > 0) {
            newNode.next = sortedList;
            return newNode;
        } else {
            LendedNode current = sortedList;
            while (current.next != null && current.next.lended.getRcode().compareTo(newNode.lended.getRcode()) <= 0) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
        return sortedList;
    }


}
