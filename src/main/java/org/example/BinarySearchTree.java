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
            if (temp.value == value) {
                return true;
            }
            if (temp.left == null && temp.right == null) {
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

    public boolean rContains(int value) {
        return rContains(this.root, value);
    }

    public void rInsert(int value) {
        if (this.root == null) root = new Node(value);
        rInsert(this.root, value);
    }

    public void rDelete(int value) {
        deleteNode(this.root, value);
    }

    private boolean rContains(Node currentNode, int value) {
        if (currentNode == null) return false;
        if (currentNode.value == value) return true;
        if (currentNode.value < value) {
            return rContains(currentNode.right, value);
        } else {
            return rContains(currentNode.left, value);
        }
    }

    private Node rInsert(Node currentNode, int value) {
        if (currentNode == null) return new Node(value);
        if (value < currentNode.value) {
            currentNode.left = rInsert(currentNode.left, value);
        } else if (value > currentNode.value) {
            currentNode.right = rInsert(currentNode.right, value);
        }
        return currentNode;
    }

    private Node deleteNode(Node currentNode, int value) {
        if (currentNode == null) return null;
        if (value < currentNode.value) {
            currentNode.left = deleteNode(currentNode.left, value);
        } else if (value > currentNode.value) {
            currentNode.right = deleteNode(currentNode.right, value);
        } else {
            if (currentNode.left == null && currentNode.right == null) {
                return null;
            } else if (currentNode.left == null) {
                currentNode = currentNode.right;
            } else if (currentNode.right == null) {
                currentNode = currentNode.left;
            } else {
                int subStreemin = minValue(currentNode.right);
                currentNode.value = subStreemin;
                currentNode.right = deleteNode(currentNode.right, subStreemin);
            }
        }
        return currentNode;
    }

    private int minValue (Node currentNode) {
        while (currentNode.left != null) {
            currentNode = currentNode.left;
        }
        return currentNode.value;
    }

    public void sortedArrayToBST(int[] nums) {
        this.root = sortedArrayToBST(nums, 0, nums.length - 1);
    }

    private Node sortedArrayToBST(int[] nums, int left, int right) {
        if(left > right) return null;
        int mid = left + (right - left) / 2;
        Node currentNode = new Node(nums[mid]);
        currentNode.left = sortedArrayToBST(nums, left, mid - 1);
        currentNode.right = sortedArrayToBST(nums, mid + 1, right);

        return currentNode;
    }

    public void invert() {
        root = invertTree(root);
    }

    private Node invertTree(Node node) {
        if(node == null) return null;
        Node temp = invertTree(node.left);
        node.left = invertTree(node.right);
        node.right = temp;

        return node;
    }
}
