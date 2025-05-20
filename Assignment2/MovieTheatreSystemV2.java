package Assignment2;
// Additional version that demonstrates alternate (less brute force) traversal of 2d arrays

// For the new suggestions we show...
// -All seats in the same row requested
// -Some seats in the adjacent row than the one suggested

// "Movie Theatre Seat Booking System"
// Assignment #2
// Data Structures and Algorithms 
// Semester #4


import java.util.Scanner;

public class MovieTheatreSystemV2 {
    
    private static final int ROWS = 6;
    private static final int COLS = 9;
    private static boolean[][] seats = new boolean[ROWS][COLS];
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        int choice;
        
        do {
            showMenu();
            choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    reserveSeat();
                    break;
                case 2:
                    cancelReservation();
                    break;
                case 3:
                    showSeatingChart();
                    break;
                case 4:
                    System.out.println("Thank you for using the Movie Theater Seating System!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);
        
        scanner.close();
    }
    
    //  Displays the main menu options

    private static void showMenu() {
        System.out.println("\n===== Movie Theater Seating System =====");
        System.out.println("1. Reserve a Seat");
        System.out.println("2. Cancel a Reservation");
        System.out.println("3. View Seating Chart");
        System.out.println("4. Exit");
        System.out.print("Please enter your choice: ");
    }
    

    private static void showSeatingChart() {
        System.out.println("\n===== Seating Chart =====");
        System.out.println("\n=========================");
        System.out.println("    1 2 3 4 5 6 7 8 9");
        System.out.println("   -------------------");
        
        for (int i = 0; i < ROWS; i++) {
            System.out.print((char)('A' + i) + " | ");
            for (int j = 0; j < COLS; j++) {
                if (seats[i][j]) {
                    System.out.print("X ");
                } else {
                    System.out.print("O ");
                }
            }
            System.out.println();
        }
        
        System.out.println("\nO = Available, X = Reserved");
        System.out.println("\n=========================");

    }
    

    private static void reserveSeat() {
        showSeatingChart();
        
        System.out.print("\nEnter row (A-E): ");
        char rowChar = scanner.next().toUpperCase().charAt(0);
        int row = rowChar - 'A';
        
        System.out.print("Enter column (1-9): ");
        int col = scanner.nextInt() - 1;
        
        if (row < 0 || row >= ROWS || col < 0 || col >= COLS) {
            System.out.println("Error: invalid seat selection. Please try again.");
            return;
        }
        
        if (seats[row][col]) {
            System.out.println("\nSorry, that seat is reserved.");
            suggestAlternativeSeat(row, col);
        } else {
            seats[row][col] = true;
            System.out.println("\nSeat " + rowChar + (col + 1) + " has been reserved successfully!");
        }
    }
    
    
    // Version 2 of 2d array traversal below...

    //  Suggests an available seat by first, looking in the same row, then adjacent ones.
    private static void suggestAlternativeSeat(int row, int col) {
        System.out.println("\nHere are some available alternatives:");
        
        boolean foundAlternative = false;
        
        System.out.println("\nAll seats in the same row:");
        for (int j = 0; j < COLS; j++) {
            if (!seats[row][j] && j != col) {
                System.out.println("- Seat " + (char)('A' + row) + (j + 1));
                foundAlternative = true;
            }
        }
        
        System.out.println("\nSome seats in adjacent rows:");
        for (int r = Math.max(0, row - 1); r <= Math.min(ROWS - 1, row + 1); r++) {
            if (r == row) continue;
            
            for (int c = 0; c < COLS; c++) {
                if (!seats[r][c]) {
                    System.out.println("- Seat " + (char)('A' + r) + (c + 1));
                    foundAlternative = true;
                    break;
                }
            }
        }
        
        if (!foundAlternative) {
            System.out.println("No available seats found.");
        }
    }
    

    private static void cancelReservation() {
        showSeatingChart();
        
        System.out.print("\nEnter row of seat to cancel (A-E): ");
        char rowChar = scanner.next().toUpperCase().charAt(0);
        int row = rowChar - 'A';
        
        System.out.print("Enter column of seat to cancel (1-9): ");
        int col = scanner.nextInt() - 1;
        
        if (row < 0 || row >= ROWS || col < 0 || col >= COLS) {
            System.out.println("Invalid seat selection. Please try again.");
            return;
        }
        
        // Check if seat is reserved
        if (seats[row][col]) {
            // Cancel the reservation
            seats[row][col] = false;
            System.out.println("The current reservation for seat " + rowChar + (col + 1) + " has been cancelled!");
        } else {
            System.out.println("Cancellation failed! Seat " + rowChar + (col + 1) + " is not currently reserved.");
        }
    }
}