package org.example;

public class Fib {
    private static Integer[] memo = new Integer[100];
    private static int counter = 0;
    public static int fib(int n) {
        if(memo[n] != null) {
            return memo[n];
        }
        counter++;
        if (n == 0 || n == 1) {
            return n;
        }
        memo[n] = fib(n - 1) + fib(n - 2);
        return memo[n];
    }

    public static int getCounter() {
        return counter;
    }
}
