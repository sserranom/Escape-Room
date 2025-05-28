package cat.itacademy.project.frontend.deco;

import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.frontend.shared.MenuScanner;
import cat.itacademy.project.shared.domain.exceptions.CustomException;

import java.util.Optional;

public class ManageDecoMenu extends MenuCommand<Void> {

    public ManageDecoMenu() {
    }

    @Override
    public Optional<Void> execute() {
        boolean isRunning = true;

        while (isRunning) {
            try {
                int choice = getUserImput();

                switch (choice) {
                    case 1:
                        log("Create decorative Item: ");
                        CreateDecoMenu createDecoMenu = new CreateDecoMenu();
                        createDecoMenu.execute();
                        break;

                    case 2:
                        log("Update decorative Item: ");
                        UpdateDecoMenu updateDecoMenu = new UpdateDecoMenu();
                        updateDecoMenu.execute();
                        break;

                    case 3:
                        log("Show all decorative Items: ");
                        FindAllDecoMenu findAllDecoMenu = new FindAllDecoMenu();
                        findAllDecoMenu.execute();
                        break;

                    case 4:
                        log("Back: ");
                        isRunning = false;
                        break;
                    default:
                        error("Invalid choice. Please try again.");

                }
            } catch (CustomException e) {
                error(e.getMessage());
            }
        }

        return Optional.empty();
    }

    private int getUserImput() {
        log("1. Create new Decorative Item ");
        log("2. Update Decorative Item ");
        log("3. Show all Decorative Items ");
        log("4. Back <-");

        return MenuScanner.readInt("Please enter your Choice: ");
    }
}
