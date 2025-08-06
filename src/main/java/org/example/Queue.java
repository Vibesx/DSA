package org.example;

public class Queue {

    Node first;
    Node last;
    int length;

    class Node {
        Node next;
        int value;

        public Node(int value) {
            this.value = value;
        }
    }

    public Queue(int value) {
        Node node = new Node(value);
        this.first = node;
        this.last = node;
        this.length = 1;
    }

    public Node getFirst() {
        return first;
    }

    public Node getLast() {
        return last;
    }

    public int getLength() {
        return length;
    }

    public void printList() {
        Node temp = first;
        while (temp != null) {
            System.out.println(temp.value);
            temp = temp.next;
        }
    }

    public void printAll() {
        if (length == 0) {
            System.out.println("First: null");
            System.out.println("Last: null");
        } else {
            System.out.println("First: " + first.value);
            System.out.println("Last: " + last.value);
        }
        System.out.println("Length:" + length);
        System.out.println("\nQueue:");
        if (length == 0) {
            System.out.println("empty");
        } else {
            printList();
        }
    }

    public void makeEmpty() {
        first = null;
        last = null;
        length = 0;
    }

    public void enqueue(int value) {
        Node node = new Node(value);
        if(length == 0) {
            this.last = node;
            this.first = node;
        } else {
            this.last.next = node;
            this.last = node;
        }
        length++;
    }

    public Node dequeue() {
        if(length == 0) {
            return null;
        }
        Node result = this.first;
        if(length == 1) {
            this.last = null;
            this.first = null;
        } else {
            this.first = this.first.next;
            result.next = null;
        }
        length--;
        return result;
    }

}