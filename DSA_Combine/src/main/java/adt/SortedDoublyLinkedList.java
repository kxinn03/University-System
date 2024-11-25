/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt;

import adt.SortedLinkedListInterface;

/**
 *
 * @author Wong Xin Yee, Ting Xin Yi, Lee Kar Xin
 */
public class SortedDoublyLinkedList<T extends Comparable<T>> implements SortedLinkedListInterface<T>{
     private Node firstNode;
    private Node lastNode;
    private int numberOfEntries;

    public SortedDoublyLinkedList() {
        firstNode = null;
        lastNode = null;
        numberOfEntries = 0;
    }

    @Override
    public boolean add(T newEntry) {
        Node newNode = new Node(newEntry);

        if (isEmpty()) {
            firstNode = newNode;
            lastNode = newNode;
        } else if (newEntry.compareTo(firstNode.data) <= 0) { //Add at first
            newNode.next = firstNode;
            firstNode.prev = newNode;
            firstNode = newNode;
        } else if (newEntry.compareTo(lastNode.data) >= 0) { // Add at last
            lastNode.next = newNode;
            newNode.prev = lastNode;
            lastNode = newNode;
        } else { // Add at middle
            Node currentNode = firstNode;
            while (currentNode != null && newEntry.compareTo(currentNode.data) > 0) {
                currentNode = currentNode.next;
            }
            Node nodeBefore = currentNode.prev;
            nodeBefore.next = newNode;
            newNode.prev = nodeBefore;
            newNode.next = currentNode;
            currentNode.prev = newNode;
        }
        numberOfEntries++;
        return true;
    }

    @Override
    public boolean remove(T anEntry) {
        Node currentNode = firstNode;
        while (currentNode != null && !currentNode.data.equals(anEntry)) {
            currentNode = currentNode.next;
        }
        if (currentNode != null) { // 如果有data
            if (currentNode == firstNode && currentNode == lastNode) { // 如果list里面只有一个node
                firstNode = null;
                lastNode = null;
            } else if (currentNode == firstNode) { // 如果要remove的是第一个node
                firstNode = currentNode.next;
                firstNode.prev = null;
            } else if (currentNode == lastNode) { // 如果要remove的是最后一个node
                lastNode = currentNode.prev;
                lastNode.next = null;
            } else { // 如果要remove的是中间的node
                currentNode.prev.next = currentNode.next;
                currentNode.next.prev = currentNode.prev;
            }
            numberOfEntries--;
            return true;
        }
        return false;
    }
    
    @Override
    public boolean removeAt(int positionToRemove) {
        if (positionToRemove < 1 || positionToRemove > numberOfEntries) {
            return false; 
        }else{
            Node currentNode = firstNode;
            for (int i = 1; i < positionToRemove; i++) {
                currentNode = currentNode.next;
            }
            // If the node to be removed is the first node
            if (currentNode.prev == null) {
                // Then the F = second node
                firstNode = currentNode.next;
            } else {
                // exp C at 3, then 2's next will be 3's next = 4 (= 2 link to 4)
                currentNode.prev.next = currentNode.next;
            }

            // If the node to be removed is the last node
            if (currentNode.next == null) {
                lastNode = currentNode.prev;
            } else {
                // exp C at 5, 6's pre will be 5's pre = (= 6 link to 4)
                currentNode.next.prev = currentNode.prev;
            }
            numberOfEntries--;
            return true;
        }
    }

    @Override
    public boolean contains(T anEntry) {  //去找有没有那个东西 类似search
        Node currentNode = firstNode;
        while (currentNode != null) {
            if (currentNode.data.equals(anEntry)) {
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    @Override
    public int getNumberOfEntries() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }
    
    public final void clear() {
        firstNode = null;
        lastNode = null; // Also clear lastNode
        numberOfEntries = 0;
    }


    @Override
    public T getEntry(int givenPosition) {
        if (givenPosition >= 1 && givenPosition <= numberOfEntries) {
            Node currentNode;
            if (givenPosition <= numberOfEntries / 2) { // 如果给的位置小过里面数量的一半 就从前面开始看
                currentNode = firstNode;
                for (int i = 1; i < givenPosition; i++) { //一直看到后面
                    currentNode = currentNode.next;
                }
            } else { // 从后面看去前面
                currentNode = lastNode;
                for (int i = numberOfEntries; i > givenPosition; i--) {
                    currentNode = currentNode.prev; //一直看去前面
                }
            }
            return currentNode.data;
        }

        return null;
    }
    
    public boolean amend(T existingEntry, T newEntry) {
        Node currentNode = firstNode;

        // Traverse the list to find the node containing the existing entry
        while (currentNode != null && currentNode.data.compareTo(existingEntry) != 0) {
            currentNode = currentNode.next;
        }

        // If the existing entry is found, update its value with the new entry
        if (currentNode != null) {
            currentNode.data = newEntry;
            return true; // Amendment successful
        }

        return false; // Existing entry not found
    }
    
    public T search(T anEntry) {
        T result = null;
        Node currentNode = firstNode;
        int middleIndex = numberOfEntries / 2;
        // Check if the list is empty
        if (currentNode == null) {
            return null;
        }
    
        while (currentNode != null) {
            // Check if anEntry whether same wif the currentNode aka firstNode anot
            if (anEntry.equals(currentNode.data)) {
                result = currentNode.data;
                break;
            }
            // If anEntry is less than or equal to the data in the last node
            if (anEntry.compareTo(lastNode.data) <= 0) {
                // Traverse the list in reverse
                Node last = lastNode;
                while (last != null && anEntry.compareTo(last.data) <= 0) {
                    if (anEntry.equals(last.data)) {
                        result = last.data;
                        break;
                    }
                    last = last.prev;
                }
                break; // Break the loop as edy searched the entire list in reverse
            }      
            currentNode = currentNode.next; 
        }    
        return result;
    }
    
    @Override
    public String toString() {
        String outputStr = "";
        Node currentNode = firstNode;
        while (currentNode != null) {
            outputStr += currentNode.data + "\n";
            currentNode = currentNode.next;
        }
        return outputStr;
    }

    private class Node {

        private T data;
        private Node next;
        private Node prev;

        private Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }

        private Node(T data, Node next, Node prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }
}
