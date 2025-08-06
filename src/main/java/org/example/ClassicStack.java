package org.example;

public class ClassicStack {

    Node top;
    int height;

    class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public ClassicStack(int value) {
        this.top = new Node(value);
        this.height = 1;
    }

    public Node getTop() {
        return top;
    }

    public int getHeight() {
        return height;
    }

    public void printStack() {
        Node temp = top;
        while (temp != null) {
            System.out.println(temp.value);
            temp = temp.next;
        }
    }

    public void printAll() {
        if (height == 0) {
            System.out.println("Top: null");
        } else {
            System.out.println("Top: " + top.value);
        }
        System.out.println("Height:" + height);
        System.out.println("\nStack:");
        if (height == 0) {
            System.out.println("empty");
        } else {
            printStack();
        }
    }

    public void makeEmpty() {
        top = null;
        height = 0;
    }

    public void push(int value) {
        Node node = new Node(value);
        if (height != 0) {
            node.next = this.top;
        }
        this.top = node;
        height++;
    }

    public Node pop() {
        Node result;
        if(height == 0) {
            return null;
        } else if (height == 1) {
            result = this.top;
            this.top = null;
        } else {
            result = this.top;
            this.top = this.top.next;
        }
        height--;
        return result;
    }

}

