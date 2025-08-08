package org.example;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class LinkedList {

    private Node head;
    private Node tail;
    private int length;

    class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    public LinkedList(int value) {
        Node newNode = new Node(value);
        head = newNode;
        tail = newNode;
        length = 1;
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
        while (temp != null) {
            System.out.println(temp.value);
            temp = temp.next;
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
        System.out.println("\nLinked List:");
        if (length == 0) {
            System.out.println("empty");
        } else {
            printList();
        }
    }

    public void makeEmpty() {
        head = null;
        tail = null;
        length = 0;
    }

    public void append(int value) {
        Node newNode = new Node(value);
        if (length == 0) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        length++;
    }

    public Node removeLast() {
        if (length == 0) return null;
        Node temp = head;
        Node pre = head;
        while (temp.next != null) {
            pre = temp;
            temp = temp.next;
        }
        tail = pre;
        tail.next = null;
        length--;
        if (length == 0) {
            head = null;
            tail = null;
        }
        return temp;
    }

    public void prepend(int value) {
        Node newNode = new Node(value);
        if (length == 0) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        length++;
    }

    public Node removeFirst() {
        if (length == 0) return null;
        Node temp = head;
        head = head.next;
        temp.next = null;
        length--;
        if (length == 0) {
            tail = null;
        }
        return temp;
    }

    public Node get(int index) {
        if (index < 0 || index >= length) return null;
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    public boolean set(int index, int value) {
        Node temp = get(index);
        if (temp != null) {
            temp.value = value;
            return true;
        }
        return false;
    }

    public boolean insert(int index, int value) {
        if (index < 0 || index > length) return false;
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
        newNode.next = temp.next;
        temp.next = newNode;
        length++;
        return true;
    }

    public Node remove(int index) {
        if (index < 0 || index >= length) return null;
        if (index == 0) return removeFirst();
        if (index == length - 1) return removeLast();

        Node prev = get(index - 1);
        Node temp = prev.next;

        prev.next = temp.next;
        temp.next = null;
        length--;
        return temp;
    }

    public void reverse() {
        if (length < 2) {
            return;
        }
        if (length == 2) {
            Node temp = this.tail;
            this.tail = this.head;
            this.head = temp;
            this.tail.next = null;
            this.head.next = this.tail;
            return;
        }
        this.tail = this.head;
        Node first = this.head.next;
        Node second = this.head.next.next;
        this.head.next = null;
        for (int i = 0; i < this.length - 1; i++) {
            first.next = this.head;
            this.head = first;
            first = second;
            if (second != null) {
                second = second.next;
            }
        }
    }

    public Node findMiddleNode() {
        if (this.head == null) {
            return null;
        }
        if (this.head.next == null) {
            return this.head;
        }
        Node turt = this.head;
        Node hare = this.head;
        while (turt.next != null) {
            if (hare.next == null) {
                return turt;
            } else if (hare.next.next == null) {
                return turt.next;
            } else {
                turt = turt.next;
                hare = hare.next.next;
            }
        }
        return turt;
    }

    public boolean hasLoop() {
        if (length == 0) {
            return false;
        }
        Node slow = this.head;
        Node fast = this.head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

    public Node findKthFromEnd(int k) {
//        Node slow = this.head;
//        Node fast = this.head;
//        int length = Integer.MAX_VALUE;
//        int index = 0;
//        while(index < length - k) {
//            if(length == Integer.MAX_VALUE) {
//                if(fast.next == null) {
//                    length = index * 2 + 1;
//                } else if(fast.next.next == null) {
//                    length = index * 2 + 2;
//                } else {
//                    fast = fast.next.next;
//                }
//            }
//            if (k > length) return null;
//            slow = slow.next;
//            index++;
//        }
//        return slow;
        if (this.head == null || this.tail == null || this.head == this.tail) return null;
        Node counter = this.head;
        Node scout = this.head;
        int length = 1;
        while (counter.next != null) {
            length++;
            counter = counter.next;
        }
        if (k > length) return null;
        for (int i = 0; i < length - k; i++) {
            scout = scout.next;
        }
        return scout;
    }

    public void removeDuplicates() {
        Set<Integer> tempSet = new HashSet<>();
        Node temp = this.head;
        tempSet.add(temp.value);
        while (temp.next != null) {
            if (tempSet.contains(temp.next.value)) {
                Node interiorTemp = temp.next.next;
                temp.next.next = null;
                temp.next = interiorTemp;
            } else {
                tempSet.add(temp.next.value);
                temp = temp.next;
            }
        }
    }

    public int binaryToDecimal() {
//        Node runner = this.head;
//        int exp = this.length - 1;
//        int result = 0;
//        while(runner != null) {
//            result += (int) (runner.value*Math.pow(2, exp--));
//            runner = runner.next;
//        }
//        return result;
        Node runner = this.head;
        int result = 0;
        while (runner != null) {
            result = result * 2 + runner.value;
            runner = runner.next;
        }
        return result;
    }

    public void partitionList(int x) {
        Node smallCurrent = null;
        Node bigCurrent = null;
        Node runner = this.head;
        Node firstBigger = null;
        while (runner != null) {
            if (runner.value < x) {
                if (smallCurrent != null) {
                    smallCurrent.next = runner;
                } else {
                    this.head = runner;
                }
                smallCurrent = runner;
            } else {
                if (firstBigger == null) {
                    firstBigger = runner;
                } else {
                    bigCurrent.next = runner;
                }
                bigCurrent = runner;
            }
            runner = runner.next;
        }
        if (smallCurrent != null) {
            smallCurrent.next = firstBigger;
        } else {
            this.head = firstBigger;
        }
        if (bigCurrent != null) {
            bigCurrent.next = null;
        }
    }

    public void reverseBetween(int m, int n) {
        if (length < 2 || m == n) {
            return;
        }
        if (length == 2) {
            Node temp = this.head;
            this.head = this.head.next;
            temp.next = null;
            this.head.next = temp;
            return;
        }
        Node firstBeforeInterval = m == 0 ? null : this.head;
        Node firstAfterInterval = null;
        Node firstRunner = this.head;
        Node secondRunner = this.head.next;
        Node tempNode = null;
        // we move the runners and the firstBeforeInterval node to the starting position
        for (int i = 0; i < m; i++) {
            firstBeforeInterval = firstRunner;
            firstRunner = firstRunner.next;
            secondRunner = secondRunner.next;
        }
        // we get the firstAfterInterval node
        if (n < length) {
            for (int i = 0; i <= n; i++) {
                if (firstAfterInterval == null) {
                    firstAfterInterval = this.head;
                }
                firstAfterInterval = firstAfterInterval.next;
            }
        }
        tempNode = firstRunner.next.next;
        firstRunner.next = firstAfterInterval;
        secondRunner.next = firstRunner;
        for (int i = m + 1; i <= n; i++) {
            secondRunner.next = firstRunner;
            firstRunner = secondRunner;
            secondRunner = tempNode;
            if (tempNode.next != null) {
                tempNode = tempNode.next;
            }
        }
        if (firstBeforeInterval != null) {
            // point the first node before interval to last node of interval
            firstBeforeInterval.next = firstRunner;
        } else {
            this.head = firstRunner;
        }
    }

    public void swapPairs() {
        if (this.head == null || this.head.next == null) {
            return;
        }
        Node dummy = new Node(0);
        Node first = this.head;
        Node second = this.head.next;

        this.head = second;
        while (second != null) {
            first.next = second.next;
            second.next = first;
            dummy.next = second;

            dummy = first;
            first = first.next;
            second = first == null ? null : first.next;
        }
    }

    public void bubbleSort() {
        if (length < 2) return;
        Node sortedUntil = null;
        while (sortedUntil != this.head.next) {
            Node current = this.head;
            while (current.next != sortedUntil) {
                if (current.value > current.next.value) {
                    int temp = current.value;
                    current.value = current.next.value;
                    current.next.value = temp;
                }
                current = current.next;
            }
            sortedUntil = current;
        }
    }

    public void selectionSort() {
        if (length < 2) return;
        Node sortedFrom = this.head;
        while (sortedFrom.next != null) {
            Node minNode = sortedFrom;
            Node current = sortedFrom.next;
            while (current != null) {
                if (current.value < minNode.value) {
                    minNode = current;
                }
                current = current.next;
            }
            if (minNode != sortedFrom) {
                int temp = sortedFrom.value;
                sortedFrom.value = minNode.value;
                minNode.value = temp;
            }
            sortedFrom = sortedFrom.next;
        }
    }

    //TODO recap
    public void insertionSort() {
        if (length < 2) return;
        Node sortedListHead = this.head;
        Node unsortedListHead = this.head.next;
        sortedListHead.next = null;
        while (unsortedListHead != null) {
            Node current = unsortedListHead;
            unsortedListHead = unsortedListHead.next;

            if (current.value < sortedListHead.value) {
                current.next = sortedListHead;
                sortedListHead = current;
            } else {
                Node searchPointer = sortedListHead;
                while (searchPointer.next != null && current.value > searchPointer.next.value) {
                    searchPointer = searchPointer.next;
                }
                current.next = searchPointer.next;
                searchPointer.next = current;
            }
        }
        head = sortedListHead;
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        tail = temp;
    }

    //TODO recap
    public void merge(LinkedList linkedList) {
        Node dummy = new Node(0);
        Node current = dummy;
        Node otherHead = linkedList.head;
        while(head != null && otherHead != null) {
            if(head.value < otherHead.value) {
                current.next = head;
                head = head.next;
            } else {
                current.next = otherHead;
                otherHead = otherHead.next;
            }
            current = current.next;
        }
        if(head != null) {
            current.next = head;
        } else {
            current.next = otherHead;
            tail = linkedList.getTail();
        }
        head = dummy.next;
        length += linkedList.getLength();
    }
}

