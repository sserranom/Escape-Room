package cat.itacademy.project.frontend.puzzle;

import cat.itacademy.project.api.puzzle.UpdatePuzzleController;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.frontend.shared.MenuScanner;
import cat.itacademy.project.shared.domain.dtos.puzzle.UpdatePuzzleDTO;

import java.util.Optional;

public class UpdatePuzzleMenu extends MenuCommand<Void> {
    @Override
    public Optional<Void> execute() {
        try {
            UpdatePuzzleDTO dto = getInfo();
            UpdatePuzzleController controller = new UpdatePuzzleController();
            controller.execute(dto);
            info("Puzzle with name '" + dto.nameToUpdate() + "' updated successfully.");

        } catch (IllegalArgumentException e) {
            error("Error: " + e.getMessage());
        } catch (Exception e) {
            error("An unexpected error occurred: " + e.getMessage());
        }
        return Optional.empty();
    }

    private UpdatePuzzleDTO getInfo() {
        String nameToUpdate = MenuScanner.readString("Enter the name of the Puzzle to update: ");
        String newName = MenuScanner.readString("Enter the new name: ");
        int newRoomId = MenuScanner.readInt("Enter the new ID of the room: ");
        String newAnswer = MenuScanner.readString("Enter the new answer: ");
        String newStory = MenuScanner.readString("Enter the new story: ");
        int newThemeId = MenuScanner.readInt("Enter the new ID of the theme: ");
        double newPrice = MenuScanner.readDouble("Enter the new price: ");
        return new UpdatePuzzleDTO(
                nameToUpdate,
                newName.isEmpty() ? null : newName,
                newRoomId <= 0 ? null : newRoomId,
                newAnswer.isEmpty() ? null : newAnswer,
                newStory.isEmpty() ? null : newStory,
                newThemeId <= 0 ? null : newThemeId,
                newPrice <= 0 ? null : newPrice);
    }
}
