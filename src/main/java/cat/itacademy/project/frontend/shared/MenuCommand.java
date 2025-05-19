package cat.itacademy.project.frontend.shared;

import cat.itacademy.project.shared.domain.Command;

public abstract class MenuCommand<T> implements Command<T> {
    private static final String RESET_COLOR = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String CYAN = "\u001B[33m";
    private static final String GREEN = "\u001B[32m";


    protected void log(String text) {
        System.out.printf("%s%s%s%n", GREEN, text, RESET_COLOR);
    }

    protected void info(String text) {
        System.out.printf("%s%s%s%n", CYAN, text, RESET_COLOR);
    }

    protected void error(String text) {
        System.out.printf("%s%s%s%n", RED, text, RESET_COLOR);
    }
}
