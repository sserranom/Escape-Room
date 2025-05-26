package cat.itacademy.project.frontend.Room;

import cat.itacademy.project.api.room.UpdateRoomController;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.frontend.shared.MenuScanner;
import cat.itacademy.project.shared.domain.dtos.room.UpdateRoomDTO;

import java.util.Optional;

public class UpdateRoomMenu extends MenuCommand<Void> {
    @Override
    public Optional<Void> execute() {
        try {
            UpdateRoomDTO dto = getInfo();
            UpdateRoomController controller = new UpdateRoomController();
            controller.execute(dto);
            info("Room with name '" + dto.nameToUpdate() + "' updated successfully.");

        } catch (IllegalArgumentException e) {
            error("Error: " + e.getMessage());
        } catch (Exception e) {
            error("An unexpected error occurred: " + e.getMessage());
        }
        return Optional.empty();
    }

    private UpdateRoomDTO getInfo() {
        String newDifficulty;
        String nameToUpdate = MenuScanner.readString("Enter the name of the room to update: ");
        String newName = MenuScanner.readString("Enter the new name: ");
        int level = MenuScanner.readInt("Please select difficulty: 1 - Easy, 2 - Medium, 3 - Hard");
        switch (level) {
            case 1 -> newDifficulty = "easy";
            case 2 -> newDifficulty = "medium";
            case 3 -> newDifficulty = "hard";
            default -> newDifficulty = null;

        }
        double newPrice = MenuScanner.readDouble("Enter the new Price: ");
        int newThemeId= MenuScanner.readInt("Enter the new ID of the theme: ");

        return new UpdateRoomDTO(nameToUpdate, newName.isEmpty() ? null : newName, newDifficulty, newPrice <= 0 ? null : newPrice, newThemeId <= 0 ? null : newThemeId);
    }
}
