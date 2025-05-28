package cat.itacademy.project.frontend.theme;

import cat.itacademy.project.frontend.Room.ManageRoomMenu;
import cat.itacademy.project.frontend.deco.ManageDecoMenu;
import cat.itacademy.project.frontend.puzzle.ManagePuzzleMenu;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.frontend.shared.MenuScanner;
import cat.itacademy.project.shared.domain.exceptions.CustomException;

import java.util.Optional;

public class ManageThemeMenu extends MenuCommand<Void> {


    @Override
    public Optional<Void> execute() {
        boolean isRunning = true;
        while (isRunning) {
            int choice = getUserInput();
            try {
                switch (choice) {
                    case 1:
                        FindAllThemesMenu findAllThemesMenu = new FindAllThemesMenu();
                        findAllThemesMenu.execute();
                        // Here you would implement the logic to list all themes.
                        break;
                    case 2:
                        log("Updating an existing theme...");
                        UpdateThemeMenu updateThemeMenu = new UpdateThemeMenu();
                        updateThemeMenu.execute();
                        // Here you would implement the logic to update a theme.
                        break;
                    case 3:
                        log("Creating a new theme...");
                        CreateThemeMenu createThemeMenu = new CreateThemeMenu();
                        createThemeMenu.execute();
                        // Here you would implement the logic to create a new theme.
                        break;

                    case 4:
                        log("Managing rooms...");
                        ManageRoomMenu manageRoomMenu = new ManageRoomMenu();
                        manageRoomMenu.execute();
                        // Here you would implement the logic to manage rooms.
                        break;
                    case 5:
                        log("Managing decorative items...");
                        ManageDecoMenu manageDecoMenu = new ManageDecoMenu();
                        manageDecoMenu.execute();
                        // Here you would implement the logic to manage decorative items.
                        break;
                    case 6:
                        log("Managing puzzles...");
                        ManagePuzzleMenu managePuzzleMenu = new ManagePuzzleMenu();
                        managePuzzleMenu.execute();
                        // Here you would implement the logic to manage puzzles.
                        break;
                    case 0:
                        isRunning = false;
                        log("Exiting theme management.");
                        break;
                    default:
                        log("Invalid choice, please try again.");
                }
            } catch (CustomException e) {
                error("An error occurred: " + e.getMessage());
            }
        }

        return Optional.empty();
    }

    private int getUserInput() {
        log("1. List all themes ");
        log("2. Update an existing theme");
        log("3. Create a new theme");
        log("4. Manage Rooms");
        log("5. Manage Decorative Items");
        log("6. Manage puzzles");
        log("0. Exit");

        return MenuScanner.readInt("Please enter your choice: ");
    }
}
