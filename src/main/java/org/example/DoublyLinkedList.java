package org.example;

public class DoublyLinkedList {

    private Node head;
    private Node tail;
    private int length;

    class Node {
        int value;
        Node next;
        Node prev;

        Node(int value) {
            this.value = value;
        }
    }

    public DoublyLinkedList(int value) {
        Node newNode = new Node(value);
        this.head = newNode;
        this.tail = newNode;
        this.length = 1;
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    public int getLength() {
        return length;
    }

    public void printList() {
        Node temp = head;
        Node tail = null;
        System.out.println("FORWARD PRINT: ");
        while (temp != null) {
            System.out.println(temp.value);
            if(temp.next == null) {
                tail = temp;
            }
            temp = temp.next;
        }
        System.out.println("BACKWARD PRINT: ");

        while (tail != null) {
            System.out.println(tail.value);
            tail = tail.prev;
        }
    }

    public void printAll() {
        if (length == 0) {
            System.out.println("Head: null");
            System.out.println("Tail: null");
        } else {
            System.out.println("Head: " + head.value);
            System.out.println("Tail: " + tail.value);
        }
        System.out.println("Length:" + length);
        System.out.println("\nDoubly Linked List:");
        if (length == 0) {
            System.out.println("empty");
        } else {
            printList();
        }
    }

    public void append(int value) {
        Node newNode = new Node(value);
        if (length == 0) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            this.tail.next = newNode;
            newNode.prev = this.tail;
            this.tail = newNode;
        }
        this.length++;
    }

    public Node removeLast() {
        if (length == 0) {
            return null;
        }
        Node temp = this.tail;
        if (length == 1) {
            this.head = null;
            this.tail = null;
        } else {
            this.tail = this.tail.prev;
            this.tail.next = null;
            temp.prev = null;
        }
        length--;
        return temp;
    }

    public void prepend(int value) {
        Node newNode = new Node(value);
        if (length == 0) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            this.head.prev = newNode;
            newNode.next = this.head;
            this.head = newNode;
        }
        this.length++;
    }

    public Node removeFirst() {
        if (length == 0) {
            return null;
        }
        Node temp = this.head;
        if (length == 1) {
            this.head = null;
            this.tail = null;
        } else {
            this.head = this.head.next;
            this.head.prev = null;
            temp.next = null;
        }
        length--;
        return temp;
    }

    public Node get(int index) {
        if (index < 0 || index >= length) {
            return null;
        }
        Node result = this.head;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result;
    }

    public boolean set(int index, int value) {
        if (index < 0 || index >= length) {
            return false;
        }
        Node result = this.get(index);
        if (result != null) {
            result.value = value;
            return true;
        }
        return false;
    }

    public boolean insert(int index, int value) {
        if (index < 0 || index > length) {
            return false;
        }
        if (index == 0) {
            prepend(value);
            return true;
        }
        if (index == length) {
            append(value);
            return true;
        }
        Node newNode = new Node(value);
        Node temp = get(index - 1);
        Node tempNext = temp.next;
        newNode.prev = temp;
        newNode.next = tempNext;
        temp.next.prev = newNode;
        temp.next = newNode;
        this.length++;
        return true;
    }

    public Node remove(int index) {
        if (index < 0 || index >= length) {
            return null;
        }
        if (index == 0) {
            return removeFirst();
        }
        if (index == length - 1) {
            return removeLast();
        }
        Node result = this.get(index);
        Node before = result.prev;
        Node after = result.next;
        if (result != null) {
            before.next = after;
            after.prev = before;
            result.next = null;
            result.prev = null;
        }
        length--;
        return result;
    }

    public boolean isPalindrome() {
        Node forward = this.head;
        Node backward = this.tail;
        while (forward != backward) {
            if (forward.value != backward.value) {
                return false;
            }
            forward = forward.next;
            backward = backward.prev;
        }
        return true;
    }

    public void reverse() {
        Node runner = this.head;
        Node temp;
        while (runner != null) {
            temp = runner.next;
            runner.next = runner.prev;
            runner.prev = temp;
            runner = temp;
        }
        temp = this.head;
        this.head = this.tail;
        this.tail = temp;
    }

    public void partitionList(int x) {
        if (this.head == null || this.head.next == null) return;
        Node firstHigher = null;
        Node firstLower = null;
        Node lowerSublist = null;
        Node highSublist = null;
        Node runner = this.head;
        while (runner != null) {
            Node next = runner.next;
            runner.next = null;
            runner.prev = null;
            if (runner.value >= x) {
                if (firstHigher == null) {
                    firstHigher = runner;
                    highSublist = runner;
                } else {
                    highSublist.next = runner;
                    highSublist.next.prev = highSublist;
                    highSublist = highSublist.next;
                }
            } else {
                if (lowerSublist == null) {
                    firstLower = runner;
                    lowerSublist = runner;
                } else {
                    lowerSublist.next = runner;
                    lowerSublist.next.prev = lowerSublist;
                    lowerSublist = lowerSublist.next;
                }
            }
            runner = next;
        }
        if (firstLower == null) {
            this.head = firstHigher;
        } else {
            this.head = firstLower;
            lowerSublist.next = firstHigher;
            if (firstHigher != null) {
                firstHigher.prev = lowerSublist;
            }
        }
        this.head.prev = null;
    }

    public void reverseBetween(int startIndex, int endIndex) {
        if (startIndex == endIndex) {
            return;
        }
        Node firstBefore = startIndex == 0 ? null : this.head;
        Node firstAfter = this.head;
        Node runner = this.head;
        Node temp;
        for (int i = 0; i < startIndex; i++) {
            if(i != 0) {
                firstBefore = firstBefore.next;
            }
            firstAfter = firstAfter.next;
            runner = runner.next;
        }
        for (int i = startIndex; i <= endIndex; i++) {
            firstAfter = firstAfter.next;
        }
        for (int i = startIndex; i < endIndex; i++) {
            temp = runner.next;
            if (i == startIndex) {
                runner.next = firstAfter;
                if (firstAfter != null) {
                    firstAfter.prev = runner;
                }
            } else {
                runner.next = runner.prev;
            }
            runner.prev = temp;
            runner = temp;
        }
        runner.next = runner.prev;
        if (startIndex == 0) {
            runner.prev = null;
            this.head = runner;
        } else {
            runner.prev = firstBefore;
            firstBefore.next = runner;
        }
    }

    public void swapPairs() {
        if (this.head == null || this.head.next == null) {
            return;
        }
        Node beforePair;
        Node afterPair;
        Node back = this.head;
        Node front = this.head.next;
        this.head = front;
        while(front != null) {
            beforePair = back.prev;
            afterPair = front.next;
            back.next = afterPair;
            back.prev = front;
            front.prev = beforePair;
            front.next = back;
            if(beforePair != null) {
                beforePair.next = front;
            }
            if(afterPair != null && afterPair.next != null) {
                afterPair.prev = back;
                back = afterPair;
                front = back.next;
            } else {
                if(afterPair != null) {
                    afterPair.prev = back;
                }
                return;
            }
        }
    }

}

