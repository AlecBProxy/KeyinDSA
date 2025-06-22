package Assignment4;
// "Undo/Rego Manager"
// Assignment #4
// Data Structures and Algorithms
// Semester #4
import java.util.Scanner;

public class UndoRedoListTest {

    public static class UndoRedoList<T> {

        private class Node<T> {
            T data;
            Node<T> next;

            Node(T data) {
                this.data = data;
            }
        }

        private Node<T> head;
        private Node<T> current;

        public void addState(T data) {
            Node<T> newNode = new Node<>(data);

            if (head == null) {
                head = newNode;
                current = head;
            } else {
                current.next = null;
                current.next = newNode;
                current = newNode;
            }
        }

        public T undo() {
            if (current == head) {
                System.out.println("Cannot undo. Already at the earliest state.");
                return current.data;
            }

            Node<T> temp = head;
            while (temp.next != current) {
                temp = temp.next;
            }

            current = temp;
            return current.data;
        }

        public T redo() {
            if (current.next == null) {
                System.out.println("Cannot redo. Already at the latest state.");
                return current.data;
            }

            current = current.next;
            return current.data;
        }

        public T getCurrentState() {
            if (current == null) {
                System.out.println("No states in the list.");
                return null;
            }
            return current.data;
        }

        public void printHistory() {
            if (head == null) {
                System.out.println("[empty list]");
                return;
            }

            Node<T> temp = head;
            while (temp != null) {
                if (temp == current) {
                    System.out.print("[" + temp.data + "] <- (current) ");
                } else {
                    System.out.print(temp.data + " ");
                }
                temp = temp.next;
            }
            System.out.println();
        }

    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UndoRedoList<String> editor = new UndoRedoList<>();

        String input;
        System.out.println("\nUndo/Redo System");
        System.out.println("\nCommands: add <text>, undo, redo, current, history, quit");
        System.out.println("\nadd <text>: Add a state to the linked list.");
        System.out.println("Example: add Location B");
        System.out.println("undo: Revert to the previous state.");
        System.out.println("redo: Move forward to the next state (if available).");
        System.out.println("current: shows the current state.");
        System.out.println("history: shows the state history, and the current position.");


        while (true) {
            System.out.print("> ");
            input = scanner.nextLine();

            if (input.equalsIgnoreCase("quit")) {
                break;
            } else if (input.startsWith("add ")) {
                String state = input.substring(4);
                editor.addState(state);
                System.out.println("Added: " + state);
            } else if (input.equalsIgnoreCase("undo")) {
                String state = editor.undo();
                System.out.println("Undo to: " + state);
            } else if (input.equalsIgnoreCase("redo")) {
                String state = editor.redo();
                System.out.println("Redo to: " + state);
            } else if (input.equalsIgnoreCase("current")) {
                System.out.println("Current state: " + editor.getCurrentState());
            } else if (input.equalsIgnoreCase("history")) {
                editor.printHistory();
            }else {
                System.out.println("Unknown command.");
            }
        }

        scanner.close();
        System.out.println("Goodbye!");
    }
}
