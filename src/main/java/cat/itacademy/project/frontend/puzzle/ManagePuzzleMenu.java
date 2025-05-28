package cat.itacademy.project.frontend.puzzle;

import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.frontend.shared.MenuScanner;

import java.util.Optional;

public class ManagePuzzleMenu extends MenuCommand<Void> {
    @Override
    public Optional<Void> execute() {


        boolean isRunning = true;
        while (isRunning) {
            int choice = getUserInput();

            switch (choice) {
                case 1:
                    log("Create new Puzzle: ");
                    CreatePuzzleMenu createPuzzleMenu = new CreatePuzzleMenu();
                    createPuzzleMenu.execute();
                    break;

                case 2:
                    log("Update Puzzle details: ");
                    UpdatePuzzleMenu updatePuzzleMenu = new UpdatePuzzleMenu();
                    updatePuzzleMenu.execute();
                    break;

                case 3:
                    log("View Puzzle details: ");
                    FindAllPuzzlesMenu findAllPuzzlesMenu = new FindAllPuzzlesMenu();
                    findAllPuzzlesMenu.execute();
                    break;

                case 4:
                    log("Back: ");
                    isRunning = false;
                    break;

                default:
                    error("Invalid choice. Please try again.");
            }
        }

        return Optional.empty();
    }

    private int getUserInput() {
        log("1. Create new Puzzle");
        log("2. Update Puzzle details");
        log("3. View All Puzzles");
        log("4. Back <-");

        return MenuScanner.readInt("Please enter your choice: ");
    }
}
