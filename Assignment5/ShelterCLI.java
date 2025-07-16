package Assignment5;
// "Animal Shelter Adoption System"
// Assignment #5
// Data Structures and Algorithms
// Semester #4

import java.util.Scanner;

public class ShelterCLI {
    public static void main(String[] args) {
        AnimalShelter shelter = new AnimalShelter(10);
        shelter.loadDemoData();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nWelcome to the Animal Shelter 🐾");
            System.out.println("1 - Add animal");
            System.out.println("2 - Adopt animal");
            System.out.println("3 - Show all animals");
            System.out.println("4 - Peek at next animal");
            System.out.println("5 - Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // clear newline

            switch (choice) {
                case 1:
                    System.out.print("Enter animal type (dog/cat): ");
                    String type = scanner.nextLine().trim().toLowerCase();
                    if (!type.equals("dog") && !type.equals("cat")) {
                        System.out.println("Only dogs and cats allowed.");
                        break;
                    }
                    System.out.print("Enter animal name: ");
                    String name = scanner.nextLine().trim();
                    shelter.enqueue(name, type);
                    break;
                case 2:
                    System.out.println("\nWhat would you like to adopt?");
                    System.out.println("1 - Dog");
                    System.out.println("2 - Cat");
                    System.out.println("3 - Choose for me");
                    System.out.print("Pick: ");
                    int adoptChoice = scanner.nextInt();
                    scanner.nextLine();
                    Animal adopted = null;
                    if (adoptChoice == 1) {
                        adopted = shelter.dequeueDog();
                    } else if (adoptChoice == 2) {
                        adopted = shelter.dequeueCat();
                    } else if (adoptChoice == 3) {
                        adopted = shelter.dequeueAny();
                    } else {
                        System.out.println("Invalid option.");
                    }
                    if (adopted != null) {
                        System.out.println(adopted.name + " the " + adopted.type + " is coming home with you! <3");
                    }
                    break;
                case 3:
                    shelter.printShelterInventory();
                    break;
                case 4:
                    System.out.println("\nPeek options:");
                    System.out.println("1 - Next animal overall");
                    System.out.println("2 - Next dog");
                    System.out.println("3 - Next cat");
                    System.out.print("Pick: ");
                    int peekChoice = scanner.nextInt();
                    scanner.nextLine(); // clear newline
                    if (peekChoice == 1) {
                        shelter.peekFront();
                    } else if (peekChoice == 2) {
                        shelter.peekType("dog");
                    } else if (peekChoice == 3) {
                        shelter.peekType("cat");
                    } else {
                        System.out.println("Invalid option.");
                    }
                    break;
                case 5:
                    System.out.println("Goodbye! 🐶🐱");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
