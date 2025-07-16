package Assignment5;
// "Animal Shelter Adoption System"
// Assignment #5
// Data Structures and Algorithms
// Semester #4

public class Main {
    public static void main(String[] args) {
        AnimalShelter shelter = new AnimalShelter(10);

        shelter.loadDemoData();

        shelter.printShelterInventory();

        shelter.dequeueAny();
        shelter.printQueue();

        shelter.dequeueCat();
        shelter.printQueue();

        shelter.dequeueDog();
        shelter.printQueue();

        shelter.dequeueDog();
        shelter.printQueue();

        shelter.dequeueAny();
        shelter.printQueue();

        shelter.dequeueAny();
    }
}
