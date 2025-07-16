package Assignment5;
// "Animal Shelter Adoption System"
// Assignment #5
// Data Structures and Algorithms
// Semester #4

public class Animal {
    String name;
    String type;
    int order;

    public Animal(String name, String type, int order) {
        this.name = name;
        this.type = type;
        this.order = order;
    }

    @Override
    public String toString() {
        return type + " named " + name + " (order " + order + ")";
    }
}
