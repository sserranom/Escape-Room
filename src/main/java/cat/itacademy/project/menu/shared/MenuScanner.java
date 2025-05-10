package cat.itacademy.project.menu.shared;

import cat.itacademy.project.shared.domain.exceptions.EmptyFieldException;

import java.util.Scanner;

public class MenuScanner {
    private static final Scanner scanner = new Scanner(System.in);


    public static String readString(String message) {
        System.out.print(message);
        String input = scanner.nextLine();
        if (input.isEmpty()) {
            throw new EmptyFieldException("Input cannot be empty");
        }
        return input;
    }

    public static int readInt(String message) {
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter a number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
    public static double readDouble(String message) {
        System.out.print(message);
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid input. Please enter a number: ");
            scanner.next();
        }
        return scanner.nextDouble();
    }

    public static void close() {
        scanner.close();
    }
}
