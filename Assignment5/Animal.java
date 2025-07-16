package Assignment5;

public class Animal {
    String name;
    String type; // "dog" or "cat"
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
