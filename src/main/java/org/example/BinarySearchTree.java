package org.example;

import java.util.HashMap;
import java.util.Map;

public class BinarySearchTree {
    Node root;

    class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public Node getRoot() {
        return root;
    }

    public boolean insert(int value) {
        Node newNode = new Node(value);
        if (root == null) {
            root = newNode;
            return true;
        }
        Node temp = root;
        while (true) {
            if (value == temp.value) {
                return false;
            }
            if (value > temp.value) {
                if (temp.right == null) {
                    temp.right = newNode;
                    return true;
                } else {
                    temp = root.right;
                }
            } else {
                if (temp.left == null) {
                    temp.left = newNode;
                    return true;
                } else {
                    temp = root.left;
                }
            }
        }
    }

    public boolean contains(int value) {
        if (root == null) {
            return false;
        }
        Node temp = root;
        while (temp != null) {
            if(temp.value == value) {
                return true;
            }
            if(temp.left == null && temp.right == null) {
                return false;
            }
            if (temp.value > value) {
                temp = temp.left;
            } else {
                temp = temp.right;
            }
        }
        return false;
    }
}
