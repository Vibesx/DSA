package org.example;

import java.util.ArrayList;

public class Stack<T> {
    private ArrayList<T> stackList = new ArrayList<>();

    public ArrayList<T> getStackList() {
        return stackList;
    }

    public void printStack() {
        for (int i = stackList.size() - 1; i >= 0; i--) {
            System.out.println(stackList.get(i));
        }
    }

    public boolean isEmpty() {
        return stackList.size() == 0;
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        } else {
            return stackList.get(stackList.size() - 1);
        }
    }

    public int size() {
        return stackList.size();
    }

    public void push(T element) {
        this.stackList.add(element);
    }

    public T pop() {
        if (this.stackList.size() == 0) {
            return null;
        }
        T lastElement = this.stackList.get(this.stackList.size() - 1);
        this.stackList.remove(this.stackList.size() - 1);
        return lastElement;
    }

    public void sortStack() {
        Stack<Integer> additionalStack = new Stack<>();
        int temp;
        while(!stackList.isEmpty()) {
            temp = (Integer) this.pop();
            while(!additionalStack.isEmpty() && additionalStack.peek() > temp) {
                this.push((T) additionalStack.pop());
            }
            additionalStack.push(temp);
        }
        while(!additionalStack.isEmpty()) {
            this.push((T) additionalStack.pop());
        }
    }
}
