package cat.itacademy.project.frontend.Room;

import cat.itacademy.project.api.room.AssignDecoToRoomController;
import cat.itacademy.project.frontend.Menu;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.frontend.shared.MenuScanner;
import cat.itacademy.project.shared.domain.dtos.room.AssignDecoToRoomDto;
import cat.itacademy.project.shared.domain.exceptions.CustomException;

import java.util.Optional;

public class AddDecoToRoomMenu extends MenuCommand<Void> {
    private final AssignDecoToRoomController assignDecoToRoomController = new AssignDecoToRoomController();


    @Override
    public Optional<Void> execute() {
        AssignDecoToRoomDto request;
        try {
            request = getUserInfo();
            assignDecoToRoomController.execute(request);
            info("Decoration added to room successfully.");
        } catch (CustomException e) {
            error("Error: " + e.getMessage());
        } catch (Exception e) {
            error("An unexpected error occurred: " + e.getMessage());
        }
        // This method should contain the logic to add a decoration to a room.
        // Since the original code does not provide any specific implementation,
        // we will leave it empty for now.

        return Optional.empty();
    }

    private AssignDecoToRoomDto getUserInfo() {
        Integer roomId = MenuScanner.readInt("Enter the ID of the room: ");
        Integer decorationId = MenuScanner.readInt("Enter the ID of the decoration: ");

        if (roomId == null || decorationId == null) {
            throw new CustomException("Room ID and Decoration ID cannot be null.");
        }

        return new AssignDecoToRoomDto(roomId, decorationId, Menu.activeRoom().id());
    }
}
