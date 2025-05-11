// Assignment #1
// Data Structures and Algorithms 
// Semester #4

import java.util.Scanner; 

public class TemperatureExaminer {
    private int[] temperatures;
    private int numberOfTemperatures = 0;
    private double averageTemperature;
    private int numberOfTemperaturesOverAverage = 0;
    private Scanner scanner = new Scanner(System.in);

    public double findAverageTemperature() {
        double sum = 0;
        for (int i = 0; i < temperatures.length; i++) {
            sum += temperatures[i];
        }
        averageTemperature = sum / (double)temperatures.length;
        System.out.printf("The average temperature of the values you have entered is: %.2f\n", averageTemperature);
        return averageTemperature;
    }

    public void findTemperaturesAboveAverage() {
        for (int i = 0; i <temperatures.length; i++) {
            if (temperatures[i] > averageTemperature) {
                numberOfTemperaturesOverAverage++;
            }
        }
        System.out.printf("The number of temperatures that are above average from your set is: " + numberOfTemperaturesOverAverage);
    }

    public void processTemperatureInputs() {
        do {
            try {
                System.out.print("How many temperatures would you like to process?:  ");
                numberOfTemperatures = scanner.nextInt();
                if (numberOfTemperatures <= 0) {
                    System.out.println("Error: Invalid input. Please enter a number above 0.");
                }
                } catch (Exception e) {
                    System.out.println("Error: Invalid input. Please enter a number above 0.");
                    scanner.nextLine();
                }

            } while (numberOfTemperatures <= 0);

            temperatures = new int[numberOfTemperatures];

            int entryIndex = 0;
            while (entryIndex < numberOfTemperatures) {
                try {
                    System.out.print("Please enter the value of temperature #" + (entryIndex + 1) + ": ");
                    int temperature = scanner.nextInt();
                    temperatures[entryIndex] = temperature;
                    entryIndex++;
                } catch (Exception e) {
                    System.out.println("Error: Invalid input. Please enter a valid temperature.");
                    scanner.nextLine();
                }
            }

            findAverageTemperature();
            findTemperaturesAboveAverage();

            scanner.close();
        }

        public static void main(String[] args) {
            TemperatureExaminer temperatureExaminer = new TemperatureExaminer();
            temperatureExaminer.processTemperatureInputs();
        }
    }
