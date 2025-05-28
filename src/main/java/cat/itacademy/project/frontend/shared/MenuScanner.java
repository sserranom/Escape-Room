package cat.itacademy.project.frontend.shared;

import cat.itacademy.project.shared.domain.exceptions.EmptyFieldException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import static com.mysql.cj.util.TimeUtil.DATE_FORMATTER;

public class MenuScanner {
    private static final Scanner scanner = new Scanner(System.in);

    private MenuScanner() {
        // Prevent instantiation
    }

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
            scanner.nextLine();
        }
        return Integer.parseInt(scanner.nextLine());
    }

    public static double readDouble(String message) {
        System.out.print(message);
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid input. Please enter a number: ");
            scanner.next();
        }
        double value = scanner.nextDouble();
        scanner.nextLine();
        return value;
        //return Double.parseDouble(scanner.nextLine());
    }

    public static boolean readBoolean(String message) {
        System.out.print(message + " (yes/no, true/false, 1/0): ");
        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();
            switch (input) {
                case "true":
                case "yes":
                case "y":
                case "1":
                    return true;
                case "false":
                case "no":
                case "n":
                case "0":
                    return false;
                default:
                    System.out.print("Invalid input. Please enter yes/no, true/false, or 1/0: ");
            }
        }
    }

    public static LocalDate readDate(String message) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = null;
        String input;

        while (date == null) {
            System.out.print(message);
            input = scanner.nextLine().trim();

            try {
                date = LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format or value. Please enter the date in 'dd/MM/yyyy' format (e.g., 25/12/2024).");
            }
        }
        return date;
    }

    public static LocalDate readOptionalDate(String message) {
        LocalDate date = null;
        String input;
        boolean validInput = false;

        while (!validInput) {
            System.out.print(message);
            input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                return null;
            }

            try {
                date = LocalDate.parse(input, DATE_FORMATTER); // Usar el formateador global
                validInput = true; // Si el parseo es exitoso, la entrada es válida
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format or value. Please enter the date in 'dd/MM/yyyy' format (e.g., 25/12/2024) or leave empty.");
            }
        }
        return date;
    }

    public static void close() {
        scanner.close();
    }
}
