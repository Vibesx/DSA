package org.example;

import java.util.ArrayList;

public class HashTable {
    private final int size = 7;
    private final Node[] dataMap;

    public HashTable() {
        dataMap = new Node[size];
    }

    public class Node {
        private String key;
        private int value;
        private Node next;

        public Node(String key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public Node[] getDataMap() {
        return dataMap;
    }

    public void printTable() {
        for (int i = 0; i < dataMap.length; i++) {
            System.out.println(i + ":");
            if (dataMap[i] != null) {
                Node temp = dataMap[i];
                while (temp != null) {
                    System.out.println("   {" + temp.key + ", " + temp.value + "}");
                    temp = temp.next;
                }
            }
        }
    }

    public void set(String key, int value) {
        Node newNode = new Node(key, value);
        int hash = hash(key);
        Node temp = dataMap[hash];
        if (temp == null) {
            dataMap[hash] = newNode;
        } else {
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    public int get(String key) {
        int hash = hash(key);
        Node result = dataMap[hash];
        while (result != null) {
            if (result.key.equals(key)) {
                return result.value;
            }
            result = result.next;
        }
        return -1;
    }

    public ArrayList<String> keys() {
        ArrayList<String> allKeys = new ArrayList<>();
        for (Node node : dataMap) {
            Node runner = node;
            while(runner != null) {
                allKeys.add(runner.key);
                runner = runner.next;
            }
        }
        return allKeys;
    }

    private int hash(String key) {
        int hash = 0;
        char[] keyChars = key.toCharArray();
        for (int asciiValue : keyChars) {
            // we multiply the ascii value by a prime number as this is more likely to generate a random number
            hash = (hash + asciiValue * 23) % dataMap.length;
        }
        return hash;
    }
}
