package Assignment5;

public class AnimalShelter {
    private Animal[] queue;
    private int front;
    private int rear;
    private int size;
    private int capacity;
    private int orderCounter;

    public AnimalShelter(int capacity) {
        this.capacity = capacity;
        this.queue = new Animal[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
        this.orderCounter = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public void enqueue(String name, String type) {
        if (isFull()) {
            System.out.println("\nShelter is full, can't add more animals.");
            return;
        }
        rear = (rear + 1) % capacity;
        queue[rear] = new Animal(name, type, orderCounter++);
        size++;
        System.out.println("\nAdded: " + queue[rear]);
    }

    public Animal dequeueAny() {
        if (isEmpty()) {
            System.out.println("\nNo animals to adopt.");
            return null;
        }
        Animal adopted = queue[front];
        front = (front + 1) % capacity;
        size--;
        System.out.println("\nAdopted: " + adopted);
        return adopted;
    }

    public Animal dequeueDog() {
        return dequeueType("dog");
    }

    public Animal dequeueCat() {
        return dequeueType("cat");
    }

    private Animal dequeueType(String type) {
        if (isEmpty()) {
            System.out.println("\nNo animals to adopt.");
            return null;
        }

        // Linear search to find the first of that type
        int index = front;
        for (int count = 0; count < size; count++) {
            if (queue[index].type.equals(type)) {
                Animal adopted = queue[index];
                // Shift all elements after index backward
                for (int i = index; i != rear; i = (i + 1) % capacity) {
                    int next = (i + 1) % capacity;
                    queue[i] = queue[next];
                }
                rear = (rear - 1 + capacity) % capacity;
                size--;
                System.out.println("\nAdopted: " + adopted);
                return adopted;
            }
            index = (index + 1) % capacity;
        }
        System.out.println("\nNo " + type + "s available for adoption.");
        return null;
    }

    public Animal peekFront() {
        if (isEmpty()) {
            System.out.println("\nShelter is empty.");
            return null;
        }
        System.out.println("\nNext animal for adoption: " + queue[front]);
        return queue[front];
    }

    public Animal peekType(String type) {
        if (isEmpty()) {
            System.out.println("\nShelter is empty.");
            return null;
        }
        int index = front;
        for (int count = 0; count < size; count++) {
            if (queue[index].type.equals(type)) {
                System.out.println("\nNext " + type + " for adoption: " + queue[index]);
                return queue[index];
            }
            index = (index + 1) % capacity;
        }
        System.out.println("\nNo " + type + "s in shelter.");
        return null;
    }

    public void printQueue() {
        if (isEmpty()) {
            System.out.println("Shelter is empty.");
        } else {
            System.out.print("Queue: ");
            int index = front;
            for (int count = 0; count < size; count++) {
                System.out.print(queue[index].name + " the " + queue[index].type);
                if (count != size - 1) {
                    System.out.print(" -> ");
                }
                index = (index + 1) % capacity;
            }
            System.out.println();
        }
    }

    public void printShelterInventory() {
        System.out.println("\n           Animal Shelter");
        System.out.println("-------------------------------------------");
        System.out.printf("%-20s| %-20s\n", "Dogs:", "Cats:");

        StringBuilder dogsLine = new StringBuilder();
        StringBuilder catsLine = new StringBuilder();

        if (isEmpty()) {
            System.out.printf("%-20s| %-20s\n", "None", "None");

        } else {
            int index = front;
            int overallCount = 0;
            int dogPos = 0;
            int catPos = 0;

            for (int count = 0; count < size; count++) {
                Animal a = queue[index];
                if (a.type.equals("dog")) {
                    dogsLine.append(a.name)
                            .append(" (")
                            .append(overallCount)
                            .append(" | ")
                            .append(dogPos++)
                            .append(")\n");
                } else if (a.type.equals("cat")) {
                    catsLine.append(a.name)
                            .append(" (")
                            .append(overallCount)
                            .append(" | ")
                            .append(catPos++)
                            .append(")\n");
                }
                index = (index + 1) % capacity;
                overallCount++;
            }

            String[] dogLines = dogsLine.toString().split("\n");
            String[] catLines = catsLine.toString().split("\n");
            int maxLines = Math.max(dogLines.length, catLines.length);

            for (int i = 0; i < maxLines; i++) {
                String dogEntry = i < dogLines.length ? dogLines[i] : "";
                String catEntry = i < catLines.length ? catLines[i] : "";
                System.out.printf("%-20s| %-20s\n", dogEntry, catEntry);
            }
        }
        System.out.println("\nLegend: (X | Y) \nX = order among all pets,\nY = order among pet type \n(0 means next to be adopted)");
        System.out.println("-------------------------------------------");

    }

    public void loadDemoData() {
        enqueue("Jalen", "dog");
        enqueue("Alpy", "cat");
        enqueue("Hakeem", "dog");
        enqueue("Clyde", "cat");
        enqueue("Charles", "dog");
        System.out.println("\nDemo data loaded into the shelter.");
    }


}
