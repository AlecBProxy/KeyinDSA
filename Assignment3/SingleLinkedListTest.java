package Assignment3;

import java.util.LinkedList;

public class SingleLinkedListTest {
    public Node head;
    public Node tail;
    public int size;

    public Node createSingleLinkedlist(int nodeValue){
        Node node = new Node();
        node.next = null;
        node.value = nodeValue;
        head = node;
        tail = node;
        size = 1;
        return head;
    }
    LinkedList<String> lit = new LinkedList<>();

    //Insert Method
    public void insertLinkedList(int nodeValue, int location) {
        Node node = new Node();
        node.value = nodeValue;

        if (head == null){
            createSingleLinkedlist(nodeValue);
            return;
        } else if (location == 0) {
            node.next = head;
            head = node;
        } else if (location >= size){
            node.next = null;
            tail.next = node;
            tail = node;
        } else {
            Node tempNode = head;
            int index = 0;
            while (index < location - 1){
                tempNode = tempNode.next;
                index++;
            }
            Node nextNode = tempNode.next;
            tempNode.next = node;
            node.next = nextNode;
        }
        size++;
    }

    // Traversing through a linked list

    public void traverseLinkedList(){
        if (head == null) {
            System.out.println("SLL does not exist");
        } else {
            Node tempNode = head;
            for (int i = 0; i < size; i++) {
                System.out.print(tempNode.value);
                if (i != size -1) {
                    System.out.print("->");
                }
                tempNode = tempNode.next;
            }
        }
        System.out.println("\n");
    }

    //Search for an element
    public boolean searchNode(int nodeValue){
        if (head != null) {
            Node tempNode = head;
            for (int i = 0; i < size; i++){
                if (tempNode.value == nodeValue) {
                    System.out.print("Found the node at location: " +i+"\n");
                    return true;
                }
                tempNode = tempNode.next;
            }
        }
        System.out.print("Node not found");
        return false;
    }

    //implement deleting a node from single linked list

    public void delete(int position) {
        if (head == null) {
            System.out.println("Single linked list does not exist ");
            return;
        }

        //Case for invalid position
        if (position < 0 || position >= size) {
            System.out.println("Invalid position: " + position);
            return;
        }

        //Delete from the beginning of list
        if (position == 0) {
            head = head.next;
            size--;
            if (size ==0) {
                tail = null;
            }
            return;
        }

        //Delete at the end of list
        if (position == size - 1) {
            Node tempNode = head;
            for (int i = 0; i < size - 2; i++) {
                tempNode = tempNode.next;
            }
            tempNode.next = null;
            tail = tempNode;
            size--;
            return;
        }

        //Delete from the middle
        Node tempNode = head;
        for (int i = 0; i < position - 1; i++) {
            tempNode = tempNode.next;
        }

        tempNode.next = tempNode.next.next;
        size--;
    }

    public static void main(String[] args) {
        SingleLinkedListTest sll = new SingleLinkedListTest();
        sll.createSingleLinkedlist(10);
        sll.insertLinkedList(20, 1);
        sll.insertLinkedList(30, 2);
        sll.insertLinkedList(40, 3);
        sll.insertLinkedList(50, 4);

        SingleLinkedListTest emptyList = new SingleLinkedListTest();

        System.out.println("Original list:");
        sll.traverseLinkedList();

        System.out.println("Deleting node at position 0:");
        sll.delete(0);
        sll.traverseLinkedList();

        System.out.println("Deleting node at last position:");
        sll.delete(sll.size - 1);
        sll.traverseLinkedList();

        System.out.println("Deleting node at position 1:");
        sll.delete(1);
        sll.traverseLinkedList();

        System.out.println("Deleting node from invalid position:");
        sll.delete(12);

        System.out.println("\nTesting uninitialized list:");
        emptyList.traverseLinkedList();
    }


}