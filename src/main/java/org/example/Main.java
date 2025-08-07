package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree myBST = new BinarySearchTree();

        myBST.insert(47);
        myBST.insert(21);
        myBST.insert(76);
        myBST.insert(18);
        myBST.insert(27);
        myBST.insert(52);
        myBST.insert(82);

        System.out.println(myBST.BFS());
    }


    // HEAPS

    public static List<Integer> streamMax(int[] nums) {
        List<Integer> result = new ArrayList<>();
        Heap heap = new Heap();
        for (int i : nums) {
            heap.insert(i);
            result.add(heap.getHeap().get(0));
        }
        return result;
    }

    public static int findKthSmallest(int[] nums, int k) {
        Heap heap = new Heap();
        for (int i : nums) {
            heap.insert(i);
        }
        for (int i = 0; i < nums.length - k; i++) {
            heap.remove();
        }
        return heap.getHeap().get(0);

        // from course:
//        Heap maxHeap = new Heap();
//
//        for (int num : nums) {
//            maxHeap.insert(num);
//            if (maxHeap.getHeap().size() > k) {
//                maxHeap.remove();
//            }
//        }
//
//        return maxHeap.remove();
    }

    // STACKS

    private static String reverseString(String input) {
        Stack<String> stack = new Stack<>();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            stack.push(String.valueOf(input.charAt(i)));
        }
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }
        return result.toString();
    }

    public static boolean isBalancedParentheses(String input) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') {
                stack.push('(');
            } else if (stack.isEmpty() || stack.pop() != '(') {
                return false;
            }
        }
        return stack.isEmpty();
    }

    public static boolean isBalancedParenthesesAlternateMethod(String input) {
        Stack<String> stack = new Stack<>();
        int balance = 0;
        for (int i = 0; i < input.length(); i++) {
            stack.push(String.valueOf(input.charAt(i)));
        }
        while (!stack.isEmpty()) {
            if (balance < 0) {
                return false;
            }
            if (stack.pop().equals(")")) {
                balance++;
            } else {
                balance--;
            }
        }
        return balance == 0;
    }

    // HashTable / HashMap

    public static List<Integer> findDuplicates(int[] nums) {
        Map<Integer, Integer> occurances = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        for (int i : nums) {
            if (occurances.get(i) == null) {
                occurances.put(i, 1);
            } else {
                int temp = occurances.get(i);
                occurances.remove(i);
                occurances.put(i, temp + 1);
            }
        }
        for (int i : occurances.keySet()) {
            if (occurances.get(i) > 1) {
                result.add(i);
            }
        }
        return result;
    }

    public static boolean itemInCommon(int[] array1, int[] array2) {
        Map<Integer, Boolean> tempMap = new HashMap<>();
        for (int i : array1) {
            tempMap.put(i, true);
        }
        for (int i : array2) {
            if (tempMap.get(i) != null) {
                return true;
            }
        }
        return false;
    }

    private static List<List<String>> groupAnagrams(String[] input6) {
        List<List<String>> result = new ArrayList<>();
        List<Character> stringAsList;
        Map<String, Integer> mapping = new HashMap<>();
        Integer index = 0;

        for (String s : input6) {
            stringAsList = new ArrayList<>();
            for (int i = 0; i < s.length(); i++) {
                stringAsList.add(s.charAt(i));
            }
            String sortedString = stringAsList
                    .stream()
                    .sorted()
                    .map(String::valueOf)
                    .collect(Collectors.joining());
            if (mapping.get(sortedString) == null) {
                mapping.put(sortedString, index);
                result.add(new ArrayList<>(List.of(s)));
                index++;
            } else {
                result.get(mapping.get(sortedString)).add(s);
            }
        }
        return result;
    }

    private static String firstNonRepeatingChar(String input5) {
        Map<Character, Integer> characters = new HashMap<>();
        for (int i = 0; i < input5.length(); i++) {
            Character c = input5.charAt(i);
            characters.merge(c, 1, Integer::sum);
        }
        for (int i = 0; i < input5.length(); i++) {
            Character c = input5.charAt(i);
            if (characters.get(c) == 1) {
                return c.toString();
            }
        }
        return null;
    }

    public static int[] subarraySum(int[] nums5, int target5) {
        Map<Integer, Integer> sumIndex = new HashMap<>();
        sumIndex.put(0, -1);
        int currentSum = 0;

        for (int i = 0; i < nums5.length; i++) {
            currentSum += nums5[i];
            if (sumIndex.containsKey(currentSum - target5)) {
                return new int[]{sumIndex.get(currentSum - target5) + 1, i};
            } else {
                sumIndex.put(currentSum, i);
            }
        }
        return new int[0];
    }

    public static int[] twoSum(int[] nums2, int target2) {
        int[] result = new int[0];
        Map<Integer, Integer> numbersMapping = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            numbersMapping.put(nums2[i], i);
        }
        for (int i = 0; i < nums2.length; i++) {
            if (numbersMapping.get(target2 - nums2[i]) != null) {
                result = new int[2];
                result[0] = i;
                result[1] = numbersMapping.get(target2 - nums2[i]);
                break;
            }
        }
        return result;
    }

    // HASHSETS

    public static Integer longestConsecutiveSequence(int[] nums) {
        Set<Integer> numsAsSet = new HashSet<>();
        Set<Integer> visited = new HashSet<>();
        int maxLength = 0;

        for (int i : nums) {
            numsAsSet.add(i);
        }
        for (int i : nums) {
            if (!visited.contains(i)) {
                boolean hasUpperNeighbour = true;
                boolean hasLowerNeighbour = true;
                int up = i + 1;
                int down = i - 1;
                int currentLength = 1;
                while (hasUpperNeighbour) {
                    if (numsAsSet.contains(up)) {
                        visited.add(up);
                        up = up + 1;
                        currentLength++;
                    } else {
                        hasUpperNeighbour = false;
                    }
                }
                while (hasLowerNeighbour) {
                    if (numsAsSet.contains(down)) {
                        visited.add(down);
                        down = down - 1;
                        currentLength++;
                    } else {
                        hasLowerNeighbour = false;
                    }
                }
                if (currentLength > maxLength) {
                    maxLength = currentLength;
                }
            }
        }
        return maxLength;
    }

    public static List<int[]> findPairs(int[] arr1, int[] arr2, int target) {
        Set<Integer> complementarySet = new HashSet<>();
        List<int[]> result = new ArrayList<>();
        for (int i : arr2) {
            complementarySet.add(target - i);
        }
        for (int i : arr1) {
            if (complementarySet.contains(i)) {
                result.add(new int[]{i, target - i});
            }
        }
        return result;
    }

    public static Boolean hasUniqueChars(String input) {
        Set<Character> existingCharacters = new HashSet<>();
        for (int i = 0; i < input.length(); i++) {
            if (!existingCharacters.contains(input.charAt(i))) {
                existingCharacters.add(input.charAt(i));
            } else {
                return false;
            }
        }
        return true;
    }

    public static List<Integer> removeDuplicates(List<Integer> list) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> existingElements = new HashSet<>();

        for (Integer i : list) {
            if (!existingElements.contains(i)) {
                result.add(i);
                existingElements.add(i);
            }
        }
        return result;
    }

    // UTILS

    private static String formatResult(int[] result) {
        return result.length == 0 ? "[]" : Arrays.toString(result);
    }

    private static String formatResult(List<Integer> list) {
        List<Integer> sorted = new ArrayList<>(list);
        Collections.sort(sorted);  // for consistent print order
        return sorted.toString();
    }

    private static String formatPairs(List<int[]> pairs) {
        List<String> formatted = new ArrayList<>();
        for (int[] pair : pairs) {
            formatted.add(Arrays.toString(pair));
        }
        return formatted.toString();
    }

    private static void printNode(DoublyLinkedList.Node node) {
        System.out.println("Node value: " + node.value);
    }

    private static void printLength(DoublyLinkedList doublyLinkedList) {
        System.out.println("DoublylinkedList length: " + doublyLinkedList.getLength());
    }

    private static void printBoolean(boolean b) {
        System.out.println("Result: " + b);
    }


}

